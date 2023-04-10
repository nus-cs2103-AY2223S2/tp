package seedu.address.logic.commands.parent;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMAGEPARENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEWNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEWPHONEPARENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENTAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONEPARENT;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Image;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Edits a parent identified using its name and phone number displayed on PowerConnect's parent list.
 */
public class ParentEditCommand extends ParentCommand {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the parent / NOK identified by their phone number and name used in the displayed parent list."
            + " Edits the parent's / NOK's detail based on the information given.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONEPARENT + "PHONE "
            + "["
            + PREFIX_NEWNAME + "NEW NAME "
            + PREFIX_NEWPHONEPARENT + "NEW  "
            + PREFIX_PARENTAGE + "AGE "
            + PREFIX_IMAGEPARENT + "IMAGE PARENT "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "]\n"
            + "Example: \n" + "parent " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tan Ah Niu "
            + PREFIX_PHONEPARENT + "91234567 "
            + PREFIX_NEWNAME + "Tan Ah Seng "
            + PREFIX_NEWPHONEPARENT + "91274444 "
            + PREFIX_PARENTAGE + "31 "
            + PREFIX_IMAGEPARENT + "XX.png (where XX is your image name) "
            + PREFIX_EMAIL + "tanahcow@gmail.com "
            + PREFIX_ADDRESS + "Blk 245 Ang Mo Kio Avenue 1 #11-800 S(560245)";

    public static final String MESSAGE_EDIT_PARENT_SUCCESS = "Edited Parent: %1$s; Phone number: %2$s;";

    private Address newAddress;
    private Age newAge;
    private Email newEmail;
    private Image newImage;
    private final Name name;
    private Name newName;
    private Phone newPhoneNumber;
    private final Phone phoneNumber;
    private Set<Tag> newTagList;

    /**
     * Creates a ParentEditCommand to edit the specified {@code Parent}
     */
    public ParentEditCommand(Name name, Name newName, Age newAge, Image newImage, Email newEmail,
                             Phone phoneNumber, Phone newPhoneNumber, Address newAddress, Set<Tag> newTagList) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.newName = newName;
        this.newAge = newAge;
        this.newImage = newImage;
        this.newEmail = newEmail;
        this.newPhoneNumber = newPhoneNumber;
        this.newAddress = newAddress;
        this.newTagList = newTagList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Parent> parents = model.getFilteredParentList();
        for (Parent parent : parents) {
            if ((parent.getPhone().equals(phoneNumber)) && (parent.getName().equals(name))) {
                if (Name.isDefaultName(newName.fullName)) {
                    this.newName = this.name;
                }
                if (Phone.isDefaultPhone(newPhoneNumber.value)) {
                    this.newPhoneNumber = this.phoneNumber;
                }
                if (Age.isDefaultAge(newAge.value)) {
                    this.newAge = parent.getAge();
                }
                if (Image.isDefaultImage(newImage.value)) {
                    this.newImage = parent.getImage();
                }
                if (Email.isDefaultEmail(newEmail.value)) {
                    this.newEmail = parent.getEmail();
                }
                if (Address.isDefaultAddress(newAddress.value)) {
                    this.newAddress = parent.getAddress();
                }
                if (newTagList.size() == 0) {
                    this.newTagList = parent.getTags();
                }
                Parent newParent = new Parent(this.newName, this.newAge, this.newImage, this.newEmail,
                        this.newPhoneNumber, this.newAddress, this.newTagList);
                model.setParent(parent, editParent(parent, newParent, model));
                return new CommandResult(String.format(MESSAGE_EDIT_PARENT_SUCCESS, newParent.getName(),
                        newParent.getPhone()));
            }
        }
        throw new CommandException(Messages.MESSAGE_PARENT_NOT_FOUND);
    }

    /**
     * Method that helps transfer and update all students in the original Parent object to the new EDITED Parent object.
     *
     * @param parent Original Parent object that is to be edited.
     * @param newParent Edited Parent object.
     * @return Edited Parent object with list of students in original Parent object and updates all the students.
     */
    private Parent editParent(Parent parent, Parent newParent, Model model) {
        if (parent.hasStudents()) {
            List<Student> students = parent.getStudents();
            for (Student student : students) {
                Student originalStudent = student;
                student.setParent(newParent);
                model.setStudent(originalStudent, student);
                newParent.addStudent(student);
            }
        }
        return newParent;
    }
}
