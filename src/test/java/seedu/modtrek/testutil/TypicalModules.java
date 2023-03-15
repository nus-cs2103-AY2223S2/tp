package seedu.modtrek.testutil;

import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CODE_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CODE_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CREDIT_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CREDIT_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_GRADE_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_GRADE_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_SEMYEAR_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_SEMYEAR_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_TAG_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_TAG_MA2002;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module CS2100 = new ModuleBuilder().withCode("CS2100")
            .withGrade("A").withSemYear("Y1S2")
            .withCredit("4")
            .withTags("COMPUTER SCIENCE FOUNDATION").build();
    public static final Module ST2334 = new ModuleBuilder().withCode("ST2334")
            .withGrade("A")
            .withSemYear("Y1S2").withCredit("4")
            .withTags("MATHEMATICS AND SCIENCES").build();
    public static final Module MA1521 = new ModuleBuilder().withCode("MA1521").withCredit("4")
            .withSemYear("Y1S1").withGrade("A").build();
    public static final Module CS1231 = new ModuleBuilder().withCode("CS1231").withCredit("4")
            .withSemYear("Y1S1").withGrade("A+").withTags("COMPUTER SCIENCE FOUNDATION").build();
    public static final Module GEA1000 = new ModuleBuilder().withCode("GEA1000").withCredit("4")
            .withSemYear("Y1S2").withGrade("A").build();
    public static final Module CS1010R = new ModuleBuilder().withCode("CS1010R").withCredit("1")
            .withSemYear("Y1S2").withGrade("A+").build();
    public static final Module IS1103 = new ModuleBuilder().withCode("IS1103").withCredit("4")
            .withSemYear("Y1S2").withGrade("A").build();

    // Manually added
    public static final Module CS2030S = new ModuleBuilder().withCode("CS2030S").withCredit("4")
            .withSemYear("Y1S2").withGrade("A").build();
    public static final Module CS2040S = new ModuleBuilder().withCode("CS2040S").withCredit("4")
            .withSemYear("Y1S2").withGrade("B+").build();

    // Manually added - Module's details found in {@code CommandTestUtil}
    public static final Module CS1101S = new ModuleBuilder().withCode(VALID_CODE_CS1101S)
            .withCredit(VALID_CREDIT_CS1101S)
            .withSemYear(VALID_SEMYEAR_CS1101S).withGrade(VALID_GRADE_CS1101S)
            .withTags(VALID_TAG_CS1101S).build();
    public static final Module MA2002 = new ModuleBuilder().withCode(VALID_CODE_MA2002)
            .withCredit(VALID_CREDIT_MA2002)
            .withSemYear(VALID_SEMYEAR_MA2002).withGrade(VALID_GRADE_MA2002)
            .withTags(VALID_TAG_CS1101S, VALID_TAG_MA2002)
            .build();

    public static final String KEYWORD_MATCHING_CS2030S = "CS2030S"; // A keyword that matches MEIER

    private TypicalModules() {} // prevents instantiation

    /**
     * Returns an {@code GradeBook} with all the typical modules.
     */
    public static DegreeProgression getTypicalDegreeProgression() {
        DegreeProgression ab = new DegreeProgression();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<Module>(Arrays.asList(CS2100, ST2334, MA1521, GEA1000, CS1231, CS1010R, IS1103))
                .stream()
                .map((mod) -> {
                    return ModuleUtil.copy(mod);
                })
                .collect(Collectors.toList());
    }
}
