package Lab11;
/**
 * @author Ori Weiss
 * @version 4/10/2018
 */
import sun.reflect.generics.tree.Tree;

import java.util.TreeSet;

public class Player
{
    private TreeSet<Character> bingoChars;
    private BingoCard bingoCard;

    public Player()
    {
        this.bingoCard = new BingoCard();
        this.bingoChars = new TreeSet<Character>();
        // TODO 2.3
    }

    public boolean isWinner()
    {
        return(this.bingoChars.size() ==  BingoCard.BINGO_KEYS.length());
    	// TODO 2.3
//        return false;
    }

    public void checkCard(BingoChip chip)
    {
        if(this.bingoCard.hasNumber(chip)){
            this.bingoChars.add(chip.getLetter());
        }
        // TODO 2.3
    }

    public void printCard()
    {
        System.out.println(bingoCard);
        // TODO 2.3
    }

}
