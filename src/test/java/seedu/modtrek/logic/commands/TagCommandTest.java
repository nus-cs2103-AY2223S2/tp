package seedu.modtrek.logic.commands;

import static seedu.modtrek.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modtrek.testutil.TypicalModules.CS2100;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.parser.ParserUtil;
import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.ModelManager;
import seedu.modtrek.model.UserPrefs;
import seedu.modtrek.model.module.Module;

class TagCommandTest {
    private Model model = new ModelManager(getTypicalDegreeProgression(),
            new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws ParseException {
        Module taggedModule = CS2100;
        TagCommand tagCommand = new TagCommand(CS2100.getCode(),
                ParserUtil.parseTag("computer science breadth and depth"));

        String expectedMessage = String.format(TagCommand.MESSAGE_ADD_TAG_SUCCESS,
                taggedModule.getCode().toString());

        Model expectedModel = new ModelManager(new DegreeProgression(model
                .getDegreeProgression()), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), taggedModule);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

}
