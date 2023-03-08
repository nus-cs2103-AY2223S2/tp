package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.listing.JobDescription;
import seedu.address.model.listing.JobTitle;
import seedu.address.model.listing.Listing;

/**
 * Jackson-friendly version of {@link Listing}.
 */
class JsonAdaptedListing {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Listing's %s field is missing!";

    private final String title;
    private final String description;

    private final List<JsonAdaptedApplicants> applicants = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedListing(@JsonProperty("title") String title, @JsonProperty("description") String description,
            @JsonProperty("applicants") List<JsonAdaptedApplicants> applicants) {
        this.title = title;
        this.description = description;
        if (applicants != null) {
            this.applicants.addAll(applicants);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedListing(Listing source) {
        title = source.getTitle().fullTitle;
        description = source.getDescription().fullDescription;
        applicants.addAll(source.getApplicants().stream()
                .map(JsonAdaptedApplicants::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Listing toModelType() throws IllegalValueException {
        final ArrayList<Applicant> listingApplicants = new ArrayList<>();
        for (JsonAdaptedApplicants applicant : applicants) {
            listingApplicants.add(applicant.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                JobTitle.class.getSimpleName()));
        }
        if (!JobTitle.isValidTitle(title)) {
            throw new IllegalValueException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        final JobTitle modelTitle = new JobTitle(title);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                JobDescription.class.getSimpleName()));
        }
        if (!JobDescription.isValidDescription(description)) {
            throw new IllegalValueException(JobDescription.MESSAGE_CONSTRAINTS);
        }
        final JobDescription modelDescription = new JobDescription(description);

        return new Listing(modelTitle, modelDescription, listingApplicants);
    }

}
