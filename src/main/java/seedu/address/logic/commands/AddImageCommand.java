package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_IMAGE;
import static seedu.address.model.util.ImageUtil.deleteImage;

import java.io.IOException;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
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
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_ADD_IMAGE + "[IMAGE_PATH]\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_ADD_IMAGE + "/pictures/johndoe.png";
    public static final String MESSAGE_SUCCESS = "Image Added";
    private final Image toAdd;
    private final Index index;
    /**
     * Creates an AddImageCommand to add the specified {@code Image}
     */
    public AddImageCommand(Index index, Image image) {
        toAdd = image;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToEdit = lastShownList.get(index.getZeroBased());
        try {
            Image oldImage = personToEdit.getImage();
            if (!oldImage.imageName.equals(Image.DEFAULT_IMAGE)) {
                deleteImage(personToEdit.getImage().imageName);
            }
        } catch (IOException io) {
            throw new CommandException("Failed to update image.", io);
        }
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), toAdd);
        model.setPerson(personToEdit, editedPerson);


        return new CommandResult(MESSAGE_SUCCESS);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddImageCommand // instanceof handles nulls
                && toAdd.equals(((AddImageCommand) other).toAdd));
    }

}
