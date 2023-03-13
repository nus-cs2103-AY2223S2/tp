package seedu.modtrek.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.modtrek.testutil.TypicalModules.MA2002;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class DegreeProgressionDataTest {

    // Using Typical Degree Progression as base test
    private DegreeProgressionData data = addModuleAndInit();

    @Test
    public void getCompletedCredit() {
        assertEquals(20, data.getCompletedCredit());
    }

    @Test
    public void getPlannedCredit() {
        assertEquals(33, data.getPlannedCredit());
    }

    @Test
    public void getCompletedRequirementCredits() {
        Map<String, Integer> expected = Map.of(
                "COMPUTER_SCIENCE_FOUNDATION", 16,
                "MATHEMATICS_AND_SCIENCES", 12);
        assertEquals(expected, data.getCompletedRequirementCredits());
    }

    @Test
    public void getGpa() {
        assertEquals(4.80, data.getGpa());
    }

    private DegreeProgressionData addModuleAndInit() {
        DegreeProgression test = getTypicalDegreeProgression();
        test.addModule(MA2002);
        return test.getProgressionData();
    }

}
