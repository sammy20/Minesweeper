package minesweeper;  
import java.awt.*; 
import java.awt.event.*; 
import java.util.*; 
import javax.swing.*;



public class Minesweeper extends JFrame implements ActionListener {
    private JPanel p1 = new JPanel(); 
    private JPanel p2 = new JPanel(new GridLayout(10,10));
    
    private JButton s = new JButton(); 
    private JButton [][] b = new JButton[10][10]; 
    
    private Icon ico1 = new ImageIcon("mina.png"); 
    private Icon ico2 = new ImageIcon("1.png"); 
    
    private int [][] m = new int[10][10];
    
    
    public Minesweeper() { 
      super("Minesweeper"); 
      
      add(p1, BorderLayout.NORTH); 
      p1.add(s); 
      s.setIcon(ico2); 
      
      add(p2); 
      
      s.addActionListener( new ActionListener() { 
        public void actionPerformed (ActionEvent ev) { 
            newGame(); 
        }
    });

      generare(20); 
      count();
      for(int i=0; i < b.length;i++) 
          for(int j=0; j < b[i].length; j++){
              b[i][j] = new JButton(); 
              p2.add(b[i][j]); 
              b[i][j].addActionListener(this); 
          }   
       
      setSize(500,500); 
      setLocationRelativeTo(null);
      setVisible(true);
    }
    
    private void initializare() {
        for (int i=0;  i<m.length;i++)  
            for(int j =0; j<m[i].length; j++) 
                m[i][j]=0; 
    }
    
    private void generare(int n) {
        Random r = new Random();
        
        for(int i=0;i<n;i++) {
            int x = r.nextInt(10); 
            int y = r.nextInt(10);
            
            if(m[x][y] != -1)
                m[x][y] = -1; 
            else 
                i--;
    }
    }
    
    private void count() {
     for (int i=0;  i<m.length;i++)  
            for(int j =0; j<m[i].length; j++) {
                if(m[i][j] != -1) { 
                    try{
                          if(m[i-1][j-1] == -1 ) m[i][j] ++; 
                    }catch (Exception e){ } 
                    
                    try{
                          if(m[i-1][j] == -1 ) m[i][j] ++;
                    }catch (Exception e){ } 
                    
                    try{
                          if(m[i-1][j+1] == -1 ) m[i][j] ++;
                    }catch (Exception e){ } 
                    
                    try{
                        if(m[i][j-1] == -1 ) m[i][j] ++;
                    }catch (Exception e){ } 
                    
                    try{
                        if(m[i][j+1] == -1 ) m[i][j] ++;
                    }catch (Exception e){ } 
                    
                    try{
                        if(m[i+1][j-1] == -1 ) m[i][j] ++;
                    }catch (Exception e){ } 
                    
                    try{
                        if(m[i+1][j] == -1 ) m[i][j] ++;
                    }catch (Exception e){ } 
                    
                    try{
                        if(m[i+1][j+1] == -1 ) m[i][j] ++;
                    }catch (Exception e){ } 
                    
                }
            }
    }
    
    private void activareButoane(boolean x){
         for(int i=0;i<b.length;i++) { 
             for(int j=0;j<b[i].length;j++) {
                 b[i][j].setEnabled(x);
             }
    }
    }
         
    private void afisareMine() { 
         for(int i=0;i<b.length;i++)  
             for(int j=0;j<b[i].length;j++) {
                 if(m[i][j] == -1 ) 
                     b[i][j].setIcon(ico1);
             }
    }
    
    public void gameOver() { 
        activareButoane(false); 
        afisareMine();
        JOptionPane.showMessageDialog(null,"Game Over");
    }
    
    private void clearButtons() {
        for(int i=0;i<b.length;i++)  
             for(int j=0;j<b[i].length;j++){
                 b[i][j].setText(null); 
                 b[i][j].setIcon(null); 
             }
                
    }
    
    private void newGame() {
        initializare(); 
        generare(20); 
        count(); 
        clearButtons(); 
        activareButoane(true); 
        JOptionPane.showMessageDialog(null,"Succes");
    }
    
    
    public void actionPerformed(ActionEvent ev){
         JButton x= (JButton) ev.getSource();
         for(int i=0;i<b.length;i++)  
             for(int j=0;j<b[i].length;j++) {
                 if(b[i][j]==x) { 
                     if(m[i][j]==-1) { 
                         gameOver(); 
                     }
                     else { 
                         b[i][j].setText(String.valueOf(m[i][j]));
                         b[i][j].setEnabled(false);
                     }
                 }
             }
             
    }
    
    
    public static void main(String[] args) {
       new Minesweeper();
    }
    
}
