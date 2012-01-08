/*
 * Written By: Gregory Owen
 * Date: 12/26/11 using StdDraw
 * Provides the GUI for a meta tictactoe board
 */

import java.awt.Color;

public class TTTGui
{ 
  /*
   * DEPRECATED: Old method for indicating the winner of a grid
   *    (replaced by shadeBackground)
  public static void colorBorder(int row, int col, boolean isX)
  {
    StdDraw.setPenRadius(0.002);
    
    if (isX)
      StdDraw.setPenColor(StdDraw.RED);
    else
      StdDraw.setPenColor(StdDraw.BLUE);
    
    StdDraw.rectangle(3*col + 1, -3*row - 1, 1.5, 1.5);
    
    StdDraw.setPenRadius();
    
    for (int i = 0; i < 2; i++)
    {
      StdDraw.line(3*col + i + 0.5, -3*row + 0.5, 3*col + i + 0.5, -3*row - 2.5);
      StdDraw.line(3*col - 0.5, -3*row - i - 0.5, 3*col + 2.5, -3*row - i - 0.5);
    }
  }
  */
  
	public static void drawBoard(Grid[][] fullGrid, Grid metaGrid)
	{
	  StdDraw.setXscale(-0.5, 8.5);
	  StdDraw.setYscale(-8.5, 0.5);
	  
	  StdDraw.setPenColor(StdDraw.WHITE);
	  StdDraw.filledRectangle(4, -4, 4.5, 4.5);
	  
	  for (int r = 0; r < 3; r++)
        for (int c = 0; c < 3; c++)
        {
          if (metaGrid.get(r, c) == 1)
            shadeBackground(r, c, true);
          else if (metaGrid.get(r, c) == -1)
            shadeBackground(r, c, false);
        }
	  
	  StdDraw.setPenRadius();
      StdDraw.setPenColor(StdDraw.BLACK);
	  
      //draw grid lines
	  for (int i = 0; i < 10; i++)
	  {
	    if (i%3 == 0)
	      StdDraw.setPenRadius(0.005); //every third line is thicker
	    else
	      StdDraw.setPenRadius();
	    
	    StdDraw.line(i - 0.5, 0.5, i - 0.5, -8.5);
	    StdDraw.line(-0.5, -i + 0.5, 8.5, -i + 0.5);
	  }
	  
	  refreshMarks(fullGrid);
	  
	  /*
	  for (int bigR = 0; bigR < 3; bigR++)
	    for (int bigC = 0; bigC < 3; bigC++)
	      for (int smallR = 0; smallR < 3; smallR++)
	        for (int smallC = 0; smallC < 3; smallC++)
	          switch (fullGrid[bigR][bigC].get(smallR, smallC))
	          {
	            case 2:
	              drawMark(bigR, bigC, smallR, smallC, true, StdDraw.RED);
	              break;
	            case 1:
	              drawMark(bigR, bigC, smallR, smallC, true, StdDraw.BLACK);
	              break;
	            case -1:
	              drawMark(bigR, bigC, smallR, smallC, false, StdDraw.BLACK);
	              break;
	            case -2:
	              drawMark(bigR, bigC, smallR, smallC, false, StdDraw.BLUE);
	              break;
	          }
	  */
	}
	
	public static void drawMark(int bigR, int bigC, int smallR, int smallC, boolean isX, Color col)
    {
      double x = 0.0;
      double y = 0.0;
      
      x += 3*bigC;
      y += -3*bigR;
      
      x += smallC;
      y -= smallR;
      
      if (isX)
        drawX(x, y, col);
      else
        drawO(x, y, col);
    }
	
	public static void drawMark(int bigR, int bigC, int smallR, int smallC, boolean isX, boolean isWin)
    {
      double x = 0.0;
      double y = 0.0;
      
      x += 3*bigC;
      y += -3*bigR;
      
      x += smallC;
      y -= smallR;
      
      Color col = StdDraw.BLACK;
      
      if (isWin)
      {
        if (isX)
          col = StdDraw.RED;
        else
          col = StdDraw.BLUE;
      }
      
      if (isX)
        drawX(x, y, col);
      else
        drawO(x, y, col);
    }
	
	public static void drawO(double x, double y, Color col)
    {
       StdDraw.setPenRadius(0.005);
       StdDraw.setPenColor(col);
       
       StdDraw.circle(x, y, 0.4);
    }
	
	public static void drawX(double x, double y, Color col)
	{
	  StdDraw.setPenRadius(0.005);
	  StdDraw.setPenColor(col);
	  
	  StdDraw.line(x - 0.4, y - 0.4, x + 0.4, y + 0.4);
	  StdDraw.line(x - 0.4, y + 0.4, x + 0.4, y - 0.4);
	}
	
	public static void refreshMarks(Grid[][] fullGrid)
	{
	  for (int bigR = 0; bigR < 3; bigR++)
        for (int bigC = 0; bigC < 3; bigC++)
          for (int smallR = 0; smallR < 3; smallR++)
            for (int smallC = 0; smallC < 3; smallC++)
              switch (fullGrid[bigR][bigC].get(smallR, smallC))
              {
                case 2:
                  drawMark(bigR, bigC, smallR, smallC, true, StdDraw.RED);
                  break;
                case 1:
                  drawMark(bigR, bigC, smallR, smallC, true, StdDraw.BLACK);
                  break;
                case -1:
                  drawMark(bigR, bigC, smallR, smallC, false, StdDraw.BLACK);
                  break;
                case -2:
                  drawMark(bigR, bigC, smallR, smallC, false, StdDraw.BLUE);
                  break;
              }
	}
	
	//provides semi-transparent background shading for grids that have been won
	public static void shadeBackground(int row, int col, boolean isX)
    {
      StdDraw.setPenRadius();
      
      if (isX)
        StdDraw.setPenColor(new Color(1, 0, 0, (float) 0.2));
      else
        StdDraw.setPenColor(new Color(0, 0, 1, (float) 0.2));
      
      StdDraw.filledRectangle(3*col + 1, -3*row - 1, 1.5, 1.5);
    }
}
