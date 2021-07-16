package org.handmade.miniproject.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.product.dto.qna.ListQnaDTO;
import org.handmade.miniproject.product.dto.qna.QnaDTO;
import org.handmade.miniproject.product.dto.qna.QnaListRequestDTO;
import org.handmade.miniproject.product.dto.review.ListReviewDTO;
import org.handmade.miniproject.product.dto.review.ReviewDTO;
import org.handmade.miniproject.product.dto.review.ReviewListRequestDTO;
import org.handmade.miniproject.product.entity.Qna;
import org.handmade.miniproject.product.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody ReviewDTO dto){
        log.info(dto);
        return ResponseEntity.ok(reviewService.register(dto));
    }

    @GetMapping("/{rno}")
    public ResponseEntity<ReviewDTO> read(Long rno){
        log.info(rno);
        return ResponseEntity.ok(reviewService.read(rno));
    }

    @DeleteMapping("/{rno}")
    public ResponseEntity<Long> delete(Long rno){
        log.info(rno);
        return ResponseEntity.ok(reviewService.delete(rno));
    }

    @PutMapping("/{rno}")
    public ResponseEntity<Long> modify(@PathVariable Long rno, @RequestBody ReviewDTO dto){
        log.info(rno);
        dto.setRno(rno);
        return ResponseEntity.ok(reviewService.modify(dto));
    }

    @GetMapping("/list")
    public ResponseEntity<ListResponseDTO<ListReviewDTO>> list(@RequestBody ReviewListRequestDTO requestDTO){
        log.info(requestDTO.getKeyword());
        return ResponseEntity.ok(reviewService.getList(requestDTO));
    }

}
