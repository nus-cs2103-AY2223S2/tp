package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.internship.Comment;
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
    public static final String DEFAULT_STATUS = "interview";
    public static final String DEFAULT_DATE = "2023-02-01";
    public static final String DEFAULT_COMMENT = "I love Apple!";

    private CompanyName companyName;
    private Role role;
    private Status status;
    private Date date;
    private Comment comment;
    private Set<Tag> tags;

    /**
     * Creates a {@code InternshipBuilder} with the default details.
     */
    public InternshipBuilder() {
        companyName = new CompanyName(DEFAULT_COMPANY_NAME);
        role = new Role(DEFAULT_ROLE);
        status = new Status(DEFAULT_STATUS);
        date = new Date(DEFAULT_DATE);
        comment = new Comment(DEFAULT_COMMENT);
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
        comment = internshipToCopy.getComment();
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
     * Sets the {@code Comment} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withComment(String comment) {
        if (comment != null) {
            this.comment = new Comment(comment);
        } else {
            this.comment = new Comment("NA");
        }
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
        this.status = new Status(status);
        return this;
    }

    public Internship build() {
        return new Internship(companyName, role, status, date, comment, tags);
    }

}
