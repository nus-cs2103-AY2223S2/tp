package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CARDS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.CardInDeckPredicate;
import seedu.address.model.card.Question;
import seedu.address.model.deck.Deck;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing card in the selected deck.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the card identified "
            + "by the index number used in the displayed card list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_QUESTION + "QUESTION] "
            + "[" + PREFIX_ANSWER + "ANSWER] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_EDIT_CARD_SUCCESS = "Edited Card: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CARD = "This card already exists in the master deck.";

    private final Index index;
    private final EditCardDescriptor editCardDescriptor;

    /**
     * @param index of the card in the selected deck to edit
     * @param editCardDescriptor details to edit the card with
     */
    public EditCommand(Index index, EditCardDescriptor editCardDescriptor) {
        requireNonNull(index);
        requireNonNull(editCardDescriptor);

        this.index = index;
        this.editCardDescriptor = new EditCardDescriptor(editCardDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Card> lastShownList = model.getFilteredCardList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }

        Card cardToEdit = lastShownList.get(index.getZeroBased());
        Card editedCard = createEditedCard(cardToEdit, editCardDescriptor);

        if (!cardToEdit.isSameCard(editedCard) && model.hasCard(editedCard)) {
            throw new CommandException(MESSAGE_DUPLICATE_CARD);
        }

        model.setCard(cardToEdit, editedCard);
        model.updateFilteredCardList(new CardInDeckPredicate(model.getSelectedDeck().get()));
        return new CommandResult(String.format(MESSAGE_EDIT_CARD_SUCCESS, editedCard));
    }

    /**
     * Creates and returns a {@code Card} with the details of {@code cardToEdit}
     * edited with {@code editCardDescriptor}.
     */
    private static Card createEditedCard(Card cardToEdit, EditCardDescriptor editCardDescriptor) {
        assert cardToEdit != null;

        Question updatedQuestion = editCardDescriptor.getQuestion().orElse(cardToEdit.getQuestion());
        Answer updatedAnswer = editCardDescriptor.getAnswer().orElse(cardToEdit.getAnswer());
        Set<Tag> updatedTags = editCardDescriptor.getTags().orElse(cardToEdit.getTags());
        Optional<Deck> updatedDeck = editCardDescriptor.getDeck().orElse(Optional.of(cardToEdit.getDeck().get()));

        return new Card(updatedQuestion, updatedAnswer, updatedTags, updatedDeck);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editCardDescriptor.equals(e.editCardDescriptor);
    }

    /**
     * Stores the details to edit the card with. Each non-empty field value will replace the
     * corresponding field value of the card.
     */
    public static class EditCardDescriptor {
        private Question question;
        private Answer answer;
        private Set<Tag> tags;
        private Optional<Deck> deck;

        public EditCardDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditCardDescriptor(EditCardDescriptor toCopy) {
            setQuestion(toCopy.question);
            setAnswer(toCopy.answer);
            setTags(toCopy.tags);
            setDeck(toCopy.deck);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(question, answer, tags);
        }

        public void setQuestion(Question question) {
            this.question = question;
        }

        public Optional<Question> getQuestion() {
            return Optional.ofNullable(question);
        }

        public void setAnswer(Answer answer) {
            this.answer = answer;
        }

        public Optional<Answer> getAnswer() {
            return Optional.ofNullable(answer);
        }
        public void setDeck(Optional<Deck> deck) {
            this.deck = deck;
        }
        public Optional<Optional<Deck>> getDeck() {
            return Optional.ofNullable(deck);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCardDescriptor)) {
                return false;
            }

            // state check
            EditCardDescriptor e = (EditCardDescriptor) other;

            return getQuestion().equals(e.getQuestion())
                    && getAnswer().equals(e.getAnswer())
                    && getTags().equals(e.getTags());
        }
    }
}
