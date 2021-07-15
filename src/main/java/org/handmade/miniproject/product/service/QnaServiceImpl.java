package org.handmade.miniproject.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.common.dto.PageMaker;
import org.handmade.miniproject.product.dto.Favorite.ListFavoriteDTO;
import org.handmade.miniproject.product.dto.qna.ListQnaDTO;
import org.handmade.miniproject.product.dto.qna.QnaDTO;
import org.handmade.miniproject.product.dto.qna.QnaListRequestDTO;
import org.handmade.miniproject.product.entity.Qna;
import org.handmade.miniproject.product.repository.ProductRepository;
import org.handmade.miniproject.product.repository.QnaRepository;
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
public class QnaServiceImpl implements QnaService{

    private final QnaRepository qnaRepository;

    private final ProductRepository productRepository;


    @Override
    public Long register(QnaDTO dto) {
        Qna entity = dtoToEntity(dto, productRepository.findById(dto.getPno()).get());
        qnaRepository.save(entity);
        return entity.getQno();
    }

    @Override
    public Long delete(Long qno) {
        qnaRepository.deleteById(qno);
        return qno;
    }

    @Override
    public QnaDTO read(Long qno){
        return entityToDTO(qnaRepository.findById(qno).get());
    }

    @Override
    public String modify(QnaDTO dto) {
        Optional<Qna> result = qnaRepository.findById(dto.getQno());

        if(result.isPresent()){
            Qna entity = result.get();

            entity.changeQnacontent(dto.getQnacontent());

            qnaRepository.save(entity);

            return dto.getUsername();
        }

        return null;
    }

    @Override
    public ListResponseDTO<ListQnaDTO> getList(QnaListRequestDTO listRequestDTO) {
        Pageable pageable = PageRequest.of(0,10);
        Page<Object[]> result =qnaRepository
                .getQnaList(listRequestDTO.getKeyword(), pageable);

        List<ListQnaDTO> qnaDTOList =
                result.getContent().stream().map(arr -> arrToDTO(arr)).collect(Collectors.toList());

        PageMaker pageMaker = new PageMaker(1,10, (int) result.getTotalElements());

        result.getContent().forEach(objects -> log.info(Arrays.toString(objects)));

        return ListResponseDTO.<ListQnaDTO>builder()
                .dtoList(qnaDTOList)
                .pageMaker(pageMaker)
                .listRequestDTO(listRequestDTO)
                .build();
    }
}
