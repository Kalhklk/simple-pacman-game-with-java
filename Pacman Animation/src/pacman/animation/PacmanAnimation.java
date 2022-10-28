/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pacman.animation;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


public class PacmanAnimation extends Canvas implements KeyListener,Runnable {

    String lastKeyPress = "";
    int x = 50,y = 50;
    boolean mouthAnim = true;
    int mouthAngle = 345;
    public PacmanAnimation(){
        (new Thread(PacmanAnimation.this)).start();
        addKeyListener(this);
    }
    public void paint(Graphics g){
        g.fillArc(x, y,45 ,45 ,0 ,360);
        g.setColor(Color.white);
        g.fillOval(x+15,y+10, 5, 5);
        if(mouthAnim)
            g.fillArc(x+1,y ,45 ,45 ,mouthAngle ,60);
        else
            g.fillArc(x+1,y ,45 ,45 ,mouthAngle+10 ,15);
        
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                lastKeyPress = "left";
                break;
            case KeyEvent.VK_RIGHT:
                lastKeyPress = "right";
                break;
            case KeyEvent.VK_UP:
                lastKeyPress = "up";
                break;
            case KeyEvent.VK_DOWN:
                lastKeyPress = "down";
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void run() {
        while (true) {            
            try {
                Thread.sleep(500);
            if(this.lastKeyPress == "up"&&this.y >= 25){
                y += -20; 
                this.mouthAngle = 65;
             }else if(this.lastKeyPress == "down"&&this.y <= 305){
                y += 20;
                this.mouthAngle = 245;                
             }else if(this.lastKeyPress == "left"&&this.x >= 25){
                x += -20;   
                this.mouthAngle = 155;
             }else if(this.lastKeyPress == "right"&&this.x <= 325){
                x += 20;
                this.mouthAngle = 345;
             }  
            mouthAnim = !mouthAnim;
            repaint();
            } catch (InterruptedException ex) {
                Logger.getLogger(PacmanAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public static void main(String[] args) {
        PacmanAnimation a=new PacmanAnimation();
        JFrame f=new JFrame();
        f.add(a);
        f.setSize(400,400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
