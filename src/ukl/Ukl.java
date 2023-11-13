package ukl;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Ukl {
	final Image img;		//=new ImageIcon("one piece luffy vs big mom.jpg").getImage();
	final int imgBHSiz;
	final int imgBWSiz;
	final public ImgB[][] tt;
	private CompletionListener comLis=null;
	
	public Ukl(int gridsiz,Image img) {
		tt=new ImgB[gridsiz][gridsiz];
		this.img=img;
		imgBHSiz=img.getHeight(new JPanel())/gridsiz;
		imgBWSiz=img.getWidth(new JPanel())/gridsiz;
		
		for(int i=0;i<gridsiz;i++)
			for(int j=0;j<gridsiz;j++)
			{
				tt[i][j]=new ImgB(img,j,i,j*imgBWSiz,i*imgBHSiz,imgBWSiz,imgBHSiz);
				tt[i][j].addActionListener(new ImgBListener());
			}
		tt[gridsiz-1][gridsiz-1]=new ImgB(null,gridsiz-1,gridsiz-1,(gridsiz-1)*imgBWSiz,(gridsiz-1)*imgBHSiz,imgBWSiz,imgBHSiz);
		tt[gridsiz-1][gridsiz-1].addActionListener(new ImgBListener());
	}
	
	class ImgBListener implements ActionListener{
		public void actionPerformed(ActionEvent ev) {
			for(int i=0;i<tt.length;i++)
				for(int j=0;j<tt.length;j++)
					if(!tt[i][j].hasImg())
					{
						int x=((ImgB)ev.getSource()).gx;
						int y=((ImgB)ev.getSource()).gy;
						if(((j==x)&&((i==y-1)||(i==y+1)))||((i==y)&&((j==x-1)||(j==x+1))))
							ImgB.swap(tt[i][j], tt[y][x]);
						if((chk())&&(comLis!=null))
							comLis.completed();
						return;
					}		
		}
	}
	
	public boolean chk() {
		for(int i=0;i<tt.length;i++)
			for(int j=0;j<tt.length;j++)
				if(!tt[i][j].chk())	return false;
		return true;
	}
	
	public void randomize(Random rgen) {
		for(int i=tt.length*tt.length;i>0;i--)
			ImgB.swap(tt[rgen.nextInt(tt.length)][rgen.nextInt(tt.length)], tt[rgen.nextInt(tt.length)][rgen.nextInt(tt.length)]);
	}
	
	public void addCompletionListener(CompletionListener cl) {
		this.comLis=cl;
	}
}
