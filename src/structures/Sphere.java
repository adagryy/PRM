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
    
    private double ks, kd;
    private int n;
    
    public Sphere(double mid_x, double mid_y, double mid_z, double radius, Configurator configurator, Vector vector, BufferedImage bi,
                double ks, double kd, int n){
        this.middle.x = mid_x;
        this.middle.y = mid_y;
        this.middle.z = mid_z;
        this.radius = radius;
        this.configurator = configurator;
        this.p_start = new Point3D(mid_x, mid_y + radius, mid_z);
        this.vector = vector;
        this.bi = bi;
        this.kd = kd;
        this.ks =ks;
        this.n = n;
    }
    
    
    public BufferedImage createSphere(){
        Graphics g = bi.getGraphics();
        g.translate(configurator.panelWidth / 2, configurator.panelHeight / 2);
        Color c = Color.red;
        Point2D ob;
        if(configurator.source.z > 0 ){
            ob = executeProjection(configurator.source, new Vector(0,0,0));
            g.fillOval((int)ob.getX(), (int)ob.getY(), 30, 30);
        }
        g.setColor(c);
        float sum = configurator.Ia + configurator.Iin * (float)(((n + 2) / (2 * Math.PI)) * 2);
        for(int i = 0; i < 600; i++){
            rotateZ();
            Point2D pointToDraw = executeProjection(this.lead1, vector);
            for(int j = 0; j < 600; j++){
//                if(i > 200){
                rotateY();
                Point2D internalPointToDraw = executeProjection(this.lead2, vector);
                Vector L = getL(lead2, configurator.source, vector);
                Vector N = getN(middle, lead2);
                Vector V = getV(lead2, configurator.observer, vector);
                double cosfi = NL(N, L);
                double cosalpha = NV(N, V);
                double fi = Math.acos(cosfi);
                double alfa = Math.acos(cosalpha);
                double theta = fi - alfa;
                
                double costheta = Math.cos(theta);

                double I = 0;

                double cz1 = configurator.Ia * configurator.Ka ;
                double cz2 = configurator.Iin * ((n+2)/(2*Math.PI)) * this.kd * cosfi;
                double cz3 = configurator.Iin * this.ks * ((n+2)/(2*Math.PI)) * Math.pow(costheta, this.n);
//                if((Math.abs(lead2.z) - Math.abs(configurator.observer.z)) > (Math.abs(middle.z) - Math.abs(configurator.observer.z)))
//                if((lead2.z - configurator.observer.z) < (middle.z - configurator.observer.z))
                     I = cz1 + cz2 + cz3; 
//
                if(I < 0 )
                    I = 0;

                g.setColor(Color.getHSBColor(1, 1, (float) (I / sum)));
//                g.setColor(new Color(red, green, blue, c.getAlpha()));
                
//                g.setColor(Color.getHSBColor(1, 1, 1));
                g.drawRect((int) internalPointToDraw.getX(), (int) internalPointToDraw.getY(), 0, 0);
                y_rotation_angle -= 0.3;        
//                }
            }
            
            g.drawRect((int) pointToDraw.getX(), (int) pointToDraw.getY(), 0, 0);
            z_rotation_angle -= 0.3;
        }
        return bi;
    }
    
    private int check(double i){
        if(i<0){
            return 0;
        } else if(i>255){
            return 255;
        } else {
            return (int)i;
        }
    }
    
    private double fatt(Point3D p){
        return 1.0/Math.sqrt(Math.pow(p.x+configurator.source.x, 2)+
                Math.pow(p.y+configurator.source.y, 2)+
                Math.pow(p.z+configurator.source.z, 2));
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
        double d = 1000;        
        p.setX((( point.x + vector.x ) * d) / ( ( point.z + vector.z )));
        p.setY((( point.y + vector.y ) * d) / ( ( point.z + vector.z )));

        return p;
    }
    
    private void rotateZ(){
        this.lead1.x = Math.cos(Math.toRadians(-1*this.z_rotation_angle)) * this.p_start.x - Math.sin(Math.toRadians(-1*this.z_rotation_angle)) * this.p_start.y;
        this.lead1.y = Math.sin(Math.toRadians(-1*this.z_rotation_angle)) * this.p_start.x + Math.cos(Math.toRadians(-1*this.z_rotation_angle)) * this.p_start.y;
        this.lead1.z = this.p_start.z;
    }
    
    private void rotateY(){
        this.lead2.x= Math.cos(Math.toRadians(-1*this.y_rotation_angle)) * this.lead1.x + Math.sin(Math.toRadians(-1*this.y_rotation_angle)) * this.lead1.z;
        this.lead2.y = this.lead1.y;
        this.lead2.z = -1 * Math.sin(Math.toRadians(-1*this.y_rotation_angle)) * this.lead1.x + Math.cos(Math.toRadians(-1*this.y_rotation_angle)) * this.lead1.z;
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
