package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.opening.Company;
import seedu.address.model.opening.Date;
import seedu.address.model.opening.Email;
import seedu.address.model.opening.Opening;
import seedu.address.model.opening.Position;
import seedu.address.model.opening.Remark;
import seedu.address.model.opening.Status;


/**
 * Jackson-friendly version of {@link Opening}.
 */
class JsonAdaptedOpening {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Opening's %s field is missing!";

    private final String position;
    private final String company;
    private final String email;
    private final String status;
    private final String remark;
    private final List<JsonAdaptedDate> dates = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedOpening} with the given Opening details.
     */
    @JsonCreator
    public JsonAdaptedOpening(@JsonProperty("Position") String position, @JsonProperty("Company") String company,
                              @JsonProperty("email") String email, @JsonProperty("Status") String status,
                              @JsonProperty("remark") String remark,
                              @JsonProperty("Dateged") List<JsonAdaptedDate> dates) {
        this.position = position;
        this.company = company;
        this.email = email;
        this.status = status;
        this.remark = remark;
        if (dates != null) {
            this.dates.addAll(dates);
        }
    }

    /**
     * Converts a given {@code Opening} into this class for Jackson use.
     */
    public JsonAdaptedOpening(Opening source) {
        position = source.getPosition().fullPosition;
        company = source.getCompany().fullCompany;
        email = source.getEmail().value;
        status = source.getStatus().fullStatus;
        remark = source.getRemark().value;
        dates.addAll(source.getDates().stream()
                .map(JsonAdaptedDate::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted Opening object into the model's {@code Opening} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Opening.
     */
    public Opening toModelType() throws IllegalValueException {
        final List<Date> openingDates = new ArrayList<>();
        for (JsonAdaptedDate date : dates) {
            openingDates.add(date.toModelType());
        }

        if (position == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Position.class.getSimpleName()));
        }
        if (!Position.isValidPosition(position)) {
            throw new IllegalValueException(Position.MESSAGE_CONSTRAINTS);
        }
        final Position modelPosition = new Position(position);

        if (company == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName()));
        }
        if (!Company.isValidCompany(company)) {
            throw new IllegalValueException(Company.MESSAGE_CONSTRAINTS);
        }
        final Company modelCompany = new Company(company);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);


        final Set<Date> modelDates = new HashSet<>(openingDates);
        return new Opening(modelPosition, modelCompany, modelEmail, modelStatus, modelRemark, modelDates);
    }

}
