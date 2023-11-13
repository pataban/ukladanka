package ukl;

import java.awt.*;
import javax.swing.*;

class ImgB extends JButton{
	private final static long serialVersionUID=1L;
	public final int gx,gy,hSiz,wSiz;
	private Image img;
	private int sx,sy;
	
	public ImgB(Image img, int gx, int gy, int sx, int sy, int wSiz,int hSiz) {
		this.img = img;
		this.gx = gx;
		this.gy = gy;
		this.sx = sx;
		this.sy = sy;
		this.hSiz = hSiz;
		this.wSiz = wSiz;
		this.setMinimumSize(new Dimension(wSiz,hSiz));
		this.setSize(wSiz, hSiz);
	}
	
	public static void swap(ImgB a,ImgB b) {
		int tmp;
		tmp=a.sx;	a.sx=b.sx;	b.sx=tmp;
		tmp=a.sy;	a.sy=b.sy;	b.sy=tmp;
		if(a.img!=b.img)	{	Image tmpi=a.img;	a.img=b.img;	b.img=tmpi;	}
		a.repaint();
		b.repaint();
	}
	
	public void paintComponent(Graphics gr){
		if(img!=null) {
			((Graphics2D)gr).drawImage(img, 0, 0, wSiz, hSiz, sx, sy, sx+wSiz, sy+hSiz, this);
		}
		else {
			gr.fillRect(0, 0, wSiz, hSiz);
		}
	}

	public boolean hasImg() {
		return (img==null)?false:true;
	}
	
	public boolean chk() {
		if((gx*wSiz==sx)&&(gy*hSiz==sy))
			return true;
		return false;
	}
}
