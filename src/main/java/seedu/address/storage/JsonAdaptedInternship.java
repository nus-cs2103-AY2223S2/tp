package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Id;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Position;
import seedu.address.model.internship.Status;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Internship}.
 */
public class JsonAdaptedInternship {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Internship's %s field is missing!";

    private final String positionName;
    private final String companyName;
    private final String statusId;
    private final String messageDescription;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedInternship} with the given internship details.
     */
    @JsonCreator
    public JsonAdaptedInternship(@JsonProperty("positionName") String positionName,
                                 @JsonProperty("companyName") String companyName,
                                 @JsonProperty("statusId") String statusId,
                                 @JsonProperty("messageDescription") String messageDescription,
                                 @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.positionName = positionName;
        this.companyName = companyName;
        this.statusId = statusId;
        this.messageDescription = messageDescription;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Internship} into this class for Jackson use.
     */
    public JsonAdaptedInternship(Internship source) {
        positionName = source.getPosition().positionName;
        companyName = source.getCompany().companyName;
        statusId = String.valueOf(source.getStatus().statusId);
        messageDescription = source.getDescription().descriptionMessage;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted internship object into the model's {@code Internship} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted internship.
     */
    public Internship toModelType() throws IllegalValueException {
        final List<Tag> internshipTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            internshipTags.add(tag.toModelType());
        }

        if (positionName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Position.class.getSimpleName()));
        }
        if (!Position.isValidPosition(positionName)) {
            throw new IllegalValueException(Position.MESSAGE_CONSTRAINTS);
        }
        final Position modelPositionName = new Position(positionName);

        if (companyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName()));
        }
        if (!Company.isValidCompany(companyName)) {
            throw new IllegalValueException(Company.MESSAGE_CONSTRAINTS);
        }
        final Company modelCompanyName = new Company(companyName);

        if (statusId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(Integer.valueOf(statusId))) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatusId = new Status(Integer.valueOf(statusId));

        if (messageDescription == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(messageDescription)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelMessageDescription = new Description(messageDescription);

        final Set<Tag> modelTags = new HashSet<>(internshipTags);
        return new Internship(modelPositionName, modelCompanyName, new Id("1"), modelStatusId, modelMessageDescription, modelTags);
    }

}
