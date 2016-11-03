    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.util.ArrayList;

/**
 *
 * @author grycz
 */
public class Sphere {
    private double mid_x, mid_y, mid_z;
    
    private ArrayList<Point3D> pts = new ArrayList<Point3D>();
    
    public Sphere(double mid_x, double mid_y, double mid_z){
        this.mid_x = mid_x;
        this.mid_y = mid_y;
        this.mid_z = mid_z;
    }
    
    public double getX(){
        return mid_x;
    }
    
    public double getY(){
        return mid_y;
    }

    public double getZ(){
        return mid_z;
    }        
}
