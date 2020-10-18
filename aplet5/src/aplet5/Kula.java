/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplet5;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JApplet;

/**
 *
 * @author MariuszJanowski
 */
public class Kula extends JApplet {
    int x=0, y=0, d=30, dx=3, dy=5, dt=10;
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init() {
        setBackground(Color.LIGHT_GRAY);
        // TODO start asynchronous download of heavy resources
    }
    void rysuj(Graphics g){
        g.clearRect(0,0,getWidth(),getHeight());
        g.setColor(new Color((int)(Math.random() * 0x1000000)));
        g.fillOval(x,y,d,d);
    }
    @Override
    public void paint(Graphics g){
        rysuj(g);
        x+=dx;
        y+=dy;
        if(x>=getWidth()-d||x<=0){dx=-dx;}
        if(y>=getHeight()-d||y<=0){dy=-dy;}
        try{
            Thread.sleep(dt);
        } catch (InterruptedException e){}
        repaint();
    }
    // TODO overwrite start(), stop() and destroy() methods
}
