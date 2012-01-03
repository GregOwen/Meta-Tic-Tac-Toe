/*
 * Written By: Gregory Owen
 * Date: 12/26/11
 * Represents a grid for use in Meta Tic Tac Toe
 * X is 1, O is -1, blank is 0
 */

public class Grid
{
  private int[][] squares;
  private boolean isWon;
  
  public Grid()
  {
    squares = new int[3][3];
    isWon = false;
  }
  
  public void addMark(int r, int c, boolean isX)
  {
    if (r > 2 || r < 0 || c > 2 || c < 0)
    {
      System.out.println("ERROR at addX line 18");
      return;
    }
    
    if (isX)
      squares[r][c] = 1;
    else
      squares[r][c] = -1;
  }
  
  //checks if the most recent move created a win in this grid
  public boolean checkWin(int lastR, int lastC)
  {
    if (isWon)
      return false;
    
    int rowSum = squares[lastR][0] + squares[lastR][1] + squares[lastR][2];
    if (rowSum > 2 || rowSum < -2)
    {
      markWon(lastR, 0, lastR, 2);
      return true;
    }
    
    int colSum = squares[0][lastC] + squares[1][lastC] + squares[2][lastC];
    if (colSum > 2 || colSum < -2)
    {
      markWon(0, lastC, 2, lastC);
      return true;
    }
    
    //corners
    if (lastC%2 == 0 && lastR%2 == 0)
    {
      int diagSum = squares[lastR][lastC] + squares[2 - lastR][2 - lastC] + squares[1][1];
      if (diagSum > 2 || diagSum < -2)
      {
        markWon(lastR, lastC, 2 - lastR, 2 - lastC);
        return true;
      }
    }
    
    //center
    if (lastR == 1 && lastC == 1)
    {
      int sum1 = squares[0][0] + squares[1][1] + squares[2][2];
      if (sum1 > 2 || sum1 < -2)
      {
        markWon(0, 0, 2, 2);
        return true;
      }
      
      int sum2 = squares[0][2] + squares[1][1] + squares[2][0];
      if (sum2 > 2 || sum2 < -2)
      {
        markWon(0, 2, 2, 0);
        return true;
      }
    }
    
    return false;
  }
  
  
  public int get(int r, int c)
  {
    return squares[r][c];
  }
  
  //doubles the value of each mark in the line between the given endpoints
  // indicating that they should be written in red
  public void markWon(int startR, int startC, int endR, int endC)
  {
    squares[startR][startC] *= 2;
    squares[endR][endC] *= 2;
    squares[(startR + endR)/2][(startC + endC)/2] *= 2;
    
    isWon = true;
  }
  
  public void print()
  {
    for (int r = 0; r < 3; r++)
    {
      for (int c = 0; c < 3; c++)
        System.out.print(squares[r][c]);
      System.out.println("");
    }
  }
}
