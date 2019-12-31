package com.sumer.TimeTasks;

import com.sumer.entity.MailInBean;
import com.sumer.entity.ReceiveBean;
import com.sumer.operation.crawler.TwoStep;
import com.sumer.service.MailService;
import com.sumer.service.ReptileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class TimeTasks {


    @Autowired
    MailService ms;

    @Autowired
    TwoStep ts;

    @Autowired
    ReptileService rs;

    //3.添加定时任务
    @Scheduled(cron = "20 * * * * ?")
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {

        List<String> list = getAllPicPath();
        String html = "";
        for (int i = 0; i < list.size(); i++) {
            html = html + "<img src=\'cid:"+i+i+i+"\' style=\'width:500px\'/><br/><br/>";
        }

        html = "<html>\n <body>\n"+html+"</body>\n </html>\n ";

        String  msg = "";
        try {
            List<ReceiveBean> rbList = rs.getAllReceiveBean();
            for (int i = 0; i < rbList.size(); i++) {
                MailInBean mib = new MailInBean();
                mib.setTo(rbList.get(i).getEmail());
                mib.setSbj(rbList.get(i).getName()+",请查看每日图片");
                mib.setHtml(html);
                mib.setList(list);

                ms.sendPicture(mib);

                msg = msg + rbList.get(i).getName()+",";
            }

            ms.sendText("18829290979@163.com","邮件发送通知~",msg+"发送完成");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        System.out.println("每日任务完成: " + LocalDateTime.now());
    }

    /**
     * 获取本日所有图片的地址
     */
    public List<String> getAllPicPath(){

        String path = ts.downloadGroup();

        File file = new File(path);
        File [] files = file.listFiles();

        List<String> list = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            list.add(path+files[i].getName()+"@@"+i+i+i);
        }

        return  list;
    }
}
