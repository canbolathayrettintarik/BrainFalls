/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hafızaoyunu3;

import static hafızaoyunu3.Shapes.colors;
import static hafızaoyunu3.Shapes.generateShape;
import static hafızaoyunu3.Shapes.shapes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author tarık
 */
public class GamePanel extends JPanel implements ActionListener, MouseListener{
    int spawnSayaci=0;
    int spawnLimiti=5;
    static int round=5;
int roundNumber = 0;        
    int playerIndex = 0;        
  boolean isPlayerTurn = false;
    int score = 0;
static int highScore = 0;
    
    public static ArrayList<Shapes> trueRoundShapes = new ArrayList<>();
    public ArrayList<Shapes> fallingShapes = new ArrayList<>(); 
    
    String[] roundShapes;
    Color[] roundcolors;
    Timer timer;
 
    ArrayList<Color> colorSequence = new ArrayList<>();
    ArrayList<String> shapeSequence = new ArrayList<>();
    
    Random random=new Random();
    public GamePanel(){
        setSize(600, 800);
        
        genRoundShapes();
         
        addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            
            kontrolEt(e.getX(), e.getY());
        }
    });
        
        
        
        
        timer = new Timer(25, this); 
        //timer.start(); butonaksd
    }


    
    
    
    
    public void genRoundShapes(){
        Random random=new Random();
        roundcolors = new Color[round]; 
        roundShapes = new String[round];
        for(int i=0; i < round; i++){
            roundcolors[i]=colors[random.nextInt(colors.length)];
            roundShapes[i]=shapes[random.nextInt(shapes.length)];
        }
    }
    
    public void clearRoundShapes(){
        trueRoundShapes.clear();
   
    }
    
    public static int getRound(){
        return round;
    
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
    Iterator<Shapes> iter = fallingShapes.iterator();
    while (iter.hasNext()) {
        Shapes s = iter.next();
        s.y += 5;  

 
        if (s.y > 800) {
            iter.remove();
        }
    }

     
    spawnSayaci++; 
    if (spawnSayaci >= spawnLimiti) {  
        fallingShapes.add(Shapes.generateShape());  
        spawnSayaci = 0;  
    }

     
    repaint();
    
    
    
    
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  
        
         
        for (Shapes s : fallingShapes) {
             
            s.Draw(g); 
        }
    }

    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    public boolean isGameRunning() {
    return timer != null && timer.isRunning();
}
    
    
    private void kontrolEt(int mouseX, int mouseY) {
    
    for (int i = fallingShapes.size() - 1; i >= 0; i--) {
        Shapes s = fallingShapes.get(i);

        
        if (mouseX >= s.x && mouseX <= s.x + s.size && 
            mouseY >= s.y && mouseY <= s.y + s.size) {
            
            
                checkInput(s.color, s.shape);
                fallingShapes.remove(i);  
                 
           
            
            break; 
        }
    }
}
    
    private boolean isClicked(Shapes s) {
     
    for (int i = 0; i < round; i++) {
         
        if (s.color.equals(roundcolors[i]) && s.shape.equals(roundShapes[i])) {
            return true;  
        }
    }
    return false;  
}
    
    
public void addToSequence() {
    Color secilenRenk = colors[random.nextInt(colors.length)];
    String secilenSekil = shapes[random.nextInt(shapes.length)];
    
    
    colorSequence.add(secilenRenk);
    shapeSequence.add(secilenSekil);
    
    
     
}
    
 
   public void prepareRound() {
    roundNumber++;
    playerIndex = 0;
    
  
    isPlayerTurn = false; 
     
 
    addToSequence(); 
 
    java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this); 
    
    new ShapeReader(parentWindow, colorSequence, shapeSequence, () -> {
         
         
        
        isPlayerTurn = true;  
         
    });
}
   
   public void checkInput(Color clickedColor, String clickedShape) {
    
       if (!isPlayerTurn) {
        System.out.println("Wait.");
        return;
    }

 
    Color expectedColor = colorSequence.get(playerIndex);
    String expectedShape = shapeSequence.get(playerIndex);
 
    if (clickedColor.equals(expectedColor) && clickedShape.equals(expectedShape)) {
        
   
        
        playerIndex++; 

        
        if (playerIndex >= colorSequence.size()) {
          
            prepareRound(); 
        }

    } else {
        
        
        System.out.println("GAME OVer " + clickedShape + " | Olması gereken: " + expectedShape);
        gameOver();
    }
}
  
   
   public void gameOver() {
    isPlayerTurn = false;  
    
    JOptionPane.showMessageDialog(this, "GAME OVER! Score:" + roundNumber);
    
    colorSequence.clear();
    shapeSequence.clear();
    roundNumber = 0;
    
   if (score > highScore) {
    highScore = score;}

    
    prepareRound();  
}
   
   
    public void startgame() {
         if (roundNumber == 0) {
        restart();
    } 
    
    else if (timer != null && !timer.isRunning()) {
        timer.start();
    }
     }

    public void pausegame() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
             
        }
    }
   
    public void restart() {
     
    if (timer != null) timer.stop();

    fallingShapes.clear();    
    colorSequence.clear();     
    shapeSequence.clear();     
    if (trueRoundShapes != null) trueRoundShapes.clear(); 
    spawnSayaci = 0;
    roundNumber = 0;  
    playerIndex = 0;
    isPlayerTurn = false;
    repaint();
    prepareRound(); 
    timer.start();
}
   
   
   
}
    
 
