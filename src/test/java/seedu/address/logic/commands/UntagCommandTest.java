package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.IndexHandler;
import seedu.address.logic.parser.TagType;
import seedu.address.model.EduMateHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

public class UntagCommandTest {
    private final Model model = new ModelManager(getTypicalEduMate(), new UserPrefs(), new EduMateHistory());
    private final IndexHandler indexHandler = new IndexHandler(model);

    @Test
    public void execute_removeModulesFromAng() throws CommandException {

        Set<ModuleTag> modulesToRemove = new HashSet<>();
        modulesToRemove.add(new ModuleTag("CS2108"));
        modulesToRemove.add(new ModuleTag("GEN2061"));

        ContactIndex index2 = new ContactIndex(2);


        UntagCommand untag2 = new UntagCommand(index2, modulesToRemove, TagType.MODULE);
        untag2.execute(model);
        Person personToEdit2 = indexHandler.getPersonByIndex(index2).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));

        Set<ModuleTag> tags2 = new HashSet<>();
        tags2.add(new ModuleTag("CS2107"));
        tags2.add(new ModuleTag("DSA2102"));
        tags2.add(new ModuleTag("CS2102"));
        tags2.add(new ModuleTag("CS2106"));

        //assertEquals(personToEdit2.getImmutableModuleTags(), tags2);


        TagCommand tag2 = new TagCommand(index2, modulesToRemove, TagType.MODULE);
        tag2.execute(model);

    }

    @Test
    public void execute_removeModuleFromAlbert() throws CommandException {
        Set<ModuleTag> moduleToRemove = new HashSet<>();
        moduleToRemove.add(new ModuleTag("CS2105"));

        ContactIndex index1 = new ContactIndex(1);

        UntagCommand untag1 = new UntagCommand(index1, moduleToRemove, TagType.MODULE);
        untag1.execute(model);
        Person personToEdit1 = indexHandler.getPersonByIndex(index1).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));

        Set<ModuleTag> tags1 = new HashSet<>();
        tags1.add(new ModuleTag("CS2104"));
        tags1.add(new ModuleTag("CS2103"));
        tags1.add(new ModuleTag("CS1010"));
        tags1.add(new ModuleTag("CHC5338"));
        tags1.add(new ModuleTag("BT2103"));

        //assertEquals(personToEdit1.getImmutableModuleTags(), tags1);

        TagCommand tag1 = new TagCommand(index1, moduleToRemove, TagType.MODULE);
        tag1.execute(model);
    }

    @Test
    public void execute_removeModulesFromUser() throws CommandException {

        Set<ModuleTag> modulesToRemove = new HashSet<>();
        modulesToRemove.add(new ModuleTag("CS2100"));
        modulesToRemove.add(new ModuleTag("CS2101"));

        UntagCommand userTest = new UntagCommand(null, modulesToRemove, TagType.MODULE);
        userTest.execute(model);

        Set<ModuleTag> tags = new HashSet<>() {{
                add(new ModuleTag("CS2102"));
                add(new ModuleTag("CS2103"));
                add(new ModuleTag("CS2104"));
                add(new ModuleTag("CS2105"));
                add(new ModuleTag("CS2106"));
            }};

        Person userAct = model.getUser();

        //assertEquals(userAct.getImmutableModuleTags(), tags);

        TagCommand tag = new TagCommand(null, modulesToRemove, TagType.MODULE);
        tag.execute(model);

    }

    @Test
    public void execute_removeGroupFromKevin() throws CommandException {
        Set<GroupTag> groupToRemove = new HashSet<>() {{
                add(new GroupTag("NUS"));
            }};

        ContactIndex index = new ContactIndex(12);

        UntagCommand untag = new UntagCommand(index, groupToRemove, TagType.GROUP);
        untag.execute(model);

        Person person = indexHandler.getPersonByIndex(index).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));

        Set<GroupTag> groups = new HashSet<>() {{
                add(new GroupTag("TA"));
            }};

        assertEquals(person.getImmutableGroupTags(), groups);

        TagCommand tag = new TagCommand(index, groupToRemove, TagType.GROUP);
        tag.execute(model);
    }

}
