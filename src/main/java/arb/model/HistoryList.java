package arb.model;

import java.util.Iterator;

import arb.logic.commands.Command;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

/**
 * Represents the history of commands.
 */
public class HistoryList implements Iterable<Command> {

    public static final int DEFAULT_SIZE = 10;

    private ObservableList<Command> commandList;

    /**
     * Constructs a {@code HistoryList} with the default size.
     */
    public HistoryList() {
        commandList = FXCollections.observableArrayList();
    }

    public HistoryList(int size) {
        commandList = FXCollections.observableArrayList(size);
    }

    @Override
    public Iterator<Command> iterator() {
        return null;
    }
}
