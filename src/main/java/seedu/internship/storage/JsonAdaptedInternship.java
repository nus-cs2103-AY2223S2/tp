package seedu.internship.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.internship.commons.exceptions.IllegalValueException;
import seedu.internship.model.internship.Comment;
import seedu.internship.model.internship.CompanyName;
import seedu.internship.model.internship.Date;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.Role;
import seedu.internship.model.internship.Status;
import seedu.internship.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Internship}.
 */
class JsonAdaptedInternship {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Internship's %s field is missing!";

    private final String companyName;
    private final String role;
    private final String status;
    private final String date;
    private final String comment;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedInternship} with the given internship details.
     */
    @JsonCreator
    public JsonAdaptedInternship(@JsonProperty("companyName") String companyName, @JsonProperty("role") String role,
            @JsonProperty("status") String status, @JsonProperty("date") String date,
            @JsonProperty("comment") String comment, @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.companyName = companyName;
        this.role = role;
        this.status = status;
        this.date = date;
        if (comment == null) {
            this.comment = "";
        } else {
            this.comment = comment;
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Internship} into this class for Jackson use.
     */
    public JsonAdaptedInternship(Internship source) {
        companyName = source.getCompanyName().fullCompanyName;
        role = source.getRole().fullRole;
        status = source.getStatus().toString();
        date = source.getDate().fullDate;
        comment = source.getComment().commentContent;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted internship object into the model's {@code Internship} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted internship
     */
    public Internship toModelType() throws IllegalValueException {
        final List<Tag> internshipTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            internshipTags.add(tag.toModelType());
        }

        if (companyName == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidCompanyName(companyName)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        final CompanyName modelCompanyName = new CompanyName(companyName);

        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName()));
        }
        if (!Role.isValidRole(role)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS);
        }
        final Role modelRole = new Role(role);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);


        if (!Comment.isValidComment(comment)) {
            throw new IllegalValueException(Comment.MESSAGE_CONSTRAINTS);
        }
        final Comment modelComment = new Comment(comment);

        final Set<Tag> modelTags = new HashSet<>(internshipTags);
        return new Internship(modelCompanyName, modelRole, modelStatus, modelDate, modelComment, modelTags);
    }
}
