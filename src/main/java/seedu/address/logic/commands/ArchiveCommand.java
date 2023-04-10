package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.pet.Pet;

/**
 * Archives the Pet to an archive file
 * stored in data/archive.json
 */
public class ArchiveCommand extends Command {
    public static final String COMMAND_WORD = "archive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Archives a pet into an archive file identified by the index number used in the displayed pet list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ARCHIVE_PET_SUCCESS = "Archived Pet:\n%1$s";

    public static final String MESSAGE_DUPLICATE_PET = "This pet already exists in the archive!";

    private final Index targetIndex;

    public ArchiveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Pet> lastShownList = model.getFilteredPetList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        }

        Pet petToArchive = lastShownList.get(targetIndex.getZeroBased());

        if (model.hasArchivePet(petToArchive)) {
            throw new CommandException(MESSAGE_DUPLICATE_PET);
        }

        model.archivePet(petToArchive);

        return new CommandResult(String.format(MESSAGE_ARCHIVE_PET_SUCCESS, petToArchive));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArchiveCommand // instanceof handles nulls
                && targetIndex.equals(((ArchiveCommand) other).targetIndex)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetIndex);
    }
}
