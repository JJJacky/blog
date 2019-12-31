package com.sumer.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 自定义配置类
 */
@PropertySource(value={"classpath:iConfigure.properties"})
@Component
@ConfigurationProperties(prefix = "mycfig")
public class MyConfigure {

    private String picPath;

    private String taskTime;  //定时任务开启时间

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    @Override
    public String toString() {
        return "MyConfigure{" +
                "picPath='" + picPath + '\'' +
                ", taskTime='" + taskTime + '\'' +
                '}';
    }
}
