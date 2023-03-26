package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * The command from user specifying a tag need to delete from storage.
 */
public class DeleteTagCommand extends Command {

    public static List<String> COMMAND_WORDS = new ArrayList<String>(Arrays.asList("delete_tag", "dt"));

    private static final Path p = Paths.get("data", "deleteTagCommand.txt");

    public static final String MESSAGE_USAGE = COMMAND_WORDS
            + ": Delete tag from the person identified by the index used in the displayed person list.\n"
            + "Parameters: Index (must be positive number), Tag Name\n"
            + "Example: " + COMMAND_WORDS + " 1 teacher";

    public static final String MESSAGE_SUCCESS = "Tag deleted: %1$s";
    public static final String MESSAGE_INVALID_PERSON = "Person does not exist.";

    private final Tag tagToDelete;
    private final Index targetIndex;

    /**
     * Creates a DeleteTagCommand to delete the specified tag from the person.
     *
     * @param index The index of the person to delete the tag from.
     * @param tag The tag to delete.
     */
    public DeleteTagCommand(Index index, Tag tag) {
        this.tagToDelete = tag;
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person deleteFromPerson = lastShownList.get(targetIndex.getZeroBased());
        //check if the tag with the given name exist
        if (!deleteFromPerson.getTags().contains(tagToDelete)) {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_TAG);
        }

        model.deleteTag(deleteFromPerson, tagToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, deleteFromPerson), true, true);
    }

    public static void saveWords() {
        if (!Files.exists(p)) {
            try {
                Files.createFile(p);
            } catch (java.io.IOException ignored) {}
        }

        try {
            FileOutputStream fos = new FileOutputStream(p.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(COMMAND_WORDS);
            oos.close();
        } catch (IOException ignored) {}
    }

    public static void loadWords() {
        if (!Files.exists(p)) {
            try {
                Files.createFile(p);
            } catch (java.io.IOException ignored) {}
        }
        try {
            FileInputStream fis = new FileInputStream(p.toFile());
            ObjectInputStream ois = new ObjectInputStream(fis);
            COMMAND_WORDS = (List<String>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException ignored) {}
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTagCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTagCommand) other).targetIndex)
                    && tagToDelete.equals(((DeleteTagCommand) other).tagToDelete));
    }

}
