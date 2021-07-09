package org.handmade.miniproject.product.controller;

import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Log4j2
public class UploadController {

    @Value("c:\ztemp")
    private String path;

    @ResponseBody
    @GetMapping("/down")

    //dfdgdddd


}
