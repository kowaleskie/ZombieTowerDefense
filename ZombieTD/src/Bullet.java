import java.lang.Thread;
import java.util.*;
import javax.swing.*;
import java.awt.Point;
import java.awt.Graphics;

class Bullet implements Runnable
{
  ArrayList<Point> path;
  Thread runner;
  ImageIcon currentImage;
  int current = 0;
  int m = 0;
  int x,y;
  Graphics g;
  JPanel h;
  Point currentPoint;

  public Bullet(ArrayList<Point> path)
  {
    this.path = path;
    this.h = h;
    currentPoint = new Point();

    runner = new Thread(this,"star");
    runner.start();
  }

  public Point getCurrentPoint()
  {
    return currentPoint;
  }

  public void run()
  {
    if(path.size()!= 0)
    {
    while(current<=path.size()-1)
    {
    int max = path.size()-1;
    currentPoint = path.get(current);
    current++;

     try
    {
      Thread.sleep(25);
    }
    catch(InterruptedException e){}
    }
    }
  }
}