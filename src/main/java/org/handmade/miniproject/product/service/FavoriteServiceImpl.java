package org.handmade.miniproject.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.common.dto.ListRequestDTO;
import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.common.dto.PageMaker;
import org.handmade.miniproject.product.dto.Favorite.FavoriteDTO;
import org.handmade.miniproject.product.dto.Favorite.FavoriteListRequestDTO;
import org.handmade.miniproject.product.dto.Favorite.ListFavoriteDTO;
import org.handmade.miniproject.product.dto.product.ListProductDTO;
import org.handmade.miniproject.product.repository.FavoriteRepository;
import org.handmade.miniproject.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService{

    private final FavoriteRepository favoriteRepository;

    private final ProductRepository productRepository;
    @Override
    public Long register(FavoriteDTO dto) {
        favoriteRepository.save(dtoToEntity(dto, productRepository.getById(dto.getPno())));
        return dto.getFno();
    }

    @Override
    public Long delete(Long fno) {
        favoriteRepository.deleteById(fno);
        return fno;
    }

    @Override
    public ListResponseDTO<ListFavoriteDTO> getList(FavoriteListRequestDTO listRequestDTO) {
        Pageable pageable = PageRequest.of(0,10);
        Page<Object[]> result =favoriteRepository
                .getFavoriteList(listRequestDTO.getKeyword(), pageable);

        List<ListFavoriteDTO> favoriteDToList =
                result.getContent().stream().map(arr -> arrToDTO(arr)).collect(Collectors.toList());

        PageMaker pageMaker = new PageMaker(1,10, (int) result.getTotalElements());
        pageMaker.makePageList(pageable);
        result.getContent().forEach(objects -> log.info(Arrays.toString(objects)));

        return ListResponseDTO.<ListFavoriteDTO>builder()
                .dtoList(favoriteDToList)
                .pageMaker(pageMaker)
                .listRequestDTO(listRequestDTO)
                .build();
    }
}
