/**
 * A client class that implements a piggy bank functionality.
 *
 //Author: Ori Weiss
 //1/30/2018
 */
public class Round
{
    private PiggyBank myPiggyBank; //commented out so the class compile for now

    public Round(int numberOfMonies, int capacity)
    {
        this.myPiggyBank = new PiggyBank(numberOfMonies ,capacity);
//        System.out.println(">> The content of your piggy bank <<");
//        System.out.println("The Piggy Bank can hold " + capacity + " coins/bills;");

        //TODO
    }

    public void addTwoMonies()
    {
        try{
            System.out.println();
            System.out.println("Adding additional monies");
            System.out.println();
            this.myPiggyBank.add(new Coin());
            this.myPiggyBank.add(new Bill());
        }
        catch(PiggyBankFullException pbfe){
            System.out.println("Piggy Bank is full can not addTwoMonies");
        }
        //TODO-Done
    }


    public int run()
    {
        addTwoMonies();
        this.myPiggyBank.shake();
        int numOfMonies = this.myPiggyBank.getNumberOfMonies();
        int headsCount= this.myPiggyBank.emptyAndCountHeads();
        return headsCount;
        //TODO
    }

}