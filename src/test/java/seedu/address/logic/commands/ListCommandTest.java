package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ListCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TypicalLectures;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    private ListCommand listCommand;
    private final Module module = new ModuleBuilder()
            .withCode("CS2040S").withName("Data Structures and Algorithms")
            .withTags("Heavy", "Math", "Analysis")
            .withLectures(TypicalLectures.CS2040S_WEEK_1, TypicalLectures.CS2040S_WEEK_2,
                    TypicalLectures.CS2040S_WEEK_3, TypicalLectures.CS2040S_WEEK_4,
                    TypicalLectures.CS2040S_WEEK_5, TypicalLectures.CS2040S_WEEK_6,
                    TypicalLectures.CS2040S_WEEK_7)
            .build();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTracker(), new UserPrefs());
        expectedModel = new ModelManager(model.getTracker(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS_MODULES, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS_MODULES, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsLectures() {
        ModuleCode moduleCode = module.getCode();
        String input = String.format("list /mod %s", moduleCode);
        try {
            listCommand = new ListCommandParser().parse(input);
            listCommand.execute(expectedModel);
        } catch (ParseException e) {
            assertNull(e);
        }
        String expectedString = String.format(ListCommand.MESSAGE_SUCCESS_LECTURES, moduleCode);
        assertCommandSuccess(listCommand, model, expectedString, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsVideos() {
        ModuleCode moduleCode = module.getCode();
        LectureName lectureName = module.getLectureList().get(0).getName();
        String input = String.format("list /mod %s /lec %s", moduleCode, lectureName);
        try {
            listCommand = new ListCommandParser().parse(input);
            listCommand.execute(expectedModel);
        } catch (ParseException e) {
            assertNull(e);
        }
        String expectedString = String.format(ListCommand.MESSAGE_SUCCESS_VIDEOS, moduleCode, lectureName);
        assertCommandSuccess(listCommand, model, expectedString, expectedModel);
    }
}
