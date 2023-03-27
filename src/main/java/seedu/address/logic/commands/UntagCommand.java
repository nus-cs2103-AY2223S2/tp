package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.parser.IndexHandler;
import seedu.address.model.Model;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;
import seedu.address.model.tag.ModuleTag;

/**
 * Removes modules from an existing person in the address book.
 */
public class UntagCommand extends Command {

    public static final String COMMAND_WORD = "untag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a ModuleTag to a person. \n"
            + "untag <index> m/<module> : Removes tags from the person of given index in displayed list. \n"
            + "untag n/<module> : Removes tags from your own profile instead.";

    public static final String MESSAGE_UNTAG_PERSON_SUCCESS = "Module(s) untagged to Person! \n";
    public static final String MESSAGE_UNTAG_USER_SUCCESS = "Module(s) untagged to User! \n";
    public static final String MESSAGE_NO_TAGS = "At least one Module must be provided.";
    public static final String MESSAGE_INCORRECT_INPUT_FOR_LESSON = "The wrong types of arguments has been provided.";


    private final ContactIndex index;
    private final Set<ModuleTag> moduleTags;

    /**
     * @param index of the person in the filtered person list to edit.
     * @param modulesToRemove set of modules to be removed.
     */
    public UntagCommand(ContactIndex index, Set<ModuleTag> modulesToRemove) {
        requireNonNull(modulesToRemove);

        this.index = index;
        this.moduleTags = modulesToRemove;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToEdit = getPersonToEdit(model);

        personToEdit.removeModuleTags(moduleTags);

        if (personToEdit instanceof User) {
            return setUserCommonModuleTags(model, (User) personToEdit);
        }

        return setPersonCommonModuleTags(model, personToEdit);
    }

    private Person getPersonToEdit(Model model) throws CommandException {
        if (index == null) {
            return model.getUser();
        }

        IndexHandler indexHandler = new IndexHandler(model);
        return indexHandler.getPersonByIndex(index).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));
    }

    /**
     * Removes tags from person at given index.
     * @param model {@code Model} which the command should operate on.
     * @param personToEdit {@code Person} which has been edited.
     * @return feedback message of the operation result for display.
     */
    public CommandResult setPersonCommonModuleTags(Model model, Person personToEdit) {
        Set<ModuleTag> userModuleTags = model.getUser().getImmutableModuleTags();

        personToEdit.setCommonModules(userModuleTags);

        return new CommandResult(String.format(MESSAGE_UNTAG_PERSON_SUCCESS
                + "Name: " + personToEdit.getName().toString() + '\n'
                + "Modules: " + personToEdit.getImmutableModuleTags().toString() + '\n'
                + "Module(s) in common: " + personToEdit.getImmutableCommonModuleTags().toString()));
    }

    /**
     * Removes tags from the user.
     * @param model {@code Model} which the command should operate on
     * @param editedUser {@code User} which has been edited.
     * @return feedback message of the operation result for display.
     */
    public CommandResult setUserCommonModuleTags(Model model, User editedUser) {
        model.getObservablePersonList().forEach(person ->
                person.setCommonModules(editedUser.getImmutableModuleTags()));

        return new CommandResult(String.format(MESSAGE_UNTAG_USER_SUCCESS
                + "Name: " + editedUser.getName().toString() + '\n'
                + "Modules: " + editedUser.getImmutableModuleTags().toString()));
    }

    public ContactIndex getIndex() {
        return this.index;
    }

    public Set<ModuleTag> getModules() {
        return this.moduleTags;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof UntagCommand) {
            UntagCommand otherCommand = (UntagCommand) other;
            return otherCommand.getIndex().equals(getIndex())
                    && otherCommand.getModules().equals(getModules());
        }
        return false;

    }
}
