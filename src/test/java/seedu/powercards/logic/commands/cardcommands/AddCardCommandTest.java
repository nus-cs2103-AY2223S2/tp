package seedu.powercards.logic.commands.cardcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.powercards.model.tag.Tag.TagName.HARD;
import static seedu.powercards.testutil.Assert.assertThrows;
import static seedu.powercards.testutil.CardBuilder.DEFAULT_DECK;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.logic.commands.exceptions.CommandException;
import seedu.powercards.model.MasterDeck;
import seedu.powercards.model.ModelStub;
import seedu.powercards.model.ReadOnlyMasterDeck;
import seedu.powercards.model.card.Answer;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.card.Question;
import seedu.powercards.model.deck.Deck;
import seedu.powercards.model.tag.Tag;
import seedu.powercards.testutil.AddCardDescriptorBuilder;
import seedu.powercards.testutil.CardBuilder;

public class AddCardCommandTest {

    @Test
    public void constructor_nullCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCardCommand(null));
    }

    @Test
    public void execute_cardAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCardAdded modelStub = new ModelStubAcceptingCardAdded();
        Card validCard = new CardBuilder().build(); // Default Card
        AddCardDescriptorBuilder cardDescBuilder = new AddCardDescriptorBuilder(validCard);
        CommandResult commandResult = new AddCardCommand(cardDescBuilder.build()).execute(modelStub);

        assertEquals(String.format(AddCardCommand.MESSAGE_SUCCESS, validCard), commandResult.getFeedbackToUser());
        assertEquals(List.of(validCard), modelStub.cardsAdded);
    }

    @Test
    public void execute_duplicateCard_throwsCommandException() {
        ModelStub modelStub = new ModelStubWithDuplicatedCard();
        Card validCard = new CardBuilder().build();
        AddCardDescriptorBuilder cardDescBuilder = new AddCardDescriptorBuilder(validCard);
        AddCardCommand addCardCommand = new AddCardCommand(cardDescBuilder.build());
        assertThrows(CommandException.class, AddCardCommand.MESSAGE_DUPLICATE_CARD, ()
                -> addCardCommand.execute(modelStub));
    }

    @Test
    public void execute_noSelectedDeck_throwsCommandException() {
        ModelStub modelStub = new ModelStubWithoutSelectedDeck();
        Card validCard = new CardBuilder().build();
        AddCardDescriptorBuilder cardDescBuilder = new AddCardDescriptorBuilder(validCard);
        AddCardCommand addCardCommand = new AddCardCommand(cardDescBuilder.build());
        assertThrows(AssertionError.class, AddCardCommand.MESSAGE_NO_SELECTED_DECK, ()
                -> addCardCommand.execute(modelStub));
    }

    @Test
    public void constructor_validDescriptor_success() {
        Question question = new Question("What is the capital of France?");
        Answer answer = new Answer("Paris");
        Tag tag = new Tag(HARD);

        AddCardCommand.AddCardDescriptor descriptor = new AddCardCommand.AddCardDescriptor(question, answer, tag);

        assertEquals(question, descriptor.getQuestion());
        assertEquals(answer, descriptor.getAnswer());
        assertEquals(tag, descriptor.getTag());
    }

    @Test
    public void equals() {
        AddCardCommand.AddCardDescriptor questionGravity = new AddCardDescriptorBuilder()
                .withQuestion("What is gravity").withAnswer("Not sure").withTag("easy").build();
        AddCardCommand.AddCardDescriptor questionPhoto = new AddCardDescriptorBuilder()
                .withQuestion("What is photosynthesis").withAnswer("Not sure either").withTag("hard").build();
        AddCardCommand addACommand = new AddCardCommand(questionGravity);
        AddCardCommand addBCommand = new AddCardCommand(questionPhoto);

        // same object -> returns true
        assertEquals(addACommand, addACommand);

        assertNotEquals(addACommand, addBCommand);

        // same values -> returns true
        AddCardCommand addGravityCommandCopy = new AddCardCommand(questionGravity);
        assertEquals(addACommand, addGravityCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addACommand);

        // null -> returns false
        assertNotEquals(null, addACommand);

        // different card -> returns false
        assertNotEquals(addACommand, addBCommand);
    }

    /**
     * A Model stub that contains a duplicated card.
     */
    private static class ModelStubWithDuplicatedCard extends ModelStub {
        final Deck selectedDeck = new Deck(DEFAULT_DECK);

        ModelStubWithDuplicatedCard() {
        }

        @Override
        public Optional<Deck> getSelectedDeck() {
            return Optional.of(selectedDeck);
        }

        @Override
        public boolean hasCard(Card card) {
            return true;
        }
    }

    /**
     * A Model stub that always accept the card being added.
     * The selectedDeck is always the DEFAULT_DECK
     */
    private static class ModelStubAcceptingCardAdded extends ModelStub {
        final Deck selectedDeck = new Deck(DEFAULT_DECK);
        final ArrayList<Card> cardsAdded = new ArrayList<>();

        @Override
        public Optional<Deck> getSelectedDeck() {
            return Optional.of(selectedDeck);
        }

        @Override
        public boolean hasCard(Card card) {
            requireNonNull(card);
            return cardsAdded.stream().anyMatch(card::isSameCard);
        }

        @Override
        public void addCard(Card card) {
            requireNonNull(card);
            cardsAdded.add(card);
        }

        @Override
        public ReadOnlyMasterDeck getMasterDeck() {
            return new MasterDeck();
        }
    }

    /**
     * A Model stub that does not have any deck selected.
     */
    private static class ModelStubWithoutSelectedDeck extends ModelStub {

        @Override
        public boolean hasCard(Card card) {
            return false;
        }

        @Override
        public Optional<Deck> getSelectedDeck() {
            return Optional.empty();
        }
    }

}
