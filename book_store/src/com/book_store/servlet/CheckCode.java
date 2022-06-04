package com.book_store.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;

@WebServlet("/CheckCode")
public class CheckCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public CheckCode() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chars="0123456ABCDEFGHIJKLMNOPQRSTUVWXYZ";//36个字符
		char[] rands=new char[4];//创建数组存放数字
		for (int i = 0; i < rands.length; i++) {//遍历数组，往里面存四个数
			int rand=(int)(Math.random()*36);//随机生成数，作为chars的索引以获取四个值
			rands[i]=chars.charAt(rand);//生成四个数存入数组
		}
		//生成image对象
		int width=60,height=20;//定义图像的高度和宽度
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics=image.getGraphics();//获得图像的画笔
		//画背景
		graphics.setColor(new Color(0xDCDCDC));
		graphics.fillRect(0, 0, width, height);//用画笔绘制矩形
		graphics.setColor(Color.black);//用浅灰色填充矩形
		Font font=new Font(null,Font.ITALIC|Font.BOLD,18);
		graphics.setFont(font);//黑色，斜体18号
		
		//在不同的高度绘制不同字符
		graphics.drawString(""+rands[0], 1, 18);
		graphics.drawString(""+rands[1], 19, 15);
		graphics.drawString(""+rands[2], 34, 19);
		graphics.drawString(""+rands[3], 46, 16);
		//设置120个干扰点
		for(int i=0;i<120;i++) {
			int x=(int)(Math.random()*width);
			int y=(int)(Math.random()*height);
			int red=(int)(Math.random()*255);
			int blue=(int)(Math.random()*255);
			int green=(int)(Math.random()*255);
			graphics.setColor(new Color(red,green,blue));
			graphics.drawOval(x, y, 1, 0);
		}
		//设置验证码中的干扰线
		for (int i = 0; i < rands.length; i++) {
			int xstart=(int)(Math.random()*30);
			int ystart=(int)(Math.random()*26);
			int xend=(int)(Math.random()*80);
			int yend=(int)(Math.random()*30);
			int red=(int)(Math.random()*255);
			int blue=(int)(Math.random()*255);
			int green=(int)(Math.random()*255);
			graphics.setColor(new Color(red,green,blue));
			graphics.drawOval(xstart, ystart, 1, 0);
			graphics.drawLine(xstart, ystart, xend, yend);
		}
		request.getSession().setAttribute("checkCode", new String(rands));//将生成的验证码存入session中
		response.setContentType("image/jpeg");//设置文档类型
		//将iamge对象送到response的OutputStream对象中
		ServletOutputStream out=response.getOutputStream();
		ImageIO.write(image, "JPEG", out);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
