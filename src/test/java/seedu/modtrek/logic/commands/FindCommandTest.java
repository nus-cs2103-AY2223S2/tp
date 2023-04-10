package seedu.modtrek.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.testutil.Assert.assertThrows;
import static seedu.modtrek.testutil.TypicalModules.CS1010R;
import static seedu.modtrek.testutil.TypicalModules.CS1231;
import static seedu.modtrek.testutil.TypicalModules.CS2100;
import static seedu.modtrek.testutil.TypicalModules.CS2102;
import static seedu.modtrek.testutil.TypicalModules.CS2103T;
import static seedu.modtrek.testutil.TypicalModules.CS2109S;
import static seedu.modtrek.testutil.TypicalModules.IS1103;
import static seedu.modtrek.testutil.TypicalModules.ST2334;
import static seedu.modtrek.testutil.TypicalModules.getEmptyDegreeProgression;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.modtrek.model.Model;
import seedu.modtrek.model.ModelManager;
import seedu.modtrek.model.UserPrefs;
import seedu.modtrek.model.module.Credit;
import seedu.modtrek.model.module.Grade;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.ModuleCodePredicate;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.model.tag.Tag;

class FindCommandTest {

    private Model populatedModel;
    private Model emptyModel;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        populatedModel = new ModelManager(getTypicalDegreeProgression(), new UserPrefs());
        emptyModel = new ModelManager(getEmptyDegreeProgression(), new UserPrefs());
        expectedModel = new ModelManager();
    }

    @Test
    public void constructor_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindCommand(null, new ArrayList<>()));
    }

    @Test
    public void execute_findFromEmptyDegreeProgression_success() {
        Set<String> codePrefixes = new HashSet<>();
        codePrefixes.add("CS");

        Set<Credit> credits = new HashSet<>();
        credits.add(new Credit("4"));

        ModuleCodePredicate predicate =
                new ModuleCodePredicate(false, "", codePrefixes,
                        credits, new HashSet<>(), new HashSet<>(), new HashSet<>());
        FindCommand findCommand = new FindCommand(predicate, new ArrayList<>());
        findCommand.execute(emptyModel);

        assertEquals(0, emptyModel.getFilteredModuleList().size());
    }

    @Test
    public void execute_findModule_success() {
        expectedModel.addModule(CS2100);

        ModuleCodePredicate predicate =
                new ModuleCodePredicate(true, CS2100.getCode().toString(), new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
        FindCommand findCommand = new FindCommand(predicate, new ArrayList<>());
        findCommand.execute(populatedModel);

        assertEquals(1, populatedModel.getFilteredModuleList().size());

        assertEquals(populatedModel.getFilteredModuleList().get(0).getCode().toString(),
                expectedModel.getDegreeProgression().getModuleList().get(0).getCode().toString());
    }

    @Test
    public void execute_findCodePrefixes_success() {
        expectedModel.addModule(CS2100);
        expectedModel.addModule(CS1231);
        expectedModel.addModule(CS1010R);
        expectedModel.addModule(IS1103);

        Set<String> codePrefixes = new HashSet<>();
        codePrefixes.add("CS");
        codePrefixes.add("IS");

        ModuleCodePredicate predicate =
                new ModuleCodePredicate(false, "", codePrefixes,
                        new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
        FindCommand findCommand = new FindCommand(predicate, new ArrayList<>());
        findCommand.execute(populatedModel);

        assertEquals(expectedModel.getDegreeProgression().getModuleList().size(),
                populatedModel.getFilteredModuleList().size());

        for (Module module : populatedModel.getFilteredModuleList()) {
            String codePrefix = module.getCodePrefix().toString();
            assertTrue(codePrefix.equals("CS") || codePrefix.equals("IS"));
        }
    }

    @Test
    public void execute_findCredits_success() {
        Set<Credit> credits = new HashSet<>();
        credits.add(new Credit("4"));

        ModuleCodePredicate predicate =
                new ModuleCodePredicate(false, "", new HashSet<>(),
                        credits, new HashSet<>(), new HashSet<>(), new HashSet<>());
        FindCommand findCommand = new FindCommand(predicate, new ArrayList<>());
        findCommand.execute(populatedModel);

        assertEquals(6, populatedModel.getFilteredModuleList().size());

        for (Module module : populatedModel.getFilteredModuleList()) {
            String credit = module.getCredit().toString();
            assertEquals("4", credit);
        }
    }

    @Test
    public void execute_findSemYear_success() {
        populatedModel.addModule(CS2109S);
        populatedModel.addModule(CS2102);
        populatedModel.addModule(CS2103T);

        Set<SemYear> semYears = new HashSet<>();
        semYears.add(new SemYear("Y1S1"));
        semYears.add(new SemYear("Y1S2"));

        ModuleCodePredicate predicate =
                new ModuleCodePredicate(false, "", new HashSet<>(),
                        new HashSet<>(), semYears, new HashSet<>(), new HashSet<>());
        FindCommand findCommand = new FindCommand(predicate, new ArrayList<>());
        findCommand.execute(populatedModel);

        assertEquals(7, populatedModel.getFilteredModuleList().size());

        for (Module module : populatedModel.getFilteredModuleList()) {
            String moduleSemYear = module.getSemYear().toString();
            assertTrue(moduleSemYear.equals("Y1S1") || moduleSemYear.equals("Y1S2"));
        }
    }

    @Test
    public void execute_findGrades_success() {
        Set<Grade> grades = new HashSet<>();
        grades.add(new Grade("A+"));
        grades.add(new Grade("A"));

        ModuleCodePredicate predicate =
                new ModuleCodePredicate(false, "", new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), grades, new HashSet<>());
        FindCommand findCommand = new FindCommand(predicate, new ArrayList<>());
        findCommand.execute(populatedModel);

        assertEquals(5, populatedModel.getFilteredModuleList().size());

        for (Module module : populatedModel.getFilteredModuleList()) {
            String grade = module.getGrade().toString();
            assertTrue(grade.equals("A+") || grade.equals("A"));
        }
    }

    @Test
    public void execute_findTags_success() {
        populatedModel.addModule(CS2109S);
        populatedModel.addModule(CS2102);
        populatedModel.addModule(CS2103T);

        /* SINGLE TAG */
        Set<Tag> singleTag = new HashSet<>();
        singleTag.add(new Tag("Computer Science Foundation"));

        ModuleCodePredicate singleTagPredicate =
                new ModuleCodePredicate(false, "", new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>(), singleTag);
        FindCommand singleTagFindCommand = new FindCommand(singleTagPredicate, new ArrayList<>());
        singleTagFindCommand.execute(populatedModel);

        assertEquals(4, populatedModel.getFilteredModuleList().size());

        for (Module module : populatedModel.getFilteredModuleList()) {
            Set<Tag> moduleTags = module.getTags();
            moduleTags.contains("CSF");
        }


        /* MULTIPLE TAGS */
        Set<Tag> multipleTags = new HashSet<>();
        multipleTags.add(new Tag("Computer Science Foundation"));
        multipleTags.add(new Tag("Computer Science Breadth and Depth"));

        ModuleCodePredicate multipleTagsPredicate =
                new ModuleCodePredicate(false, "", new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>(), multipleTags);
        FindCommand multipleTagsFindCommand = new FindCommand(multipleTagsPredicate, new ArrayList<>());
        multipleTagsFindCommand.execute(populatedModel);

        assertEquals(1, populatedModel.getFilteredModuleList().size());

        for (Module module : populatedModel.getFilteredModuleList()) {
            Set<Tag> moduleTags = module.getTags();
            moduleTags.contains("CSF");
            moduleTags.contains("CSBD");
        }
    }

    @Test
    public void execute_findMultipleFilters_success() {
        populatedModel.addModule(CS2109S);
        populatedModel.addModule(CS2102);
        populatedModel.addModule(CS2103T);

        Set<String> codePrefixes = new HashSet<>();
        codePrefixes.add("CS");

        Set<SemYear> semYears = new HashSet<>();
        semYears.add(new SemYear("Y1S1"));

        Set<Grade> grades = new HashSet<>();
        grades.add(new Grade("A+"));

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("Computer Science Foundation"));

        ModuleCodePredicate predicate =
                new ModuleCodePredicate(false, "", codePrefixes,
                        new HashSet<>(), semYears, grades, tags);
        FindCommand findCommand = new FindCommand(predicate, new ArrayList<>());
        findCommand.execute(populatedModel);

        assertEquals(1, populatedModel.getFilteredModuleList().size());

        assertEquals("CS1231", populatedModel.getFilteredModuleList().get(0).getCode().toString());
    }

    @Test
    public void equals() {
        ModuleCodePredicate firstPredicate =
                new ModuleCodePredicate(true, CS2100.getCode().toString(), new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
        ModuleCodePredicate secondPredicate =
                new ModuleCodePredicate(true, ST2334.getCode().toString(), new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());

        FindCommand findFirstCommand = new FindCommand(firstPredicate, new ArrayList<>());
        FindCommand findSecondCommand = new FindCommand(secondPredicate, new ArrayList<>());

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate, new ArrayList<>());
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different person -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }
}
