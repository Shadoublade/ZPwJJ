/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafika;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author MariuszJanowski
 */
public class Grafika extends javax.swing.JFrame {
    Image obrazek = new ImageIcon("k1.jpg").getImage();
    Random r = new Random();
    /**
     * Creates new form Grafika
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Grafika() {
        initComponents();
        setSize(500,500);
        jTabbedPane1.add("Przykłady figur", new Figury());
        jTabbedPane1.add("Grafika 2D", new Grafika2d());
        jTabbedPane1.add("Obrazek", new Obrazek());
        jTabbedPane1.add("Obrazek2D", new Obrazek2D());
        jTabbedPane1.add("Skoczek", new Skoczek());
        jTabbedPane1.add("Liczba Pi", new LiczbaPi());
    }
    public class LiczbaPi extends JPanel{
        double x, y; int x0 = 150, y0 = 150, R = 100, maxP = 25000, lp = 0,ls = 0;
        @Override
        public void paintComponent(Graphics g){
            lp++;
            x = 2 * R *r.nextDouble();
            y = 2 * R *r.nextDouble();
            if((x - R) * (x - R) + (y - R) * (y - R) <= R * R){
                g.setColor(Color.black);
                ls++;
            } else {g.setColor(Color.white);}
            g.drawLine(x0 + (int)x, y0 + (int)y, x0 + (int)x, y0 + (int)y);
            if((lp % 500) == 0){
                g.clearRect(0,0,250,100);
                g.setColor(Color.red);
                g.drawString("Liczba wszystkich punktów = " + lp,20,20);
                g.drawString("Liczba punktów w kole = " + ls, 20, 40);
                g.drawLine(20, 60, 200, 60);
                NumberFormat f = new DecimalFormat("0.####");
                g.drawString("Liczba pi = " + f.format(4.0 * ls / lp), 20, 80);
            }
            if(lp < maxP){
                repaint();
            } else {lp = 0; ls = 0;}
        }
    }
    public class Obrazek extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            g.drawImage(obrazek,5,5,null);
        }
    }
    public class Obrazek2D extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D)g;
            g2d.setClip(new Ellipse2D.Double(20,50,350,300));
            g2d.drawImage(obrazek, 5,5, null);
        }
    }
    public class Skoczek extends JComponent implements ActionListener{
        Color kolor = Color.yellow;
        float x = 0, y = 0;
        public Skoczek(){
            Timer timer = new Timer(500, this);
            timer.start();
        }
        @Override
        protected void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D)g;
            g2d.translate(x, y);
            g2d.setColor(kolor);
            g2d.fillOval(0, 0, 50, 50);
            g2d.setColor(Color.black);
            g2d.fillOval(10, 15, 6, 6);
            g2d.fillOval(35, 15, 6, 6);
            g2d.setStroke(new BasicStroke(3.0f));
            g2d.drawArc(10, 10, 30, 30, -140, 110);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            x = (float)r.nextInt(getWidth()-50);
            y = (float)r.nextInt(getHeight()-50);
            kolor = new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256));
            repaint();
        }
    }
    public class Figury extends JComponent{
        @Override
        protected void paintComponent(Graphics g){
            Font f = new Font("Sanserif",Font.BOLD,18);
            g.setFont(f);
            g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
            g.setColor(Color.magenta);
            g.drawRect(30,30,80,90);
            g.drawString("Pusty prostokąt", 5, 150);
            g.fillRect(30,30 + getHeight()/2,80,90);
            g.drawString("Pełny prostokąt", 5, 350);
            g.setColor(Color.red);
            g.drawOval(150,30,90,90);
            g.drawString("Puste koło", 150, 150);
            g.fillOval(150,30 + getHeight()/2,90,90);
            g.drawString("Pełne koło", 150, 350);
            int[] x = new int[8]; int[]y = new int[8];int[] y1 = new int[8];
            for (int i=0; i<8;i++){
                x[i]=(int)(350+60*Math.cos(i*2*Math.PI/8));
                y[i]=(int)(100+60*Math.sin(i*2*Math.PI/8));
                y1[i]=y[i]+200;
            }
            g.setColor(Color.blue);
            g.drawPolygon(x,y,8);
            g.drawString("Pusty wielobok", 290, 180);
            g.fillPolygon(x,y1,8);
            g.drawString("Pełny wielobok", 290, 380);
        }
    }
    public class Grafika2d extends JComponent{
        @Override
        protected void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint g1 = new GradientPaint (0,0,Color.blue,getWidth(),0,Color.red);
            g2d.setPaint(g1);
            g2d.fillRect(0, 20, getWidth(), 40);
            GradientPaint g2 = new GradientPaint (0,20,Color.blue,20,20,Color.red,true);
            g2d.setPaint(g2);
            g2d.fillRect(0, getHeight() - 80, getWidth(), 40);
            g2d.setStroke(new BasicStroke(9.0f));
            g2d.translate(getWidth()/2,getHeight()/2);
            GradientPaint g4 = new GradientPaint(20,20,Color.red,40,40,Color.white);
            g2d.setPaint(g4);
            for (int i=0;i<8;i++){
                g2d.rotate(Math.PI/4);
                g2d.fillRect(20,20,40,40);
                g2d.drawLine(0, -10, 0, -40);
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Grafika i animacja");
        setSize(new java.awt.Dimension(1000, 1000));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Grafika.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Grafika.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Grafika.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Grafika.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Grafika().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
