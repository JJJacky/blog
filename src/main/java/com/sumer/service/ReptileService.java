package com.sumer.service;

import com.sumer.entity.MailInBean;
import com.sumer.entity.ReceiveBean;
import com.sumer.entity.URLBean;
import com.sumer.entity.Web_Address;
import com.sumer.mapper.ReptileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;import java.util.List;

@Service
public class ReptileService {

    @Autowired
    ReptileMapper mm;

    public void addAddress(String str){
        mm.addAddress(str);
    }

    public Web_Address getAddress(){
         return mm.getAddress();
    }

    public List<Web_Address> getAllAddress(){
        return mm.getAllAddress();
    }
    public void addAllURl(URLBean ub){
        mm.addAllURL(ub);
    }

    public URLBean getOneURL(){
       return mm.getOneURL();
    }

    public List<ReceiveBean> getAllReceiveBean(){
        return mm.getAllReceiveBean();
    }

    public void updateStatus(String str){
        mm.updateStatus(str);
        System.out.println("已更新");
    }

}
