package org.handmade.miniproject.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.product.dto.Favorite.FavoriteListRequestDTO;
import org.handmade.miniproject.product.dto.Favorite.ListFavoriteDTO;
import org.handmade.miniproject.product.dto.qna.ListQnaDTO;
import org.handmade.miniproject.product.dto.qna.QnaDTO;
import org.handmade.miniproject.product.dto.qna.QnaListRequestDTO;
import org.handmade.miniproject.product.entity.Qna;
import org.handmade.miniproject.product.service.QnaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/qnas")
public class QnaController {

    private final QnaService qnaService;

    //리스트 만들기

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody QnaDTO dto){
        log.info(dto);
        return ResponseEntity.ok(qnaService.register(dto));
    }

    //질문 한 개 조회(수정할때 불러오기용으로 가능할듯)
    @GetMapping("/{qno}")
    public ResponseEntity<QnaDTO> read(Long qno){
        log.info(qno);
        return ResponseEntity.ok(qnaService.read(qno));
    }

    @DeleteMapping("/{qno}")
    public ResponseEntity<Long> delete(Long qno){
        log.info(qno);
        return ResponseEntity.ok(qnaService.delete(qno));
    }

    @PutMapping("/{qno}")
    public ResponseEntity<String> modify(@PathVariable Long qno, @RequestBody QnaDTO dto){
        log.info(qno);
        dto.setQno(qno);
        return ResponseEntity.ok(qnaService.modify(dto));
    }

    @GetMapping("/list")
    public ResponseEntity<ListResponseDTO<ListQnaDTO>> list(@RequestBody QnaListRequestDTO requestDTO){
        log.info("------------------------------");
        log.info(requestDTO);
        return ResponseEntity.ok(qnaService.getList(requestDTO));
    }




}
