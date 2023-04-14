package seedu.powercards.logic.commands.cardcommands;

import static java.util.Objects.requireNonNull;
import static seedu.powercards.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.powercards.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.powercards.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Optional;

import seedu.powercards.commons.core.Messages;
import seedu.powercards.commons.core.index.Index;
import seedu.powercards.commons.util.CollectionUtil;
import seedu.powercards.logic.commands.Command;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.logic.commands.exceptions.CommandException;
import seedu.powercards.model.Model;
import seedu.powercards.model.card.Answer;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.card.Question;
import seedu.powercards.model.deck.Deck;
import seedu.powercards.model.tag.Tag;

/**
 * Edits the details of an existing card in the selected deck.
 */
public class EditCardCommand extends Command {

    public static final String COMMAND_WORD = "editCard";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the card identified "
            + "by the index number used in the displayed card list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_QUESTION + "QUESTION] "
            + "[" + PREFIX_ANSWER + "ANSWER] "
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_QUESTION + "When was the RSA (Rivest–Shamir–Adleman) algorithm invented? "
            + PREFIX_ANSWER + "1977 by Ron Rivest, Adi Shamir, and Leonard Adleman "
            + PREFIX_TAG + "hard";

    public static final String MESSAGE_EDIT_CARD_SUCCESS = "Edited Card: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CARD = "A card with the same question "
            + "already exists in this selected deck.";

    private final Index index;
    private final EditCardDescriptor editCardDescriptor;

    /**
     * @param index of the card in the selected deck to edit
     * @param editCardDescriptor details to edit the card with
     */
    public EditCardCommand(Index index, EditCardDescriptor editCardDescriptor) {
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
        Tag updatedTag = editCardDescriptor.getTag().orElse(cardToEdit.getTag());

        assert cardToEdit.getDeck() != null : "The edited card must be inside a deck";
        Deck updatedDeck = cardToEdit.getDeck();

        return new Card(updatedQuestion, updatedAnswer, updatedTag, updatedDeck);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCardCommand)) {
            return false;
        }

        // state check
        EditCardCommand e = (EditCardCommand) other;
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
        private Tag tag;
        private Deck deck;

        public EditCardDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditCardDescriptor(EditCardDescriptor toCopy) {
            setQuestion(toCopy.question);
            setAnswer(toCopy.answer);
            setTag(toCopy.tag);
            setDeck(toCopy.deck);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(question, answer, tag);
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

        public void setDeck(Deck deck) {
            this.deck = deck;
        }

        public Optional<Deck> getDeck() {
            return Optional.ofNullable(deck);
        }

        /**
         * Sets {@code tag} to this object's {@code tag}.
         */
        public void setTag(Tag tag) {
            this.tag = tag;
        }

        /**
         * Returns an unmodifiable tag, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         */
        public Optional<Tag> getTag() {
            return Optional.ofNullable(tag);
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
                    && getTag().equals(e.getTag());
        }
    }
}
