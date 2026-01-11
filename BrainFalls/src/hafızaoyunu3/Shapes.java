/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hafızaoyunu3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import hafızaoyunu3.GamePanel;
import java.awt.Graphics;
 
/**
 *
 * @author tarık
 */
public class Shapes {
      int size;
      int x ;
      int y=0;
    String shape;
    Color color;

    static final String[] shapes={"triangle", "square", "circle","pentagon","pentagon" };/*, \"pentagon\", \"hexagon*/
    static final Color[] colors={Color.BLACK,Color.BLUE,Color.CYAN,Color.RED,Color.PINK,Color.ORANGE};
    
    
    public Shapes(int size, int x, int y, String shape, Color color) {
        this.size = size;
        this.x = x;
        this.y=y;
        this.shape = shape;
        this.color = color;
    }
    
    public static Shapes generateShape(){
        Random random=new Random();
        
        Color choosencolor=colors[random.nextInt(colors.length)];
        
        String choosenshape=shapes[random.nextInt(shapes.length)];
        
        int randx=random.nextInt(0,600);
        
        int randsize=random.nextInt(30,60);
        
        return new Shapes(randsize, randx, 0, choosenshape, choosencolor);
    }
    
   
    
    
    
    void Draw(Graphics g){
        g.setColor(this.color);
        switch(shape){
                case "triangle":
                    int[] pointsx={x, x - 40, x + 40};
                    int[] pointsy={y - 40, y, y + 40};
                    g.fillPolygon(pointsx, pointsy, 3);
                    g.setColor(color);
                    break;
                case "circle":
                    g.fillOval(x, y, 40, 40);
                    g.setColor(color);
                    break;
                case "square":
            
                    g.fillRect(x, y, 40, 40); 
                    g.setColor(color);
                    break;  
               case "pentagon":
               drawPolygon(g, x, y, size, 5); 
               break;
                case "hexagon":
                drawPolygon(g, x, y, size, 6);  
                break;
        
        
        }
    }
    
    private void drawPolygon(java.awt.Graphics g, int x, int y, int size, int sides) {
    int[] xPoints = new int[sides];
    int[] yPoints = new int[sides];
     
    int radius = size / 2;
    int centerX = x + radius;
    int centerY = y + radius;

    for (int i = 0; i < sides; i++) {
        
        double angle = Math.toRadians(-90 + (i * 360.0 / sides));
        
 
        xPoints[i] = centerX + (int) (radius * Math.cos(angle));
        yPoints[i] = centerY + (int) (radius * Math.sin(angle));
    }
    
    g.fillPolygon(xPoints, yPoints, sides);
}
    
    
}
