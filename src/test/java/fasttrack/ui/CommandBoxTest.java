package fasttrack.ui;

import static fasttrack.ui.JavaFxTestHelper.initJavaFxHelper;
import static fasttrack.ui.JavaFxTestHelper.setUpHeadlessMode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.exceptions.CommandException;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class CommandBoxTest {

    private static final String VALID_COMMAND = "add n/Milk c/Groceries p/12";
    private static final String INCOMPLETE_COMMAND = "add n/Milk c/Gro";
    private static final String ERROR_STYLE_CLASS = "error";

    private CommandBox commandBox;
    private boolean commandExecuted;


    @BeforeEach
    public void setUp() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        commandExecuted = false;
        // Dummy command executor function
        CommandBox.CommandExecutor commandExecutor = commandText -> {
            if (Objects.equals(commandText, "invalid command")) {
                throw new CommandException("Invalid command");
            }
            commandExecuted = true;
            return null;
        };
        commandBox = new CommandBox(commandExecutor, false);
    }

    @BeforeAll
    static void initJfx() throws InterruptedException {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        setUpHeadlessMode();
        initJavaFxHelper();
    }


    @Test
    public void handleCommandEntered_emptyCommand_commandNotExecuted() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        TextField textField = (TextField) commandBox.getRoot().lookup("#commandTextField");
        textField.setText("");
        commandBox.handleCommandEntered();
        assertFalse(commandExecuted);
    }

    @Test
    public void handleCommandEntered_validCommand_commandExecutedSuccessfully() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        TextField textField = (TextField) commandBox.getRoot().lookup("#commandTextField");
        textField.setText(VALID_COMMAND);
        commandBox.handleCommandEntered();
        assertTrue(commandExecuted);
    }

    @Test
    public void handleCommandEntered_invalidCommand_setsStyleToIndicateCommandFailure() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        commandBox.getTextProperty().setValue("invalid command");
        commandBox.handleCommandEntered();
        TextField commandTextField = (TextField) commandBox.getRoot().lookup("#commandTextField");
        ObservableList<String> styleClass = commandTextField.getStyleClass();
        assertTrue(styleClass.contains(ERROR_STYLE_CLASS));
    }

    @Test
    public void setFocus_commandBox_getsFocus() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        TextField textField = (TextField) commandBox.getRoot().lookup("#commandTextField");
        HBox hbox = new HBox();
        hbox.getChildren().add(commandBox.getRoot());
        Scene scene = new Scene(hbox);
        commandBox.setFocus();
        TextField focusedTextField = (TextField) scene.getFocusOwner();
        assertEquals(focusedTextField, textField);
    }

    @Test
    public void updateCommandBoxText_validCategoryName_updatesText() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        TextField textField = (TextField) commandBox.getRoot().lookup("#commandTextField");
        textField.setText(INCOMPLETE_COMMAND);
        commandBox.updateCommandBoxText("Groceries");
        String expected = "add n/Milk c/Groceries ";
        assertEquals(expected, textField.getText());
    }

}
