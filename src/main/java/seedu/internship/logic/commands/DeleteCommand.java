package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.internship.MainApp;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.commons.core.Messages;
import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.InternshipContainsKeywordsPredicate;

/**
 * Deletes an internship identified using it's displayed index from InternBuddy.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all internships identified by the index numbers used in the displayed internship list.\n"
            + "If optional parameters are given, only the selected internships whose company names contain any"
            + " of the specified name, role, status, date and tag keywords will be deleted.\n"
            + "Parameters: [INDEX]... (must be a positive integer)\n"
            + "[" + PREFIX_COMPANY_NAME + "NAME_KEYWORD]... "
            + "[" + PREFIX_ROLE + "ROLE_KEYWORD]... "
            + "[" + PREFIX_DATE + "DATE_KEYWORD]... "
            + "[" + PREFIX_STATUS + "STATUS_KEYWORD]... "
            + "[" + PREFIX_TAG + "TAG_KEYWORD]...\n"
            + "Example: " + COMMAND_WORD + " 1 2 "
            + PREFIX_COMPANY_NAME + "apple "
            + PREFIX_COMPANY_NAME + "google ";

    public static final String MESSAGE_DELETE_INTERNSHIP_SUCCESS = "%1$d internships have been deleted!";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    private final List<Index> targetIndexes;
    private final InternshipContainsKeywordsPredicate predicate;
    /**
     * Create a DeleteCommand object from the list of user supplied indexes and a predicate
     * @param targetIndexes A list of {@code Index} that refers to the index of internship entries in list.
     * @param predicate A user supplied predicate to filter internships.
     */
    public DeleteCommand(List<Index> targetIndexes, InternshipContainsKeywordsPredicate predicate) {
        this.targetIndexes = targetIndexes;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();

        int previousSize = lastShownList.size();

        // Remove duplicates
        List<Index> uniqueTargetIndexes = new ArrayList<>(new HashSet<>(this.targetIndexes));


        // Sort list
        Collections.sort(uniqueTargetIndexes);

        if (uniqueTargetIndexes.size() > 0 && uniqueTargetIndexes.get(uniqueTargetIndexes.size() - 1)
                .getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        List<Internship> internshipsToDelete = new ArrayList<>(lastShownList);

        if (uniqueTargetIndexes.size() > 0) {
            internshipsToDelete = uniqueTargetIndexes.stream()
                    .map(index -> lastShownList.get(index.getZeroBased()))
                    .collect(Collectors.toList());
        }

        if (!this.predicate.isEmpty()) {
            internshipsToDelete = internshipsToDelete.stream()
                    .filter(this.predicate)
                    .collect(Collectors.toList());
        }

        logger.info(String.format("Deleting internships: %s", Arrays.toString(internshipsToDelete.toArray())));

        for (Internship internshipToDelete: internshipsToDelete) {
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
                || (other instanceof DeleteCommand // instanceof handles nulls
                && predicate.equals(((DeleteCommand) other).predicate)
                && new HashSet<>(this.targetIndexes).equals(new HashSet<>(((DeleteCommand) other).targetIndexes)));
    }
}
