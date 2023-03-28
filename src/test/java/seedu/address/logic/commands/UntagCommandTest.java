package seedu.address.logic.commands;

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

        //        assertEquals(person.getImmutableGroupTags(), groups);

        TagCommand tag = new TagCommand(index, groupToRemove, TagType.GROUP);
        tag.execute(model);
    }

}
