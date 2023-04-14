package seedu.powercards.logic.commands.cardcommands;

import static java.util.Objects.requireNonNull;
import static seedu.powercards.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.powercards.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.powercards.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;

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
 * Adds a card to the selected deck.
 */
public class AddCardCommand extends Command {

    public static final String COMMAND_WORD = "addCard";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a card to the selected deck. "
            + "Parameters: "
            + PREFIX_QUESTION + "QUESTION "
            + PREFIX_ANSWER + "ANSWER "
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_QUESTION + "When was the RSA (Rivest–Shamir–Adleman) algorithm invented? "
            + PREFIX_ANSWER + "1977 by Ron Rivest, Adi Shamir, and Leonard Adleman "
            + PREFIX_TAG + "hard";

    public static final String MESSAGE_SUCCESS = "New card added: %1$s";
    public static final String MESSAGE_DUPLICATE_CARD = "A card with the same question "
            + "already exists in this selected deck.";
    public static final String MESSAGE_NO_SELECTED_DECK = "A deck must be selected before a card can be added";
    private final AddCardDescriptor cardDescriptor;

    /**
     * Creates an AddCardCommand to add the specified {@code AddCardDescriptor} as Card
     */
    public AddCardCommand(AddCardDescriptor cardDescriptor) {
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
                || (other instanceof AddCardCommand // instanceof handles nulls
                && cardDescriptor.equals(((AddCardCommand) other).cardDescriptor));
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
        public AddCardDescriptor(AddCardCommand.AddCardDescriptor toCopy) {
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
            return new Card(question, answer, tag, deck);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof AddCardCommand.AddCardDescriptor)) {
                return false;
            }

            // state check
            AddCardCommand.AddCardDescriptor otherDesc = (AddCardCommand.AddCardDescriptor) other;

            return question.equals(otherDesc.question)
                    && answer.equals(otherDesc.answer)
                    && tag == null ? otherDesc.tag == null : tag.equals(otherDesc.tag);
        }
    }
}
