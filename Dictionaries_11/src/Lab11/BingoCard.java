package Lab11;

/**
 * @author Ori Weiss
 * @version 4/10/2018
 */

import java.util.*;

public class BingoCard {
    private HashMap<Character, TreeSet<Integer>> card;
    public final static String BINGO_KEYS = "BINGO";
    public final static int MAX_VALUES_PER_LETTER = 15;
    public final static int NUMBERS_PER_LETTER = 5;

    public BingoCard() {
        this.card = new HashMap<>();
        Random randomRandy = new Random();
        for (int i = 0; i < this.BINGO_KEYS.length(); i++) {
            TreeSet<Integer> set = new TreeSet<Integer>();
            while (set.size() != this.NUMBERS_PER_LETTER) {
                int newNum = (randomRandy.nextInt(this.MAX_VALUES_PER_LETTER) + 1) + (i * this.MAX_VALUES_PER_LETTER);
                set.add(newNum);
            }
            this.card.put(this.BINGO_KEYS.charAt(i), set);
        }

        // TODO-DONE Project 2.2
    }

    public boolean hasNumber(BingoChip chip) {
        boolean result = false;
        TreeSet<Integer> chipTree = this.card.get(chip.getLetter());
        result = chipTree.contains(chip.getNumber());
        if(result){
            TreeSet<Integer> tree = this.card.get(chip.getLetter());
            tree.add(0);
        }
        return result;

        // TODO-DONE Project 2.2
        //return false;
    }

    public String toString() {
        // TODO Project 2.2
        StringBuffer newStr = new StringBuffer();
        for (int i = 0; i < this.BINGO_KEYS.length(); i++) {
            TreeSet<Integer> tree = this.card.get(this.BINGO_KEYS.charAt(i));
            newStr.append(String.format("%s ",this.BINGO_KEYS.charAt(i)));
            tree.forEach(name -> newStr.append(String.format("%3d",name )));
            newStr.append("\n");
        }



        // utilize StringBuffer and String.format
        // utilize forEach lambda construct to process a row
        return newStr.toString();

    }

    public boolean equals(Object o) {
        {
            boolean same = true;
            if (this == o) {
                same = true;
            } else if (o == null || getClass() != o.getClass()) {
                same = false;
            } else {
                BingoCard other = (BingoCard) o;
                if (!this.card.equals(other.card)) {
                    same = false;
                }
            }
            return same;
            // TODO-DONE Project 2.2
//        return false;
        }
    }
}