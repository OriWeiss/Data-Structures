//Author: Ori Weiss
//1/30/2018
import java.util.Arrays;
import java.util.Random;
public class PiggyBank {
    //Should it be an abstract class?
    private BagInterface<Money> bucket;
    private int capacity;
    Random rand = new Random();
    private double totalValue=0;
    public PiggyBank(int initElementsAmount, int capacity ){

        this.capacity = capacity;
        this.bucket = new ResizableArrayBag<>();
        int type = -1; // -1 will throw an error if not changed to type money
        System.out.println(">> Adding " + initElementsAmount + "  monies to your piggy bank <<");
        for(int i = 0; i < initElementsAmount; i++){
            type = rand.nextInt(2);
            if(type == 0) {
                add(new Coin());
            }
            if (type == 1){
                add(new Bill());
            }

        }

            //TODO-DONE
    }
    public void add(Money newMoney){

        if(this.capacity >= getNumberOfMonies()){
            this.bucket.add(newMoney);
            System.out.println("Added: $" + newMoney.getValue() + " to the piggy bank");
        }
        else{
            throw new PiggyBankFullException("Attempt to create a bag whose capacity exceeds " +
                    "allowed maximum of " + this.capacity);
        }
        //TODO-DONE
    }
    public Money remove(){
        if(isEmpty()){
            throw new PiggyBankEmptyException("Piggy Bank is empty can not remove");
        }
        else {
            Money removedItem = this.bucket.remove();
            return removedItem;
        }
        //TODO-DONE
    }
    public boolean isEmpty(){
        return this.bucket.isEmpty();
        //TODO-DONE
    }
    public boolean isFull(){
        boolean result;
        if(getNumberOfMonies()>= this.capacity){
            result = true;
        }
        else{
            result = false;
        }
        return result;
        //TODO-DONE
    }
    public int getCapacity(){
        return this.capacity;
        //TODO-DONE
    }
    public int getNumberOfMonies(){
        return this.bucket.getCurrentSize();
        //TODO-DONE
    }
    public void shake(){
        System.out.println();
        System.out.println("Shaking the Piggy Bank");
        System.out.println();
        Object[] toShake = this.bucket.toArray();
        Money shakeElement = null;
        int size = toShake.length;
        this.bucket.clear();
        //shuffle toShake
        for (int i = size - 1; i > 0; i--) {
            double j = Math.floor(Math.random() * (i + 1));
            Object temp = toShake[i];
            toShake[i] = toShake[(int)j];
            toShake[(int)j] = temp;
        }
        //add back the elements to bucket
        for(int i = 0; i< size; i++){
            shakeElement = (Money) toShake[i];
            totalValue = totalValue + shakeElement.getValue();

            this.bucket.add(shakeElement);
        }
        System.out.println(toString());
        //TODO-DONE
    }
    public int emptyAndCountHeads(){
        System.out.println();
        System.out.println("Emptying your piggy bank");
        System.out.println();
        int itemsInside = getNumberOfMonies();
        int headsCount = 0;
        double dollarValue = 0;
        for(int i=0; i < itemsInside; i++) {
            Money currency = remove();
            currency.toss();
            System.out.println(currency);
            if(currency.isHeads()){
                headsCount++;
                dollarValue = dollarValue + currency.getValue();
            }
        }

        System.out.println(headsCount + " out of " + itemsInside + " landed HEADS " );
        System.out.println("The total value of HEADS is " + dollarValue);
        return headsCount;
        //TODO-DONE
    }
    public String toString(){
        Object[] bag = this.bucket.toArray();
        return ">> The content of your piggy bank << \n" + "The piggy bank can hold " + getCapacity() + " coins/bills \n"
                + "There are " + getNumberOfMonies() + " monies in the piggy bank: " + Arrays.toString(bag) + " \nThe total value is " + totalValue;
// >> The content of your piggy bank <<
//The piggy bank can hold 10 coins/bills;
//There are 6 coins/bills in the piggy bank:[HALF_DOLLAR landed TAILS, NICKEL landed TAILS, DIME landed TAILS, JACKSON landed TAILS, HAMILTON landed TAILS, PENNY landed TAILS]
//The total of $30.66
//        //TODO
    }
}
