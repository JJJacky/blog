package com.sumer.entity;


/**
 * URL实体类
 */
public class URLBean {

    private String id;

    private String url;

    private String pid;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "URLBean{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", pid='" + pid + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
