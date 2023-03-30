package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.internship.MainApp;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.InternshipContainsKeywordsPredicate;





/**
 * Deletes an internship identified using it's displayed index from InternBuddy.
 */
public class DeleteFieldCommand extends Command {

    public static final String COMMAND_WORD = "delete-field";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all internships containing at least one of the inputs for every different field "
            + "(COMPANY_NAME, ROLE, STATUS, DATE or TAG) that you have provided.\n"
            + "Fields: [" + PREFIX_COMPANY_NAME + "COMPANY_NAME]... "
            + "[" + PREFIX_ROLE + "ROLE]... "
            + "[" + PREFIX_DATE + "DATE]... "
            + "[" + PREFIX_STATUS + "STATUS]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY_NAME + "Apple "
            + PREFIX_COMPANY_NAME + "Google ";

    public static final String MESSAGE_DELETE_INTERNSHIP_SUCCESS = "%1$d internship/s have been deleted!";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);
    private final InternshipContainsKeywordsPredicate predicate;
    /**
     * Create a DeleteFieldCommand object from the list of user supplied indexes and a predicate
     * @param predicate A user supplied predicate to filter internships.
     */
    public DeleteFieldCommand(InternshipContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();

        int previousSize = lastShownList.size();

        if (!this.predicate.isEmpty()) {
            lastShownList = lastShownList.stream()
                    .filter(this.predicate)
                    .collect(Collectors.toList());
        }

        logger.info(String.format("Deleting internships: %s", Arrays.toString(lastShownList.toArray())));

        for (Internship internshipToDelete: lastShownList) {
            //Delete internship
            model.deleteInternship(internshipToDelete);
            //Update right panel
            if (internshipToDelete.equals(model.getSelectedInternship())) {
                model.updateSelectedInternship(null);
            }
        }

        int newSize = model.getFilteredInternshipList().size();

        return new CommandResult(String.format(MESSAGE_DELETE_INTERNSHIP_SUCCESS, previousSize - newSize));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteFieldCommand // instanceof handles nulls
                && predicate.equals(((DeleteFieldCommand) other).predicate));
    }
}
