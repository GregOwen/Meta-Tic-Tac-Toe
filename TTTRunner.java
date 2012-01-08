/*
 * Written By: Gregory Owen
 * Date: 12/26/11
 * Runs Meta Tic Tac Toe
 */

public class TTTRunner
{
  private Grid[][] bigGrid;
  private Grid gameGrid;
  private boolean isWon;
  private int turn;
  
  private int lastR;
  private int lastC;
  
  public TTTRunner()
  {
    turn = 1;
    isWon = false;
    bigGrid = new Grid[3][3];
    
    for (int r = 0; r < 3; r++)
      for (int c = 0; c < 3; c++)
        bigGrid[r][c] = new Grid();
    
    gameGrid = new Grid();
    
    TTTGui.drawBoard(bigGrid, gameGrid);
  }
  
  /*
   * Records position of mouse clicks,
   *  makes mark if appropriate,
   *  checks win conditions for small and large grids
   */
  public void clickListener(boolean isX)
  {
    while (true)
    {
      if (StdDraw.mousePressed())
      {
        //x and y will be on the interval [0,8]
        int x = round(StdDraw.mouseX());
        int y = round(StdDraw.mouseY());
        
        int bigR = coordToBig(y, false);
        int bigC = coordToBig(x, true);
        int smallR = coordToSmall(y, false);
        int smallC = coordToSmall(x, true);
        
        /*
         * Marks can only be added if the following criteria are met:
         *  1. The square to be marked is empty AND
         *      a. It is the first turn OR
         *      b. The new mark is placed in the correct square of the meta-grid OR
         *      c. The correct grid is full
         */
        if (bigGrid[bigR][bigC].get(smallR, smallC) == 0 
            && (turn == 1 || (bigR == lastR && bigC == lastC) || bigGrid[lastR][lastC].isFull()))
        {
          bigGrid[bigR][bigC].addMark(smallR, smallC, isX);
          TTTGui.refreshMarks(bigGrid);
          
          lastR = smallR;
          lastC = smallC;
          
          if (bigGrid[bigR][bigC].checkWin(smallR, smallC))
          {
            gameGrid.addMark(bigR, bigC, isX);
            TTTGui.shadeBackground(bigR, bigC, isX);
            
            if (gameGrid.checkWin(bigR, bigC))
            {
              isWon = true;
              System.out.println("The game is over.");
              if (isX)
                System.out.print("X");
              else
                System.out.print("O");
              
              System.out.println(" is the winner!");
            }
          }
            
          TTTGui.refreshMarks(bigGrid);
          
          return;
        }
      }
    }
  }
  
  //converts an x or y mouse coordinate into an index in bigGrid
  // y-values must be inverted becuase array indices are positive
  // but y-coordinates are negative
  public static int coordToBig(int coord, boolean isX)
  {
    if (!isX)
      coord = -coord;
    
    if (coord < 0)
      coord = 0;
    if (coord > 8)
      coord = 8;
    
    return coord/3;
  }
  
  //converts an x or y mouse coordinate into an index in a small Grid
  //see note for coordToBig
  public static int coordToSmall(int coord, boolean isX)
  {
    if (!isX)
      coord = -coord;
    
    if (coord < 0)
      coord = 0;
    if (coord > 8)
      coord = 8;
    
    return coord%3;
  }
  
  //returns the int value closest to the value of dub
  public static int round(double dub)
  {
    if(dub < 0)
      return (int) (dub - 0.5);
    else
      return (int) (dub + 0.5);
  }
  
  public void takeTurn()
  {
    boolean xTurn = (turn%2 == 1);  //is it X's turn?
    
    clickListener(xTurn);
    
    turn++;
  }
  
  public static void main(String[] args)
  {
    TTTRunner uBolt = new TTTRunner();
    
    while (!uBolt.isWon)
    {
      uBolt.takeTurn();
    }
  }
}
