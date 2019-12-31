package com.sumer.operation.crawler;

import com.sumer.entity.URLBean;
import com.sumer.entity.Web_Address;
import com.sumer.service.ReptileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 初始化阶段
 */
@Component
public class OneStep {

    @Autowired
    ReptileService rs;

    public void addAllURL(){

        PCBasics pcb = null;
        List<Web_Address> list = getAllAddress();

        int a = 0;
        for (int i = 0; i < list.size(); i++) {
            pcb = new PCBasics(list.get(i).getAddress(),"<a href=(.*?)/([0-9]{4,5}).html(.*?)>");
            List<String> urlList = pcb.getGroupURL();
            for (int j = 0; j < urlList.size(); j++) {
                if (j%2==1){
                    URLBean ub = new URLBean();
                    ub.setUrl(urlList.get(j).split("\"")[1]); //每组图的url
                    ub.setStatus("0");
                    ub.setPid(list.get(i).getId());   //获取并设置外键
                    rs.addAllURl(ub);
                }
            }
        }
    }

    public List<Web_Address> getAllAddress(){
        List<Web_Address> list = rs.getAllAddress();
        return list;
    }
}
