package seedu.powercards.testutil;

import seedu.powercards.model.card.Answer;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.card.Question;
import seedu.powercards.model.deck.Deck;
import seedu.powercards.model.tag.Tag;
import seedu.powercards.model.tag.Tag.TagName;

/**
 * A utility class to help with building Card objects.
 */
public class CardBuilder {

    public static final String DEFAULT_QUESTION = "What is a default question";
    public static final String DEFAULT_ANSWER = "A default question is a standard, "
            + "common question used to provide basic information or understanding";

    public static final TagName DEFAULT_TAG = TagName.HARD;
    public static final String DEFAULT_DECK = "Default";
    private Question question;
    private Answer answer;
    private Tag tag;
    private Deck deck;
    private boolean isFlipped;


    /**
     * Creates a {@code CardBuilder} with the default details.
     */
    public CardBuilder() {
        question = new Question(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
        tag = new Tag(DEFAULT_TAG);
        deck = new Deck(DEFAULT_DECK);
        isFlipped = true;
    }

    /**
     * Initializes the CardBuilder with the data of {@code cardToCopy}.
     */
    public CardBuilder(Card cardToCopy) {
        question = cardToCopy.getQuestion();
        answer = cardToCopy.getAnswer();
        tag = cardToCopy.getTag();
        deck = cardToCopy.getDeck();
        isFlipped = cardToCopy.isFlipped();
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
        this.tag = new Tag(TagName.valueOf(tagName.toUpperCase()));
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

    /**
     * Sets the flip attribute of the card we are building.
     */
    public CardBuilder withFlipAttribute(boolean isFlipped) {
        this.isFlipped = isFlipped;
        return this;
    }

    /**
     * Builds the card.
     * @return the card built.
     */
    public Card build() {
        Card toBuild = new Card(question, answer, tag, deck);
        return new Card(toBuild, isFlipped);
    }

}
