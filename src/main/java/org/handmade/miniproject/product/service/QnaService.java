package org.handmade.miniproject.product.service;

import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.product.dto.Favorite.FavoriteListRequestDTO;
import org.handmade.miniproject.product.dto.Favorite.ListFavoriteDTO;
import org.handmade.miniproject.product.dto.product.ProductDTO;
import org.handmade.miniproject.product.dto.qna.ListQnaDTO;
import org.handmade.miniproject.product.dto.qna.QnaDTO;
import org.handmade.miniproject.product.dto.qna.QnaListRequestDTO;
import org.handmade.miniproject.product.entity.Favorite;
import org.handmade.miniproject.product.entity.Product;
import org.handmade.miniproject.product.entity.Qna;

import java.time.LocalDateTime;
import java.util.Optional;

public interface QnaService {
    //리스트 만들기

    Long register(QnaDTO dto);

    Long delete(Long qno);

    QnaDTO read(Long qno);

    String modify(QnaDTO dto);

    ListResponseDTO<ListQnaDTO> getList(QnaListRequestDTO listRequestDTO);

    default Qna dtoToEntity(QnaDTO dto, Product product){
        return Qna.builder()
                .qno(dto.getQno())
                .username(dto.getUsername())
                .qnacontent(dto.getQnacontent())
                .answer(dto.isAnswer())
                .ano(dto.getAno())
                .del(dto.isDel())
                .product(product)
                .build();
    }

    default QnaDTO entityToDTO(Qna entity){

        return QnaDTO.builder()
                .qno(entity.getQno())
                .username(entity.getUsername())
                .qnacontent(entity.getQnacontent())
                .answer(entity.isAnswer())
                .ano(entity.getAno())
                .del(entity.isDel())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    }

    // QnA 리스트 DTO 로 변환
    default ListQnaDTO arrToDTO(Object[] arr){
        Qna qna = (Qna) arr[0];
        long favoriteCount = (long)arr[1];
        return ListQnaDTO.builder()
                .qnaDto(entityToDTO(qna))
                .qnaCount(favoriteCount)
                .build();
    }

}
