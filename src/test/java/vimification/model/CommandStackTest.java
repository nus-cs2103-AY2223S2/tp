package vimification.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import vimification.common.core.Index;
import vimification.internal.command.logic.DeleteTaskCommand;
import vimification.internal.command.logic.UndoableLogicCommand;

public class CommandStackTest {
    private static final int MAX_SIZE = 20;

    private static void assertMaxSize(CommandStack stack) {
        assertFalse(stack.isEmpty());
        assertEquals(stack.size(), MAX_SIZE);
    }

    @Test
    public void testSimpleConstructor() {
        CommandStack stack = new CommandStack();
        assertTrue(stack instanceof CommandStack);
        assertEquals(stack.getClass(), CommandStack.class);

        assertTrue(stack.isEmpty());
        assertEquals(stack.size(), 0);
    }

    @Test
    public void testCollectionConstructor() {
        List<UndoableLogicCommand> commands = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            UndoableLogicCommand newCommand = new DeleteTaskCommand(Index.fromZeroBased(i));
            commands.add(newCommand);
        }

        CommandStack stack = new CommandStack(commands);
        assertTrue(stack instanceof CommandStack);
        assertEquals(stack.getClass(), CommandStack.class);
        assertMaxSize(stack);
    }

    @Test
    public void testSize() {
        List<UndoableLogicCommand> commands = new ArrayList<>();
        int n = 7;
        assert n > 0;
        for (int i = 0; i < n; i++) {
            UndoableLogicCommand newCommand = new DeleteTaskCommand(Index.fromZeroBased(i));
            commands.add(newCommand);
        }

        CommandStack stack = new CommandStack(commands);
        assertFalse(stack.isEmpty());
        assertEquals(stack.size(), n);
    }

    @Test
    public void testPush() {
        CommandStack stack = new CommandStack();
        assertEquals(stack.size(), 0);
        int n = 9;
        assert n > 0;
        for (int i = 0; i < n; i++) {
            UndoableLogicCommand newCommand = new DeleteTaskCommand(Index.fromZeroBased(i));
            stack.push(newCommand);
        }

        assertFalse(stack.isEmpty());
        assertEquals(stack.size(), n);
    }

    @Test
    public void testPush_ensureSize() {
        CommandStack stack = new CommandStack();
        assertEquals(stack.size(), 0);

        for (int i = 0; i < 25; i++) {
            UndoableLogicCommand newCommand = new DeleteTaskCommand(Index.fromZeroBased(i));
            stack.push(newCommand);
        }

        assertFalse(stack.isEmpty());
        assertMaxSize(stack);
    }

    @Test
    public void testPop() {
        CommandStack stack = new CommandStack();
        assertEquals(stack.size(), 0);
        int nPush = 15;
        int nPop = 8;
        assert nPop > 0;
        assert nPush > nPop;
        for (int i = 0; i < nPush; i++) {
            UndoableLogicCommand newCommand = new DeleteTaskCommand(Index.fromZeroBased(i));
            stack.push(newCommand);
        }

        assertEquals(stack.size(), nPush);
        for (int i = 0; i < nPop; i++) {
            stack.pop();
        }

        assertFalse(stack.isEmpty());
        assertEquals(stack.size(), nPush - nPop);
    }

    @Test
    public void testPopTilEmpty() {
        CommandStack stack = new CommandStack();
        assertEquals(stack.size(), 0);
        int n = 9;
        assert n > 0;
        for (int i = 0; i < n; i++) {
            UndoableLogicCommand newCommand = new DeleteTaskCommand(Index.fromZeroBased(i));
            stack.push(newCommand);
        }

        assertEquals(stack.size(), n);
        for (int i = 0; i < n; i++) {
            stack.pop();
        }

        assertTrue(stack.isEmpty());
        assertEquals(stack.size(), 0);
    }
}
