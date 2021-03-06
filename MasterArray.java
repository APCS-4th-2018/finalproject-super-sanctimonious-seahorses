import javafx.scene.Parent;
/**
 * Master array that represents big board
 *
 * @author (Birla, Chang, Chung ,Liu)
 * @version (1.0)
 */
public class MasterArray
{
    //holds the MiniArrays that house each individual score
    //each cell in bigTicTac represents a big square on the boar
    private MiniArray [][] bigTicTac; 
    
    //holds the board Graphics
    private BoardGraphics graphics;
    
    //the Play class we are using to make the game run
    private Play myGame;
    
    //the object that houses the winner 
    private String myWinner;
    
    /**
     * Constructor for the MasterArray class
     * @param game    the Play class you use to run the whole game
     */
    public MasterArray(Play game)
    {
      bigTicTac = new MiniArray [3][3];
      int num = 0;
      myGame = game;
      myWinner = " ";
      
      for (int row = 0; row < bigTicTac.length; row++)
      {
        for(int col = 0; col < bigTicTac[0].length; col++)
        {
            bigTicTac[row][col] = new MiniArray(myGame);
           
            //gives the square the correct identifier number for easy orginization 
            for(int r2 = 0; r2 < 3; r2++)
                {
                    for(int c2 = 0; c2 < 3; c2++)
                
                        bigTicTac[row][col].getSquare(r2, c2).setInput(9*num 
                            + bigTicTac[row][col].getSquare(r2, c2).getInput());
                    }
            num++;
        }
            
    }
   
        graphics = new BoardGraphics(this);
   }
    
    /**
     * Does the move specified 
     * @param x1     row index of the big square move needs was played 
     * @param y1     col index of the big square move needs was played
     * @param x      row index of the little square move was played 
     * @param y      col index of the little square move was played
     * @param player "X" or "O" depending on who just played the move 
     */
    public void doMove(int x1, int y1, int x, int y, String player)
    {
        if(!bigTicTac[x1][y1].getFinishedSquare().equals(" "))
            throw new IllegalArgumentException();
        bigTicTac[x1][y1].doMove(x, y, player);
    }
    
    /**
     * Checks if the game was won 
     * @return   winner   "X", "O", "D" if a result was made, " " if no result yet 
     */
    public String checkWon()
    {
        //check the row and see if someone won
        String check = checkRow();
        String returner = " ";
        boolean flag = false;
        if(check.equals("X"))
        {
            myWinner = returner = "X";
            flag = true;
        }
        else        
        if(check.equals("O"))
        {
            flag = true;
            returner = myWinner = "O";
        }
        
        //checkCol and see if someone won
        check = checkCol(); 
        if(check.equals("X")) 
        {
            myWinner = returner =  "X";
            flag = true;
        }
        else
            if(check.equals("O"))
            {
            flag = true;
            returner  = myWinner = "O";
        }
            
            
        //check the diagonal and see if someone has won
        check = checkDiagonal();
       
        if(check.equals("X"))
        {
            returner = myWinner = "X";
            flag = true;
        }
        else
            if(check.equals("O"))
            {
              returner = myWinner = "O";  
              flag = true;
            }
               
        //lastly check if a draw is valid
        if(!flag)
        {
        check = checkDraw();
         if(check.equals("X"))
            returner = myWinner = "X";
        else
            if(check.equals("O"))
              returner = myWinner = "O"; 
              else
                if(check.equals("D"))
                 returner = myWinner = "D";
            }
        return returner;
    }
    
    //checks if a row has been won 
    private String checkRow()
        {
         String returner = " "; 
            //traverses all three rows
             for(int i =0; i<3; i++)
        {
            //gets finished square from every miniArray in the row and checks for X or O
            //winner
            
             if ((bigTicTac[i][0].getFinishedSquare() + bigTicTac[i][1].getFinishedSquare() 
             + bigTicTac[i][2].getFinishedSquare()).equals("OOO"))
             {
                returner = "O";
                i = 99;
            }
            else
            {
             if ((bigTicTac[i][0].getFinishedSquare() + bigTicTac[i][1].getFinishedSquare() 
             + bigTicTac[i][2].getFinishedSquare()).equals("XXX"))
             {
                returner = "X";
                i = 99;
            }
          }
        }
        
        return returner;
    }
    
    //checks the column
    private String checkCol()
    {
        String returner = " ";
        
        //traverses each column 
        for(int i =0; i<3; i++)
        {
            //checks the row for the column
             if ((bigTicTac[0][i].getFinishedSquare() + bigTicTac[1][i].getFinishedSquare() 
             + bigTicTac[2][i].getFinishedSquare()).equals("OOO"))
             {
                // O has won 
                returner =  "O";
                i = 99;
            }
                
            
             if ((bigTicTac[0][i].getFinishedSquare() + bigTicTac[1][i].getFinishedSquare() 
             + bigTicTac[2][i].getFinishedSquare()).equals("XXX"))
             {
                 //X has won 
                returner = "X";
                i = 99;
            }
        }
        
        return returner;
    }
    
    //checks the diagonal of the bigTicTac class for a winner
    private String checkDiagonal()
    {
        //get the three miniArray's finished square from top left to bottom right 
        String x = bigTicTac[0][2].getFinishedSquare() + bigTicTac[1][1].getFinishedSquare() + bigTicTac[2][0].getFinishedSquare();
        String returner = " ";
        
        //if all three equal X or O, someone has won
        if(x.equals("XXX"))
            returner =  "X";
        else
            if(x.equals("OOO"))
              returner =  "O";
        
        //get the three miniArray's finished square from top right to bottom left
        x = bigTicTac[2][2].getFinishedSquare() + bigTicTac[1][1].getFinishedSquare() + bigTicTac[0][0].getFinishedSquare();
        
        //check for a win
        if(x.equals("XXX"))
            returner =  "X";
        else
            if(x.equals("OOO"))
                returner =  "O";
        
       return returner;
    }
    
    //checks if the game has been drawn
    private String checkDraw()
    {
      boolean flag = true;
      int totX = 0, totO = 0;
      String returner  = " ";
      
      if (myWinner.equals(" ")) //if no one has won so far
      {
          if(!checkFull())
          {
              flag = false;
              
            }
        }
        
      if(flag)
      {
      for(int x = 0; x< 3; x++)
        {
          for(int y = 0; y<3; y++)
          {
             String temp = this.getMiniArray(x,y).getFinishedSquare();
             if (temp.equals("X"))
                totX++;
             if(temp.equals("O"))
                totO++;  
            }
        }
        
        if(totX>totO)
            returner = "X";
        else
            if(totO>totX)
                returner = "O";
                else
                    returner = "D";
       }
       
       return returner;
    
      
    }
    
    //checks if every single space in array is taken
    //if every big square is won or drawn
    private boolean checkFull()
    {
      boolean flag = true;  
      for(int r = 0; r<3; r++)
      {
          for(int c = 0; c<3; c++)
          {
              if(bigTicTac[r][c].getFinishedSquare().equals(" ")) 
              //if a big square still has a result pending a draw is not possible
              {
                  flag = false;                   
                  //exit the for loop
                  r = 99;
                  c = 99;
              }
          }
      }  
      return flag;
    }
    
    
    
    /**
     * Get the MiniArray from the index specified
     * @param x1  index of the row the MiniArray you want is located
     * @param y1  index of the column the MiniArray you want is located
     * @return miniarray    the miniArray requested 
     */
    public MiniArray getMiniArray(int x1, int y1)
    {
        return bigTicTac[x1][y1];
    }
    
    /**
     * This converts an integer from 1-81 to the index location for the move that was played
     * Housed in an integer array of 4 elements
     * array[0] holds row index of the big board the move was played
     * array[1] holds col index of the big board the move was played
     * answer[2] holds row index of the small board the move was played
     * answer[3] holds col index of the small board the move was played
     * 
     * @param input    the integer from 1-81 that the user played in 
     *                 (the location of the square the user clicked)
     * @return answer  int array that holds the values specified above
     */
    public int[] inputConvert(int input)
    {
        int[] answer = new int[4];
        
        //switch case to get the first two locations of the array
        switch(input)
        {
          case 1:
          case 2:
          case 3:
          case 4: 
          case 5: 
          case 6: 
          case 7:
          case 8:
          case 9:
            //square 1-9 are housed in index 0,0 
            answer[0] = 0; 
            answer [1]= 0;
            break;
          case 10:
          case 11:
          case 12:
          case 13:
          case 14: 
          case 15:
          case 16:
          case 17:
          case 18:
           //square 10-18 are housed ar index 0,1 
            answer[0] = 0;
            answer [1] = 1;
            break;
          case 19:
          case 20:
          case 21:
          case 22:
          case 23:
          case 24: 
          case 25:
          case 26:
          case 27:
            //square 19-27 are housed at index 0,2
            answer[0] = 0;
            answer[1] = 2;
            break;
          case 28:
          case 29:      
          case 30:
          case 31:
          case 32:
          case 33:
          case 34: 
          case 35:
          case 36:
            //square 28-36 are housed at index 1,0
            answer[0] = 1;
            answer[1] = 0;
            break;
          case 37:
          case 38:
          case 39:
          case 40:  
          case 41:
          case 42:
          case 43:
          case 44: 
          case 45:
            //square 37-45 are housed at index 1,1
            answer[0] = 1;
            answer[1] = 1;
            break;
          case 46: 
          case 47:
          case 48:
          case 49:  
          case 50:  
          case 51:
          case 52:
          case 53:
          case 54: 
            //square 46-54 housed at index 1,2 
            answer[0] = 1;
            answer[1] = 2;
            break;
          case 55: 
          case 56: 
          case 57:
          case 58:
          case 59:
          case 60:
          case 61:
          case 62:
          case 63:
            //square 55-63 housed at index 2,0 
            answer[0] = 2;
            answer[1] = 0;
            break;
          case 64: 
          case 65: 
          case 66: 
          case 67:
          case 68:
          case 69:
          case 70:
          case 71:
          case 72:
            //square 64,72 housed at index 2,1
            answer[0] = 2;
            answer[1] = 1;
            break;
          case 73:
          case 74: 
          case 75: 
          case 76: 
          case 77:
          case 78:
          case 79:
          case 80:
          case 81:
            //square 73,81 housed at index 2,2 
            answer[0] = 2;
            answer[1] = 2;
            break;
          default: 
            throw new IllegalArgumentException();
        }
        
        //mod done so we can get the location of the square inside the big sqaure
        input %=9;
        
        switch(input)
        {
           case 1:
               //this means its in the top right
               answer[2] = 0;
               answer[3] = 0;
               break;
           case 2:
                //this means its in the top middle
               answer[2] = 0;
               answer[3] =1;
               break;
           case 3:
                //this means its in the top right
              answer[2] = 0;
              answer[3] = 2;
              break;
           case 4:
                //this means its in the middle left 
              answer[2] = 1;
              answer[3] = 0;
              break;
           case 5:
                //this means its in the middle middle 
              answer[2] = 1;
              answer [3] = 1;
              break;
           case 6:
               //this means the squareis in the middle right
              answer[2] = 1;
              answer [3] = 2;
              break;
           case 7: 
                //this means the sqaure is in the bottom left
             answer[2] = 2;
             answer [3] = 0;
             break;
           case 8:
                //this means the sqaure is in the bottom middle 
             answer[2] = 2;
             answer [3] = 1;
             break;
           case 0:
            //this means the square is in the bottom right
            answer[2] = 2;
            answer [3] = 2;
            break;
           default:
            throw new IllegalArgumentException();
        }
        return answer;
    }
    
    /**
     * Returns the sqaure object specified by the user
     * @param row   the row index for the square you want 
     * @param col   the col index for the sqaure you want 
     * @return sqaurey    the sqaure object 
     */
    public MiniArray getSquare(int row, int col)
    {
        return bigTicTac[row][col];
    }
    
    /**
     * Returns the graphics class associated with the game 
     * @return graphics   the graphics class associated with the game 
     */
    public Parent getGraphics()
    {
        return graphics;
    }
    
    /**
     * Returns the myWinner string
     * @return myWinner     the winner of the game 
     */
    public String getMyWinner(){
        return myWinner;
    }
}
