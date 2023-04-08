package trackr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TestUtil.ModelStub;

import org.junit.jupiter.api.Test;

import trackr.commons.core.index.Index;
import trackr.model.ObservableTabIndex;

public class TabCommandTest {

    private ModelStub unused;

    @Test
    public void constructor_nulCommand_throwsNullException() {
        assertThrows(NullPointerException.class, () -> new TabCommand(null));
    }

    @Test
    public void execute_updateTabSuccess() throws Exception {
        Index validIndex = Index.fromZeroBased(0);
        TabCommand tabCommand = new TabCommand(validIndex);
        CommandResult res = tabCommand.execute(unused);
        assertEquals(TabCommand.MESSAGE_SUCCESS, res.getFeedbackToUser());
        assertEquals(ObservableTabIndex.getTargetTab(), validIndex.getZeroBased());
    }

    @Test
    public void execute_tabCommand_updatesObservable() throws Exception {
        int initial = 1;
        ObservableTabIndex.updateToTab(initial);
        Index afterIndex = Index.fromZeroBased(2);

        CommandResult res = new TabCommand(afterIndex).execute(unused);
        assertEquals(TabCommand.MESSAGE_SUCCESS, res.getFeedbackToUser());
        assertEquals(ObservableTabIndex.getTargetTab(), afterIndex.getZeroBased());
    }

}
