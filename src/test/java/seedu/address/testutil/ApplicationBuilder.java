package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.application.Application;
import seedu.address.model.application.CompanyEmail;
import seedu.address.model.application.CompanyName;
import seedu.address.model.application.Role;
import seedu.address.model.application.Status;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.util.ApplicationSampleDataUtil;

/**
 * A utility class to help with building Application objects.
 */
public class ApplicationBuilder {

    public static final String DEFAULT_ROLE = "Software Engineer Intern";
    public static final String DEFAULT_COMPANY_NAME = "Meta";
    public static final String DEFAULT_COMPANY_EMAIL = "apply@meta.com";
    public static final String DEFAULT_STATUS = "applied";
    public static final String DEFAULT_TASK_DESCRIPTION = "Online Assessment";
    public static final String DEFAULT_TASK_DEADLINE = "01-04-2023";

    private Role role;
    private CompanyName companyName;
    private CompanyEmail companyEmail;
    private Status status;
    private Task task;
    private Set<Tag> tags;

    /**
     * Creates a {@code ApplicationBuilder} with the default details.
     */
    public ApplicationBuilder() {
        role = new Role(DEFAULT_ROLE);
        companyName = new CompanyName(DEFAULT_COMPANY_NAME);
        companyEmail = new CompanyEmail(DEFAULT_COMPANY_EMAIL);
        status = new Status(DEFAULT_STATUS);
        task = null;
        tags = new HashSet<>();
    }

    /**
     * Initializes the ApplicationBuilder with the data of {@code applicationToCopy}.
     */
    public ApplicationBuilder(Application applicationToCopy) {
        role = applicationToCopy.getRole();
        companyName = applicationToCopy.getCompanyName();
        companyEmail = applicationToCopy.getCompanyEmail();
        status = applicationToCopy.getStatus();
        task = applicationToCopy.getTask();
        tags = new HashSet<>(applicationToCopy.getTags());
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Application} that we are building.
     */
    public ApplicationBuilder withTags(String ... tags) {
        this.tags = ApplicationSampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code Company Name} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withCompanyName(String companyName) {
        this.companyName = new CompanyName(companyName);
        return this;
    }

    /**
     * Sets the {@code Company Email} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withCompanyEmail(String companyEmail) {
        this.companyEmail = new CompanyEmail(companyEmail);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withDeadline(String deadline) {
        this.task = new Task(new Deadline(deadline), task.getDescription());
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withDescription(String description) {
        this.task = new Task(task.getDeadline(), new Description(description));
        return this;
    }

    /**
     * Sets the {@code Task} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withTask(String deadline, String description) {
        this.task = new Task(new Deadline(deadline), new Description(description));
        return this;
    }

    /**
     * Nullifies the {@code Task} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withoutTask() {
        this.task = null;
        return this;
    }

    public Application build() {
        return new Application(role, companyName, companyEmail, status, task, tags);
    }

}
