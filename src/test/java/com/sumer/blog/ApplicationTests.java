package com.sumer.blog;

import com.sumer.BlogApplication;
import com.sumer.entity.MyConfigure;
import com.sumer.operation.crawler.OneStep;
import com.sumer.operation.crawler.TwoStep;
import com.sumer.service.ReptileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.text.ParseException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BlogApplication.class }) // 指定启动类
public class ApplicationTests {

   @Autowired
    TwoStep ts;

    @Test
    public void sendSimpleMail() throws ParseException {

        System.out.println();
     /*   String str = "E:\\allPicture\\2019-08\\11\\";

        File file = new File(str);
        File [] files = file.listFiles();

        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());
        }*/

    }
}
