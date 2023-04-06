package seedu.modtrek.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.modtrek.testutil.TypicalModules.MA2002;
import static seedu.modtrek.testutil.TypicalModules.getEmptyDegreeProgression;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.modtrek.model.degreedata.DegreeProgressionData;

public class DegreeProgressionDataTest {

    // Using Empty Degree Progression as test
    private DegreeProgressionData emptyData = initEmptyData();

    // Using Typical Degree Progression as test
    private DegreeProgressionData populatedData = addModuleAndInit();

    @Test
    public void getCompletedCreditForEmptyData() {
        assertEquals(0, emptyData.getCompletedCredit());
    }

    @Test
    public void getPlannedCreditForEmptyData() {
        assertEquals(0, emptyData.getPlannedCredit());
    }

    @Test
    public void getMeaningfulCreditForEmptyData() {
        assertEquals(0, emptyData.getMeaningfulCredit());
    }

    @Test
    public void getGpaForEmptyData() {
        assertEquals(5.00, emptyData.getGpa());
    }

    @Test
    public void getCompletedRequirementCreditsForEmptyData() {
        Map<String, Integer> expected = Map.of(
                "CSF", 0,
                "MS", 0,
                "ITP", 0,
                "CSBD", 0,
                "ULR", 0,
                "UE", 0);
        assertEquals(expected, emptyData.getCompletedRequirementCredits());
    }

    @Test
    public void getCompletedCreditForPopulatedData() {
        assertEquals(20, populatedData.getCompletedCredit());
    }

    @Test
    public void getPlannedCreditForPopulatedData() {
        assertEquals(13, populatedData.getPlannedCredit());
    }

    @Test
    public void getMeaningfulCreditForPopulatedData() {
        assertEquals(20, populatedData.getMeaningfulCredit());
    }

    @Test
    public void getCompletedRequirementCreditsForPopulatedData() {
        Map<String, Integer> expected = Map.of(
                "CSF", 16,
                "MS", 12,
                "ITP", 0,
                "CSBD", 0,
                "ULR", 0,
                "UE", 0);
        assertEquals(expected, populatedData.getCompletedRequirementCredits());
    }

    @Test
    public void getGpaForPopulatedData() {
        assertEquals(4.80, populatedData.getGpa());
    }

    private DegreeProgressionData initEmptyData() {
        DegreeProgression test = getEmptyDegreeProgression();
        return test.getProgressionData();
    }

    private DegreeProgressionData addModuleAndInit() {
        DegreeProgression test = getTypicalDegreeProgression();
        test.addModule(MA2002);
        return test.getProgressionData();
    }

}
