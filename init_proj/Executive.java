/**
 * Executive class
 *
 * @author: ORI WEISS
 * @version: 12/11/2017
 */


import java.text.DecimalFormat;
import java.util.Random;



public class Executive extends StaffMember
{
    private double bonus;
    private double yearly;
    private static final int MIN_BONUS = 500;
    private static final int MAX_BONUS = 5000;
    private final int NUMBER_OF_MONTHS = 12;
    // TODO

    public Executive(String eName, String eAddress, String ePhone,
                     String socSecNumber, double yearly)
    {
        super(eName,eAddress,ePhone,socSecNumber);
        this.yearly= yearly;
        // TODO-Done
    }

    public void awardBonus(double execBonus) {
        if (execBonus >= MIN_BONUS && execBonus <= MAX_BONUS){
            bonus = execBonus;
        }
        // TODO-Done
    }

    //-----------------------------------------------------------------
    //  Computes and returns the pay for an executive, which is the
    //  regular employee payment plus a one-time bonus.
    //-----------------------------------------------------------------
    public double calculatePayment()
    {
        double execSalary = ((yearly / (double)NUMBER_OF_MONTHS) + bonus);
        //return ((yearly / (double)NUMBER_OF_MONTHS) + bonus);
        return execSalary;
        // TODO-Done
    }

    public boolean equals(Object o)
    {
        boolean same = true;
        if (!(o instanceof Executive))
            same = false;
        else
        {
            Executive objEmp = (Executive) o;

            if (bonus == (objEmp.bonus) && yearly == (objEmp.yearly) && MIN_BONUS == (objEmp.MIN_BONUS) &&
                    MAX_BONUS == (objEmp.MAX_BONUS) && NUMBER_OF_MONTHS == (objEmp.NUMBER_OF_MONTHS))
            {
                same = true;
            }
            else
            {
                same = false;
            }
        }

        return same;
        // TODO-Done
    }

    public String toString()
    {
        // TODO

        return "---Executive--- Name: " + getName() +  "\n" + "Address " + getAddress() + "\n" + "Phone: " + getPhone()
                + "\n" + "Social Security Number: " + getSsn() + "\n" + "Yearly salary of: $" + calculatePayment() + "\n" + "Bonus: " + bonus;
    }
}
