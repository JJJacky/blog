package com.sumer.operation.crawler;


import com.sumer.entity.MyConfigure;
import com.sumer.entity.URLBean;
import com.sumer.service.ReptileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 实际下载阶段
 */
@Component
public class TwoStep {

    @Autowired
    ReptileService rs;

    @Autowired
    MyConfigure mc;

    /**
     * 下载组图
     */
    public String downloadGroup(){

        URLBean ub = rs.getOneURL();
        String url = ub.getUrl();

        PCBasics pcb = new PCBasics(url,"<img.*src=(.*?)[^>]*?>",getTimePath());
        pcb.downloadMain();

        int max = pcb.pageNum(url,">[1-9]{1}<");

        if (max!=0){
            for (int i = 2; i <= max; i++) {
                String[] str = url.split("\\.h");

                PCBasics _pcb = new PCBasics(str[0]+"_"+i+".html","<img.*src=(.*?)[^>]*?>",getTimePath());
                _pcb.downloadMain();
            }
        }

        rs.updateStatus(ub.getId());

        return getTimePath();
    }

    /**
     * 获取时间
     */
    private String getTimePath(){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String str =  dateFormat.format(date);
        String [] strs = str.split("-");
        String path = mc.getPicPath()+strs[0]+"-"+strs[1]+"/"+strs[2]+"/";

        return  path;
    }
}
