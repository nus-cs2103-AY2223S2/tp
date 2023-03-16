package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.IndexHandler;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.tag.ModuleTag;

public class UntagCommandTest {
    private final Model model = new ModelManager(getTypicalEduMate(), new UserPrefs());
    private final IndexHandler indexHandler = new IndexHandler(model);

    @Test
    public void execute_persons_correctTagsRemoved() throws CommandException {

        Set<ModuleTag> moduleToRemove = new HashSet<>();
        moduleToRemove.add(new ModuleTag("CS2105"));

        Set<ModuleTag> modulesToRemove = new HashSet<>();
        modulesToRemove.add(new ModuleTag("CS2108"));
        modulesToRemove.add(new ModuleTag("GEN2061"));

        ContactIndex index1 = new ContactIndex(1);
        ContactIndex index2 = new ContactIndex(2);

        UntagCommand untag1 = new UntagCommand(index1, moduleToRemove);
        untag1.execute(model);
        Person personToEdit1 = indexHandler.getPersonByIndex(index1).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));

        Set<ModuleTag> tags1 = new HashSet<>();
        tags1.add(new ModuleTag("CS2104"));
        tags1.add(new ModuleTag("CS2103"));
        tags1.add(new ModuleTag("CS1010"));
        tags1.add(new ModuleTag("CHC5338"));
        tags1.add(new ModuleTag("BT2103"));

        assertEquals(personToEdit1.getImmutableModuleTags().toString(), tags1.toString());

        UntagCommand untag2 = new UntagCommand(index2, modulesToRemove);
        untag2.execute(model);
        Person personToEdit2 = indexHandler.getPersonByIndex(index2).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));

        Set<ModuleTag> tags2 = new HashSet<>();
        tags2.add(new ModuleTag("CS2107"));
        tags2.add(new ModuleTag("DSA2102"));
        tags2.add(new ModuleTag("CS2102"));
        tags2.add(new ModuleTag("CS2106"));

        assertEquals(personToEdit2.getImmutableModuleTags().toString(), tags2.toString());

        TagCommand tag1 = new TagCommand(index1, moduleToRemove);
        tag1.execute(model);

        TagCommand tag2 = new TagCommand(index2, modulesToRemove);
        tag2.execute(model);

    }
}
