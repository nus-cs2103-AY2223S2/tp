package seedu.powercards.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.powercards.commons.exceptions.IllegalValueException;
import seedu.powercards.model.card.Answer;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.card.Question;
import seedu.powercards.model.deck.Deck;
import seedu.powercards.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Card}.
 */
class JsonAdaptedCard {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Card's %s field is missing!";

    private final String question;
    private final String answer;
    private final String tag;
    private final String deck;

    /**
     * Constructs a {@code JsonAdaptedCard} with the given card details.
     */
    @JsonCreator
    public JsonAdaptedCard(@JsonProperty("question") String question, @JsonProperty("answer") String address,
                           @JsonProperty("tag") String tag, @JsonProperty("deck") String deck) {
        this.question = question;
        this.answer = address;
        this.tag = tag;
        this.deck = deck;
    }

    /**
     * Converts a given {@code Card} into this class for Jackson use.
     */
    public JsonAdaptedCard(Card source) {
        question = source.getQuestion().question;
        answer = source.getAnswer().answer;
        tag = source.getTagName();
        deck = source.getDeck().getDeckName();
    }

    /**
     * Converts this Jackson-friendly adapted card object into the model's {@code Card} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted card.
     */
    public Card toModelType() throws IllegalValueException {
        final Question modelQuestion = toModelQuestion();
        final Answer modelAnswer = toModelAnswer();
        final Tag modelTag = toModelTag();
        final Deck modelDeck = new Deck(deck);

        return new Card(modelQuestion, modelAnswer, modelTag, modelDeck);
    }

    private Question toModelQuestion() throws IllegalValueException {
        if (question == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Question.class.getSimpleName())
            );
        }
        if (!Question.isValidQuestion(question)) {
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }
        return new Question(question);
    }

    private Answer toModelAnswer() throws IllegalValueException {
        if (answer == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName())
            );
        }
        if (!Answer.isValidAnswer(answer)) {
            throw new IllegalValueException(Answer.MESSAGE_CONSTRAINTS);
        }
        return new Answer(answer);
    }

    private Tag toModelTag() throws IllegalValueException {
        if (tag == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Tag.class.getSimpleName()));
        }

        Tag modelTag;
        if (tag.equalsIgnoreCase("untagged") || Tag.isValidTagName(tag)) {
            modelTag = new Tag(Tag.TagName.valueOf(tag.toUpperCase()));
        } else {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return modelTag;
    }

}
