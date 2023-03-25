package seedu.address.ui.displays;

import javafx.scene.Node;
import javafx.scene.control.TextArea;

public final class StringDisplay {

    private StringDisplay() {}

    public static Node of(String message) {
        final TextArea output = new TextArea(message);
        output.getStyleClass().add("string-display");
        output.setEditable(false);
        output.setPrefHeight(485);
        return output;
    }
}
