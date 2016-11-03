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
//            add(jb2);
            
            System.out.println(Color.yellow.brighter().getRGB());
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
            System.out.println(x);
            panel.repaint();
        }
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
    
    
    public Point3D multiplyMatrices(Point3D point, boolean flag){
        double a, b, cc;
        double angleMatrix[][] = new double[4][4];
        if(flag == true)
            angleMatrix = c.angleMatrixforward;
        else
            angleMatrix = c.angleMatrixreverse;
        a = point.x * angleMatrix[0][0] + angleMatrix[0][1] * point.y + angleMatrix[0][2] * point.z;
        b = point.x * angleMatrix[1][0] + angleMatrix[1][1] * point.y + angleMatrix[1][2] * point.z;
        cc= point.x * angleMatrix[2][0] + angleMatrix[2][1] * point.y + angleMatrix[2][2] * point.z;
        return new Point3D(a, b, cc, Color.red);
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