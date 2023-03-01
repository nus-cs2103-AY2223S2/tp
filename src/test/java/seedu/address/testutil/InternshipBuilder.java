package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.internship.Address;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Date;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.Status;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Internship objects.
 */
public class InternshipBuilder {

    public static final String DEFAULT_COMPANY_NAME = "Apple";
    public static final String DEFAULT_ROLE = "Mobile Developer";
    public static final Status DEFAULT_STATUS = Status.INTERVIEW;
    public static final String DEFAULT_DATE= "2023-02-01";

    private CompanyName companyName;
    private Role role;
    private Status status;
    private Date date;
    private Set<Tag> tags;

    /**
     * Creates a {@code InternshipBuilder} with the default details.
     */
    public InternshipBuilder() {
        companyName = new CompanyName(DEFAULT_COMPANY_NAME);
        role = new Role(DEFAULT_ROLE);
        status = DEFAULT_STATUS;
        date = new Date(DEFAULT_DATE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the InternshipBuilder with the data of {@code internshipToCopy}.
     */
    public InternshipBuilder(Internship internshipToCopy) {
        companyName = internshipToCopy.getCompanyName();
        role = internshipToCopy.getRole();
        status = internshipToCopy.getStatus();
        date = internshipToCopy.getDate();
        tags = new HashSet<>(internshipToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withCompanyName(String companyName) {
        this.companyName = new CompanyName(companyName);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Internship} that we are building.
     */
    public InternshipBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Status} that we are building.
     */
    public InternshipBuilder withStatus(String status) {
        switch (status) {
        case "new":
            this.status = Status.NEW;
            break;
        case "applied":
            this.status = Status.APPLIED;
            break;
        case "assessment":
            this.status = Status.ASSESSMENT;
            break;
        case "interview":
            this.status = Status.INTERVIEW;
            break;
        case "offered":
            this.status = Status.OFFERED;
            break;
        case "rejected":
            this.status = Status.REJECTED;
            break;
        default:
            this.status = null;
        }
        return this;
    }

    public Internship build() {
        return new Internship(companyName, role, status, date, tags);
    }

}
