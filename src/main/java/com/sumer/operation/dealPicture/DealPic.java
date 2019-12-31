package com.sumer.operation.dealPicture;

import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DealPic {
    //生成中间图片
    private static Create create = new Create();

    public String getPicture(File fi) throws IOException {

        BufferedImage image = ImageIO.read(fi);
        int width = image.getWidth();//图片宽度
        int height = image.getHeight();//图片高度

        String fileName = fi.getName();

        String[] _name = fileName.split("\\.");

        String path = "E:/图片输出为文件/";//所创建文件的路径

        File newFile = create.createPicture(path+_name[0]+".jpg",width,height);
        BufferedImage newImage = ImageIO.read(newFile);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = getRGB(image, j, i);
                int r = (rgb & 0xff0000) >> 16;
                int g = (rgb & 0xff00) >> 8;
                int b = (rgb & 0xff);
                double hh = r * 0.299 + g * 0.578 + b * 0.114;
                if (  hh > 125) {
                    newImage.setRGB(j,i,0xffffff);
                }
                if (hh >0 &&hh < 36){
                    newImage.setRGB(j,i,0xffffff);
                    list.add(j+","+i);
                }
            }
            }

        int minW = Integer.parseInt(list.get(0).split(",")[0]);
        int maxW = Integer.parseInt(list.get(0).split(",")[0]);
        int minH = Integer.parseInt(list.get(0).split(",")[1]);
        int maxH = Integer.parseInt(list.get(0).split(",")[1]);
        for (int i = 0; i < list.size(); i++) {
            int w = Integer.parseInt(list.get(i).split(",")[0]);
            if (w > maxW) {
                maxW = w;
            }
            if (w < minW){
                minW = w;
            }
            int h = Integer.parseInt(list.get(i).split(",")[1]);
            if (h > maxH) {
                maxH = h;
            }
            if (h < minH){
                minH = h;
            }
        }

        Graphics2D g = newImage.createGraphics();

        Font font = new Font("Fixedly", Font.PLAIN, 13);
        g.setFont(font);
        g.setColor(new Color(0, 0, 0));

        int flag = 0;
        for (int i = minH/8; i< maxH/8; i++){
            for (int j = minW/5; j<maxW/5; j++){
                if (list.contains((j*5+minW%5)+","+(i*8+maxH%8))) {
                    if (flag==0){
                        g.drawString("0",j*5,i*8);
                        flag = 1;
                    }else if(flag==1){
                        g.drawString("1",j*5,i*8);
                        flag = 2;
                    }else if(flag==2){
                        g.drawString(".",j*5,i*8);
                        flag = 0;
                    }
                }
            }
        }

        File newPath = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!newPath.exists()){
            newPath = new File("");
        }
        File dest = new File(newPath.getAbsolutePath(), "static/newPicture/");
        if (!dest.exists()){
            dest.mkdirs();
        }

        ImageIO.write(newImage, "jpg", new File(newPath.getAbsolutePath(), "static/newPicture/new"+_name[0]+".jpg"));

        return newPath.getAbsolutePath()+"/static/newPicture/new"+_name[0]+".jpg";
    }

    public  int getRGB(BufferedImage image, int x, int y) {
        int pixel = image.getRGB(x, y);
        return pixel;
    }

    public void createFile(String str, File file, String path) {
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();//创建目录
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}