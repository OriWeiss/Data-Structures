//Author: Ori Weiss
//1/30/2018
import java.util.Random;
public abstract class Money {
    private int denomination;
    private boolean heads;
    Random rand = new Random();
    public Money(int numberOfDenominations) {

        this.denomination = rand.nextInt(numberOfDenominations);

//        int coinFlip = rand.nextInt(2);
//        if(coinFlip == 0){
            this.heads = false;
//        }
//        if(coinFlip == 1){
//            this.heads = true;
//        }



        //TODO
    }

    public int getDenomination(){
        return this.denomination;
        //TODO-DONE
    }
    public abstract double getValue();
    public void toss() {
        Random rand = new Random();
        int toss = rand.nextInt(2);
        if (toss == 0) {
            this.heads = false;
        }
        else {
            this.heads = true;
        }
    }
    public boolean isHeads(){
        boolean result;
        if(this.heads == true){
            result = true;
        }
        else{
            result = false;
        }
        return result;
        //TODO-DONE
    }
    public abstract String toString();

}
