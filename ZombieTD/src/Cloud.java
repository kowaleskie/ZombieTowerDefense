import java.lang.Thread;
import java.util.*;
import javax.swing.*;

class Cloud implements Runnable
{
  ArrayList<ImageIcon> images,attack;
  Thread runner;
  ImageIcon currentImage;
  int current = 0;
  int m = 0;
  int i;

  public Cloud()
  {
    images = new ArrayList<ImageIcon>();
    String name = "Cloud";
    i = 0;

    for(int i=19;i<=25;i++)
    {
      images.add(new ImageIcon(this.getClass().getResource("/Cloud/"+name+String.valueOf(i)+".png")));
    }

    currentImage = images.get(0);

    runner = new Thread(this,"Cloud");
    runner.start();
  }

  public Cloud(ArrayList<ImageIcon> images)
  {
    currentImage = images.get(0);
    runner = new Thread(this,"Cloud");
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
      if(i==0)
        current=0;

    int max = images.size();

    currentImage = images.get(current);

    if(current == max-1)
    {
      current = 0;
      i=0;
    }
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