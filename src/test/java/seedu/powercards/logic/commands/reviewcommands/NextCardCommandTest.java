package seedu.powercards.logic.commands.reviewcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIFTH;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.UserPrefs;
import seedu.powercards.model.tag.Tag;

public class NextCardCommandTest {
    private Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());

    @Test
    public void execute_nextCard_success() {
        model.reviewDeck(INDEX_FIRST, List.of(new Tag.TagName[]{Tag.TagName.HARD}));

        CommandResult expectedCommandResult = new CommandResult(NextCardCommand.MESSAGE_SUCCESS);
        assertEquals(expectedCommandResult, new NextCardCommand().execute(model));
    }

    @Test
    public void execute_noMoreNextCard_throwsCommandException() {
        model.reviewDeck(INDEX_FIFTH, List.of(new Tag.TagName[]{Tag.TagName.UNTAGGED}));

        CommandResult expectedCommandResult = new CommandResult(NextCardCommand.MESSAGE_NO_MORE_NEXT_CARD);
        assertEquals(expectedCommandResult, new NextCardCommand().execute(model));
    }
}
