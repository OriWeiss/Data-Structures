/**
 * A class that implements a piggy bank full exception.
 *
 * @author Anna Bieszczad
 * @version 1/23/2018
 */
public class PiggyBankFullException  extends RuntimeException
{

    /**
     * Constructor for objects of class PiggyBankFullException
     */
    public PiggyBankFullException(String reason)
    {
        super(reason);
    }
}
