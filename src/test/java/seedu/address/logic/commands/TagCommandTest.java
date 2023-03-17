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

public class TagCommandTest {
    private final Model model = new ModelManager(getTypicalEduMate(), new UserPrefs());
    private final IndexHandler indexHandler = new IndexHandler(model);

    @Test
    public void execute_addTwoModulesToAng() throws CommandException {
        Set<ModuleTag> modulesToAdd = new HashSet<>();
        modulesToAdd.add(new ModuleTag("CS1234"));
        modulesToAdd.add(new ModuleTag("CS2345"));

        ContactIndex index2 = new ContactIndex(2);

        TagCommand tag2 = new TagCommand(index2, modulesToAdd);
        tag2.execute(model);
        Person personToEdit2 = indexHandler.getPersonByIndex(index2).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));

        Set<ModuleTag> tags2 = new HashSet<>();
        tags2.add(new ModuleTag("CS2108"));
        tags2.add(new ModuleTag("GEN2061"));
        tags2.add(new ModuleTag("CS2107"));
        tags2.add(new ModuleTag("DSA2102"));
        tags2.add(new ModuleTag("CS2102"));
        tags2.add(new ModuleTag("CS2106"));
        tags2.add(new ModuleTag("CS1234"));
        tags2.add(new ModuleTag("CS2345"));
        assertEquals(personToEdit2.getImmutableModuleTags().toString(), tags2.toString());

        UntagCommand untag2 = new UntagCommand(index2, modulesToAdd);
        untag2.execute(model);
    }

    @Test
    public void execute_addOneModuleToAlbert() throws CommandException {
        Set<ModuleTag> moduleToAdd = new HashSet<>();
        moduleToAdd.add(new ModuleTag("CS1234"));

        ContactIndex index1 = new ContactIndex(1);

        TagCommand tag1 = new TagCommand(index1, moduleToAdd);
        tag1.execute(model);
        Person personToEdit1 = indexHandler.getPersonByIndex(index1).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));

        Set<ModuleTag> tags1 = new HashSet<>();
        tags1.add(new ModuleTag("CS2105"));
        tags1.add(new ModuleTag("CS2104"));
        tags1.add(new ModuleTag("CS2103"));
        tags1.add(new ModuleTag("CS1010"));
        tags1.add(new ModuleTag("CHC5338"));
        tags1.add(new ModuleTag("BT2103"));
        tags1.add(new ModuleTag("CS1234"));
        assertEquals(personToEdit1.getImmutableModuleTags().toString(), tags1.toString());

        UntagCommand untag1 = new UntagCommand(index1, moduleToAdd);
        untag1.execute(model);
    }

    @Test
    public void execute_addModulesToUser() throws CommandException {

        Set<ModuleTag> modulesToAdd = new HashSet<>();
        modulesToAdd.add(new ModuleTag("CS1234"));
        modulesToAdd.add(new ModuleTag("CS2345"));

        TagCommand userTest = new TagCommand(null, modulesToAdd);
        userTest.execute(model);

        Set<ModuleTag> tags = new HashSet<>() {{
                add(new ModuleTag("CS2100"));
                add(new ModuleTag("CS2101"));
                add(new ModuleTag("CS2102"));
                add(new ModuleTag("CS2103"));
                add(new ModuleTag("CS2104"));
                add(new ModuleTag("CS2105"));
                add(new ModuleTag("CS2106"));
                add(new ModuleTag("CS1234"));
                add(new ModuleTag("CS2345"));
            }};

        Person userAct = model.getUser();

        assertEquals(userAct.getImmutableModuleTags().toString(), tags.toString());

        UntagCommand untag = new UntagCommand(null, modulesToAdd);
        untag.execute(model);

    }

}
