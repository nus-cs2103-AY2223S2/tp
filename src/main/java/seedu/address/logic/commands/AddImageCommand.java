package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_IMAGE;
import static seedu.address.model.util.ImageUtil.deleteImage;
import static seedu.address.model.util.ImageUtil.importImage;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Image;
import seedu.address.model.person.Person;



/**
 * Adds an image to the address on the address book.
 */
public class AddImageCommand extends Command {

    public static final String COMMAND_WORD = "add-image";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an image to the person identified "
            + "by the index number used in the last person listing.\n"
            + "Existing image will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a valid index) "
            + PREFIX_ADD_IMAGE + "[IMAGE_PATH]\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_ADD_IMAGE + "/Users/user/pictures/johndoe.png";
    public static final String MESSAGE_SUCCESS = "Image Added";
    private final String stringPath;
    private final Index index;
    /**
     * Creates an AddImageCommand to add the specified {@code Image}
     */
    public AddImageCommand(Index index, String stringPath) {
        requireNonNull(index);
        requireNonNull(stringPath);
        this.stringPath = stringPath;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToEdit = lastShownList.get(index.getZeroBased());
        Image newImage;
        try {
            String fileName = importImage(stringPath);
            newImage = new Image(fileName);
        } catch (CommandException ce) {
            throw ce;
        } catch (InvalidPathException ipe) {
            throw new CommandException("Path to image provided is invalid.");
        } catch (IOException io) {
            if (io.getMessage().contains("Operation not permitted")) {
                throw new CommandException("Upload failed due to lack of permission for directory/file.");
            }
            throw new CommandException("Upload image failed.");
        }
        try {
            Image oldImage = personToEdit.getImage();
            if (!oldImage.imageName.equals(Image.DEFAULT_IMAGE)) {
                deleteImage(personToEdit.getImage().imageName);
            }
        } catch (IOException io) {
            throw new CommandException("Failed to update image.", io);
        } catch (ParseException pe) {
            throw new CommandException("Failed to update image due to permissions", pe);
        }
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getStatus(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), newImage);
        model.setPerson(personToEdit, editedPerson);


        return new CommandResult(MESSAGE_SUCCESS);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddImageCommand)) {
            return false;
        }

        // state check
        AddImageCommand a = (AddImageCommand) other;
        return index.equals(a.index)
                && this.stringPath.equals(a.stringPath);

    }

}
