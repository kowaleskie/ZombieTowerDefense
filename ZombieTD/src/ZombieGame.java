import javax.swing.*;

public class ZombieGame extends JFrame 
{
    public ZombieGame() 
    { 	
        add(new Arena());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 305);
        setLocationRelativeTo(null);
        setTitle("Zombie Tower Defense");
        setResizable(false);
        setVisible(true);

    }

    public static class StartMenu extends JFrame
    {
      public StartMenu()
      {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 200);	
        setLocationRelativeTo(null); 
        setTitle("StartMenu");
        setResizable(false);
        setVisible(true);
        
      }
    }

    public static void main(String[] args) 
    {
        new ZombieGame();
        //new StartMenu();
    }
}