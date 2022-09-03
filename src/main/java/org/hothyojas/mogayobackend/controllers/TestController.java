package org.hothyojas.mogayobackend.controllers;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.services.S3UploaderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("test")
public class TestController {

     private final S3UploaderService s3UploaderService;

     @PostMapping("")
     public String test(@RequestParam("file") MultipartFile multipartFile) {
          try {
               return s3UploaderService.uploadFiles(multipartFile, "images");
          } catch (IOException e) {
               throw new RuntimeException(e);
          }
     }
}
