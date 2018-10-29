package Lab08;

//Author: Ori Weiss
//Date: 3/21/2018

import java.time.Duration;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayDeque;
import java.util.Random;

public class SchedulingSimulation {
    private PriorityQueue<Lab08.Job> waitingJobs;
    private ArrayDeque<Lab08.Job> allJobs;
    private Lab08.Job currentJob;
    private final int TIME_SLICE_PER_JOB = 3;
    private final int SIMULATION_DURATION = 100;
    private final int JOB_PROBABILITY = 30;
    private final int JOB_PRIORITY = 4;
    private final int JOB_MIN_TIME = 1;
    private final int JOB_MAX_TIME = 5;
    private static final int SORT_BY_PRIORITY = 0;
    private static final int SORT_BY_LENGTH = 1;

    public SchedulingSimulation(int sortMethod){

        if(sortMethod == SORT_BY_LENGTH){
            this.waitingJobs = new PriorityQueue<Lab08.Job>(new Comparator<Lab08.Job>() {
                public int compare(Lab08.Job job1, Lab08.Job job2) {
                    return job1.getTimeLeft() - job2.getTimeLeft();
                }
            });
        }
        else if(sortMethod == SORT_BY_PRIORITY){
            this.waitingJobs = new PriorityQueue<Lab08.Job>();
        }
        this.allJobs = new ArrayDeque<Lab08.Job>();

    }
    /* ****************IMPORTANT****************
    Not sure how runSimulation is supposed to work since the instructions were not clear with time slice and I could
    not go to office hours to ask questions, so my implementation may be different than yours. I assumed that the job
    with the highest priority is always the current job running (so if a new job is created with a higher priority than
    the one running, the new job will take over) but if a job runs for more than timeSlice value it is put into temp
    until either the next job running finishes or runs for a timeSlice (in which case temp would be pushed back into the
    queue and the job that just ran would be put into temp). In order to make it work this way I had to add a function
    called getJobNo so I can compare the last job which ran to the currentJob running so I could increment a counter
    and compare the counter to the timeSlice. I'd be more than happy to redo this with clearer instructions on what
    timeSlice does.
    * */
    public void runSimulation(){
        Lab08.Job temp = null;
        int count = 1;
        int jobNo = 0;
        for (int clock = 0; clock < this.SIMULATION_DURATION; clock++) {
            reportAtTimeMarker(clock);
            generateJob(clock);
            this.currentJob = this.waitingJobs.peek();
            if (this.currentJob == null){
                System.out.println("    Executing: NONE");
            }
            else{
                System.out.println("    Executing: " + this.currentJob);
                this.currentJob.update();
                if(this.currentJob.isFinished()) { //if the job is finished
                    System.out.println(clock); //needs to print the time completed so there is another print statement in Lab08.Job.isFinished()
                    jobNo = this.waitingJobs.poll().getJobNo(); //remove from waitingList and changing jobNO

                    count = 1; //reset the count
                    if(temp != null){
                        this.waitingJobs.offer(temp); //push temp back in if not null
                        temp = null; //reset temp
                    }
                }
                else if(jobNo == currentJob.getJobNo()){ //if the last job ran is the same as the current job running
                    count++; //increment count
                    if(count >= this.TIME_SLICE_PER_JOB && this.waitingJobs.size() > 1){ //if the count is greater than time slice and the the size of the list is greater tan 1
                        this.currentJob = this.waitingJobs.poll();
                        if(temp != null){
                            this.waitingJobs.offer(temp);//check to make sure temp is not empty if it's not push temp in
                            //System.out.println("reinserting temp slice");
                        }
                        temp = this.currentJob; //change temp to the current job
                        count = 1; //reset the count
                    }
                }
                else{
                    count = 1; //reset count
                    jobNo = currentJob.getJobNo(); //set jobNo to current job number
                }
            }
            if((clock == this.SIMULATION_DURATION -1) && temp!= null) { //if the simulation is finishing push temp back in
                this.waitingJobs.offer(temp);
            }
        }
        finalSimulationreport();
        System.out.println("\n***END OF SIMULATION***\n");
    }
    public void generateJob(int clock){
        Random generator = new Random();
        int chanceOfNewJob = generator.nextInt(100) + 1; //random chance between 1 and 100
        if(chanceOfNewJob <= this.JOB_PROBABILITY) { //if chance of job is within the job probability
            int priority = generator.nextInt(4)+1; //random priority between 4 and 1
            int jobTime = generator.nextInt(JOB_MAX_TIME - JOB_MIN_TIME + 1) + JOB_MIN_TIME; //random time between min time and max time variable
            Lab08.Job newJob = new Lab08.Job(priority, clock, jobTime);
            this.allJobs.offer(newJob);
            this.waitingJobs.offer(newJob);
            System.out.println("    Created: " + newJob);

        }
    }
    public void reportAtTimeMarker(int clock){
        if(this.waitingJobs.size() > 0) {
            System.out.println("Time Marker: " + (clock) + "  waiting: " + (this.waitingJobs.size() - 1)); //subtracting one since im using peek and therefore
            //the first element is always the currentJob and everything after is waiting
        }
        else{
            System.out.println("Time Marker: " + (clock) + "  waiting: 0"  );
        }
    }
    public void finalSimulationreport(){
        System.out.println("\n*************** Final Report: ***************");
        System.out.println("Active jobs:");
        int waitingSizeInt = this.waitingJobs.size();
        double waitingSizeDouble = (double)this.waitingJobs.size();
        double totalTimeLeft = 0;
        for (int i = 0; i < waitingSizeInt; i++) {
            this.currentJob = this.waitingJobs.poll();
            totalTimeLeft = totalTimeLeft + this.currentJob.getTimeLeft();
            System.out.println(this.currentJob);
        }
        System.out.println("\nThe number of jobs currently executing is " + waitingSizeInt);
        System.out.println("The number of completed jobs is " + (this.allJobs.size() - waitingSizeInt));
        System.out.println("The total number of jobs is " + this.currentJob.getJobsCreated() + "Other " + this.allJobs.size()); //for testing purposes other
        System.out.println("The average time left for unfinished jobs is " +(totalTimeLeft/waitingSizeDouble));


    }
    public static void main(String args[])
    {
//        System.out.println("***STARTING THE SIMULATION WITH PRIORITY MODE SET TO SORT_BY_PRIORITY***\n");
//        SchedulingSimulation s1 = new SchedulingSimulation(0);
//        s1.runSimulation();
        //End of simulation1 with priority sorting
        //Beginning simulation2 with length sorting


        System.out.println("\n***STARTING THE SIMULATION WITH PRIORITY MODE SET TO SORT_BY_LENGTH***\n");
        SchedulingSimulation s2 = new SchedulingSimulation(1);
        s2.runSimulation();
    }
}