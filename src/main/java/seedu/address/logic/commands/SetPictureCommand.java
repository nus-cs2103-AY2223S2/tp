package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeId;
import seedu.address.model.employee.PicturePath;

/**
 * Sets the picture of an employee, identified using it's displayed index from the address book.
 */
public class SetPictureCommand extends Command {
    public static final String COMMAND_WORD = "setpicture";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets the picture of an employee, identified by the EMPLOYEE_ID used in the displayed employee list.\n"
            + "Parameters: EMPLOYEE_ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SET_PICTURE_SUCCESS = "Set picture for Employee: %1$s";
    public static final String MESSAGE_SET_PICTURE_FAILURE = "Failed to set picture.";
    public static final String MESSAGE_IO_ERROR = "I/O error occurred.";

    private final EmployeeId employeeId;

    public SetPictureCommand(EmployeeId employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Employee> lastShownList = model.getFilteredEmployeeList();
        Employee employeeToSetPicture;

        for (Employee employee : lastShownList) {
            if (employee.getEmployeeId().equals(employeeId)) {
                employeeToSetPicture = employee;
                Path sourcePath = chooseSourcePicture();
                PicturePath destPicturePath = new PicturePath(PicturePath.VALID_DIRECTORY
                        + employeeToSetPicture.getName().fullName + PicturePath.VALID_EXTENSION);
                Path destPath = destPicturePath.toPath().toAbsolutePath();
                try {
                    if (!destPath.toFile().exists()) {
                        Files.createDirectories(destPath);
                    }
                    Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new CommandException(MESSAGE_IO_ERROR);
                }
                employeeToSetPicture.setPicturePath(destPicturePath);
                return new CommandResult(String.format(MESSAGE_SET_PICTURE_SUCCESS, employeeToSetPicture));
            }
        }
        throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
    }

    private Path chooseSourcePicture() throws CommandException {
        FileChooser fileChooser = new FileChooser();
        ExtensionFilter filter = new ExtensionFilter("image files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Downloads"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile == null) {
            throw new CommandException(MESSAGE_SET_PICTURE_FAILURE);
        }
        return selectedFile.toPath();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetPictureCommand // instanceof handles nulls
                && employeeId.equals(((SetPictureCommand) other).employeeId)); // state check
    }
}
