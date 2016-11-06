/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

/**
 *
 * @author grycz
 */
public class Point2D {
    private double x, y;
    
    public Point2D(){}
    
    public Point2D(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public void setX(double x) { this.x = x; }
    
    public void setY(double y) { this.y = y; }
    
    public double getX(){ return this.x; }
    
    public double getY(){ return this.y; }
    
    @Override
    public String toString(){
        return x + ", " + y;
    }
}
