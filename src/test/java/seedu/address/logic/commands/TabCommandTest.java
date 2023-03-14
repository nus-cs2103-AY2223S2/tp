package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.ui.tab.TabInfo;
import seedu.address.logic.ui.tab.TabType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_ADDRESS_BOOK_TAB;
import static seedu.address.testutil.TypicalIndexes.INDEX_CALENDAR_TAB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

/**
 * Contains integration tests (interaction with the model)
 * and unit tests for {@code TabCommand}.
 */
public class TabCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final TabInfo addressBookTabInfo = new TabInfo(INDEX_ADDRESS_BOOK_TAB, TabType.ADDRESS_BOOK);

    @Test
    void execute_validIndex_success() {
        TabCommand tabCommand = new TabCommand(INDEX_ADDRESS_BOOK_TAB);
        String expectedMessage = String.format(TabCommand.MESSAGE_SUCCESS, addressBookTabInfo);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setSelectedTab(INDEX_ADDRESS_BOOK_TAB);

        model.setSelectedTab(INDEX_CALENDAR_TAB);
        assertCommandSuccess(tabCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidIndex_throwsCommandException() {
        int tabsSize = model.getTabUtil().getTabInfoList().size();
        Index outOfBoundIndex = Index.fromOneBased(tabsSize + 1);
        TabCommand tabCommand = new TabCommand(outOfBoundIndex);
        String expectedMessage = String.format(TabCommand.MESSAGE_INVALID_INDEX, 1, tabsSize);

        assertCommandFailure(tabCommand, model, expectedMessage);
    }
}
