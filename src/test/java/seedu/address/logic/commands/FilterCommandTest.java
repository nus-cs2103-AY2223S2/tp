package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.FilterCommand.MESSAGE_NO_PERSON_WITH_TAG;
import static seedu.address.logic.commands.FilterCommand.MESSAGE_SUCCESS;
import static seedu.address.model.util.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

public class FilterCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_tagNotPresent_noPersonFound() {
        Tag notPresentTag = new Tag("nonsense");
        FilterCommand filterCommand = new FilterCommand(notPresentTag);
        CommandResult expectedResult = filterCommand.execute(model, new OfficeConnectModel());
        assertEquals(expectedResult, new CommandResult(String.format(MESSAGE_NO_PERSON_WITH_TAG, notPresentTag)));
    }

    @Test
    public void execute_tagPresent_personsFound() {
        Tag presentTag = new Tag("friends");
        FilterCommand filterCommand = new FilterCommand(presentTag);
        CommandResult expectedResult = filterCommand.execute(model, new OfficeConnectModel());
        assertEquals(model.getFilteredPersonList().size(), 3);
        assertEquals(expectedResult, new CommandResult(String.format(MESSAGE_SUCCESS, presentTag)));
    }

    @Test
    public void equals() {
        Tag logistics = new Tag("logistics");
        Tag marketing = new Tag("marketing");
        FilterCommand filterLogisticsCommand = new FilterCommand(logistics);
        FilterCommand filterMarketingCommand = new FilterCommand(marketing);
        //check same object
        assertEquals(filterLogisticsCommand, filterLogisticsCommand);

        FilterCommand filterLogisticsCommandCopy = new FilterCommand(logistics);
        //check same value
        assertEquals(filterLogisticsCommand, filterLogisticsCommandCopy);
        //check null
        assertNotEquals(filterLogisticsCommand, null);
        //check diff values
        assertNotEquals(filterLogisticsCommand, filterMarketingCommand);
    }
}
