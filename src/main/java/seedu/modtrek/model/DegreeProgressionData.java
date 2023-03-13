package seedu.modtrek.model;

import java.util.HashMap;
import java.util.Map;

import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.UniqueModuleList;

/**
 * DegreeProgressionData holds all the data for an overview of Degree Progression.
 */
public class DegreeProgressionData {

    // Currently for cohort 2122
    public static final int TOTALCREDIT = 160;
    public static final Map<String, Integer> REQUIREMENTCREDITS = Map.of(
            "UNIVERSITY_LEVEL_REQUIREMENTS", 16,
            "COMPUTER_SCIENCE_FOUNDATION", 36,
            "COMPUTER_SCIENCE_BREADTH_AND_DEPTH", 40,
            "IT_PROFESSIONALISM", 12,
            "MATHEMATICS_AND_SCIENCES", 16,
            "UNRESTRICTED_ELECTIVES", 40);
    private int completedCredit = 0;
    private int plannedCredit = 0; // Includes Incomplete Modules
    private HashMap<String, Integer> completedRequirementCredits = new HashMap<>();
    private float cumulativePoints = 0;
    private float gpa;

    private DegreeProgressionData() {}

    /**
     * Factory method to generate the data needed to show progress. Progress
     * is calculated from the user's current modlist.
     *
     * @param modList
     * @return
     */
    public static DegreeProgressionData generate(UniqueModuleList modList) {
        DegreeProgressionData data = new DegreeProgressionData();
        modList.forEach((module) -> {
            data.computeModule(module);
        });
        data.computeGpa();
        return data;
    }

    public int getCompletedCredit() {
        return completedCredit;
    }

    public int getPlannedCredit() {
        return plannedCredit;
    }

    public Map<String, Integer> getCompletedRequirementCredits() {
        return completedRequirementCredits;
    }

    public double getGpa() {
        // Rounded to 2 dp
        return (double) Math.round(gpa * 100) / 100;
    }

    private void computeModule(Module module) {
        int credit = Integer.valueOf(module.getCredit().toString());
        if (module.isComplete()) {
            module.getTags().forEach((tag) -> {
                completedRequirementCredits.merge(tag.toString(),
                        credit, (oldValue, newValue) -> {
                            return oldValue + newValue;
                        });
            });
            completedCredit += credit;
            cumulativePoints += credit * module.getGrade().toPoints();
        }
        plannedCredit += credit;
    }

    private void computeGpa() {
        this.gpa = cumulativePoints / completedCredit;
    }

}
