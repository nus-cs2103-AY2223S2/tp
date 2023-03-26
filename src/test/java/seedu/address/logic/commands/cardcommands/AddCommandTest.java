package seedu.address.logic.commands.cardcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.CardBuilder.DEFAULT_DECK;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.commandresult.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.MasterDeck;
import seedu.address.model.Model;
import seedu.address.model.ModelState;
import seedu.address.model.ReadOnlyMasterDeck;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.card.Card;
import seedu.address.model.deck.Deck;
import seedu.address.model.review.Review;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AddCardDescriptorBuilder;
import seedu.address.testutil.CardBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_cardAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCardAdded modelStub = new ModelStubAcceptingCardAdded();
        Card validCard = new CardBuilder().build(); // Default Card
        AddCardDescriptorBuilder cardDescBuilder = new AddCardDescriptorBuilder(validCard);
        CommandResult commandResult = new AddCommand(cardDescBuilder.build()).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validCard), commandResult.getFeedbackToUser());
        assertEquals(List.of(validCard), modelStub.cardsAdded);
    }

    @Test
    public void execute_duplicateCard_throwsCommandException() {
        ModelStub modelStub = new ModelStubWithDuplicatedCard();
        Card validCard = new CardBuilder().build();
        AddCardDescriptorBuilder cardDescBuilder = new AddCardDescriptorBuilder(validCard);
        AddCommand addCommand = new AddCommand(cardDescBuilder.build());
        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_CARD, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_noSelectedDeck_throwsCommandException() {
        ModelStub modelStub = new ModelStubWithoutSelectedDeck();
        Card validCard = new CardBuilder().build();
        AddCardDescriptorBuilder cardDescBuilder = new AddCardDescriptorBuilder(validCard);
        AddCommand addCommand = new AddCommand(cardDescBuilder.build());
        assertThrows(AssertionError.class, AddCommand.MESSAGE_NO_SELECTED_DECK, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddCommand.AddCardDescriptor questionGravity = new AddCardDescriptorBuilder()
                .withQuestion("What is gravity").withAnswer("Not sure").withTag("easy").build();
        AddCommand.AddCardDescriptor questionPhoto = new AddCardDescriptorBuilder()
                .withQuestion("What is photosynthesis").withAnswer("Not sure either").withTag("hard").build();
        AddCommand addACommand = new AddCommand(questionGravity);
        AddCommand addBCommand = new AddCommand(questionPhoto);

        // same object -> returns true
        assertEquals(addACommand, addACommand);

        // same values -> returns true
        AddCommand addGravityCommandCopy = new AddCommand(questionGravity);
        assertEquals(addACommand, addGravityCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addACommand);

        // null -> returns false
        assertNotEquals(null, addACommand);

        // different card -> returns false
        assertNotEquals(addACommand, addBCommand);
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getMasterDeckFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMasterDeckFilePath(Path deckFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCard(Card card) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMasterDeck(ReadOnlyMasterDeck newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMasterDeck getMasterDeck() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCard(Card card) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCard(Card target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCard(Card target, Card editedCard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Card> getFilteredCardList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Deck> getFilteredDeckList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCardList(Predicate<Card> predicate) {
            return; // AddCommand does call updateFilteredCardList method
        }

        @Override
        public void updateFilteredDeckList(Predicate<Deck> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        /* NEWLY ADDED COMMANDS TO SUPPORT DECK LIST */
        @Override
        public Optional<Deck> getSelectedDeck() {
            return Optional.of(new Deck("Default"));
        }

        @Override
        public void addDeck(Deck deck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDeck(Deck deck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeck(Deck target, Deck editedDeck) {
            throw new AssertionError("This method should not be called.");
        }

        public void moveCards(Deck oldDeck, Deck newDeck) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void deleteDeck(Deck key) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void selectDeck(Index deckIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unselectDeck() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getSelectedDeckName() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getDeckSize(int deckIndex) {
            throw new AssertionError("This method should not be called.");
        }

        public Optional<Review> getReview() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void reviewDeck(Index deckIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNumCardsPerReview(int limit) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Pair<String, String> > getReviewStatsList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void endReview() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getReviewDeckName() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Pair<String, String> > getReviewDeckNameList() {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void flipCard() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markWrong() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markCorrect() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean goToPrevCard() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean goToNextCard() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ModelState getState() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void tagCurrentCardInReview(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isReviewCardFlipped() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a duplicated card.
     */
    private class ModelStubWithDuplicatedCard extends ModelStub {
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
     *
     * The selectedDeck is always the DEFAULT_DECK
     */
    private class ModelStubAcceptingCardAdded extends ModelStub {
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
    private class ModelStubWithoutSelectedDeck extends ModelStub {

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
