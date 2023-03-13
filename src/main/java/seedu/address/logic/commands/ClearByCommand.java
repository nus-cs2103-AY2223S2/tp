package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Status;
import seedu.address.ui.ConfirmationDialog;

/**
 * Clears the address book entries by parameter specified.
 */
public class ClearByCommand extends Command {
    enum ParamType {
        JOBTITLE("Job title"), COMPANYNAME("Company name"), STATUS("Status");

        private String name;

        ParamType(String name) {
            this.name = name;
        }

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
            + COMMAND_WORD + " n/Meta Clears all application with COMPANY_NAME as Meta.\n"
            + COMMAND_WORD + " j/Software engineer Clears all application with JOB_TITLE as Software Engineer.\n"
            + COMMAND_WORD + " s/REJECTED Clears all rejected application (with STATUS as REJECTED).";

    public static final String MESSAGE_INVALID_PARAMETER = "Invalid param!";
    public static final String MESSAGE_CLEAR_SUCCESS = "All internship application with %s : %s has been cleared!";
    public static final String MESSAGE_CLEAR_FAILED = "Applications with %s \"%s\" Not Deleted";
    public static final String MESSAGE_NULL = "There is nothing to clear!";
    public static final String MESSAGE_FAILED = "Clear command cannot be executed!";
    public static final String MESSAGE_CLEAR_CONFIRMATION = "Are you sure you want to clear this: %s";
    public static final String MESSAGE_EMPTY_FILTERED_LIST = "There is no %s with keyword \"%s\"!";

    private CompanyName companyName = null;
    private JobTitle jobTitle = null;
    private Status status = null;
    private String param;
    private ConfirmationDialog confirmationDialog;

    private ParamType paramType;

    /**
     * Creates an ClearByCommand to delete all the relevant {@code company} internship
     */
    public ClearByCommand(CompanyName companyName) {
        this.companyName = companyName;
        param = companyName.fullName;
        paramType = ParamType.COMPANYNAME;
    }

    /**
     * Creates an ClearByCommand to delete all the relevant {@code jobTitle} internship
     */
    public ClearByCommand(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
        param = jobTitle.fullName;
        paramType = ParamType.JOBTITLE;
    }

    /**
     * Creates an ClearByCommand to delete all the relevant {@code status} internship
     */
    public ClearByCommand(Status status) {
        this.status = status;
        param = status.fullName;
        paramType = ParamType.STATUS;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternshipApplication> lastShownList = model.getFilteredInternshipList();

        if (lastShownList.size() == 0) {
            return new CommandResult(MESSAGE_NULL);
        }

        try {
            ParamType.valueOf(String.valueOf(paramType));
            List<InternshipApplication> filteredList = getFilteredList(lastShownList);

            if (filteredList.size() == 0) {
                return new CommandResult(String.format(MESSAGE_EMPTY_FILTERED_LIST, paramType.getName(), param));
            }

            confirmationDialog = new ConfirmationDialog((
                    String.format(MESSAGE_CLEAR_CONFIRMATION, param)));

            return deleteAll(model, filteredList, confirmationDialog.getConfirmationStatus());

        } catch (IllegalArgumentException e) {
            return new CommandResult(MESSAGE_FAILED);
        }

    }

    /**
     * Finds {@code param} and deletes relevant internship applications from the {@code model} data and returns result
     * message with respect to the user's action to {@code confirm}.
     */
    public CommandResult deleteAll(Model model, List<InternshipApplication> filteredList, boolean confirm) {
        if (confirm) {
            for (InternshipApplication application : filteredList) {
                model.deleteInternship(application);
            }

            return new CommandResult(String.format(MESSAGE_CLEAR_SUCCESS, paramType.getName(), param));
        }
        return new CommandResult(String.format(MESSAGE_CLEAR_FAILED, paramType.getName(), param));
    }

    private List<InternshipApplication> getFilteredList(List<InternshipApplication> lastShownList) {
        List<InternshipApplication> filteredList = lastShownList;
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
