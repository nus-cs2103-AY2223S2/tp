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
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_DECK = "Default";
    private Question question;
    private Answer answer;
    private Set<Tag> tags;
    private Deck deck;


    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        question = new Question(DEFAULT_NAME);
        answer = new Answer(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        deck = new Deck(DEFAULT_DECK);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code cardToCopy}.
     */
    public PersonBuilder(Card cardToCopy) {
        question = cardToCopy.getQuestion();
        answer = cardToCopy.getAnswer();
        tags = new HashSet<>(cardToCopy.getTags());
        deck = cardToCopy.getDeck();
    }

    /**
     * Sets the {@code Question} of the {@code Card} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.question = new Question(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Card} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code Card} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.answer = new Answer(address);
        return this;
    }

    /**
     * Sets the {@code Deck} of the {@code Card} that we are building.
     */
    public PersonBuilder withDeck(String deckName) {
        this.deck = new Deck(deckName);
        return this;
    }

    public Card build() {
        return new Card(question, answer, tags, deck);
    }

}
