package seedu.modtrek.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
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
    private HashMap<String, Integer> totalRequirementCredits = new HashMap<>(Map.of(
        "ULR", 16,
        "CSF", 36,
        "CSBD", 40,
        "ITP", 12,
        "MS", 16,
        "UE", 40));

    // User's calculateProgress data
    private int completedCredit = 0; // Credits counted for gpa
    private int plannedCredit = 0;
    private HashMap<String, Integer> completedRequirementCredits = new HashMap<>();
    private float cumulativePoints = 0;
    private float gpa = 5.00f;
    private int duplicatedCredits = 0;
    private int overallPercentage;
    private int meaningfulCredits;

    private DegreeProgressionData() {}

    /**
     * Factory method to generate the data needed to show progress. Progress
     * is calculateProgressd from the user's current modlist.
     *
     * @param modList
     * @return
     */
    public static DegreeProgressionData generate(UniqueModuleList modList) {
        DegreeProgressionData data = new DegreeProgressionData();
        Stack<Module> multiTagged = new Stack<>();
        data.initCompletedRequirementCredits();
        modList.forEach((module) -> {
            if (module.isMultiTagged()) {
                multiTagged.add(module);
            } else {
                data.computeSingleTagModule(module);
            }
        });
        data.computeMultiTagModules(multiTagged);
        data.updateUeTotal();
        data.computeGpa();
        data.calculateProgress();
        return data;
    }

    private void updateUeTotal() {
        this.totalRequirementCredits.merge("UE", this.duplicatedCredits, (x, y) -> x + y);
    }

    private void computeMultiTagModules(Stack<Module> stack) {
        while (!stack.isEmpty()) {
            Module module = stack.pop();
            int credit = Integer.valueOf(module.getCredit().toString());
            if (module.isComplete() && module.isGradeable()) {
                int total = module.getTags()
                        .stream()
                        .map((tag) -> {
                            String tagName = tag.tagName;
                            return Math.min(credit,
                                    totalRequirementCredits.get(tagName) - completedRequirementCredits.get(tagName));
                        })
                        .reduce(0, (old, next) -> {
                            return old + next;
                        });
                duplicatedCredits += Math.max(total - credit, 0);
                module.getTags().forEach((tag) -> {
                    completedRequirementCredits.merge(tag.tagName,
                            credit, (oldValue, newValue) -> {
                                int merged = oldValue + newValue;
                                int cap = totalRequirementCredits.get(tag.tagName);
                                return merged > cap ? cap : merged;
                            });
                });
                if (!module.isSatisfactory()) { // Checks for SU option
                    completedCredit += credit;
                    cumulativePoints += credit * module.getGrade().toPoints();
                }
            } else if (!module.isComplete()) {
                plannedCredit += credit;
            }
        }
    }

    public Map<String, Integer> getTotalRequirementCredits() {
        return totalRequirementCredits;
    }

    public int getTotalWithDuplicatedCredit() {
        return TOTALCREDIT + duplicatedCredits;
    }

    public int getCompletedCredit() {
        return completedCredit;
    }

    public int getMeaningfulCredit() {
        return meaningfulCredits;
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

    public Map<String, Integer> getRequirementsPercentage() {
        Map<String, Integer> result = completedRequirementCredits
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                    float percent = ((float) entry.getValue() / totalRequirementCredits.get(entry.getKey())) * 100;
                    return percent > 100 ? 100 : (int) percent;
                }));
        return result;
    }

    public int getOverallPercentage() {
        return overallPercentage;
    }

    private void calculateProgress() {
        float totalRequirementCompletion = 0;
        for (Entry<String, Integer> entry : completedRequirementCredits.entrySet()) {
            int total = totalRequirementCredits.get(entry.getKey());
            int current = entry.getValue();
            totalRequirementCompletion += Math.min(total, current);
        }
        totalRequirementCompletion -= duplicatedCredits;
        assert totalRequirementCompletion >= 0;
        meaningfulCredits = (int) totalRequirementCompletion;
        overallPercentage = (int) (totalRequirementCompletion / TOTALCREDIT * 100);
    }

    private void computeSingleTagModule(Module module) {
        assert module != null;
        int credit = Integer.valueOf(module.getCredit().toString());
        if (module.isComplete() && module.isGradeable()) {
            module.getTags().forEach((tag) -> {
                completedRequirementCredits.merge(tag.tagName,
                        credit, (oldValue, newValue) -> {
                            int merged = oldValue + newValue;
                            int cap = totalRequirementCredits.get(tag.tagName);
                            return merged > cap ? cap : merged;
                        });
            });
            if (!module.isSatisfactory()) { // Checks for SU option
                completedCredit += credit;
                cumulativePoints += credit * module.getGrade().toPoints();
            }
        } else if (!module.isComplete()) {
            plannedCredit += credit;
        }
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

    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("Requirement: completed/total\n");
        for (String tag : ValidTag.getTags()) {
            String shortFormTag = ValidTag.getShortForm(tag).toString();
            int tagTotal = totalRequirementCredits.get(shortFormTag);
            details.append(String.format("%1$s: %2$d / %3$d\n",
                    tag.replace("_", " "),
                    completedRequirementCredits.get(shortFormTag),
                    tag.equals("UE") ? tagTotal + duplicatedCredits : tagTotal));
        }
        details.append(String.format("\nCurrent CAP: %.2f\n", getGpa()))
                .append("OVERALL PROGRESS\n")
                .append(String.format("> Planned:    %1$d\n", plannedCredit))
                .append(String.format("> Meaningful: %1$d\n", meaningfulCredits))
                .append("Meaningful credits count towards progression!");
        return details.toString();
    }

}
