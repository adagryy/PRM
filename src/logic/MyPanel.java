/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.awt.*;
import java.util.ArrayList;
import structures.*;

import javax.swing.JPanel;
/**
 *
 * @author grycz
 */
public class MyPanel extends JPanel{
        public int x,y;
        Configurator c;
        public Image img = null, img2 = null, img3 = null;
        
        Color col = Color.black;
        
    	public MyPanel(Configurator c, int x, int y) {
                setLayout(null);
                setBackground(c.panelColor);
                setBounds(c.panelStartX, c.panelStartY, c.panelWidth, c.panelHeight);
                this.x = x;
                this.y = y;
                this.c = c;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
                g2d.translate(c.panelWidth / 2, c.panelHeight / 2);

//                g2d.setColor(col);                
                
                if(img != null)
                    g.drawImage(img, -1 * c.panelWidth / 2, -1 * c.panelHeight / 2, this);
                if(img2 != null)
                    g.drawImage(img2, -1 * c.panelWidth / 2, -1 * c.panelHeight / 2, this);
                if(img3 != null)
                    g.drawImage(img3, -1 * c.panelWidth / 2, -1 * c.panelHeight / 2, this);                
//               g2d.fillRect(25, 45, 50, 50);
	}
        
        public void setX(int x){
            this.x = x;
        }
        
        public void setY(int y){
            this.y = y;
        }
        
        @Override
        public int getX(){
            return x;
        }
        
        @Override
        public int getY(){
            return y;
        }
}
