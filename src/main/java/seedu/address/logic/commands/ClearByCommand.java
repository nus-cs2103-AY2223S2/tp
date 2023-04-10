package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.CompanyName;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.InternshipStatus;
import seedu.address.model.application.JobTitle;

/**
 * Clears the internship application entries by parameter specified.
 */
public class ClearByCommand extends Command {
    /**
     * An enum class to specify the clear_by types.
     */
    enum ParamType {
        JOBTITLE("Job title"), COMPANYNAME("Company name"), STATUS("Status");

        private String name;

        /**
         * Creates a string representation {@code name} for the respective ParamType.
         */
        ParamType(String name) {
            this.name = name;
        }

        /**
         * Getter for the string representation of the respective ParamType enum object.
         */
        public String getName() {
            return name;
        }
    }

    public static final String COMMAND_WORD = "clear_by";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clear all relevant entries from the internship tracker with specific keyword.\n"
            + "Clears all application with the specified keyword - COMPANY_NAME, JOB_TITLE or STATUS.\n"
            + "Three types of clear_by features are provided, but can only execute independently.\n"
            + "Examples: \n"
            + COMMAND_WORD + " " + PREFIX_COMPANY_NAME + "Meta Clears all application with COMPANY_NAME as Meta.\n"
            + COMMAND_WORD + " " + PREFIX_JOB_TITLE + "Software engineer Clears all application with JOB_TITLE as "
            + "Software Engineer.\n"
            + COMMAND_WORD + " " + PREFIX_STATUS
            + "REJECTED Clears all rejected application (with STATUS as REJECTED).";

    public static final String MESSAGE_INVALID_PARAMETER = "Invalid param!";
    public static final String MESSAGE_NO_PARAMETER = "Please provide clear_by parameter!";
    public static final String MESSAGE_CLEAR_SUCCESS = "All internship application with %s : %s has been cleared!";
    public static final String MESSAGE_NULL = "There is nothing to clear!";
    public static final String MESSAGE_FAILED = "Clear_by command cannot be executed!";
    public static final String MESSAGE_EMPTY_FILTERED_LIST = "There is no %s with keyword \"%s\"!";

    private CompanyName companyName = null;
    private JobTitle jobTitle = null;
    private InternshipStatus status = null;
    private String param;

    private ParamType paramType;

    /**
     * Creates a ClearByCommand to delete all the relevant applications that have company names match with
     * {@code companyName}.
     */
    public ClearByCommand(CompanyName companyName) {
        this.companyName = companyName;
        param = companyName.fullName;
        paramType = ParamType.COMPANYNAME;
    }

    /**
     * Creates a ClearByCommand to delete all the relevant applications that have jobTitles match with
     * {@code jobTitle}.
     */
    public ClearByCommand(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
        param = jobTitle.fullName;
        paramType = ParamType.JOBTITLE;
    }

    /**
     * Creates a ClearByCommand to delete all the relevant applications that have statuses match with
     * {@code status}.
     */
    public ClearByCommand(InternshipStatus status) {
        this.status = status;
        param = status.toString();
        paramType = ParamType.STATUS;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternshipApplication> lastShownList = model.getSortedFilteredInternshipList();

        if (lastShownList.size() == 0) {
            return new CommandResult(MESSAGE_NULL);
        }

        try {
            ParamType.valueOf(String.valueOf(paramType));
            List<InternshipApplication> filteredList = getFilteredList(lastShownList);

            if (filteredList.size() == 0) {
                return new CommandResult(String.format(MESSAGE_EMPTY_FILTERED_LIST, paramType.getName(), param));
            }

            for (InternshipApplication application : filteredList) {
                model.addInternshipToCache(application);
                model.deleteInternship(application);
            }

            return new CommandResult(String.format(MESSAGE_CLEAR_SUCCESS, paramType.getName(), param));

        } catch (IllegalArgumentException e) {
            return new CommandResult(MESSAGE_FAILED);
        }

    }

    private List<InternshipApplication> getFilteredList(List<InternshipApplication> lastShownList) {
        List<InternshipApplication> filteredList;
        switch (paramType) {
        case COMPANYNAME:
            filteredList = lastShownList
                    .stream()
                    .filter(a -> (a.getCompanyName()).equals(companyName))
                    .collect(Collectors.toList());
            break;
        case JOBTITLE:
            filteredList = lastShownList
                    .stream()
                    .filter(a -> (a.getJobTitle().equals(jobTitle)))
                    .collect(Collectors.toList());
            break;
        case STATUS:
            filteredList = lastShownList
                    .stream()
                    .filter(a -> (a.getStatus().equals(status)))
                    .collect(Collectors.toList());
            break;
        default:
            filteredList = new ArrayList<>();
        }
        return filteredList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof ClearByCommand)) {
            return false;
        }

        switch (paramType) {
        case COMPANYNAME:
            return companyName.equals(((ClearByCommand) other).companyName);
        case JOBTITLE:
            return jobTitle.equals(((ClearByCommand) other).jobTitle);
        case STATUS:
            return status.equals(((ClearByCommand) other).status);
        default:
            return false;
        }
    }
}
