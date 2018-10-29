/**
 * Payroll class
 * @author: ORI WEISS
 * @version: 12/11/2017
 */
//Fix Mary and Carl

import java.security.InvalidParameterException;
import java.util.*;

public class Payroll
{
    // TODO
    private ArrayList<StaffMember> staffList = new ArrayList<>();
    private static final String EXECUTIVE ="Executive";
    private static final String VOLUNTEER = "Volunteer";
    private  static final String HOURLY = "Hourly";
    public Payroll(Scanner file){

        System.out.println("---> Reading staff data from the file");
        while (file.hasNextLine()) {
            String line = file.nextLine();
            Scanner line1 = new Scanner(line);
            line1.useDelimiter(",");
            while (line1.hasNext()) {

                System.out.println("Processing record: " + line);
                double pay = 0;
                try {
                    String title = line1.next();
                    String name = line1.next();
                    String address = line1.next();
                    String number = line1.next();
                    String ssn = line1.next();
                    if(line1.hasNext()) {
                        pay = line1.nextDouble();
                    }

                    if (title.equals(EXECUTIVE)) {
                        staffList.add(new Executive(name, address, number, ssn, pay));
                    }
                    if (title.equals(HOURLY)) {
                        staffList.add(new HourlyEmployee(name, address, number, ssn, pay));
                    }
                    if (title.equals(VOLUNTEER)) {
                        staffList.add(new Volunteer(name, address, number, ssn));
                    }
                } catch (InputMismatchException ipe) {
                    System.out.println("Input mismatch exception found");
                } catch (NoSuchElementException nse) {
                    System.out.println("No Such Element Exception found");
                } catch (InvalidParameterException ipe) {
                    System.out.println("Invalid Parameter Exception found");
                }
            }
        }





        // TODO



        System.out.println("---> Finished reading from the file\n");
    }

    public void prepareForPayDay()
    {
        Random rand = new Random();
        for (StaffMember e : staffList){
            if (e instanceof Executive){
                int  n = rand.nextInt(5000) + 500;
                Executive exec = (Executive) e;
                exec.awardBonus(n);
            }
            if (e instanceof HourlyEmployee){
                int  v = rand.nextInt(100) + 20;
                HourlyEmployee member = (HourlyEmployee) e;
                member.addHours(v);
            }
        }
        // TODO-DONE

    }

    //-----------------------------------------------------------------
    //  Pays all staff members.
    //-----------------------------------------------------------------
    public double[] processPayroll()
    {
        double payment;
        int size= staffList.size();
        double paymentList [] = new double[size];
        int i = 0;
        for (StaffMember e : staffList){
            if (e instanceof Executive) {
                Executive exec = (Executive) e;
                payment = exec.calculatePayment();
                paymentList[i] = payment;
            }
            if (e instanceof HourlyEmployee) {
                HourlyEmployee employee = (HourlyEmployee) e;
                payment = employee.calculatePayment();
                paymentList[i]=payment;
            }
            if (e instanceof Volunteer) {
                Volunteer free = (Volunteer) e;
                payment = free.calculatePayment();
                paymentList[i]=payment;
            }
            i++;
        }
        return paymentList;
//        TODO-DONE
    }

    public ArrayList<StaffMember> getStaffList()
    {

        // TODO

        return staffList;
    }

    public void displayStaffData()
    {
        String data = null;
        for (StaffMember e : staffList){
            if (e instanceof Executive) {
                Executive exec = (Executive) e;
                data = exec.toString();
            }
            if (e instanceof HourlyEmployee){
                HourlyEmployee employee = (HourlyEmployee) e;
                data = employee.toString();
            }
            if (e instanceof Volunteer){
                Volunteer free = (Volunteer) e;
                data = free.toString();
            }
            System.out.println(data);
        }

        // TODO
    }
}
