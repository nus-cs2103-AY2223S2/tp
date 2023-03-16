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
    public void execute_persons_correctTagsAdded() throws CommandException {

        Set<ModuleTag> moduleToAdd = new HashSet<>();
        moduleToAdd.add(new ModuleTag("CS1234"));

        Set<ModuleTag> modulesToAdd = new HashSet<>();
        modulesToAdd.add(new ModuleTag("CS1234"));
        modulesToAdd.add(new ModuleTag("CS2345"));

        ContactIndex index1 = new ContactIndex(1);
        ContactIndex index2 = new ContactIndex(2);

        TagCommand tag1 = new TagCommand(index1, moduleToAdd);
        CommandResult result1 = tag1.execute(model);
        Person personToEdit1 = indexHandler.getPersonByIndex(index1).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));
        // "CS2105", "CS2104", "CS2103", "CS1010", "CHC5338", "BT2103"
        Set<ModuleTag> tags1 = new HashSet<>();
        tags1.add(new ModuleTag("CS2105"));
        tags1.add(new ModuleTag("CS2104"));
        tags1.add(new ModuleTag("CS2103"));
        tags1.add(new ModuleTag("CS1010"));
        tags1.add(new ModuleTag("CHC5338"));
        tags1.add(new ModuleTag("BT2103"));
        tags1.add(new ModuleTag("CS1234"));
        System.out.println(tags1);
        System.out.println("hello");
        assertEquals(personToEdit1.getImmutableModuleTags().toString(), tags1.toString());

        TagCommand tag2 = new TagCommand(index2, modulesToAdd);
        CommandResult result2 = tag2.execute(model);
        Person personToEdit2 = indexHandler.getPersonByIndex(index2).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));
        // "CS2108", "GEN2061", "CS2107", "DSA2102", "CS2102", "CS2106"
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

        UntagCommand untag1 = new UntagCommand(index1, moduleToAdd);
        untag1.execute(model);

        UntagCommand untag2 = new UntagCommand(index2, modulesToAdd);
        untag2.execute(model);
    }

    @Test
    public void execute_user_correctTagsAdded() throws CommandException {

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

    } // how do i checkstyle for test files

}
