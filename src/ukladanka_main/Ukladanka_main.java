package ukladanka_main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import ukl.*;
import java.io.*;
import java.util.*;
import imB.ImB;

public class Ukladanka_main {
	static final int gridsiz=3;
	static final int gridVGap=1;
	static final int gridHGap=1;
	static final int emptyBorderSiz=20;
	static final int frameHSiz=700;
	static final int frameWSiz=900;
	static final int frameHMax=700;
	static final int frameWMax=1300;
	static final Random rgen=new Random();
	static String[] imgList;
	JFrame frame;
	Image img;
	Ukl u;
	
	public static void main(String[] args) {
		loadImgList();
		new Ukladanka_main().buildMenu();
	}
	
	public void buildMenu() {
		final int cGridWSiz=(int)Math.sqrt(imgList.length-1)+1;
		final int cGridHSiz=(imgList.length-1)/cGridWSiz+1;
		ImB[] imbt;
		
		frame=new JFrame("Ukladanka");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel mainP=new JPanel(new GridLayout(cGridHSiz,cGridWSiz,gridHGap,gridVGap));
		frame.getContentPane().add(BorderLayout.CENTER,mainP);
		
		imbt=new ImB[imgList.length];
		for(int i=0;i<imgList.length;i++)
		{
			imbt[i]=new ImB(new ImageIcon("ukl/"+imgList[i]).getImage(),frameWSiz/cGridWSiz,frameHSiz/cGridHSiz); 
			imbt[i].addActionListener(new imgChoiceListener());
			mainP.add(imbt[i]);
		}
		frame.setBounds(0,0,frameWSiz, frameHSiz);
		frame.setVisible(true);
	}
	
	public void buildUkl() {
		frame.getContentPane().removeAll();
		JPanel mainP=new JPanel(new GridLayout(gridsiz,gridsiz,gridHGap,gridVGap));
		frame.getContentPane().add(BorderLayout.CENTER,mainP);
		
		u=new Ukl(gridsiz,img);
		for(int i=0;i<gridsiz;i++)
			for(int j=0;j<gridsiz;j++)
				mainP.add(u.tt[i][j]);
		u.randomize(rgen);
		u.addCompletionListener(new UklCompletionListener());
		
		frame.setBounds(50,50,img.getWidth(new JPanel()), img.getHeight(new JPanel()));
		frame.setVisible(true);
	}
	
	public void buildFinalImg() {
		frame.getContentPane().removeAll();
		ImB finalImg=new ImB(img,img.getWidth(new JPanel()),img.getHeight(new JPanel()));
		finalImg.addActionListener(new RestartListener());
		frame.getContentPane().add(BorderLayout.CENTER,finalImg);
		frame.setBounds(50,50,img.getWidth(new JPanel()), img.getHeight(new JPanel()));
		frame.setVisible(true);
	}
	
	public void restart() {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		new Ukladanka_main().buildMenu();
	}
	class RestartListener implements ActionListener{
		public void actionPerformed(ActionEvent ev) {
			restart();
		}
	}
	
	class UklCompletionListener implements CompletionListener{
		public void completed() {
			buildFinalImg();
		}
	}
	
	class imgChoiceListener implements ActionListener{
		public void actionPerformed(ActionEvent ev) {
			img=((ImB)ev.getSource()).getImg();
			if((img.getWidth(new JPanel())>frameWMax)||(img.getHeight(new JPanel())>frameHMax)) {
				img=new ImageIcon(img.getScaledInstance(img.getWidth(new JPanel())>>1, -1,Image.SCALE_SMOOTH)).getImage();
			}
			buildUkl();
		}
	}
	
	public static void loadImgList() {
		File uklDir=new File("ukl");
		if(!uklDir.isDirectory()) {
			System.out.print("No ukl directory existing");
			System.exit(-1);
		}
		imgList=uklDir.list();
		int e=imgList.length;
		for(int i=0;i<e;i++)
			if((!imgList[i].endsWith(".jpg"))&&(!imgList[i].endsWith(".png"))) {
				imgList[i]=imgList[--e];
				i--;
			}
		if(e!=imgList.length) {
			String[] tmp=new String[e];
			for(int i=0;i<e;i++)
				tmp[i]=imgList[i];
			imgList=tmp;
		}
		if(imgList.length==0) {
			System.out.print("Brak obrazow");
			System.exit(-1);
		}
		
	}
	
}
