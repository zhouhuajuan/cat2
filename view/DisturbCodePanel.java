package com.zhj.event.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class DisturbCodePanel extends JPanel {
    public void paint(Graphics g) {
        String num = "";
        BufferedImage image = new BufferedImage(400,300,BufferedImage.TYPE_INT_RGB);
        Graphics gs = image.getGraphics();
        if(!num.isEmpty()) {
            num = "";
        }
        Font font = new Font("黑体",Font.BOLD,20);
        gs.setFont(font);
        gs.fillRect(0, 0, 400, 300);
        Image img = null;
        try {
            img = ImageIO.read(new File("src/0.jpg"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        image.getGraphics().drawImage(img, 0, 0, 400,300,null);

        Random random = new Random();
        int startX1 = random.nextInt(250);   //随机获取第一条干扰线起点的x坐标
        int startY1 = random.nextInt(250);	//随机获取第一条干扰线起点的y坐标
        int startX2 = random.nextInt(250)+35;//随机获取第一条干扰线终点的x坐标，也是第二条干扰线起点的x坐标
        int startY2 = random.nextInt(250)+20;//随机获取第一条干扰线终点的y坐标，也是第二条干扰线起点的y坐标
        int startX3 = random.nextInt(250)+90;//随机获取第二条干扰线终点的x坐标
        int startY3 = random.nextInt(300)+5;//随机获取第二条干扰线终点的y坐标
        gs.setColor(Color.WHITE);
        gs.drawLine(startX1, startY1, startX2, startY2);
        gs.setColor(Color.LIGHT_GRAY);
        gs.drawLine(startX2, startY2, startX3, startY3);
        for(int i=0;i<4;i++) {
            char ctmp = (char)(random.nextInt(26)+65);
            num += ctmp;
            Color color = new Color(20+random.nextInt(120),20+random.nextInt(120),20+random.nextInt(120));
            gs.setColor(color);
            Graphics2D gs2d = (Graphics2D)gs;
            AffineTransform trans = new AffineTransform();
            trans.rotate(random.nextInt(45)*3.14/180,22*i+8,7);
            float scaleSize = random.nextFloat()+0.8f;
            if(scaleSize>1f)
                scaleSize = 1f;
            trans.scale(scaleSize, scaleSize);
            gs2d.setTransform(trans);
            gs.drawString(String.valueOf(ctmp), 400/6*i+28, 300/2);
        }
        g.drawImage(image, 0, 0, null);
    }
}
