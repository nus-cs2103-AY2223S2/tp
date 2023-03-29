package seedu.address.model.stats;

import java.util.ArrayList;

/**
 * Represents a list of Statistics to be displayed.
 */
public class StatisticItemList {
    private ArrayList<Statistic> statsList;

    /**
     * Constructor to create a StatisticItemList object.
     */
    public StatisticItemList() {
        this.statsList = new ArrayList<>();
    }

    /**
     * Returns statistic list
     */
    public ArrayList<Statistic> getStatsList() {
        return this.statsList;
    }

    /**
     * Adds a statistic to statistic list
     *
     * @param toAdd
     */
    public void addStats(Statistic toAdd) {
        this.statsList.add(toAdd);
    }

    /**
     * Prints the statistics to be displayed
     *
     */
    public String printStats() {
        String stats = "";
        for (Statistic stat: statsList) {
            stats += stat.toString();
        }
        return stats;
    }
}
