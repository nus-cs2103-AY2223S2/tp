package seedu.address.model.jobs;

import java.util.ArrayList;

/**
 * Represents an arraylist of array list of jobs
 * Represents list of jobs in a day - classified into different slots
 */
public class DeliveryList {
    private ArrayList<ArrayList<DeliveryJob>> jobList;

    public DeliveryList(ArrayList<ArrayList<DeliveryJob>> jobList) {
        this.jobList = jobList;
    }

    public DeliveryList() {
        this.jobList = new ArrayList<ArrayList<DeliveryJob>>();
    }

    /**
     * Returns job list
     */
    public ArrayList<ArrayList<DeliveryJob>> getJobList() {
        return this.jobList;
    }

    /**
     * Returns size of list
     */
    public int size() {
        return jobList.size();
    }

    public ArrayList<DeliveryJob> get(int index) {
        return jobList.get(index);
    }

}
