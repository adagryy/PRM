/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JLabel;
import structures.*;

/**
 *
 * @author grycz
 */
public class MyFrame extends JFrame implements  KeyListener, ActionListener{
    public JButton jb, jb2;
    public MyPanel panel;
    public JLabel label1;
    float x = 0;
    Configurator c;

    public boolean flag = false;
    public MyFrame(Configurator c) {
            super("GRAKOM Simulator");
            this.c = c;
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);            
            setPreferredSize(new Dimension(c.windowWidth ,c.windowHeight));
            setLocation(c.windowStartX, c.windowStartY);
            panel = new MyPanel(c, c.panelStartX, c.panelStartY);  
            setFocusable(true);            
            
            add(panel);
            
            pack();           
            
            jb = new JButton("Wyjście");
//            jb2 = new JButton("Przycisk");    
            
            jb.setBounds(c.buttonX, nthButton(1), c.buttonWidth, c.buttonHeight);
//            jb2.setBounds(c.buttonX, nthButton(2), c.buttonWidth, c.buttonHeight);
            
            jb.addActionListener(this);
//            jb2.addActionListener(this);
            
            addKeyListener(this);
            
            
            label1 = new JLabel("Distance: 1000px");
            label1.setBounds(c.buttonX, nthButton(2), c.buttonWidth, c.buttonHeight);

            add(label1);
            add(jb);
            
            drawSpheres();
            
            setVisible(true);
            
    }
        
        
    @Override
    public void keyReleased(KeyEvent evt) {
    }
 
    @Override
    public void keyPressed(KeyEvent evt) {      
        char character = evt.getKeyChar();
        
        if(character == 'w'){
            panel.col = Color.getHSBColor(0, 0, x);
            x += 0.01;
            if(x > 1)
                x = 0;
            drawSpheres();
            panel.repaint();
        }
        if(character == 'w')
            translationY(50);
        if(character == 's')
            translationY(-50);
        if(character == 'a')
            translationX(50);
        if(character == 'd')
            translationX(-50);
        if(character == 'y')
            translationZ(50);
        if(character == 't')
            translationZ(-50);
    } 
    
    public void drawSpheres(){
        BufferedImage bi = new BufferedImage(c.panelWidth, c.panelHeight, BufferedImage.TYPE_INT_RGB);
        Sphere sphere = new Sphere(0, 0, 0, 100, c, new Vector(0, 0, 1500), bi);//(x, y, z, radius, config)
        panel.img = sphere.createSphere();

//        Sphere sphere2 = new Sphere(0, 0, 0, 100, c, new Vector(-400, 0, 1500), bi);//(x, y, z, radius, config)
//        panel.img2 = sphere2.createSphere();
//
//        Sphere sphere3 = new Sphere(0, 0, 0, 100, c, new Vector(400, 0, 1500), bi);//(x, y, z, radius, config)
//        panel.img3 = sphere3.createSphere();
    }
    @Override
    public void keyTyped(KeyEvent evt) {
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == jb)
            System.exit(0);        
    }
    
    public void translationX(double x){
        c.source.x += x;
        drawSpheres();
        panel.repaint();
    }
    public void translationY(double y){
        c.source.x += y;
        drawSpheres();
        panel.repaint();        
    }
    public void translationZ(double z){
        c.source.x += z;
        drawSpheres();
        panel.repaint();        
    }
   
    public int nthButton(int n){
        return c.buttonMarginTop + (n - 1) * c.buttonHeight + c.distanceBetweenButtons * (n - 1);
    }  

}




//    public DrawLine execute_projection(double x1, double y1, double x2, double y2, double z1, double z2, Color color)
//    {
//        int x1_int = (int) x1, x2_int = (int) x2, 
//            y2_int = (int) y2, y1_int = (int) y1, 
//            z1_int = (int) z1, z2_int = (int) z2;
//        DrawLine dl = new DrawLine();      
//        dl.x1 = (x1_int * c.initial_distance ) / ( z1_int );
//        dl.y1 = (y1_int * c.initial_distance ) / ( z1_int );
//        dl.x2 = (x2_int * c.initial_distance ) / ( z2_int );
//        dl.y2 = (y2_int * c.initial_distance ) / ( z2_int );
//
//        dl.c = color;
//        
//        return dl;
//    }


//                    for(int j = 0; j < 4; j++){
//                        //liczę odległość punktów od płaszczyzny
//                        double point_distance = (A * checked.getPoint(j).x + B * checked.getPoint(j).y + C * checked.getPoint(j).z + D ) / (Math.sqrt(A * A + B * B + C * C));
//                        double observer_distance = ( D ) / (Math.sqrt(A * A + B * B + C * C));//obserwator w pkt (0,0,0)
//
//                        if(point_distance * observer_distance > 0){ 
//                            temp.covers++;//punkty po tej samej stronie
//                            break;
//                        }
//                    }