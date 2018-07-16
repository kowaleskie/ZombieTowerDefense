import java.lang.Thread;
import java.util.*;
import javax.swing.*;
import java.awt.*;

class Zombie implements Runnable
{
  ArrayList<ImageIcon> images;
  Thread runner;
  ImageIcon currentImage;
  int current = 0;
  int m = 0;
  Rectangle bounds;
  int x,y;

  public Zombie()
  {
    images = new ArrayList<ImageIcon>();

    String name = "Zombie";

    for(int i=0;i<=2;i++)
    {
      images.add(new ImageIcon(this.getClass().getResource("/Zombie/"+name+String.valueOf(i)+".png")));
    }

    currentImage = images.get(0);
    x=0;
    y=230;
    bounds = new Rectangle();
    bounds.setBounds(0,250,33,45);

    runner = new Thread(this,"Zombie");
    runner.start();
  }

  public ImageIcon getCurrentImage()
  {
    return currentImage;
  }

  public void run()
  {
    while(m<100)
    {
    int max = images.size();

    bounds.setBounds(x,y,33,45);

    currentImage = images.get(current);

    if(current == max-1)
      current = 0;
    else
      current++;
     try
    {
      Thread.sleep(100);
    }
    catch(InterruptedException e){}
    }
  }
}