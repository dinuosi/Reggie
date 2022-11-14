package com.itheima.reggie.controller;

import com.itheima.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @Program: reggie_take_out
 * @Description: 文件的上传和下载
 * @Author: ATao
 * @Create: 2022-11-14 16:04
 * @Since version-1.0
 **/

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        // 变量名必须为file(规定)
        // 当前的file是一个临时文件,需要转存到指定位置,否则本词请求完成后临时文件会删除

        File dir = new File(basePath);
        if (!dir.exists()){
            //目录不存在
            dir.mkdir();
        }

        String filename = UUID.randomUUID().toString();
        String suffix = file.getOriginalFilename().toString().substring(file.getOriginalFilename().lastIndexOf("."));
        filename=filename+suffix;
        try {
            file.transferTo(new File(basePath+filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("文件:{}",filename);
        return R.success(filename);
    }

    @GetMapping("download")
    public void download(HttpServletResponse response, String name){
        log.info(name);
        // 输入流,通过输入流读取文件内容
        try {
            FileInputStream fileInputStream = new FileInputStream(basePath+name);
            // 输出流,通过输出流将文件写会浏览器,在浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            fileInputStream.close();
            outputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
