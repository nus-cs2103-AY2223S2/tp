package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntities.getTypicalReroll;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;


/**
 * Contains integration tests (interaction with the Model) and unit tests for FilterCommand.
 */
public class FilterCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalReroll(), new UserPrefs());
        expectedModel = new ModelManager(model.getReroll(), new UserPrefs());


    }

    @Test
    public void execute_listIsFilteredOne_showsSameList() {

        addPredicateHelper(expectedModel, Arrays.asList("girl"));
        assertCommandSuccess(new FilterCommand(Arrays.asList("girl")),
                model, FilterCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFilteredList_showsSameList() {

        addPredicateHelper(expectedModel, Arrays.asList("girl", "boy"));
        assertCommandSuccess(new FilterCommand(Arrays.asList("girl", "boy")),
                model, FilterCommand.MESSAGE_SUCCESS, expectedModel);
    }

    public void addPredicateHelper(Model model, List<String> tagList) {
        expectedModel.addPredicate(entity -> true);
        model.addPredicate(entity -> {
            boolean result = true;
            for (String tag : tagList) {
                if (!entity.getTags().contains(new Tag(tag))) {
                    result = false;
                }
            }

            return result;
        });

    }

}
