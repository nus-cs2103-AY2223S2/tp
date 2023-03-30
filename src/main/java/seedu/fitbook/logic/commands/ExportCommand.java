package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.commons.core.CsvConfig.CSV_EXTENSION;
import static seedu.fitbook.commons.core.CsvConfig.FILE_NAME_CLIENT;

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
    public static final String MESSAGE_SUCCESS = "Client File has been exported.";
    public static final String MESSAGE_FAILURE = "File could not be exported. Ensure the csv file is not opened in the "
            + "background ";

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
        try (PrintWriter pw = new PrintWriter(new File(FILE_NAME_CLIENT + CSV_EXTENSION))) {
            writeHeaderRow(pw);
            writeClientRows(pw, clients);
            return ExportCommand.MESSAGE_SUCCESS;
        } catch (FileNotFoundException e) {
            throw new CommandException(MESSAGE_FAILURE);
        }
    }

    /**
     * Writes the header row in the csv file.
     * @param pw the PrintWriter responsible for writing the header row into the csv file.
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
            // defensive check to ensure client details are not null in any cases
            String name = client.getName() != null ? String.valueOf(client.getName()) : "";
            String phone = client.getPhone() != null ? String.valueOf(client.getPhone()) : "";
            String email = client.getEmail() != null ? String.valueOf(client.getEmail()) : "";
            String address = client.getAddress() != null
                    ? client.getAddress().toString().replaceAll("[^a-zA-Z0-9]", " ") : "";
            String weight = client.getWeight() != null ? client.getWeightValue() : "";
            String gender = client.getGender() != null ? String.valueOf(client.getGender()) : "";

            pw.printf("%s, %s, %s, %s, %s, %s\n", name, phone, email, address, weight, gender);
        }
        pw.close();
    }

}
