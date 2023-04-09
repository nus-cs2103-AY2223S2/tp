package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.ChallengeRating;

/**
 * Jackson-friendly version of {@link ChallengeRating}
 */
public class JsonAdaptedChallengeRating {
    private final double rating;

    /**
     * Constructs a {@code JsonAdaptedChallengeRating} with the given progression details.
     */
    @JsonCreator
    public JsonAdaptedChallengeRating(@JsonProperty("rating") double rating) {
        this.rating = rating;
    }

    /**
     * Converts a given {@code ChallengeRating} into this class for Jackson use.
     */
    public JsonAdaptedChallengeRating(ChallengeRating cr) {
        this.rating = cr.getRating();
    }

    /**
     * Converts this Jackson-friendly adapted ChallengeRating object into the model's {@code ChallengeRating} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ChallengeRating.
     */
    public ChallengeRating toModalType() throws IllegalValueException {
        return new ChallengeRating(rating);
    }

}
