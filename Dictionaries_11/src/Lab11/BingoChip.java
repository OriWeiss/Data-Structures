package Lab11;

/**
 * @author Ori Weiss
 * @version 4/10/2018
 */

public class BingoChip
{
    private char letter;
    private int number;

    public BingoChip(int number)
    {
        setNumber(number);
        setLetter();
    }

    private void setNumber(int number)
    {
        //passed a number between 1 and 75
        this.number = number;

       // TODO-DONE Project 2.1
    }

    private void setLetter()
    {
        this.letter = BingoCard.BINGO_KEYS.charAt((this.number - 1)/BingoCard.MAX_VALUES_PER_LETTER);

        // TODO Project 2.1
    }

    public int getNumber()
    {
        // TODO-DONE Project 2.1
        return this.number;
    }

    public char getLetter()
    {
        // TODO-DONE Project 2.1
        //Project 2.1
        return this.letter;
    }

    public String toString()
    {
        // TODO-DONE Project 2.1
        return "---> Calling: " + this.letter + " " + this.number;
    }
}