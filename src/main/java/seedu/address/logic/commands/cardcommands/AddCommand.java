package seedu.address.logic.commands.cardcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.commandresult.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;
import seedu.address.model.deck.Deck;
import seedu.address.model.tag.Tag;

/**
 * Adds a card to the selected deck.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a card to the selected deck. "
            + "Parameters: "
            + PREFIX_QUESTION + "QUESTION "
            + PREFIX_ANSWER + "ANSWER "
            + PREFIX_TAG + "TAG...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_QUESTION + "When was the RSA (Rivest–Shamir–Adleman) algorithm invented? "
            + PREFIX_ANSWER + "1977 by Ron Rivest, Adi Shamir, and Leonard Adleman "
            + PREFIX_TAG + "hard";

    public static final String MESSAGE_SUCCESS = "New card added: %1$s";
    public static final String MESSAGE_DUPLICATE_CARD = "This card already exists in the this selected deck.";
    public static final String MESSAGE_NO_SELECTED_DECK = "A deck must be selected before a card can be added";
    private final AddCardDescriptor cardDescriptor;

    /**
     * Creates an AddCommand to add the specified {@code AddCardDescriptor} as Card
     */
    public AddCommand(AddCardDescriptor cardDescriptor) {
        requireNonNull(cardDescriptor);
        this.cardDescriptor = new AddCardDescriptor(cardDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Deck> selectedDeck = model.getSelectedDeck();

        assert selectedDeck.isPresent() : MESSAGE_NO_SELECTED_DECK;

        cardDescriptor.setDeck(selectedDeck.get());
        Card toAdd = cardDescriptor.buildCard();

        if (model.hasCard(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CARD);
        }

        model.addCard(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && cardDescriptor.equals(((AddCommand) other).cardDescriptor));
    }

    /**
     * Stores the details to add a card without a deck assigned.
     */
    public static class AddCardDescriptor {
        private Question question;
        private Answer answer;
        private Tag tag;
        private Deck deck;

        public AddCardDescriptor() {}

        /**
         * Constructor for a Card Descriptor to be used to create a card instance.
         * @param question Question of the card to create
         * @param answer Answer of the card to create
         * @param tag Tag of the card to create
         */
        public AddCardDescriptor(Question question, Answer answer, Tag tag) {
            this.question = question;
            this.answer = answer;
            this.tag = tag;
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public AddCardDescriptor(AddCommand.AddCardDescriptor toCopy) {
            setQuestion(toCopy.question);
            setAnswer(toCopy.answer);
            setTag(toCopy.tag);
            setDeck(toCopy.deck);
        }

        public void setQuestion(Question question) {
            this.question = question;
        }

        public Question getQuestion() {
            return question;
        }

        public void setAnswer(Answer answer) {
            this.answer = answer;
        }

        public Answer getAnswer() {
            return answer;
        }

        public void setDeck(Deck deck) {
            this.deck = deck;
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
        public Tag getTag() {
            return tag;
        }

        /**
         * Creates an immutable card that is associated with the given deck.
         *
         * @return The new Card instance.
         */
        public Card buildCard() {
            if (tag == null) {
                return new Card(question, answer, deck);
            }
            return new Card(question, answer, tag, deck);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof AddCommand.AddCardDescriptor)) {
                return false;
            }

            // state check
            AddCommand.AddCardDescriptor e = (AddCommand.AddCardDescriptor) other;
            if (getTag() != null) {
                return getQuestion().equals(e.getQuestion())
                        && getAnswer().equals(e.getAnswer())
                        && getTag().equals(e.getTag());
            }
            return getQuestion().equals(e.getQuestion())
                    && getAnswer().equals(e.getAnswer());
        }
    }
}
