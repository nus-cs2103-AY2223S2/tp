package seedu.internship.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.internship.model.internship.Company;
import seedu.internship.model.internship.Description;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.Position;
import seedu.internship.model.internship.Status;
import seedu.internship.model.tag.Tag;
import seedu.internship.model.util.SampleDataUtil;


/**
 * A utility class to help with building Internship objects.
 */
public class InternshipBuilder {
    public static final String DEFAULT_POSITION = "Architect";
    public static final String POSITION_FOR_FINDTEST = "Software Engineer";
    public static final String DEFAULT_COMPANY = "ABC";
    public static final String COMPANY_FOR_FINDTEST = "Apple";
    public static final Integer DEFAULT_STATUS = 1;
    public static final String DEFAULT_DESCRIPTION = "The is a dummy internship. Interview date on DDMMYYYY.";

    // private Id id;
    private Position position;
    private Position positionForFindTest;
    private Company company;
    private Company companyForFindTest;
    private Status status;
    private Description description;
    private Set<Tag> tags;
    private Set<Tag> tagsForFindTest;

    /**
     * Creates a {@code InternshipBuilder} with the default details.
     */
    public InternshipBuilder() {
        // id = new Id(DEFAULT_ID);
        position = new Position(DEFAULT_POSITION);
        positionForFindTest = new Position(POSITION_FOR_FINDTEST);
        company = new Company(DEFAULT_COMPANY);
        companyForFindTest = new Company(COMPANY_FOR_FINDTEST);
        status = new Status(DEFAULT_STATUS);
        description = new Description(DEFAULT_DESCRIPTION);
        tags = new HashSet<>();
        tagsForFindTest = new HashSet<>();
        tagsForFindTest.add(new Tag("priority"));
        tagsForFindTest.add(new Tag("fun"));
    }

    /**
     * Initializes the InternshipBuilder with the data of {@code internshipToCopy}.
     */
    public InternshipBuilder(Internship internshipToCopy) {
        position = internshipToCopy.getPosition();
        company = internshipToCopy.getCompany();
        status = internshipToCopy.getStatus();
        description = internshipToCopy.getDescription();
        tags = new HashSet<>(internshipToCopy.getTags());
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Internship} that we are building.
     */
    public InternshipBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Position} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withPosition(String position) {
        this.position = new Position(position);
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withStatus(Integer status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }


    public Internship build() {
        return new Internship(position, company, status, description, tags);
    }

    public Internship buildForFind() {
        return new Internship(positionForFindTest, companyForFindTest, status, description, tagsForFindTest);
    }

}

