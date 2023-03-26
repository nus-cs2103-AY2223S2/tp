package seedu.address.testutil;

import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;
import seedu.address.model.deck.Deck;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Card objects.
 */
public class CardBuilder {

    public static final String DEFAULT_QUESTION = "What is a default question";
    public static final String DEFAULT_ANSWER = "A default question is a standard, "
            + "common question used to provide basic information or understanding";

    public static final String DEFAULT_TAG = "Hard";
    public static final String DEFAULT_DECK = "Default";
    private Question question;
    private Answer answer;
    private Tag tag;
    private Deck deck;


    /**
     * Creates a {@code CardBuilder} with the default details.
     */
    public CardBuilder() {
        question = new Question(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
        tag = new Tag(DEFAULT_TAG);
        deck = new Deck(DEFAULT_DECK);
    }

    /**
     * Initializes the CardBuilder with the data of {@code cardToCopy}.
     */
    public CardBuilder(Card cardToCopy) {
        question = cardToCopy.getQuestion();
        answer = cardToCopy.getAnswer();
        tag = cardToCopy.getTag();
        deck = cardToCopy.getDeck();
    }

    /**
     * Sets the {@code Question} of the {@code Card} that we are building.
     */
    public CardBuilder withQuestion(String question) {
        this.question = new Question(question);
        return this;
    }

    /**
     * Sets the {@code Tag} of the {@code Card} that we are building.
     */
    public CardBuilder withTag(String tagName) {
        this.tag = new Tag(tagName);
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code Card} that we are building.
     */
    public CardBuilder withAnswer(String answer) {
        this.answer = new Answer(answer);
        return this;
    }

    /**
     * Sets the {@code Deck} of the {@code Card} that we are building.
     */
    public CardBuilder withDeck(String deckName) {
        this.deck = new Deck(deckName);
        return this;
    }

    public Card build() {
        return new Card(question, answer, tag, deck);
    }

}
