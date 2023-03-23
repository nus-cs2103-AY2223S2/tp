package seedu.modtrek.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.UniqueModuleList;
import seedu.modtrek.model.tag.ValidTag;

/**
 * DegreeProgressionData holds all the data for an overview of Degree Progression.
 */
public class DegreeProgressionData {

    // Currently for cohort 2122
    public static final int TOTALCREDIT = 160;
    private int completedCredit = 0;
    private int plannedCredit = 0; // Includes Incomplete Modules
    private HashMap<String, Integer> completedRequirementCredits = new HashMap<>();
    private float cumulativePoints = 0;
    private float gpa = 5.00f;

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
        data.initCompletedRequirementCredits();
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

    public boolean isNoneCompleted() {
        return completedCredit == 0;
    }

    public Map<String, Integer> getCompletedRequirementCredits() {
        return completedRequirementCredits;
    }

    public double getGpa() {
        // Rounded to 2 dp
        return (double) Math.round(gpa * 100) / 100;
    }

    public String getFullDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Requirement: completed/total\n");
        for (String tag : ValidTag.getTags()) {
            details.append(String.format("%1$s: %2$d / %3$d\n",
                    tag.replace("_", " "),
                    completedRequirementCredits.getOrDefault(ValidTag.getShortForm(tag).toString(), 0),
                    ValidTag.getTotalCredit(tag)));
        }
        details.append(String.format("\nCurrent GPA: %.2f\n", getGpa()))
                .append("OVERALL PROGRESS\n")
                .append(String.format("> Completed: %1$d\n", completedCredit))
                .append(String.format("> Planned:   %1$d\n", plannedCredit))
                .append(String.format("> Total:     %1$d\n", TOTALCREDIT));
        return details.toString();
    }

    public Map<String, Integer> getRequirementsPercentage() {
        Map<String, Integer> result = completedRequirementCredits
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                    float percent = ((float) entry.getValue() / ValidTag.getTotalCredit(entry.getKey())) * 100;
                    return percent > 100 ? 100 : (int) percent;
                }));
        return result;
    }

    public int getOverallPercentage() {
        return (int) ((float) completedCredit / TOTALCREDIT * 100);
    }

    private void computeModule(Module module) {
        int credit = Integer.valueOf(module.getCredit().toString());
        if (module.isComplete() && module.isGradeable()) {
            module.getTags().forEach((tag) -> {
                completedRequirementCredits.merge(tag.tagName,
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
        if (completedCredit == 0) {
            this.gpa = 5.00f;
            return;
        }
        this.gpa = cumulativePoints / completedCredit;
    }

    private void initCompletedRequirementCredits() {
        List<String> tags = ValidTag.getTags();
        for (String tag : tags) {
            completedRequirementCredits.put(ValidTag.getShortForm(tag).toString(), 0);
        }
    }
}
