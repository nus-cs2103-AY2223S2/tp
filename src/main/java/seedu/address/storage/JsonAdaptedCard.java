package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;
import seedu.address.model.deck.Deck;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Card}.
 */
class JsonAdaptedCard {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Card's %s field is missing!";

    private final String question;
    private final String answer;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String deck;

    /**
     * Constructs a {@code JsonAdaptedCard} with the given card details.
     */
    @JsonCreator
    public JsonAdaptedCard(@JsonProperty("question") String question, @JsonProperty("answer") String address,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged, @JsonProperty("deck") String deck) {
        this.question = question;
        this.answer = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.deck = deck;
    }

    /**
     * Converts a given {@code Card} into this class for Jackson use.
     */
    public JsonAdaptedCard(Card source) {
        question = source.getQuestion().question;
        answer = source.getAnswer().answer;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        deck = source.getDeck().map(Deck::getDeckName).get();
    }

    /**
     * Converts this Jackson-friendly adapted card object into the model's {@code Card} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted card.
     */
    public Card toModelType() throws IllegalValueException {
        final List<Tag> cardTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            cardTags.add(tag.toModelType());
        }

        if (question == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Question.class.getSimpleName())
            );
        }
        if (!Question.isValidQuestion(question)) {
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }
        final Question modelQuestion = new Question(question);

        if (answer == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName()));
        }
        if (!Answer.isValidAnswer(answer)) {
            throw new IllegalValueException(Answer.MESSAGE_CONSTRAINTS);
        }
        final Answer modelAnswer = new Answer(answer);

        final Set<Tag> modelTags = new HashSet<>(cardTags);
        final Deck modelDeck = new Deck(deck); // todo: any constraints on deck name?
        return new Card(modelQuestion, modelAnswer, modelTags, modelDeck);
    }

}
