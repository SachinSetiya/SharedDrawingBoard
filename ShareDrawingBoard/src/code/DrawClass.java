package code;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawClass
{

  private JFrame frame;

  /**
   * Launch the application.
   */
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          DrawClass window= new DrawClass();
          window.frame.setVisible(true);
        } catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public DrawClass()
  {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize()
  {
    frame= new JFrame();
    frame.getContentPane().addMouseMotionListener(new MouseMotionAdapter()
    {
      @Override
      public void mouseMoved(MouseEvent arg0)
      {

      }
    });
    frame.setBounds(100, 100, 838, 491);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JPanel panel= new DrawPanel();
    panel.setBounds(202, 60, 546, 362);
    frame.getContentPane().add(panel);
  }


}

class DrawPanel extends JPanel
{
  /**
   * 
   */
  int prev_x= 0, prev_y= 0;
  private static final long serialVersionUID= 1L;

  public DrawPanel()
  {
    setBackground(Color.ORANGE);
    InternalMouseHandler handler= new InternalMouseHandler();
    addMouseListener(handler);
    addMouseMotionListener(handler);
  }

  class InternalMouseHandler extends MouseAdapter
  {
    @Override
    public void mousePressed(MouseEvent e)
    {
      // TODO Auto-generated method stub
      super.mouseClicked(e);
      prev_x= e.getX();
      prev_y= e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
      // TODO Auto-generated method stub
      super.mouseDragged(e);
      int x= e.getX(), y= e.getY();
      getGraphics().drawLine(prev_x, prev_y, x, y);
      prev_x= x;
      prev_y= y;
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
      // TODO Auto-generated method stub
      super.mouseMoved(e);
      System.out.println("Sachin  " + e.getX() + "   " + e.getY());
    }

  }

}
