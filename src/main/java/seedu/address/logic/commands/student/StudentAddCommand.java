package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAILSTUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMAGEPARENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMAGESTUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEXNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONEPARENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Image;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.student.Student;
import seedu.address.model.tag.Tag;


/**
 * A class for "student Class Name add" command"
 */
public class StudentAddCommand extends StudentCommand {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "student CLASS_NAME " + COMMAND_WORD
            + ": Adds a student to the address book. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_INDEXNUMBER + "INDEX NUMBER "
            + PREFIX_SEX + "SEX "
            + PREFIX_PARENTNAME + "NOK NAME "
            + PREFIX_PHONEPARENT + "NOK CONTACT NUMBER "
            + PREFIX_RELATIONSHIP + "NOK RELATIONSHIP TO STUDENT "
            + "["
            + PREFIX_AGE + "AGE "
            + PREFIX_IMAGESTUDENT + "IMAGE STUDENT "
            + PREFIX_EMAILSTUDENT + "EMAIL STUDENT "
            + PREFIX_PHONE + "PHONE STUDENT "
            + PREFIX_CCA + "CCA "
            + "]\n"
            + "Example: " + "student 1A " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tan Ah Cow "
            + PREFIX_INDEXNUMBER + "13 "
            + PREFIX_SEX + "M "
            + PREFIX_PARENTNAME + "Tan Ah Niu "
            + PREFIX_PHONEPARENT + "91234567 "
            + PREFIX_RELATIONSHIP + "Father "
            + PREFIX_AGE + "14 "
            + PREFIX_IMAGESTUDENT + "C:// "
            + PREFIX_EMAILSTUDENT + "tanahcow@gmail.com "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_CCA + "Captain Ball";

    public static final String MESSAGE_SUCCESS = "New student added:";

    private final Student toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public StudentAddCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    /**
     * Returns a CommandResult from adding a New Student to PowerConnect.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult from adding a New Student.
     * @throws CommandException When there's an unexpected situation occurring.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(toAdd)) {
            throw new DuplicatePersonException();
        }

        model.addStudent(toAdd);
        ObservableList<Parent> parents = model.getFilteredParentList();
        setParent(parents, toAdd, model);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentAddCommand // instanceof handles nulls
                && toAdd.equals(((StudentAddCommand) other).toAdd));
    }

    /**
     * A method that binds the Student's Parent / NOK to the Student
     *
     * @param parents List of existing Parents / NOK.
     * @param student Student that needs the binding of Parent/NOK to.
     * @param model {@code Model} which the command should operate on.
     */
    public void setParent(ObservableList<Parent> parents, Student student, Model model) {
        Phone parentNumber = student.getParentNumber();
        Name parentName = student.getParentName();
        for (Parent p : parents) {
            if ((p.getPhone().equals(parentNumber)) && (p.getName().equals(parentName))) {
                student.setParent(p);
                return;
            }
        }
        ArgumentMultimap argMultimap = new ArgumentMultimap();
        try {
            Age age = ParserUtil.parseAge((argMultimap.getValue(PREFIX_AGE).get()));
            Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
            Image image = ParserUtil.parseImage(argMultimap.getValue(PREFIX_IMAGEPARENT).get());
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            Parent newParent = new Parent(student.getStudentClass(), student.getIndexNumber(), parentName,
                    student.getRls(), age, image, email, parentNumber,
                    address, tagList);
            model.addParent(newParent);
        } catch (ParseException parseException) {
            return;
        }
    }
}
