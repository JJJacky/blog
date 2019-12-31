package com.sumer.operation.crawler;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 爬虫基础类
 * by  YJ  2019.07.27
 * <a href=(.*?)/([0-9]{1,2}).html(.*?)>  获取每一组图片的地址正则
 */
public class PCBasics {

    // 地址
    private String URL = "https://www.meitulu.com/t/aiyouwu/";
    // 获取img标签正则
    private String GROUPURL_REG = "<a href=(.*?)/([0-9]{1,2}).html(.*?)>";//"<img.*src=(.*?)[^>]*?>";
    // 获取src路径的正则
    private String IMGSRC_REG = "[a-zA-z]+?://(.*?)/([1-9]{1}[0-9]{0,1}).jpg";//"[a-zA-z]+://[^\\s]*";
    //图片存储地址
    private String Path = "E:/";

    /**
     * 默认构造方法
     */
    public PCBasics() {
    }

    /**
     * 获取每组图片的构造函数
     */
    public PCBasics(String url, String groupURL_REG) {
        this.URL = url;
        this.GROUPURL_REG = groupURL_REG;
    }

    /**
     * 下载图片的构造函数
     */
    public PCBasics(String url, String groupURL_REG, String path) {
        this.URL = url;
        this.GROUPURL_REG = groupURL_REG;
        this.Path = path;
    }

    /**
     * 获取组图URL
     */
    public List<String> getGroupURL(){

        List<String> urlList = new ArrayList<>();
        try {
            String HTML = getHtml(URL);
            urlList = getUrl(HTML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlList;
    }

    /**
     * 下载图片方法
     */
    public List<String> downloadMain(){

        List<String> urlList = new ArrayList<>();
        try {
            String HTML = getHtml(URL);
            urlList = getUrl(HTML);
            List<String> picList = getImageSrc(urlList);
            System.out.println(picList);
            Download(picList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlList;
    }

    /**
     * 获取每组图片的最大页数
     * @param url
     * @param reg
     */
    public int pageNum(String url, String reg){
        int max = 0;
        try {
            String h = getHtml(url);
            Matcher matcher=Pattern.compile(reg).matcher(h);
            List<String> numStr = new ArrayList<String>();
            while (matcher.find()){
                numStr.add(matcher.group().substring(1,2));
            }


            if (numStr==null || numStr.size()<=1){
                return 0;
            }

            for (int i = 1; i < numStr.size(); i++) {
                if (Integer.parseInt(numStr.get(i))>Integer.parseInt(numStr.get(i-1))){
                    max = Integer.parseInt(numStr.get(i));
                }else{
                    max = Integer.parseInt(numStr.get(i-1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return max;
    }



/*************************************************************************************************************************************/

    /**
     * 获取HTML内容
     */
    private String getHtml(String url)throws Exception{
        URL url1=new URL(url);
        HttpURLConnection connection=(HttpURLConnection)url1.openConnection();
       // connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
        InputStream in=connection.getInputStream();
        InputStreamReader isr=new InputStreamReader(in);
        BufferedReader br=new BufferedReader(isr);

        String line;
        StringBuffer sb=new StringBuffer();
        while((line=br.readLine())!=null){
            sb.append(line,0,line.length());
            sb.append('\n');
        }
        br.close();
        isr.close();
        in.close();
        return sb.toString();
    }

    /**
     * 获取组图Url地址
     */
    private List<String> getUrl(String html){
        Matcher matcher=Pattern.compile(GROUPURL_REG).matcher(html);
        List<String>listimgurl=new ArrayList<String>();
        while (matcher.find()){
            listimgurl.add(matcher.group());
        }
        return listimgurl;
    }

    /**
     * 获取ImageSrc地址
     */
    private List<String> getImageSrc(List<String> listimageurl){
        List<String> listImageSrc=new ArrayList<String>();
        for (String image:listimageurl){
            Matcher matcher=Pattern.compile(IMGSRC_REG).matcher(image);
            while (matcher.find()){
                listImageSrc.add(matcher.group());//.substring(0, matcher.group().length()-1)
            }
        }
        return listImageSrc;
    }

    /**
     * 下载图片
     */
    private void Download(List<String> listImgSrc) {
        try {
            //开始时间
            Date begindate = new Date();
            for (String url : listImgSrc) {
                //开始时间
                Date begindate2 = new Date();
                String imageName = url.substring(url.lastIndexOf("/") + 1, url.length());
                URL uri = new URL(url);
                URLConnection connection = uri.openConnection();
                connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
                connection.setRequestProperty("Accept","text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01");
                connection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
                connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.9");
                connection.setRequestProperty("Connection","keep-alive");
                //connection.setRequestProperty("cookie","UM_distinctid=16c33e518571bd-05ae3c0eb348a1-5b4a2c1d-100200-16c33e51858158; CNZZDATA1255357127=2103900576-1564236093-https%253A%252F%252Fwww.baidu.com%252F%7C1564405027");
                connection.setRequestProperty("referer","https://www.meitulu.com/t/aiyouwu/");
                InputStream in = null;
                try {
                    in = connection.getInputStream();
                } catch (Exception e){
                    e.printStackTrace();
                    continue;
                }

                File file = new File(Path);
                if (!file.exists()){
                    file.mkdirs();
                }

                FileOutputStream fo = new FileOutputStream(new File(Path+imageName));
                byte[] buf = new byte[1024];
                int length = 0;
                System.out.println("开始下载:" + url);
                while ((length = in.read(buf, 0, buf.length)) != -1) {
                    fo.write(buf, 0, length);
                }
                in.close();
                fo.close();

                System.out.println(imageName + "下载完成");
                //结束时间
                Date overdate2 = new Date();
                double time = overdate2.getTime() - begindate2.getTime();
                System.out.println("耗时：" + time / 1000 + "s");

                Thread.sleep(300);
            }
            Date overdate = new Date();
            double time = overdate.getTime() - begindate.getTime();
            System.out.println("总耗时：" + time / 1000 + "s");
        } catch (Exception e) {
            System.out.println("下载失败");
            e.printStackTrace();
        }
    }
}