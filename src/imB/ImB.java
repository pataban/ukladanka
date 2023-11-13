package imB;

import java.awt.*;
import javax.swing.*;

public class ImB extends JButton{
	private static final long serialVersionUID = 1L;
	private Image img;
	private int wSiz,hSiz;
	
	public ImB(Image img, int wSiz,int hSiz) {
		this.img = img;
		this.wSiz = wSiz;
		this.hSiz = hSiz;
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, wSiz, hSiz, this);
	}

	public Image getImg() {
		return img;
	}

}
