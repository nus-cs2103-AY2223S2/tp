package seedu.powercards.logic.commands.reviewcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIFTH;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.powercards.logic.commands.Command;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.UserPrefs;
import seedu.powercards.model.tag.Tag;

public class FlipCardCommandTest {

    private Model model;
    private Command commandUnderTest;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        commandUnderTest = new FlipCardCommand();
    }

    @Test
    public void execute_flipCard_success() throws seedu.powercards.logic.commands.exceptions.CommandException {
        model.reviewDeck(INDEX_FIFTH, List.of(new Tag.TagName[]{Tag.TagName.UNTAGGED}));
        CommandResult expectedCommandResult = new CommandResult(FlipCardCommand.MESSAGE_FLIP_SUCCESS);
        assertEquals(expectedCommandResult, commandUnderTest.execute(model));
    }

    @Test
    public void execute_unflipCard_success() throws seedu.powercards.logic.commands.exceptions.CommandException {
        model.reviewDeck(INDEX_FIFTH, List.of(new Tag.TagName[]{Tag.TagName.UNTAGGED}));
        commandUnderTest.execute(model);
        CommandResult expectedCommandResult = new CommandResult(FlipCardCommand.MESSAGE_UNFLIP_SUCCESS);
        assertEquals(expectedCommandResult, commandUnderTest.execute(model));
    }
}
