package seedu.address.logic.commands.reviewcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.commandresult.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

public class PreviousCardCommandTest {
    private Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());

    @Test
    public void execute_previousCard_success() {
        model.reviewDeck(INDEX_FIRST, List.of(new Tag.TagName[]{Tag.TagName.HARD}));
        model.goToNextCard();
        CommandResult expectedCommandResult = new CommandResult(PreviousCardCommand.MESSAGE_SUCCESS);
        assertEquals(expectedCommandResult, new PreviousCardCommand().execute(model));
    }

    @Test
    public void execute_noMorePreviousCard_throwsCommandException() {
        model.reviewDeck(INDEX_FIFTH, List.of(new Tag.TagName[]{Tag.TagName.UNTAGGED}));

        CommandResult expectedCommandResult = new CommandResult(PreviousCardCommand.MESSAGE_NO_MORE_PREV_CARD);
        assertEquals(expectedCommandResult, new PreviousCardCommand().execute(model));
    }
}
