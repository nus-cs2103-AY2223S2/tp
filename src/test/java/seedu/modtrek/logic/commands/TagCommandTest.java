package seedu.modtrek.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_TAG_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_TAG_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modtrek.testutil.TypicalModules.CS2100;
import static seedu.modtrek.testutil.TypicalModules.ST2334;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.parser.ParserUtil;
import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.ModelManager;
import seedu.modtrek.model.UserPrefs;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.tag.Tag;

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

    @Test
    public void equals() {
        final TagCommand standardCommand = new TagCommand(CS2100.getCode(), new Tag(VALID_TAG_CS1101S));

        // same values -> returns true
        TagCommand commandWithSameValues = new TagCommand(CS2100.getCode(), new Tag(VALID_TAG_CS1101S));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new TagCommand(ST2334.getCode(), new Tag(VALID_TAG_CS1101S))));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new TagCommand(CS2100.getCode(), new Tag(VALID_TAG_MA2002))));
    }

}
