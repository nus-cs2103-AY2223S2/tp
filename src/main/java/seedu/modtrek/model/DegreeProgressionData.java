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
    public final int TOTALCREDIT = 160;
    public int currentCredit = 0;
    public static Map<String, Integer> requirementCredits = Map.of(
            "UNIVERSITY_LEVEL_REQUIREMENTS", 16,
            "COMPUTER_SCIENCE_FOUNDATION", 36,
            "COMPUTER_SCIENCE_BREADTH_AND_DEPTH", 40,
            "IT_PROFESSIONALISM", 12,
            "MATHEMATICS_AND_SCIENCES", 16,
            "UNRESTRICTED_ELECTIVES", 40);
    public HashMap<String, Integer> completedRequirementCredits = new HashMap<>();
    private float cumulativePoints = 0;
    public float gpa;

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
        data.computeGPA();
        return data;
    }

    private void computeModule(Module module) {
        int credit = Integer.valueOf(module.getCredit().toString());
        module.getTags().forEach((tag) -> {
            completedRequirementCredits.merge(tag.toString(), 
                    credit,
                    (oldValue, newValue) -> {
                        return oldValue + newValue;
                    });
        });
        currentCredit += credit;
        cumulativePoints += credit * module.getGrade().toPoints();
    }

    private void computeGPA() {
        this.gpa = cumulativePoints / currentCredit;
    }

}
