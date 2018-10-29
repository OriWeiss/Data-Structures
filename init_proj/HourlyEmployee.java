/**
 * HourlyEmployee class
 *
 * @author: ORI WEISS
 * @version: 12/11/2017
 */

public class HourlyEmployee extends StaffMember {
    // TODO
    private int hoursWorked;
    private double payRate;
    private final int MIN_HOURS = 0;
    private final int MAX_HOURS = 10000000;

    public HourlyEmployee(String eName, String eAddress, String ePhone,
                          String socSecNumber, double rate) {
        super(eName, eAddress, ePhone, socSecNumber);
        this.payRate = rate;
        // TODO-Done
    }

    public void addHours(int moreHours) {
        hoursWorked = hoursWorked + moreHours;
        // TODO-Done
    }

    public double calculatePayment() {
        // TODO-Done
        return (double) hoursWorked * payRate;
    }

    public boolean equals(Object o) {
        // TODO-Done
        boolean same;
        if (!(o instanceof HourlyEmployee))
            same = false;
        else {
            HourlyEmployee objEmp = (HourlyEmployee) o;

            if (hoursWorked == (objEmp.hoursWorked) && payRate == (objEmp.payRate) && MIN_HOURS == (objEmp.MIN_HOURS) && MAX_HOURS == (objEmp.MAX_HOURS)) {
                same = true;
            }
            else {
                same = false;
            }
        }

        return same;
    }

    //-----------------------------------------------------------------
    //  Returns information about this hourly employee as a string.
    //-----------------------------------------------------------------
    public String toString() {
        // TODO

        return "--- Hourly Employee --- Name: " + getName() +  "\n" + "Address " + getAddress() + "\n" + "Phone: " + getPhone()
                + "\n" + "Social Security Number: " + getSsn() + "\n" + "Current Hours: " + hoursWorked + " at rate of " + payRate;
    }
}



