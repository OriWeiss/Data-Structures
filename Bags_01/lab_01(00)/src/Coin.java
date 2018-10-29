//Author: Ori Weiss
//1/30/2018
public class Coin extends Money {
    private static int [] DENOMINATION_VALUE = {1, 5, 10, 25, 50 } ;
    private static String [] DENOMINATION_NAME = {"PENNY", "NICKEL", "DIME", "QUARTER", "HALF_DOLLAR"};
    private final static int NUMBER_OF_DENOMINATIONS = 5;

    public Coin(){
        super(NUMBER_OF_DENOMINATIONS);
        //TODO-DONE
    }

    public double getValue(){
        int denomination = super.getDenomination();
        int largeValue = DENOMINATION_VALUE[denomination];
        double value = -1;
        switch(largeValue){
            case 1:
                value = 0.01;
                break;
            case 5:
                value = 0.05;
                break;
            case 10:
                value = 0.10;
                break;
            case 25:
                value = 0.25;
                break;
            case 50:
                value = 0.50;
                break;
        }
        return value;
        //TODO-DONE
    }
    public String toString(){
        int denomination = super.getDenomination();
        return DENOMINATION_NAME[denomination] + " landed " + ((isHeads())? "Heads":"Tails");
        //TODO-DONE
    }
}
