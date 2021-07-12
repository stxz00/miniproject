package org.handmade.miniproject.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.common.dto.PageMaker;
import org.handmade.miniproject.product.dto.product.ListProductDTO;
import org.handmade.miniproject.product.dto.product.ProductDTO;
import org.handmade.miniproject.product.dto.product.ProductListRequestDTO;
import org.handmade.miniproject.product.entity.Category;
import org.handmade.miniproject.product.entity.Product;
import org.handmade.miniproject.product.repository.CategoryRepository;
import org.handmade.miniproject.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public Long register(ProductDTO productDTO) {
        log.info(productDTO);

        Product entity = dtoToEntity(productDTO,categoryRepository.findById(productDTO.getCno()).get());

        log.info("========================================================");
        log.info(entity);
        Product result = productRepository.save(entity);
        return result.getPno();
    }

    @Override
    public ListResponseDTO<ListProductDTO> getList(ProductListRequestDTO productListRequestDTO) {

        Pageable pageable = PageRequest.of(0,10);
        Page<Object[]> result =productRepository
                .getSearchList(productListRequestDTO.getType(), productListRequestDTO.getKeyword(), pageable);

        List<ListProductDTO> boardDToList =
                result.getContent().stream().map(arr -> arrToDTO(arr)).collect(Collectors.toList());

        PageMaker pageMaker = new PageMaker(1,10, (int) result.getTotalElements());

        result.getContent().forEach(objects -> log.info(Arrays.toString(objects)));

        return ListResponseDTO.<ListProductDTO>builder()
                .dtoList(boardDToList)
                .pageMaker(pageMaker)
                .listRequestDTO(productListRequestDTO)
                .build();
    }



}
