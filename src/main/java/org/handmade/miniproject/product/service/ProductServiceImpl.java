package org.handmade.miniproject.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.common.dto.PageMaker;
import org.handmade.miniproject.common.service.UploadImageService;
import org.handmade.miniproject.member.repository.MemberInfoRepository;
import org.handmade.miniproject.member.service.MemberInfoService;
import org.handmade.miniproject.product.dto.product.ListProductDTO;
import org.handmade.miniproject.product.dto.product.ProductDTO;
import org.handmade.miniproject.product.dto.product.ProductListRequestDTO;
import org.handmade.miniproject.product.entity.Product;
import org.handmade.miniproject.product.repository.CategoryRepository;
import org.handmade.miniproject.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    //카테고리에 대한 정보를 받아오기 위해 사용
    private final CategoryRepository categoryRepository;

    private final UploadImageService uploadImageService;

    private final MemberInfoRepository memberInfoRepository;

    //상품 등록하기
    @Override
    public Long register(ProductDTO productDTO) {
        log.info(productDTO);

        //Entity 로 변경
        Product entity = dtoToEntity(productDTO,categoryRepository.findByCategory(productDTO.getCname()),memberInfoRepository.findById(productDTO.getUsername()).get());

        log.info("========================================================");
        log.info(entity);

        //상품 등록(저장)
        Product result = productRepository.save(entity);

        return result.getPno();
    }

    //상품 삭제
    @Override
    public Long delete(Long pno) {
        productRepository.deleteById(pno);
        return pno;
    }

    //상품 가져오기
    @Override
    public ProductDTO read(Long pno) {
        return entityToDTO(productRepository.findById(pno).get());
    }

    //실제 상품 수정 저장
    @Override
    public String modify(ProductDTO productDTO) {
        Optional<Product> result = productRepository.findById(productDTO.getPno());
        if(result.isPresent()){
            Product entity = result.get();
            //상품명 변경
            entity.changePname(productDTO.getPname());

            //상품가격 변경
            entity.changePrice(productDTO.getPrice());

            //상품 내용 변경
            entity.changePcontent(productDTO.getPcontent());

            //이전 카테고리
            Long preCno = result.get().getCategory().getCno();

            //현재 수정 카테고리
            Long afterCno = productDTO.getCno();

            //카테고리가 변경되었는지 확인
            if(preCno != afterCno){
                //변경된 경우 변경한 카테고리의 번호를 조회하여 반영
                entity.changeCategory(categoryRepository.findById(afterCno).get());
            }

            entity.changeuploadImages(productDTO.getImageList()
                    .stream()
                    .map(uploadImageDTO -> uploadImageService.dtoToEntity(uploadImageDTO))
                    .collect(Collectors.toSet()));

            //수정본 저장
            productRepository.save(entity);

            return productDTO.getPname();
        }

        return null;
    }

    //상품 통합검색 및 조건별 검색
    @Override
    public ListResponseDTO<ListProductDTO> getList(ProductListRequestDTO productListRequestDTO) {

        Pageable pageable =
                PageRequest.of( (productListRequestDTO.getPage()<=0 ? 0 : productListRequestDTO.getPage()) -1, 10);
        Page<Object[]> result =productRepository
                .getSearchList(productListRequestDTO.getType(), productListRequestDTO.getKeyword(),productListRequestDTO.getCname(), pageable);

        List<ListProductDTO> boardDToList =
                result.getContent().stream().map(arr -> arrToDTO(arr)).collect(Collectors.toList());

        PageMaker pageMaker = new PageMaker(1,10, (int) result.getTotalElements());
        pageMaker.makePageList(pageable);

        result.getContent().forEach(objects -> log.info(Arrays.toString(objects)));

        return ListResponseDTO.<ListProductDTO>builder()
                .dtoList(boardDToList)
                .pageMaker(pageMaker)
                .listRequestDTO(productListRequestDTO)
                .page(pageMaker.getPage())
                .start(pageMaker.getStart())
                .end(pageMaker.getEnd())
                .build();
    }



}
