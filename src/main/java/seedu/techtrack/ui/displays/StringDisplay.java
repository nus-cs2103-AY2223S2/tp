package seedu.techtrack.ui.displays;

import static java.util.Objects.requireNonNull;

import javafx.scene.Node;
import javafx.scene.control.TextArea;

/**
 * A customizable display for Strings. Will be displayed in ResultDisplay.
 */
public final class StringDisplay {

    private StringDisplay() {}

    /**
     * Creates a custom StringDisplay based on the given string.
     * @param message A string to be displayed in the UI.
     * @return The TextArea Node to be displayed in the UI.
     */
    public static Node of(String message) {
        requireNonNull(message);
        final TextArea output = new TextArea(message);
        output.getStyleClass().add("string-display");
        output.setEditable(false);
        output.setPrefHeight(485);
        return output;
    }
}
