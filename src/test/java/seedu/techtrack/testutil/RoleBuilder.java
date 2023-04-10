package seedu.techtrack.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.techtrack.model.role.Company;
import seedu.techtrack.model.role.Contact;
import seedu.techtrack.model.role.Deadline;
import seedu.techtrack.model.role.Email;
import seedu.techtrack.model.role.Experience;
import seedu.techtrack.model.role.JobDescription;
import seedu.techtrack.model.role.Name;
import seedu.techtrack.model.role.Role;
import seedu.techtrack.model.role.Salary;
import seedu.techtrack.model.role.Website;
import seedu.techtrack.model.util.SampleDataUtil;
import seedu.techtrack.model.util.tag.Tag;

/**
 * A utility class to help with building Role objects.
 */
public class RoleBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_COMPANY = "GOOGLE";
    public static final String DEFAULT_JOBDESCRIPTION = "Software Engineer Intern @ DBS";
    public static final String DEFAULT_SALARY = "4000";
    public static final String DEFAULT_DEADLINE = "2023-10-20";
    public static final String DEFAULT_WEBSITE = "www.google.com";
    public static final String DEFAULT_EXPERIENCE = "C - 1 Year";

    private Name name;
    private Contact contact;
    private Email email;
    private Company company;
    private JobDescription jd;
    private Set<Tag> tags;
    private Salary salary;
    private Deadline deadline;
    private Website website;
    private Experience experience;

    /**
     * Creates a {@code RoleBuilder} with the default details.
     */
    public RoleBuilder() {
        name = new Name(DEFAULT_NAME);
        contact = new Contact(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        company = new Company(DEFAULT_COMPANY);
        jd = new JobDescription(DEFAULT_JOBDESCRIPTION);
        tags = new HashSet<>();
        salary = new Salary(DEFAULT_SALARY);
        deadline = new Deadline(DEFAULT_DEADLINE);
        website = new Website(DEFAULT_WEBSITE);
        experience = new Experience(DEFAULT_EXPERIENCE);
    }

    /**
     * Initializes the RoleBuilder with the data of {@code roleToCopy}.
     */
    public RoleBuilder(Role roleToCopy) {
        name = roleToCopy.getName();
        contact = roleToCopy.getContact();
        email = roleToCopy.getEmail();
        company = roleToCopy.getCompany();
        jd = roleToCopy.getJobDescription();
        salary = roleToCopy.getSalary();
        deadline = roleToCopy.getDeadline();
        tags = new HashSet<>(roleToCopy.getTags());
        website = roleToCopy.getWebsite();
        experience = roleToCopy.getExperience();
    }

    /**
     * Sets the {@code Name} of the {@code Role} that we are building.
     */
    public RoleBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Role} that we are building.
     */
    public RoleBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code Role} that we are building.
     */
    public RoleBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Sets the {@code JobDescription} of the {@code Role} that we are building.
     */
    public RoleBuilder withJobDescription(String jd) {
        this.jd = new JobDescription(jd);
        return this;
    }

    /**
     * Sets the {@code Contact} of the {@code Role} that we are building.
     */
    public RoleBuilder withPhone(String phone) {
        this.contact = new Contact(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Role} that we are building.
     */
    public RoleBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Salary} of the {@code Role} that we are building.
     */
    public RoleBuilder withSalary(String salary) {
        this.salary = new Salary(salary);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Role} that we are building.
     */
    public RoleBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code Website} of the {@code Role} that we are building.
     */
    public RoleBuilder withWebsite(String website) {
        this.website = new Website(website);
        return this;
    }

    /**
     * Sets the {@code Experience} of the {@code Role} that we are building.
     */
    public RoleBuilder withExperience(String experience) {
        this.experience = new Experience(experience);
        return this;
    }


    public Role build() {
        return new Role(name, contact, email, company, jd, tags, website, salary, deadline, experience);
    }

}
