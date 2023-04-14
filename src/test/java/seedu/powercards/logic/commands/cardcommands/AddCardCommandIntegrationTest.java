package seedu.powercards.logic.commands.cardcommands;

import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.powercards.commons.core.index.Index;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.UserPrefs;
import seedu.powercards.model.card.Card;
import seedu.powercards.testutil.AddCardDescriptorBuilder;
import seedu.powercards.testutil.CardBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCardCommand}.
 */
public class AddCardCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        model.selectDeck(Index.fromOneBased(1)); // Deck("Programming Concept")
    }

    @Test
    public void execute_newCard_success() {
        Card validCard = new CardBuilder().build(); // Deck("Default") Tag("hard")
        AddCardCommand.AddCardDescriptor cardDescriptor = new AddCardDescriptorBuilder(validCard).build();

        Model expectedModel = new ModelManager(model.getMasterDeck(), new UserPrefs());
        expectedModel.selectDeck(Index.fromOneBased(1));
        cardDescriptor.setDeck(expectedModel.getSelectedDeck().get()); // Change Deck to selectedDeck
        expectedModel.addCard(cardDescriptor.buildCard());

        assertCommandSuccess(new AddCardCommand(cardDescriptor), model,
                String.format(AddCardCommand.MESSAGE_SUCCESS, validCard), expectedModel);
    }

    @Test
    public void execute_duplicateCard_throwsCommandException() {
        Card cardInList = model.getMasterDeck().getCardList().get(0); // This card also belongs to the selectedDeck
        AddCardCommand.AddCardDescriptor cardDescriptor = new AddCardDescriptorBuilder(cardInList).build();
        assertCommandFailure(new AddCardCommand(cardDescriptor), model, AddCardCommand.MESSAGE_DUPLICATE_CARD);
    }

}
