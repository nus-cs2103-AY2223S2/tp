package seedu.modtrek.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_TAG_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_TAG_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modtrek.testutil.TypicalModules.CS2100;
import static seedu.modtrek.testutil.TypicalModules.ST2334;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import java.util.HashSet;
import java.util.Set;

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
        Set<Tag> taggedModuleTags = CS2100.getModifiableTags();
        taggedModuleTags.add(new Tag("computer science breadth and depth"));
        Module taggedModule = new Module(CS2100.getCode(), CS2100.getCredit(),
                CS2100.getSemYear(), taggedModuleTags, CS2100.getGrade());
        Set<String> tagsToAdd = new HashSet<>();
        tagsToAdd.add("computer science breadth and depth");
        TagCommand tagCommand = new TagCommand(CS2100.getCode(),
                true, ParserUtil.parseTags(tagsToAdd));


        String expectedMessage = String.format(TagCommand.MESSAGE_ADD_TAG_SUCCESS,
                taggedModule.getCode().toString());

        Model expectedModel = new ModelManager(new DegreeProgression(model
                .getDegreeProgression()), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), taggedModule);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Set<Tag> tagToAdd = new HashSet<>();
        tagToAdd.add(new Tag(VALID_TAG_CS1101S));
        final TagCommand standardCommand = new TagCommand(CS2100.getCode(), true, tagToAdd);

        // same values -> returns true
        TagCommand commandWithSameValues = new TagCommand(CS2100.getCode(), true, tagToAdd);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new TagCommand(ST2334.getCode(), true, tagToAdd)));

        // different descriptor -> returns false
        Set<Tag> tagToAdd2 = new HashSet<>();
        tagToAdd2.add(new Tag(VALID_TAG_MA2002));
        assertFalse(standardCommand.equals(new TagCommand(CS2100.getCode(), true, tagToAdd2)));
    }

}
