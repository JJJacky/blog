package com.sumer.controller;

import com.sumer.operation.dealPicture.DealPic;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class UploadController {

    @PostMapping("/upload")
    @ResponseBody
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws FileNotFoundException {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();

        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()){
            path = new File("");
        }

        File dest = new File(path.getAbsolutePath(), "static/oldPicture/");
        if (!dest.exists()){
            dest.mkdirs();
        }



        try {
            file.transferTo(new File(path.getAbsolutePath(),"static/oldPicture/"+fileName));
            DealPic dp = new DealPic();
            String newFile = dp.getPicture(new File(path.getAbsolutePath(), "static/oldPicture/"+fileName));
            System.out.println(newFile);
            return "E:\\图片输出为文件/1.jpg";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败！";
    }
}
