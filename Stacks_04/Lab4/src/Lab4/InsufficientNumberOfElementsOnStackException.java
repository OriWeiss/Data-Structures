package Lab4;

/**
 * A class that implements a stack empty exception.
 *
 * @author Anna Bieszczad
 * @version 02/13/2018
 */
public class InsufficientNumberOfElementsOnStackException extends RuntimeException
{
    public InsufficientNumberOfElementsOnStackException(String reason)
    {
        super(reason);
    }
}
