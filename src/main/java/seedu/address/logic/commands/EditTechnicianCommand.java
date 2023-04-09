package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.person.Address;
import seedu.address.model.entity.person.Email;
import seedu.address.model.entity.person.Name;
import seedu.address.model.entity.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing technician in the shop.
 */
public class EditTechnicianCommand extends Command {

    public static final String COMMAND_WORD = "edittechnician";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the technician identified "
        + "by the id number. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: "
        + PREFIX_INTERNAL_ID + "TECHNICIAN_ID "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_PHONE + "PHONE] "
        + "[" + PREFIX_EMAIL + "EMAIL] "
        + "[" + PREFIX_ADDRESS + "ADDRESS] "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_PHONE + "91234567 "
        + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited technician %d";

    private final int id;
    private final Optional<Name> name;
    private final Optional<Phone> phone;
    private final Optional<Email> email;
    private final Optional<Address> address;
    private final Optional<Set<Tag>> tags;


    /**
     * @param id      ID of the technician to be edited
     * @param name    new name of the technician
     * @param phone   new phone number of the technician
     * @param email   new email of the technician
     * @param address new address of the technician
     * @param tags    new tags of the technician
     */
    public EditTechnicianCommand(int id, Optional<Name> name, Optional<Phone> phone, Optional<Email> email,
                                 Optional<Address> address, Optional<Set<Tag>> tags) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.getShop().editTechnician(id, name, phone, email, address, tags);
            model.resetSelected();
            model.selectTechnician(lst -> lst.stream().filter(t -> t.getId() == id).findFirst().get());
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, id), Tab.TECHNICIANS);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}
