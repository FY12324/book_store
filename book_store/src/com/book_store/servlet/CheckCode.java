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
		String chars="0123456ABCDEFGHIJKLMNOPQRSTUVWXYZ";//36���ַ�
		char[] rands=new char[4];//��������������
		for (int i = 0; i < rands.length; i++) {//�������飬��������ĸ���
			int rand=(int)(Math.random()*36);//�������������Ϊchars�������Ի�ȡ�ĸ�ֵ
			rands[i]=chars.charAt(rand);//�����ĸ�����������
		}
		//����image����
		int width=60,height=20;//����ͼ��ĸ߶ȺͿ��
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics=image.getGraphics();//���ͼ��Ļ���
		//������
		graphics.setColor(new Color(0xDCDCDC));
		graphics.fillRect(0, 0, width, height);//�û��ʻ��ƾ���
		graphics.setColor(Color.black);//��ǳ��ɫ������
		Font font=new Font(null,Font.ITALIC|Font.BOLD,18);
		graphics.setFont(font);//��ɫ��б��18��
		
		//�ڲ�ͬ�ĸ߶Ȼ��Ʋ�ͬ�ַ�
		graphics.drawString(""+rands[0], 1, 18);
		graphics.drawString(""+rands[1], 19, 15);
		graphics.drawString(""+rands[2], 34, 19);
		graphics.drawString(""+rands[3], 46, 16);
		//����120�����ŵ�
		for(int i=0;i<120;i++) {
			int x=(int)(Math.random()*width);
			int y=(int)(Math.random()*height);
			int red=(int)(Math.random()*255);
			int blue=(int)(Math.random()*255);
			int green=(int)(Math.random()*255);
			graphics.setColor(new Color(red,green,blue));
			graphics.drawOval(x, y, 1, 0);
		}
		//������֤���еĸ�����
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
		request.getSession().setAttribute("checkCode", new String(rands));//�����ɵ���֤�����session��
		response.setContentType("image/jpeg");//�����ĵ�����
		//��iamge�����͵�response��OutputStream������
		ServletOutputStream out=response.getOutputStream();
		ImageIO.write(image, "JPEG", out);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
