package seedu.modtrek.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.modtrek.testutil.TypicalModules.MA2002;
import static seedu.modtrek.testutil.TypicalModules.getEmptyDegreeProgression;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.modtrek.model.degreedata.DegreeProgressionData;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.testutil.ModuleBuilder;

public class DegreeProgressionDataTest {

    private DegreeProgressionData populatedData;
    private DegreeProgressionData emptyData;

    @BeforeEach
    public void setUp() {
        DegreeProgression populatedProgression = getTypicalDegreeProgression();
        populatedProgression.addModule(MA2002);
        populatedData = populatedProgression.getProgressionData();

        emptyData = getEmptyDegreeProgression().getProgressionData();
    }


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
        assertEquals(4.70, populatedData.getGpa());
    }

    @Test
    public void getCreditForAbsurdData() {
        DegreeProgression absurdProgression = getEmptyDegreeProgression();
        final Module cs0000 = new ModuleBuilder().withCode("CS0000")
                .withCredit("99")
                .withSemYear("Y1S1")
                .withGrade("A+")
                .withTags("COMPUTER SCIENCE BREADTH AND DEPTH")
                .build();
        absurdProgression.addModule(cs0000);
        DegreeProgressionData absurdData = absurdProgression.getProgressionData();

        assertEquals(99, absurdData.getCompletedCredit());
        assertEquals(40, absurdData.getMeaningfulCredit());
    }

    @Test
    public void getCreditForDataWithDoubleTaggedModules() {
        DegreeProgression progression = getTypicalDegreeProgression();
        final Module cs2106 = new ModuleBuilder().withCode("CS2106")
                .withCredit("4")
                .withSemYear("Y2S2")
                .withGrade("A+")
                .withTags("COMPUTER SCIENCE FOUNDATION", "COMPUTER SCIENCE BREADTH AND DEPTH")
                .build();
        progression.addModule(cs2106);
        DegreeProgressionData progressionData = progression.getProgressionData();

        assertEquals(164, progressionData.getTotalWithDuplicatedCredit());
        assertEquals(16, progressionData.getCompletedCredit());
        assertEquals(16, progressionData.getMeaningfulCredit());
    }

}
