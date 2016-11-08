/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import structures.Point3D;

/**
 *
 * @author grycz
 */
public class Configurator {    
    //Set here amount of the buildings
    public final int buildings = 2;
    
    //Sets initial distance of the scene from projection surface
    public double initial_distance = 1500;
    
    //Rotation angle step
    public double angle = 7.5;
    
    //We are getting screen dimensions here
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public int width = (int) screenSize.getWidth();
    public int height = (int) screenSize.getHeight();
    
    //Setting application window properties
    public int windowWidth = (int)(0.7 * width);
    public int windowHeight = (int) (0.8 * height);        
    public int windowStartX = 10;
    public int windowStartY = 20;
    
    
    //Setting panel (JPanel) properties
    public int panelWidth = (int) (0.8 * windowWidth);
    public int panelHeight = (int) (0.9 * windowHeight);    
    public Color panelColor = Color.LIGHT_GRAY;
    public int panelStartX = 10;//It forces to start draw panel from point (10,10) in JFrame
    public int panelStartY = 10;
    
    //Setting button(s) properties
    public int buttonX = (int) (0.83 * windowWidth);
    public int buttonHeight = 40;
    public int buttonWidth = 150;
    public int buttonMarginTop = 10;
    public int distanceBetweenButtons = 25;
    
    public Point3D source = new Point3D(0, 100, 1390);
    public Point3D observer = new Point3D(0, 0, 0);
    
    public float Ia = 100, Iin = 1000, Ka = 0.4f, Kd =  0.25f, Ks = 0.75f, n = 5;
    
    public double b1[][] = {

        {100.0, -250.0, 1500.0},
        {100.0, 0.0, 1500.0},
        {200.0, 0.0, 1500.0},
        {200.0, -250.0, 1500.0},            
        {100.0, -250.0, 1900.0},
        {100.0, 0.0, 1900.0},
        {200.0, 0.0, 1900.0},
        {200.0, -250.0, 1900.0} 
    };
    
    public double b2[][] = {
        {-173, -250.0, 1426},
        {-173, 0.0, 1426},
        {-86, 0.0, 1476},
        {-86, -250.0, 1476},            
        {-373, -250.0, 1772},
        {-373, 0.0, 1772},
        {-286, 0.0, 1822},
        {-286, -250.0, 1822}, 
    };        
        
    
//    public double angleMatrixreverse[][] = {
//        {Math.cos(Math.toRadians(-1 * angle)), 0, Math.sin(Math.toRadians(-1 * angle)), 0},
//        {0, 1, 0, 0},
//        {-1 * Math.sin(Math.toRadians(-1 * angle)), 0, Math.cos(Math.toRadians(-1 * angle)), 0},
//        {0, 0, 0, 1}
//    };
}