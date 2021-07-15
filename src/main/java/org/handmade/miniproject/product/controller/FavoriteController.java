package org.handmade.miniproject.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.product.dto.Favorite.FavoriteDTO;
import org.handmade.miniproject.product.dto.Favorite.FavoriteListRequestDTO;
import org.handmade.miniproject.product.dto.Favorite.ListFavoriteDTO;
import org.handmade.miniproject.product.dto.product.ListProductDTO;
import org.handmade.miniproject.product.dto.product.ProductListRequestDTO;
import org.handmade.miniproject.product.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/")
    public ResponseEntity<Long> register(@RequestBody FavoriteDTO dto){
        return ResponseEntity.ok(favoriteService.register(dto));
    }

    @DeleteMapping("/{fno}")
    public ResponseEntity<Long> delete(@PathVariable Long fno){
        log.info("=======================================");
        return ResponseEntity.ok(favoriteService.delete(fno));
    }

    @GetMapping("/")
    public ResponseEntity<ListResponseDTO<ListFavoriteDTO>> list(@RequestBody FavoriteListRequestDTO requestDTO){
        log.info("------------------------------");
        log.info(requestDTO);
        return ResponseEntity.ok(favoriteService.getList(requestDTO));
    }
}
