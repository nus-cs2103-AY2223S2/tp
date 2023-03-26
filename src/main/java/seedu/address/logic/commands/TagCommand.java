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
 * Changes the remark of an existing person in the e-lister.
 */
public class TagCommand extends Command {

    public static List<String> COMMAND_WORDS = new ArrayList<String>(Arrays.asList("tag", "t"));

    private static final Path p = Paths.get("data", "tagCommand.txt");


    public static final String MESSAGE_USAGE = COMMAND_WORDS
            + ": Tags the person identified by the index used in the displayed person list.\n"
            + "Parameters: Index (must be positive number) \n"
            + "Example: " + COMMAND_WORDS + " 1";

    public static final String MESSAGE_SUCCESS = "New Tag added: %1$s";
    public static final String MESSAGE_INVALID_PERSON = "Person does not exist.";

    private final Tag tagToAdd;
    private final Index targetIndex;

    /**
     * Creates a TagCommand to add a specified tag to the person at a supplied index.
     *
     * @param index The index of the person to add the tag to
     * @param tag The tag to add
     */
    public TagCommand(Index index, Tag tag) {
        this.tagToAdd = tag;
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size() || targetIndex.getZeroBased() < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToTag = lastShownList.get(targetIndex.getZeroBased());
        model.addTag(personToTag, tagToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, personToTag), true, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagCommand // instanceof handles nulls
                && targetIndex.equals(((TagCommand) other).targetIndex)
                && tagToAdd.equals(((TagCommand) other).tagToAdd));
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

}
