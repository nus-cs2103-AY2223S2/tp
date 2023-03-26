package seedu.address.logic.commands.cardcommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalMasterDeck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.card.Card;
import seedu.address.testutil.AddCardDescriptorBuilder;
import seedu.address.testutil.CardBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        model.selectDeck(Index.fromOneBased(1)); // Deck("Programming Concept")
    }

    @Test
    public void execute_newCard_success() {
        Card validCard = new CardBuilder().build(); // Deck("Default") Tag("hard")
        AddCommand.AddCardDescriptor cardDescriptor = new AddCardDescriptorBuilder(validCard).build();

        Model expectedModel = new ModelManager(model.getMasterDeck(), new UserPrefs());
        expectedModel.selectDeck(Index.fromOneBased(1));
        cardDescriptor.setDeck(expectedModel.getSelectedDeck().get()); // Change Deck to selectedDeck
        expectedModel.addCard(cardDescriptor.buildCard());

        assertCommandSuccess(new AddCommand(cardDescriptor), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validCard), expectedModel);
    }

    @Test
    public void execute_duplicateCard_throwsCommandException() {
        Card cardInList = model.getMasterDeck().getCardList().get(0); // This card also belongs to the selectedDeck
        AddCommand.AddCardDescriptor cardDescriptor = new AddCardDescriptorBuilder(cardInList).build();
        assertCommandFailure(new AddCommand(cardDescriptor), model, AddCommand.MESSAGE_DUPLICATE_CARD);
    }

}
