package Lab08;

//Author: Ori Weiss
//Date: 3/21/2018

public class Job implements Comparable<Job>{
    private int jobNo;
    private int priority;
    private int createdAtTime;
    private int timeLeft;
    private static int jobsCreated = 0;

    public Job(int priority, int timeCreated, int timeNeeded){
        this.priority = priority;
        this.timeLeft = timeNeeded;
        this.createdAtTime = timeCreated;
        this.jobNo = this.jobsCreated;
        this.jobsCreated++;

    }
    public int compareTo(Job other){
        int result = 0;
        if (this.equals(other)) {
            result = 0;
        } else if (priority < other.priority) {
            result = -1;
        } else {
            result = 1;
        }
        return result;
    }
    public int getTimeLeft(){
        return this.timeLeft;
    }
    public int getJobsCreated(){
        return this.jobsCreated;
    }
    public void update(){
        this.timeLeft--;
    }
    public int getJobNo(){ //I made this one
        return this.jobNo;
    }
    public boolean isFinished(){
        boolean result = false;
        if(this.timeLeft == 0){
            result = true;
            System.out.print("    Completed: Job #" + this.jobNo + " at time "); //the rest of this print statement is in the
                // simulation file under the else if statement in the runSimulation function
        }
        return result;
    }
    public String toString(){
        return (" Job #" + this.jobNo + " Priority (" + this.priority + ") Created at " + this.createdAtTime + ", Time Left "+ this.timeLeft);
    }
}
