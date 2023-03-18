package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.card.Card;
import seedu.address.model.deck.Deck;

/**
 * Adds a card to the selected deck.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a card to the selected deck. "
            + "Parameters: "
            + PREFIX_QUESTION + "QUESTION "
            + PREFIX_ANSWER + "ANSWER "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_QUESTION + "When was the RSA (Rivest–Shamir–Adleman) algorithm invented? "
            + PREFIX_ANSWER + "1977 by Ron Rivest, Adi Shamir, and Leonard Adleman "
            + PREFIX_TAG + "CS2107";

    public static final String MESSAGE_SUCCESS = "New card added: %1$s";
    public static final String MESSAGE_DUPLICATE_CARD = "This card already exists in the master deck";
    public static final String MESSAGE_NO_SELECTED_DECK = "A deck must be selected before a card can be added";

    private final Card toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Card}
     */
    public AddCommand(Card card) {
        requireNonNull(card);
        toAdd = card;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Deck> selectedDeck = model.getSelectedDeck();
        if (model.hasCard(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CARD);
        }

        if (selectedDeck.isEmpty()) {
            throw new CommandException(MESSAGE_NO_SELECTED_DECK);
        }

        toAdd.setDeck(selectedDeck.get());
        model.addCard(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }

    //    /**
    //     * Stores the details to add a card without a deck assigned.
    //     */
    //    public static class AddCardDescriptor {
    //        private Question question;
    //        private Answer answer;
    //        private Set<Tag> tags;
    //
    //
    //        public AddCardDescriptor() {}
    //
    //        /**
    //         * Copy constructor.
    //         * A defensive copy of {@code tags} is used internally.
    //         */
    //        public AddCardDescriptor(AddCommand.AddCardDescriptor toCopy) {
    //            setQuestion(toCopy.question);
    //            setAnswer(toCopy.answer);
    //            setTags(toCopy.tags);
    //        }
    //
    //        public void setQuestion(Question question) {
    //            this.question = question;
    //        }
    //
    //        public Question getQuestion() {
    //            return question;
    //        }
    //
    //        public void setAnswer(Answer answer) {
    //            this.answer = answer;
    //        }
    //
    //        public Answer getAnswer() {
    //            return answer;
    //        }
    //
    //        /**
    //         * Sets {@code tags} to this object's {@code tags}.
    //         * A defensive copy of {@code tags} is used internally.
    //         */
    //        public void setTags(Set<Tag> tags) {
    //            this.tags = (tags != null) ? new HashSet<>(tags) : null;
    //        }
    //
    //        /**
    //         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
    //         * if modification is attempted.
    //         * Returns {@code Optional#empty()} if {@code tags} is null.
    //         */
    //        public Optional<Set<Tag>> getTags() {
    //            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    //        }
    //
    //        @Override
    //        public boolean equals(Object other) {
    //            // short circuit if same object
    //            if (other == this) {
    //                return true;
    //            }
    //
    //            // instanceof handles nulls
    //            if (!(other instanceof AddCommand.AddCardDescriptor)) {
    //                return false;
    //            }
    //
    //            // state check
    //            AddCommand.AddCardDescriptor e = (AddCommand.AddCardDescriptor) other;
    //
    //            return getQuestion().equals(e.getQuestion())
    //                    && getAnswer().equals(e.getAnswer())
    //                    && getTags().equals(e.getTags());
    //        }
    //    }
}
