package com.sumer.operation.dealPicture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

public class Create {
    private  BufferedImage buffImg = null;

    private  Random random = new Random();

    public File createPicture(String path, int width, int height){
        String[] num = {"0","1"};//,"2","3","4","5","6","7","8","9","A","B","C","D","E","F","#"};

        // 图像buffer
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();

        // 将图像背景填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        Font font = new Font("Fixedly", Font.PLAIN, 8);
        g.setFont(font);
        g.setColor(new Color(0, 0, 0));

        for (int i = 0; i< height/12; i++){
            for (int j = 0; j<width/6; j++){
                g.drawString(num[random.nextInt(num.length)],6*j,12*i);
            }
        }

        try {
            OutputStream sos = new FileOutputStream(path);
            ImageIO.write(buffImg, "jpg", sos);
            sos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  new File(path);
    }
}