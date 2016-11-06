    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import logic.Configurator;

/**
 *
 * @author grycz
 */
public class Sphere {
    private Configurator configurator;
    private double radius, y_start, z_rotation_angle = 0.0, y_rotation_angle = 0.0;    
    private Point3D lead1 = new Point3D(), lead2 = new Point3D(), middle = new Point3D(), p_start ;
    private Vector vector;
    private BufferedImage bi;
    
    public Sphere(double mid_x, double mid_y, double mid_z, double radius, Configurator configurator, Vector vector, BufferedImage bi){
        this.middle.x = mid_x;
        this.middle.y = mid_y;
        this.middle.z = mid_z;
        this.radius = radius;
        this.configurator = configurator;
        this.p_start = new Point3D(mid_x, mid_y + radius, mid_z);
        this.vector = vector;
        this.bi = bi;
    }
    
    
    public Image createSphere(){
//        BufferedImage bi = new BufferedImage(configurator.panelWidth, configurator.panelHeight, BufferedImage.TYPE_INT_RGB);
        
        Graphics g = bi.getGraphics();
        g.translate(configurator.panelWidth / 2, configurator.panelHeight / 2);
        g.setColor(Color.red);       
//        g.drawRect(0,0, 0, 0);
        for(int i = 0; i < 360; i++){
            rotateZ();
            Point2D pointToDraw = executeProjection(this.lead1, vector);
            for(int j = 0; j < 1200; j++){
                
                rotateY();
                Point2D internalPointToDraw = executeProjection(this.lead2, vector);
                Vector L = getL(lead2, configurator.source, vector);
                Vector N = getN(middle, lead2);
                Vector V = getV(lead2, configurator.observer, vector);
                double fi = Math.acos(NL(N, L));
                double alfa = Math.acos(NV(N, V));
                double theta = fi - alfa;

                double I = configurator.Ia * configurator.Ka + configurator.Iin * (((configurator.n + 2) / (2 * Math.PI)) *configurator.Kd * Math.cos(fi) + 
                        configurator.Ks * ((configurator.n + 2) / (2 * Math.PI)) * Math.pow(Math.cos(theta), configurator.n));
//                double I = configurator.Ia * configurator.Ka + configurator.Iin * (configurator.Kd * Math.cos(fi) + 
//                        configurator.Ks * ((configurator.n + 2) / (2 * Math.PI)) * Math.pow(Math.cos(theta), configurator.n));                
                g.setColor(Color.getHSBColor(0, 0, (float) (I / 6760)));
                g.drawRect((int) internalPointToDraw.getX(), (int) internalPointToDraw.getY(), 0, 0);
                y_rotation_angle -= 0.3;              
            }
            
            g.drawRect((int) pointToDraw.getX(), (int) pointToDraw.getY(), 0, 0);
            z_rotation_angle -= 0.5;
        }
        return bi;
    }
    
    private double NV(Vector vec1, Vector vec2){
        double norm1, norm2, scalar;
        
        norm1 = Math.sqrt(vec1.x * vec1.x + vec1.y * vec1.y + vec1.z * vec1.z);
        norm2 = Math.sqrt(vec2.x * vec2.x + vec2.y * vec2.y + vec2.z * vec2.z);
        
        scalar = vec1.x * vec2.x + vec1.y * vec2.y + vec1.z * vec2.z;
        
        return (scalar / (norm1 * norm2));
    }
    
    private double NL(Vector vec1, Vector vec2){ //returns cosine
        double norm1, norm2, scalar;
        
        norm1 = Math.sqrt(vec1.x * vec1.x + vec1.y * vec1.y + vec1.z * vec1.z);
        norm2 = Math.sqrt(vec2.x * vec2.x + vec2.y * vec2.y + vec2.z * vec2.z);
        
        scalar = vec1.x * vec2.x + vec1.y * vec2.y + vec1.z * vec2.z;
        
        return (scalar / (norm1 * norm2));
    }
   
    
    private Vector getL(Point3D p1, Point3D p2, Vector vec){
//        Vector vector = new Vector();        
        return new Vector(p2.x - (p1.x + vec.x), p2.y - (p1.y + vec.y), p2.z - (p1.z + vec.z));
    }
    
    private Vector getN(Point3D p1, Point3D p2){
        return new Vector(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z);
    }
    
    private Vector getV(Point3D p1, Point3D p2, Vector vec){
        return new Vector(p2.x - (p1.x + vec.x), p2.y - (p1.y + vec.y), p2.z - (p1.z + vec.z));
    }
    
    private Point2D executeProjection(Point3D point, Vector vector){
        Point2D p = new Point2D();        
        double d = 1500;        
        p.setX((( point.x + vector.x ) * d) / ( ( point.z + vector.z )));
        p.setY((( point.y + vector.y ) * d) / ( ( point.z + vector.z )));

        return p;
    }
    
    private void rotateZ(){
        this.lead1.x = Math.cos(Math.toRadians(this.z_rotation_angle)) * this.p_start.x - Math.sin(Math.toRadians(this.z_rotation_angle)) * this.p_start.y;
        this.lead1.y = Math.sin(Math.toRadians(this.z_rotation_angle)) * this.p_start.x + Math.cos(Math.toRadians(this.z_rotation_angle)) * this.p_start.y;
        this.lead1.z = this.p_start.z;
    }
    
    private void rotateY(){
        this.lead2.x= Math.cos(Math.toRadians(this.y_rotation_angle)) * this.lead1.x + Math.sin(Math.toRadians(this.y_rotation_angle)) * this.lead1.z;
        this.lead2.y = this.lead1.y;
        this.lead2.z = -1 * Math.sin(Math.toRadians(this.y_rotation_angle)) * this.lead1.x + Math.cos(Math.toRadians(this.y_rotation_angle)) * this.lead1.z;
    }  
    
    public double getX(){
        return middle.x;
    }
    
    public double getY(){
        return middle.y;
    }

    public double getZ(){
        return middle.z;
    }  
    
}
