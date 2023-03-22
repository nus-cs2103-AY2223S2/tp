package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.application.Application;
import seedu.address.model.application.CompanyEmail;
import seedu.address.model.application.CompanyName;
import seedu.address.model.application.Role;
import seedu.address.model.application.Status;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Application}.
 */
class JsonAdaptedApplication {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Application's %s field is missing!";

    private final String companyName;
    private final String companyEmail;
    private final String status;
    private final String role;
    private final String taskDescription;
    private final String taskDeadline;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedApplication} with the given application details.
     */
    @JsonCreator
    public JsonAdaptedApplication(@JsonProperty("companyName") String companyName,
                                  @JsonProperty("companyEmail") String companyEmail,
                                  @JsonProperty("status") String status,
                                  @JsonProperty("role") String role,
                                  @JsonProperty("taskDescription") String taskDescription,
                                  @JsonProperty("taskDeadline") String taskDeadline,
                                  @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.status = status;
        this.role = role;
        this.taskDescription = taskDescription;
        this.taskDeadline = taskDeadline;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Application} into this class for Jackson use.
     */
    public JsonAdaptedApplication(Application source) {
        companyName = source.getCompanyName().name;
        status = source.getStatus().value.toString();
        companyEmail = source.getCompanyEmail().value;
        role = source.getRole().roleApplied;
        if (source.hasTask()) {
            taskDescription = source.getTask().getDescription().value;
            taskDeadline = source.getTask().getDeadline().toString();
        } else {
            taskDescription = null;
            taskDeadline = null;
        }
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Application} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted application.
     */
    public Application toModelType() throws IllegalValueException {
        final List<Tag> applicationTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            applicationTags.add(tag.toModelType());
        }

        if (companyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidName(companyName)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        final CompanyName modelCompanyName = new CompanyName(companyName);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);

        if (companyEmail == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyEmail.class.getSimpleName()));
        }
        if (!CompanyEmail.isValidEmail(companyEmail)) {
            throw new IllegalValueException(CompanyEmail.MESSAGE_CONSTRAINTS);
        }
        final CompanyEmail modelCompanyEmail = new CompanyEmail(companyEmail);

        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName()));
        }
        if (!Role.isValidRole(role)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS);
        }
        final Role modelRole = new Role(role);

        if (taskDeadline == null && taskDescription != null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (taskDeadline != null && taskDescription == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }

        Task modelTask = null;
        if (taskDescription != null) {
            if (!Description.isValidDescription(taskDescription)) {
                throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
            }
            if (!Deadline.isValidDate(taskDeadline)) {
                throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
            }
            if (!Deadline.isValidDeadline(taskDeadline)) {
                throw new IllegalValueException(Deadline.DEADLINE_HAS_PASSED);
            }
            final Description modelDescription = new Description(taskDescription);
            final Deadline modelDeadline = new Deadline(taskDeadline);
            modelTask = new Task(modelDeadline, modelDescription);
        }
        final Set<Tag> modelTags = new HashSet<>(applicationTags);
        return new Application(modelRole, modelCompanyName, modelCompanyEmail, modelStatus, modelTask, modelTags);
    }

}
