package com.sumer.entity;

import java.util.List;

/**
 * 邮件发送输入类
 */
public class MailInBean {

    private String to;//邮件接收方

    private String sbj; //邮件主题

    private String html; //html内容

    private List<String> list; //图片附件信息集合

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSbj() {
        return sbj;
    }

    public void setSbj(String sbj) {
        this.sbj = sbj;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "MailInBean{" +
                "to='" + to + '\'' +
                ", sbj='" + sbj + '\'' +
                ", html='" + html + '\'' +
                ", list=" + list +
                '}';
    }
}
