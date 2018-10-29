package Lab11;

/**
 * @author Ori Weiss
 * @version 4/10/2018
 */

import java.util.*;

public class BingoGame
{
    public final static int NUMBER_OF_CHIPS = 75;
    private int numberOfPlayers;
    private ArrayList<BingoChip> bingoDrum;
    private Player[] players;

    public BingoGame(int numOfPlayers)
    {
        setNumOfPlayers(numOfPlayers);
        createBingoDrum();
        createPlayers();
        // TODO Project 2.4
    }

    private void createBingoDrum()
    {
        this.bingoDrum = new ArrayList<BingoChip>();
        Random randomRandy = new Random();
        for (int i = 0; i < this.NUMBER_OF_CHIPS; i++) {
            int randomRon = randomRandy.nextInt(75) +1; //numbers one to 75 inclusive
            this.bingoDrum.add(new BingoChip(randomRon));
        }

        // TODO Project 2.4
    }

    private void createPlayers()
    {
        this.players = new Player[this.numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player();
        }
        // TODO Project 2.4
    }

    private void setNumOfPlayers(int numOfPlayers)
    {
        this.numberOfPlayers = numOfPlayers;
        // TODO Project 2.4
    }

    public int getNumberOfPulledChips()
    {
        return this.NUMBER_OF_CHIPS - this.bingoDrum.size();
        // TODO Project 2.4
        //return 0; // This is a stub
    }

    public BingoChip pullChip()
    {
        Random randomRandy = new Random();
        int randomRon = randomRandy.nextInt(this.bingoDrum.size());
        return this.bingoDrum.remove(randomRon);
        // TODO Project 2.4
        //return null; // This is a stub
    }

    public void play()
    {
        boolean winner = false;
        for (int i = 0; i < this.players.length; i++) {
            System.out.println("---> Creating bingo card for Player " + (i+1));
            this.players[i].printCard();
        }
        while(!winner) {
            BingoChip pulledChip = pullChip();
            System.out.println(pulledChip);
            for (int i = 0; i < this.players.length; i++) {
                this.players[i].checkCard(pulledChip);
                this.players[i].printCard();
            }
            for (int i = 0; !winner &&i < this.players.length; i++) {
                if(this.players[i].isWinner()){
                    System.out.println("!!! Player " + i+1 + " says BINGO !!!");
                    winner = true;
                }
            }
        }
        System.out.println(getNumberOfPulledChips() + " chips were called.");
        // TODO Project 2.4
    }
}