package seedu.address.logic.commands.reviewcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.commandresult.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

public class FlipCardCommandTest {

    private Model model;
    private Command commandUnderTest;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        commandUnderTest = new FlipCardCommand();
    }

    @Test
    public void execute_flipCard_success() throws seedu.address.logic.commands.exceptions.CommandException {
        model.reviewDeck(INDEX_FIFTH, List.of(new Tag.TagName[]{Tag.TagName.UNTAGGED}));
        CommandResult expectedCommandResult = new CommandResult(FlipCardCommand.MESSAGE_FLIP_SUCCESS);
        assertEquals(expectedCommandResult, commandUnderTest.execute(model));
    }

    @Test
    public void execute_unflipCard_success() throws seedu.address.logic.commands.exceptions.CommandException {
        model.reviewDeck(INDEX_FIFTH, List.of(new Tag.TagName[]{Tag.TagName.UNTAGGED}));
        commandUnderTest.execute(model);
        CommandResult expectedCommandResult = new CommandResult(FlipCardCommand.MESSAGE_UNFLIP_SUCCESS);
        assertEquals(expectedCommandResult, commandUnderTest.execute(model));
    }
}
