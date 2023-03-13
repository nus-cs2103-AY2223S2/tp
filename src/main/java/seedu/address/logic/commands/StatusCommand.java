package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS_ASSIGN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS_FIND;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.status.LeadStatus;
import seedu.address.model.tag.Tag;


/**
 * Assigns a person's lead status,
 * or finds people matching a lead status.
 */
public class StatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Handles lead statuses, "
            + "allows for setting of statuses or finding contacts matching the status.\n"
            + "Parameters (choose only one): \n"
            + "[INDEX " + PREFIX_STATUS_ASSIGN + " STATUS_TYPE] OR "
            + "[" + PREFIX_STATUS_FIND + "STATUS_TYPE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_STATUS_ASSIGN + "qualified\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STATUS_ASSIGN + "uncontacted";

    public static final String MESSAGE_STATUS_ASSIGN_PERSON_SUCCESS = "Assigned %1$s status of "
            + " %2$s";
    public static final String MESSAGE_STATUS_FIND_PERSON_SUCCESS = "Listed all contacts with "
            + "status %1$s";
    public static final String MESSAGE_STATUS_NOT_IMPLEMENTED = "status command not implemented";
    public static final String MESSAGE_STATUS_IS_SAME = "The status of %1$s is already %1$s";

    private final Index index;
    private final LeadStatus status;
    private final boolean isSearch;

    //TODO add DateTime timestamp of when the status was set.

    /**
     * Constructor for a StatusCommand. Takes in an index of a person valid lead status.
     * @param index a valid index for a person
     * @param status a valid LeadStatus
     */
    public StatusCommand(Index index, LeadStatus status) {
        requireNonNull(index);
        requireNonNull(status);

        this.index = index;
        this.status = status;
        this.isSearch = false;
    }

    /**
     * Constructor for a status command. Enables search for the status given.
     * @param status a valid LeadStatus to search for
     */
    public StatusCommand(LeadStatus status) {
        requireNonNull(status);

        this.index = null;
        this.status = status;
        this.isSearch = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (!isSearch) {
            requireNonNull(index);
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person toBeUpdated = lastShownList.get(index.getZeroBased());
            LeadStatus currLeadStatus = toBeUpdated.getStatus();

            if (status.equals(currLeadStatus)) {
                throw new CommandException(MESSAGE_STATUS_IS_SAME);
            }

            Person updatedStatusPerson = createPersonWithNewStatus(toBeUpdated, status);

            if (!toBeUpdated.isSamePerson(updatedStatusPerson) && model.hasPerson(updatedStatusPerson)) {
                throw new CommandException(MESSAGE_STATUS_IS_SAME);
            }

            model.setPerson(toBeUpdated, updatedStatusPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

            return new CommandResult(String.format(MESSAGE_STATUS_ASSIGN_PERSON_SUCCESS, updatedStatusPerson.getName(),
                    status));
        }

        //TODO look for matching lead status
        throw new CommandException("find status: " + status);
    }

    private static Person createPersonWithNewStatus(Person toBeUpdated, LeadStatus status) {
        assert toBeUpdated != null;

        Name updatedName = toBeUpdated.getName();
        Phone updatedPhone = toBeUpdated.getPhone();
        Email updatedEmail = toBeUpdated.getEmail();
        Address updatedAddress = toBeUpdated.getAddress();
        Remark updatedRemark = toBeUpdated.getRemark();
        Set<Tag> updatedTags = toBeUpdated.getTags();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedRemark, updatedTags,
                status);
    }

    //TODO add equals method
}
