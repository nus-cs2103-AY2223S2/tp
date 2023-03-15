package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.parser.IndexHandler;
import seedu.address.model.Model;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.ModuleTagSet;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;
import seedu.address.model.tag.ModuleTag;

/**
 * Adds a ModuleTag to a person.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a ModuleTag to a person. \n"
            + "tag <index> m/<module> : Adds tags from the person of given index in displayed list. \n"
            + "tag m/<module> : Adds tags to your own profile instead.";

    public static final String MESSAGE_TAG_PERSON_SUCCESS = "Module(s) tagged to Person! \n";
    public static final String MESSAGE_TAG_USER_SUCCESS = "Module(s) tagged to User! \n";
    public static final String MESSAGE_NO_TAGS = "At least one Module must be provided.";

    private final ContactIndex index;
    private final Set<ModuleTag> moduleTags;

    /**
     * @param index of the person in the filtered person list to add modules.
     * @param modulesToAdd modules to add to the person
     */
    public TagCommand(ContactIndex index, Set<ModuleTag> modulesToAdd) {
        requireNonNull(modulesToAdd);

        this.index = index;
        this.moduleTags = modulesToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (index == null) {
            return addUserTags(model);
        }
        return addPersonTags(model);
    }

    /**
     * Add tags to person at given index.
     * @param model {@code Model} which the command should operate on
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult addPersonTags(Model model) throws CommandException {
        IndexHandler indexHandler = new IndexHandler(model);

        Person personToEdit = indexHandler.getPersonByIndex(index).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));

        ModuleTagSet oldModules = personToEdit.getModuleTags();

        oldModules.addAll(this.moduleTags);

        Set<ModuleTag> userModuleTags = model.getUser().getImmutableModuleTags();

        // caches the common modules in each ModuleTagSet as running set
        // intersection is expensive if we only use it in the compareTo method
        personToEdit.setCommonModules(userModuleTags);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_TAG_PERSON_SUCCESS,
                personToEdit.getImmutableGroupTags().toString()));
    }

    /**
     * Adds modules to user.
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult addUserTags(Model model) throws CommandException {
        User editedUser = model.getUser();

        ModuleTagSet userModuleTags = model.getUser().getModuleTags();

        userModuleTags.addAll(this.moduleTags);

        model.getFilteredPersonList().forEach(person ->
                person.setCommonModules(editedUser.getImmutableModuleTags()));

        return new CommandResult(String.format(MESSAGE_TAG_USER_SUCCESS, editedUser));
    }

}
