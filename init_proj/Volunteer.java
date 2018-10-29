/**
 * Volunteer class
 * @author: ORI WEISS
 * @version: 12/11/2017
 */

public class Volunteer extends StaffMember
{
    public Volunteer (String eName, String eAddress, String ePhone, String ssn)
    {
        super(eName,eAddress,ePhone,ssn);
// TODO-Done
    }

    //-----------------------------------------------------------------
    //  Returns a zero pay value for this volunteer.
    //-----------------------------------------------------------------
    public double calculatePayment()
    {

        return 0;
        // TODO-Done
    }

    public String toString()
    {
        // TODO-Done

        return "--- Volunteer --- Name: " + getName() + "\n" + "Address: " + getAddress() + "\n" + "Phone: " + getPhone() + "\n"
                + "Social Security Number: " + getSsn();
    }
}
