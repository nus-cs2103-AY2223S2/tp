package seedu.recipe.ui.util;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * A utility class containing methods for working with TextArea components.
 */
public class FieldsUtil {
    /**
     * Creates a dynamic TextArea with the specified initial text.
     * The TextArea will support UP, DOWN, and TAB navigation.
     * If the TextArea is the last in the VBox and gains focus, a new empty TextArea will be added below it.
     * If the TextArea loses focus and is the last in the VBox and empty, it will be removed.
     *
     * @param text The initial text for the TextArea.
     * @return The created dynamic TextArea.
     */
    public static TextArea createDynamicTextArea(String text) {
        //Styling
        TextArea textArea = new TextArea(text);
        textArea.setWrapText(true);
        textArea.setMaxHeight(5.0);
        textArea.setPrefHeight(5.0);
        //Keyboard listener for navigation
        textArea.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.BACK_SPACE) {
                return;
            }
            int currentIndex = ((VBox) textArea.getParent()).getChildren().indexOf(textArea);
            handleNavigation(event, textArea, currentIndex);
        });

        //Text field listener for automatically adding/removing new input rows
        textArea.focusedProperty().addListener((observable, oldValue, newValue) -> {
            VBox parentBox = (VBox) textArea.getParent();
            if (parentBox == null) {
                return;
            }
            // Check if the TextArea has gained focus
            if (newValue) {
                addNewRow(parentBox, textArea);
            } else {
                removeNewRow(parentBox, textArea);
            }
        });

        return textArea;
    }

    /**
     * Returns the next TextArea below the current TextArea within the same parent VBox, if any.
     *
     * @param currentTextArea The current TextArea.
     * @return The next TextArea below the current TextArea or null if there's no TextArea below it.
     */
    private static TextArea getNextTextArea(TextArea currentTextArea) {
        VBox parentBox = (VBox) currentTextArea.getParent();
        int currentIndex = parentBox.getChildren().indexOf(currentTextArea);
        int lastIndex = parentBox.getChildren().size() - 1;

        if (currentIndex < lastIndex) {
            Node nextNode = parentBox.getChildren().get(currentIndex + 1);
            if (nextNode instanceof TextArea) {
                return (TextArea) nextNode;
            }
        }

        return null;
    }

    /**
     * Handles keyboard navigation events for TextArea components.
     *
     * @param event       The KeyEvent to handle.
     * @param textArea   The TextArea to perform navigation on.
     * @param currentIndex The index of the TextArea in its parent VBox.
     */
    public static void handleNavigation(KeyEvent event, TextArea textArea, int currentIndex) {
        if (event.getCode() == KeyCode.ESCAPE) {
            textArea.getScene().getWindow().requestFocus();
            return;
        }

        TextArea nextField = (TextArea) ((VBox) textArea.getParent()).getChildren().get(currentIndex + 1);

        // >>> Should be removed - arrow keys are for navigation WITHIN text field
        // Condition 2.1: DOWN key pressed
        if (event.getCode() == KeyCode.DOWN) {
            nextField.requestFocus();

        // Condition 2.2: Purely TAB key pressed
        } else if (event.getCode() == KeyCode.TAB && !event.isShiftDown()) {
            // If it is a new placeholder row and there's another TextArea after it, skip to the field after
            if (nextField.getText().isEmpty()
                    && currentIndex + 2
                        < ((VBox) textArea.getParent()).getChildren().size()) {
                nextField = (TextArea) ((VBox) textArea.getParent())
                        .getChildren()
                        .get(currentIndex + 2);
            }
            nextField.requestFocus();
        // Shift + TAB
        } else if (event.getCode() == KeyCode.TAB) {
            ObservableList<Node> childList = ((VBox) textArea.getParent()).getChildren();
            if (textArea.getText().isEmpty()) {
                childList.remove(textArea);
            } else {
                int index = childList.indexOf(textArea);
                if (index > 0) {
                    childList.get(index - 1).requestFocus();
                }
            }
        }
        event.consume();
    }

    /**
     * Adds a new empty TextArea to the end of the VBox if the provided TextArea is the last one.
     *
     * @param parentBox The VBox containing the TextArea.
     * @param textArea The TextArea to check if it is the last one in the VBox.
     */
    public static void addNewRow(VBox parentBox, TextArea textArea) {
        int lastIndex = parentBox.getChildren().size() - 1;
        // Check if it's the last TextArea in the VBox
        if (parentBox.getChildren().indexOf(textArea) == lastIndex) {
            TextArea newField = createDynamicTextArea("");
            parentBox.getChildren().add(newField);
        }
    };

    /**
     * Removes the empty TextArea below the current TextArea if both are empty and not focused.
     *
     * @param parentBox The VBox containing the TextArea.
     * @param textArea The TextArea to check for removal conditions.
     */
    public static void removeNewRow(VBox parentBox, TextArea textArea) {
        // Check if it's the last TextArea, it's empty, and the focus is not in the same VBox, then remove it
        if (getNextTextArea(textArea) != null
                && getNextTextArea(textArea).getText().isEmpty()
                && textArea.getText().isEmpty()) {
            parentBox.getChildren().remove(getNextTextArea(textArea));
        }
    }
}
