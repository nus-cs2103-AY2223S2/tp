package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;

/**
 * Exports the database into a csv file with the employees.
 * This class inherits from the {@code Command} class.
 */
public class BatchExportCommand extends Command {
    public static final String COMMAND_WORD = "batchexport";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exports the database to a CSV file.\n"
            + "Parameters: FILENAME (the exported CSV file will be saved in the data folder)\n"
            + "Example: " + COMMAND_WORD + " exported_database.csv";
    public static final String MESSAGE_WORKS = "Database exported to %s.";
    private final String fileName;
    private final Path filePath;

    /**
     * Constructs a {@code BatchExportCommand} with the specified file name.
     * The CSV file will be saved in the "data" folder with the provided file name.
     *
     * @param fileName the name of the exported CSV file
     */
    public BatchExportCommand(String fileName) {
        requireAllNonNull(fileName);
        this.fileName = fileName;
        this.filePath = Paths.get("data", this.fileName);
    }

    /**
     * Executes the batch export command and exports the employee database to a CSV file.
     *
     * @param model the {@code Model} object which represents the current model
     * @return a {@code CommandResult} object containing the result of the execution
     * @throws CommandException if there is an error while exporting data
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            exportToCsv(model, filePath);
        } catch (IOException e) {
            throw new CommandException("Error exporting data: " + e.getMessage());
        }

        return new CommandResult(String.format(MESSAGE_WORKS, fileName));
    }

    /**
     * Exports the employee database to a CSV file.
     *
     * @param model the {@code Model} object representing the current model
     * @param filePath the path of the exported CSV file
     * @throws IOException if there is an error while writing the CSV file
     */
    private void exportToCsv(Model model, Path filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader("Name", "Phone", "Email", "Address", "Department", "Payroll",
                             "Leave Count", "Date of Birth", "Date of Joining", "Tags"))) {

            List<Employee> employeeList = model.getExecutiveProDb().getEmployeeList();

            for (Employee employee : employeeList) {
                csvPrinter.printRecord(
                        employee.getName().fullName,
                        employee.getPhone().value,
                        employee.getEmail().value,
                        employee.getAddress().value,
                        employee.getDepartment().value,
                        employee.getPayroll().toString(),
                        employee.getLeaveCount(),
                        employee.getDateOfBirth(),
                        employee.getDateOfJoining(),
                        employee.getTags().stream().map(tag -> tag.tagName).collect(Collectors.joining("/"))
                );
            }

            csvPrinter.flush();
        }
    }
}
