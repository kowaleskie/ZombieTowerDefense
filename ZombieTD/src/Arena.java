import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.awt.event.ActionListener;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JProgressBar;

public class Arena extends JPanel implements ActionListener,MouseListener,MouseMotionListener
{

    Image star;
    Timer timer;
    ArrayList<Point> path;
    ArrayList<Zombie> zombies;
    int x, y, i, q, insideCloud;
    int healthValue = 100;
    public int scoreZ=0;
    boolean zombs=true;
    Cloud cloud;
    Bullet bul;
    Rectangle linkBounds,bulletBounds,cloudBounds,towerBounds;
    Point start;
    ImageIcon tower;
    JProgressBar health;
    JLabel score;


    public Arena() 
    {
    	score = new JLabel("Score: "+scoreZ);
    	score.setForeground(Color.white);
		add(score);
		
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        path = new ArrayList<Point>();
        bul = new Bullet(path);
        bulletBounds = new Rectangle();
        cloudBounds = new Rectangle();
        zombies = new ArrayList<Zombie>();
        zombies.add(new Zombie());
        start = new Point(100,100);
        tower = new ImageIcon(this.getClass().getResource("tower2.png"));
        towerBounds = new Rectangle();
        insideCloud = 0;

        health = new JProgressBar();
        health.setValue(healthValue);
        health.setString("Health");
        health.setStringPainted(true);
        add(health);

        cloud = new Cloud();
        i = 0;
        q = 0;

        setDoubleBuffered(true);

        x = y = 100;
        timer = new Timer(25, this);
        Timer zombieSpawn = new Timer(100, this);
        zombieSpawn.setActionCommand("Spawn");
        timer.setActionCommand("Timer");
        timer.start();
        zombieSpawn.start();
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(tower.getImage(),600,50 ,this);
        towerBounds.setBounds(625,200,100,100);
        g2d.drawImage(cloud.getCurrentImage().getImage(), 515, -38, this);
        cloudBounds.setBounds(600,17,45,75);
        for(int z=0;z<zombies.size();z++)
        {
          g2d.drawImage(zombies.get(z).getCurrentImage().getImage(), zombies.get(z).x, zombies.get(z).y, this);
        }
        if(bul.path.size()==0 || bul.path.size() == 1)
          q = 0;
        if(q == 1)
        {
          g2d.fillOval((int)bul.getCurrentPoint().getX(),(int)bul.getCurrentPoint().getY(),15,15);
          bulletBounds.setBounds((int)bul.getCurrentPoint().getX(),(int)bul.getCurrentPoint().getY(),15,15);
          if(bul.current==bul.path.size()-1)
          {
            q = 0;
            bulletBounds = new Rectangle();
          }
        }
        repaint();
        g.dispose();
    }

    public void paintComponent(Graphics g)
    {
      super.paintComponent(g);

      ImageIcon background = new ImageIcon(this.getClass().getResource("forest.jpg"));

      g.drawImage(background.getImage(),0,0,null);
    }

    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e)
    {
      bul = new Bullet(path);
      q = 1;
      if(insideCloud == 1)
        cloud.i=1;
      insideCloud = 0;
    }
    public void mousePressed(MouseEvent e)
    {
      if(cloudBounds.contains(e.getPoint()))
        insideCloud = 1;
      path.clear();
    }
    public void mouseClicked(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
    public void mouseDragged(MouseEvent e)
    {
      if(insideCloud == 1)
        path.add(new Point(e.getX(),e.getY()));
    }

    public void actionPerformed(ActionEvent e) 
    {
      if(e.getActionCommand().equals("Timer"))
      {
        for(int z=0; z<zombies.size(); z++)
         {
          zombies.get(z).x = zombies.get(z).x+1;
          if(zombies.size()!=0 && zombies.get(z).bounds.intersects(bulletBounds))
          {
            q = 0;
            zombies.remove(z);
            bulletBounds = new Rectangle();
            scoreZ+=100;
            score.setText("Score: " + scoreZ);
            playSound();
            
          }
          try //Putting try to fix seemingly random out of bounds issue
          {
          if(zombies.size()!=0 && zombies.get(z).bounds.intersects(towerBounds))
          {
            zombies.remove(z);
            healthValue-=10;
            health.setValue(healthValue);
            
            if (healthValue==0)
            {
            	zombies.removeAll(zombies);
            	zombs=false;
            	score.setText("You have died. Your final score was " + scoreZ);
            	health.show(false);
            }
          }
          }
          catch (Exception OFB){}          
          }
        }

      if(e.getActionCommand().equals("Spawn"))
      {
        int ran = 1 + (int)(Math.random()*10);

        if(ran == 1&&zombs)
          zombies.add(new Zombie());
      }
    }
    public void playSound()
    {
    	try
    	{
    	Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(new File("zs.wav")));
        clip.start();
    	}
    	catch (Exception e)
    	{
    		System.out.println(e);    		
    	}

    }
}