package com.sumer.mapper;

import com.sumer.entity.MailInBean;
import com.sumer.entity.ReceiveBean;
import com.sumer.entity.URLBean;
import com.sumer.entity.Web_Address;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReptileMapper {
     /**
      * 添加地址
      * @param str
      */
     void addAddress(String str);

     /**
      * 获取一条地址
      * @return
      */
     Web_Address getAddress();

     /**
      * 获取所有地址
      * @return
      */
     List<Web_Address> getAllAddress();

     /**
      * 添加URl
      * @param ub
      */
     void addAllURL(URLBean ub);

     /**
      * 获取一条URL
      */
     URLBean getOneURL();

     List<ReceiveBean> getAllReceiveBean();

     void updateStatus(String str);
}
