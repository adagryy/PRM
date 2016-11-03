/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.awt.EventQueue;
import structures.*;
/**
 *
 * @author grycz
 */
public class Glowna {
    public static void main(String[] args){
                EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                        new MyFrame(new Configurator());
                }
        });
    }
}
