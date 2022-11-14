package com.itheima.reggie.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
class CommonControllerTest {

    @Autowired
    CommonController commonController;

    @Test
    public void test1(){
        String file = "sklvnas.jkcnas.jpg";
        String filename = UUID.randomUUID().toString();
        String suffix = file.substring(file.lastIndexOf("."));
        filename=filename+suffix;
        log.info(filename);
    }

}