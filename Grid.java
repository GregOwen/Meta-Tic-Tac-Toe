/*
 * Written By: Gregory Owen
 * Date: 12/26/11
 * Represents a grid for use in Meta Tic Tac Toe:
 *  0 is a blank slot
 *  positive is an X
 *  negative is an O
 *  1 means black
 *  2 means colored (red for X, blue for O)
 */

public class Grid
{
  private int[][] squares;
  private boolean isWon;
  private int numMarks;
  
  public Grid()
  {
    squares = new int[3][3];
    isWon = false;
    numMarks = 0;
  }
  
  public void addMark(int r, int c, boolean isX)
  { 
    numMarks++; //record that a mark has been added
    
    if (isX)
      squares[r][c] = 1;
    else
      squares[r][c] = -1;
  }
  
  /*
   * checks if the most recent move created a win in this grid
   * if the row, column, or diagonal of the last move has a sum
   *  with absolute value of +/- 3, then a win is registered
   */
  public boolean checkWin(int lastR, int lastC)
  {
    if (isWon)
      return false; //you can't win the same grid twice
    
    //first check the row
    int rowSum = squares[lastR][0] + squares[lastR][1] + squares[lastR][2];
    if (rowSum > 2 || rowSum < -2)
    {
      markWon(lastR, 0, lastR, 2);
      return true;
    }
    
    //then check the column
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
  
  public boolean isFull()
  {
    return numMarks == 9;
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
