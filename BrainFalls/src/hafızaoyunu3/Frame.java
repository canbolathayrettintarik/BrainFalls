/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hafızaoyunu3;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author tarık
 */
public class Frame extends JFrame{
    public Frame(){
        setSize(1100, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        GamePanel panel=new GamePanel();
        setLayout(null);
        
        add(panel);
        panel.setBounds(0,0,800,800);
        JButton start=new JButton("Start Game");
        
        start.setBounds(850, 20 , 200, 100 );
        add(start);
        
        JButton pause=new JButton("pause Game");
        
        pause.setBounds(850, 140 , 200, 100 );
        add(pause);
        
        JButton restart=new JButton("Restart Game");
        
        restart.setBounds(850, 260 , 200, 100 );
        add(restart);
        
        start.addActionListener(new java.awt.event.ActionListener() {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
         
        panel.startgame();
        
        
        panel.requestFocusInWindow(); 
    }
});
        
    pause.addActionListener(new java.awt.event.ActionListener() {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
         
        panel.pausegame();
    }
});    
       
    restart.addActionListener(new java.awt.event.ActionListener() {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        
        panel.restart();
        panel.requestFocusInWindow();
    }
});    
        
        
        
        
        setVisible(true);
         
        
        
        
        
        
        
    }
    
    
    
}
