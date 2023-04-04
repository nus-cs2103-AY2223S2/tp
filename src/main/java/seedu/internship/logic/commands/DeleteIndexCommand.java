package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;

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

/**
 * Deletes an internship identified using it's displayed index from InternBuddy.
 */
public class DeleteIndexCommand extends Command {

    public static final String COMMAND_WORD = "delete-index";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all internships identified by the index numbers used in the displayed internship list.\n"
            + "Fields: INDEX [INDEX]... (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_DELETE_INTERNSHIP_SUCCESS = "%1$d internships have been deleted!";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    private final List<Index> targetIndexes;
    /**
     * Create a DeleteIndexCommand object from the list of user supplied indexes and a predicate
     * @param targetIndexes A list of {@code Index} that refers to the index of internship entries in list.
     */
    public DeleteIndexCommand(List<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
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
            throw new CommandException(Messages.MESSAGE_OUT_OF_RANGE_INTERNSHIP_DISPLAYED_INDEX);
        }

        List<Internship> internshipsToDelete = new ArrayList<>(lastShownList);

        if (uniqueTargetIndexes.size() > 0) {
            internshipsToDelete = uniqueTargetIndexes.stream()
                    .map(index -> lastShownList.get(index.getZeroBased()))
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
                || (other instanceof DeleteIndexCommand // instanceof handles nulls
                && new HashSet<>(this.targetIndexes).equals(new HashSet<>(((DeleteIndexCommand) other).targetIndexes)));
    }
}
