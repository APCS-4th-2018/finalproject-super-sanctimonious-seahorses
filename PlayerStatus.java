
/**
 * Write a description of class PlayerStatus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PlayerStatus
{
    private boolean player = true;
    public PlayerStatus()
    {
        
    }
    
    public boolean whichPlayer()
    {
        player = !player;
        return player;
        //x's turn: player == true
        //0's turn: player == false
    }
}
