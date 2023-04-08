package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ExecutiveProDb;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeId;

/**
 * Adds multiple employees to the database.
 */
public class BatchAddCommand extends Command {
    public static final String COMMAND_WORD = "batchadd";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds multiple employees into the database from a csv file \n"
            + "Parameters: filename (file must be placed into the data folder of the repository and in CSV format) \n"
            + "Example: " + COMMAND_WORD + " executivepro.csv";
    public static final String MESSAGE_WORKS = "Batch added employees. %d employees were added.";
    public static final String MESSAGE_FILE_NOT_FOUND = "File Not Found";
    public static final String MESSAGE_MISSING_NEEDED_FIELDS = "Name, Phone, Department or Payroll is missing "
            + "for one person!";
    public static final String MESSAGE_NO_DATA = "%s does not have any data";
    public static final String MESSAGE_DUPLICATE_FOUND = "One person in the list is found to be a duplicate. "
            + "Call aborted";


    private final String fileName;
    private Path filePath;

    /**
     * Creates an BatchAddCommand to add multiple {@code Employee}
     */
    public BatchAddCommand(String fileName) {
        requireAllNonNull(fileName);
        this.fileName = fileName;
        this.filePath = Paths.get("data", this.fileName);
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public List<AddCommand> getInfo() throws CommandException {
        Path file = this.filePath;
        String line = "";
        String splitBy = ",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
        List<AddCommand> addCommandList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file.toString()));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                String arg = " ";
                for (int i = 0; i < data.length; i++) {
                    String value = data[i].replace("\"", ""); // Remove quotes
                    if (i == (data.length - 1)) {
                        if (!value.equals("")) {
                            String[] tags = value.split("/");
                            for (String tag : tags) {
                                arg += PREFIX_LIST[i] + tag + " ";
                            }
                        }
                    } else {
                        if (!value.equals("")) {
                            arg += PREFIX_LIST[i] + value;
                            arg = arg + " ";
                        }
                    }
                }
                addCommandList.add(new AddCommandParser().parse(arg));
            }
        } catch (FileNotFoundException exception) {
            throw new CommandException(MESSAGE_FILE_NOT_FOUND);
        } catch (IOException exception) {
            throw new CommandException(exception.getMessage());
        } catch (ParseException exception) {
            if (exception.getMessage().equals(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE))) {
                throw new CommandException(MESSAGE_MISSING_NEEDED_FIELDS);
            } else {
                throw new CommandException(exception.getMessage());
            }
        }
        return addCommandList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        int currEmployeeId = EmployeeId.getCount();
        List<AddCommand> addCommandList = this.getInfo();
        if (addCommandList.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_NO_DATA, this.fileName));
        }
        List<Employee> copyEmployeeList = new ArrayList<>();

        for (Employee employee : model.getExecutiveProDb().getEmployeeList()) {
            copyEmployeeList.add(employee);
        }

        try {
            for (AddCommand item : addCommandList) {
                item.execute(model);
            }
        } catch (CommandException e) {
            ExecutiveProDb database = new ExecutiveProDb();
            database.setEmployees(copyEmployeeList);
            model.setExecutiveProDb(database);
            EmployeeId.setCount(currEmployeeId);
            throw new CommandException(MESSAGE_DUPLICATE_FOUND);
        }
        return new CommandResult(String.format(MESSAGE_WORKS, addCommandList.size()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BatchAddCommand)) {
            return false;
        }

        // state check
        BatchAddCommand e = (BatchAddCommand) other;
        return fileName.equals(e.fileName);
    }
}
