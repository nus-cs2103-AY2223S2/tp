package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;
import seedu.address.model.deck.Deck;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Card objects.
 */
public class CardBuilder {

    public static final String DEFAULT_QUESTION = "What is a default question";
    public static final String DEFAULT_ANSWER = "A default question is a standard, "
            + "common question used to provide basic information or understanding";
    public static final String DEFAULT_DECK = "Default";
    private Question question;
    private Answer answer;
    private Set<Tag> tags;
    private Deck deck;


    /**
     * Creates a {@code CardBuilder} with the default details.
     */
    public CardBuilder() {
        question = new Question(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
        tags = new HashSet<>();
        deck = new Deck(DEFAULT_DECK);
    }

    /**
     * Initializes the CardBuilder with the data of {@code cardToCopy}.
     */
    public CardBuilder(Card cardToCopy) {
        question = cardToCopy.getQuestion();
        answer = cardToCopy.getAnswer();
        tags = new HashSet<>(cardToCopy.getTags());
        deck = cardToCopy.getDeck().get();
    }

    /**
     * Sets the {@code Question} of the {@code Card} that we are building.
     */
    public CardBuilder withQuestion(String question) {
        this.question = new Question(question);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Card} that we are building.
     */
    public CardBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
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
        return new Card(question, answer, tags, deck);
    }

}
