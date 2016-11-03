/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.awt.Color;

/**
 *
 * @author grycz
 */
public class Point3D {
    public double x, y, z;
    
    private Color color;
    
    public Point3D(){}
    
    public Point3D(double x, double y, double z, Color color){
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
    }
    
    public void setPoint(Point3D point){
        this.x = point.x;
        this.y = point.y;
        this.z = point.z;
    }
    
    public Point3D substract(Point3D point){
        Point3D p = new Point3D();
        p.x = this.x - point.x;
        p.y = this.y - point.y;
        p.z = this.z - point.z;  
        return p;
    }
}
