package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.ultron.model.opening.Company;
import seedu.ultron.model.opening.Email;
import seedu.ultron.model.opening.Keydate;
import seedu.ultron.model.opening.Opening;
import seedu.ultron.model.opening.Position;
import seedu.ultron.model.opening.Remark;
import seedu.ultron.model.opening.Status;

/**
 * A utility class to help with building Opening objects.
 */
public class OpeningBuilder {

    public static final String DEFAULT_POSITION = "Software Engineer";
    public static final String DEFAULT_COMPANY = "Google";
    public static final String DEFAULT_EMAIL = "google_hr@gmail.com";
    public static final String DEFAULT_STATUS = "APPLIED";
    public static final String DEFAULT_REMARK = "I really want this job.";

    private Position position;
    private Company company;
    private Email email;
    private Status status;
    private Remark remark;
    private List<Keydate> keydates;

    /**
     * Creates a {@code OpeningBuilder} with the default details.
     */
    public OpeningBuilder() {
        position = new Position(DEFAULT_POSITION);
        company = new Company(DEFAULT_COMPANY);
        email = new Email(DEFAULT_EMAIL);
        status = new Status(DEFAULT_STATUS);
        remark = new Remark(DEFAULT_REMARK);
        keydates = new ArrayList<>();
    }

    /**
     * Initializes the OpeningBuilder with the data of {@code openingToCopy}.
     */
    public OpeningBuilder(Opening openingToCopy) {
        position = openingToCopy.getPosition();
        company = openingToCopy.getCompany();
        email = openingToCopy.getEmail();
        status = openingToCopy.getStatus();
        remark = openingToCopy.getRemark();
        keydates = new ArrayList<>(openingToCopy.getKeydates());
    }

    /**
     * Sets the {@code Position} of the {@code Opening} that we are building.
     */
    public OpeningBuilder withPosition(String position) {
        this.position = new Position(position);
        return this;
    }

    /**
     * Parses the {@code keydates} into a {@code Set<Keydate>} and set it to the {@code Opening} that we are building.
     */
    public OpeningBuilder withKeydates(ArrayList<String>... dates) {
        this.keydates = Stream.of(dates).map(date -> new Keydate(date.get(0), date.get(1)))
                .collect(Collectors.toList());
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Opening} that we are building.
     */
    public OpeningBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Opening} that we are building.
     */
    public OpeningBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code Opening} that we are building.
     */
    public OpeningBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Status} that we are building.
     */
    public OpeningBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    public Opening build() {
        return new Opening(position, company, email, status, remark, keydates);
    }
}
