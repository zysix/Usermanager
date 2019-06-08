package com.zy.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int WIDTH = 60;// 验证码图片的宽度
    private static int HEIGHT = 20;// 验证码图片的高度
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		 HttpSession session = request.getSession(); 
		 // 1.设置响应消息体的类型
		 response.setContentType("image/jpeg"); 
		 // 2.创建ServletOutputStream打印图片 
		 ServletOutputStream sos = response.getOutputStream(); 
		 // 3.告知浏览器不要缓存此图片 
		 response.setHeader("Pragma", "No-cache"); 
		 response.setHeader("Cache-Control", "No-cache"); 
		 response.setDateHeader("Expires", 0); 
		 // 4.创建内存图片并获得图像上下文 
		 BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); 
		 Graphics g = image.getGraphics(); 
		 // 5.产生随机验证码 
		 char[] rands = generateCheckCode(); 
		 // 6绘制图像 
		 drawBackground(g); 
		 drawRands(g, rands); 
		 // 7.绘制完毕，完成图像
		 g.dispose(); 
		 // 8.将图片打印到浏览器上
		 ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		 ImageIO.write(image, "JPEG", bos); 
		 byte[] buf = bos.toByteArray();
		 response.setContentLength(buf.length); 
		 sos.write(buf); 
		 bos.close(); 
		 sos.close(); 
		 // 9.将验证码存入到Session中 
		 session.setAttribute("check_code", new String(rands));
	
	}
	private void drawRands(Graphics g, char[] rands) { 
		g.setColor(Color.BLACK); 
		g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18)); 
		// 10在不同高度上输入验证码的每一个字符 
		g.drawString("" + rands[0], 1, 17); 
		g.drawString("" + rands[1], 16, 15); 
		g.drawString("" + rands[2], 31, 18); 
		g.drawString("" + rands[3], 46, 16); 
	}
	private void drawBackground(Graphics g) { 
		// 11.绘制背景 
		g.setColor(new Color(0xDCDCDC)); 
		g.fillRect(0, 0, WIDTH, HEIGHT); 
		// 12.产生120个干扰点 
		for (int i = 0; i < 120; i++) { 
			int x = (int) (Math.random() * WIDTH); 
			int y = (int) (Math.random() * HEIGHT); 
			int red = (int) (Math.random() * 255); 
			int blue = (int) (Math.random() * 255); 
			int green = (int) (Math.random() * 255); 
			g.setColor(new Color(red, green, blue)); 
			g.drawOval(x, y, 1, 0); 	
		}
		 //随机产生 15 条干扰线, 使图像中的认证码不易被其它程序探测到
        Random random = null;
        random = new Random();
        g.setColor(Color.GREEN);
        for(int i = 0; i < 55; i++){
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            int x1 = random.nextInt(20);
            int y1 = random.nextInt(20);
            g.drawLine(x, y, x + x1, y + y1);
        }
	}
	private char[] generateCheckCode() { 
		String chars = "0123456789abcdefghijklmnopqrstuvwxyz"; 
		char[] rands = new char[4]; 
		for (int i = 0; i < 4; i++) { 
			int rand = (int) (Math.random() * 36); 
			rands[i] = chars.charAt(rand); 
		} 
		return rands; 
	}
}
