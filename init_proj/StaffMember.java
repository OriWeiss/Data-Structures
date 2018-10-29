import java.security.InvalidParameterException;

/**
 * StaffMember class
 *
 * @author: ORI WEISS
 * @version: 12/11/2017
 */




public abstract class StaffMember
{
    private String name;
    private String address;
    private String phone;
    private String ssn;
    // TODO


    public StaffMember(String eName, String eAddress, String ePhone, String ssn)
    {
        this.name = eName;
        this.address = eAddress;
        this.phone = ePhone;
        setSsn(ssn);
        // TODO-Done

    }

    public String getName()
    {

        return name;
        // TODO
    }

    public String getAddress()
    {

        return address;
        // TODO
    }

    public String getPhone()
    {

        return phone;
        // TODO
    }

    public void setSsn(String ssn1) throws InvalidParameterException
    {
            if (ssn1.matches("^(?!000|666)[0-9][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$")) {
                this.ssn = ssn1;
            }
            else{
                throw new InvalidParameterException();
            }
        // TODO
    }

    public String getSsn()
    {

// TODO
        return ssn;
    }

    public boolean equals(Object o)
    {
        boolean same = true;
        if (!(o instanceof StaffMember))
            same = false;
        else
        {
            StaffMember objEmp = (StaffMember) o;

            if (address.equals(objEmp.address) && name.equals(objEmp.name) && ssn.equals(objEmp.ssn) && phone.equals(objEmp.phone))
            {
                same = true;
            }
            else
            {
                same = false;
            }
        }

        // TODO

        return same;
    }

    public String toString()
    {
        // TODO

        return "\"--- Staff Member --- Name: \" + getName() + \"\\n\" + \"Address: \" + getAddress() + \"\\n\" + \"Phone: \" + getPhone() + \"\\n\"\n" +
                "                + \"Social Security Number: \" + getSsn();";
    }

    public abstract double calculatePayment();
}
