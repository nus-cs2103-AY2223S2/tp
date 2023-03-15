package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.client.Client;

/**
 * Exports the client details into a csv file.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_SUCCESS = "File has been exported.";
    public static final String MESSAGE_FAILURE = "File could not be exported. Ensure the csv file is not opened in the "
            + "background ";
    public static final String CSV_EXTENSION = ".csv";
    public static final String FILE_NAME = "FitBook";

    @Override
    public CommandResult execute(FitBookModel model) throws CommandException {
        requireNonNull(model);
        String feedback = writeToCsvFile(model);
        return new CommandResult(feedback);
    }

    /**
     * Writes to the csv file with details of {@code Client}
     * @param model model {@code FitBookModel} which the command should operate on.
     * @throws CommandException If an error occurs during command execution.
     */
    public static String writeToCsvFile(FitBookModel model) throws CommandException {
        List<Client> clients = model.getFilteredClientList().stream().collect(Collectors.toList());
        try {
            File csv = new File(FILE_NAME + CSV_EXTENSION);
            PrintWriter pw = new PrintWriter(csv);
            writeHeaderRow(pw);
            writeClientRows(pw, clients);
        } catch (FileNotFoundException e) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        return ExportCommand.MESSAGE_SUCCESS;
    }

    /**
     * Writes the header row in the csv file.
     * @param pw the PrintWriter responsible for writing row into the csv file.
     */
    public static void writeHeaderRow(PrintWriter pw) {
        pw.printf("Name, Phone Number, Email, Address, Weight, Gender\n");
    }

    /**
     * Writes the client detail rows into the csv file.
     * @param pw the PrintWriter responsible for writing rows into the csv file.
     * @param clients List of clients stored in FitBook.
     */
    public static void writeClientRows(PrintWriter pw, List<Client> clients) {
        for (Client client : clients) {
            pw.printf("%s, %s, %s, %s, %s, %s\n", client.getName(), client.getPhone(), client.getEmail(),
                    client.getAddress().toString().replaceAll("[^a-zA-Z0-9]", " "),
                    client.getWeight(), client.getGender());
        }
        pw.close();
    }

}
