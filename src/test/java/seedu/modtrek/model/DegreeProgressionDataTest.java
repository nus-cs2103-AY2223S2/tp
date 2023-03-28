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
        assertEquals(13, data.getPlannedCredit());
    }

    @Test
    public void getMeaningfulCredit() {
        assertEquals(20, data.getMeaningfulCredit());
    }

    @Test
    public void getCompletedRequirementCredits() {
        Map<String, Integer> expected = Map.of(
                "CSF", 16,
                "MS", 12,
                "ITP", 0,
                "CSBD", 0,
                "ULR", 0,
                "UE", 0);
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
