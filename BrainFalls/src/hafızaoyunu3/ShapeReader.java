package hafızaoyunu3;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShapeReader extends JFrame {

    private JLabel infoLabel;
    
   
    private ArrayList<Color> colorList; 
    private ArrayList<String> shapeList;
    
    private Runnable onCompleteCallback;
    private Timer timer;
    private int currentIndex = 0;
    private boolean isShowingText = true;

   
    public ShapeReader(Component parent, ArrayList<Color> colors, ArrayList<String> shapes, Runnable onComplete) {
        this.colorList = new ArrayList<> (colors);
        this.shapeList = new ArrayList<>(shapes);
        this.onCompleteCallback = onComplete;

        setAlwaysOnTop(true);
        
        setTitle("Round");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(new BorderLayout());
        
        setLocationRelativeTo(parent);
        getContentPane().setBackground(new Color(50, 50, 50)); 
        
        
        infoLabel = new JLabel("", SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 60));  
        infoLabel.setOpaque(false); 
        add(infoLabel, BorderLayout.CENTER);

        startAnimation();
        setVisible(true);
    }

    private void startAnimation() {
        timer = new Timer(1000, e -> {
            
            
            if (currentIndex >= colorList.size()) {
                timer.stop();
                dispose(); 
                if (onCompleteCallback != null) onCompleteCallback.run();
                return;
            }

            if (isShowingText) {
                
                Color gelenRenk = colorList.get(currentIndex);
                
                String gelenSekil = shapeList.get(currentIndex);
                
                
                infoLabel.setForeground(gelenRenk);
                
                
                infoLabel.setText(gelenSekil);
                
                isShowingText = false; 
            } else {
                infoLabel.setText(""); 
                isShowingText = true; 
                currentIndex++;       
            }
        });
        
        timer.setInitialDelay(500);
        timer.start();
    }
}