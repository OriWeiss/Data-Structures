//Author: Ori Weiss
//1/30/2018
public class Bill extends Money {
    private static int [] DENOMINATION_VALUE = {1,2,5,10,20,50,100};
    private static String [] DENOMINATION_NAME = {"WASHINGTON", "JEFFERSON", "LINCOLN", "HAMILTON", "JACKSON", "GRANT", "FRANKLIN" };
    private final static int NUMBER_OF_DENOMINATIONS = 7;

    public Bill(){
        super(NUMBER_OF_DENOMINATIONS);
        //TODO-DONE
    }

    public double getValue(){
        int denomination = super.getDenomination();
        int largeValue = DENOMINATION_VALUE[denomination];
        double value = 0;
        switch(largeValue){
            case 1:
                value = 1.00;
                break;
            case 2:
                value =2.00;
                break;
            case 5:
                value = 5.00;
                break;
            case 10:
                value = 10.00;
                break;
            case 20:
                value = 20.00;
                break;
            case 50:
                value = 50.00;
                break;
            case 100:
                value = 100.00;
                break;
        }
        return value;
        //TODO-DONE
    }
    public String toString(){
        int denomination = super.getDenomination();
        return DENOMINATION_NAME[denomination] + " landed " + ((isHeads())? "Heads":"Tails");
    }
        //TODO
}

