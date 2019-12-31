package com.sumer.controller;

import com.sumer.service.ReptileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReptileController {

    @Autowired
    ReptileService rs;

    @RequestMapping("/addAddress")
    public void addAddress(){

        /*for (int i = 2; i <=25 ; i++) { //https://www.meitulu.com/t/aiyouwu/10.html
            String str = "https://www.meitulu.com/t/aiyouwu/"+i+".html";
            rs.addAddress(str);
        }

        String str = "https://www.meitulu.com/t/aiyouwu";
        rs.addAddress(str);*/
    }

}
