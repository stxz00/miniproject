package org.handmade.miniproject.common.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.handmade.miniproject.common.dto.upload.UploadImageDTO;
import org.handmade.miniproject.product.repository.ProductRepository;
import org.handmade.miniproject.product.service.ProductService;
import org.handmade.miniproject.common.service.UploadImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@Controller
@Log4j2
@RequiredArgsConstructor
public class UploadController {

    private final UploadImageService uploadImageService;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Value("${spring.servlet.multipart.location}")
    private String path;

    @ResponseBody
    @GetMapping(value = "/down/{file}")
    public ResponseEntity<byte[]> down(@PathVariable String file){

        log.info("--------------------down: " + file);
        System.out.println(file);
        File target = new File(path, file);
        System.out.println("-------------------------------------");
        String mimeType = null;
        try {
            mimeType = Files.probeContentType(target.toPath()); //파일의 확장자 네임 추출

            if(mimeType.startsWith("image") == false){
                mimeType ="application/octet-stream";   //이미지가 아닌 경우
            }

            byte[] arr = FileCopyUtils.copyToByteArray(target); //파일 배열로 카피

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).body(arr);
            //ContentType 을 해당 확장자 타입으로 설정 후 바디에 response
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


    @ResponseBody
    @PostMapping(value ="/upload", produces = MediaType.APPLICATION_JSON_VALUE) //지금부터 만드는 데이타는 JSON 데이터라고 선언
    public List<UploadImageDTO> upload(@RequestPart(value="img", required=true) MultipartFile[] files){

        log.warn(path);

        List<UploadImageDTO> result = new ArrayList<>();

        for (MultipartFile file:files) {

            log.warn(file);

            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();

            File outFile = new File(path, uuid+"_"+fileName );
            File thumbFile = new File(path, "s_"+uuid+"_"+fileName );

            try {
                InputStream fin = file.getInputStream(); //< -- 변경
                Files.copy(fin, outFile.toPath()); // <-- 변경

                BufferedImage originalImage = ImageIO.read(outFile);

                BufferedImage thumbnail = Thumbnails.of(originalImage)
                        .size(100, 100)
                        .asBufferedImage();

                ImageIO.write(thumbnail, "JPG", thumbFile);

                fin.close(); // <-- 추가


            } catch (IOException e) {
                e.printStackTrace();
            }

            result.add(UploadImageDTO.builder().uuid(uuid).fileName(fileName).build());
        }//end for
        return result;
    }


}
