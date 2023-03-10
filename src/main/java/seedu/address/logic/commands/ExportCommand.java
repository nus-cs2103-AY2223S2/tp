package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FitBookModel;
import seedu.address.model.client.Client;

public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_SUCCESS = "File has been exported.";
    public static final String MESSAGE_FAILURE = "File could not be exported. Check if the csv file is opened in the "
            + "background ";
    public static final String CSV_EXTENSION = ".csv";
    public static final String FILE_NAME = "FitBook";

    @Override
    public CommandResult execute(FitBookModel model) throws CommandException {
        requireNonNull(model);
        ExportCommand.writeToCsvFile(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    public static void writeToCsvFile(FitBookModel model) throws CommandException {
        List<Client> clients = model.getFilteredClientList().stream().collect(Collectors.toList());
        try {
            File csv = new File(FILE_NAME + CSV_EXTENSION);
            PrintWriter pw = new PrintWriter(csv);
            ExportCommand.addHeaderRow(pw);
            ExportCommand.addClientRows(pw, clients);
        } catch (FileNotFoundException e) {
            throw new CommandException(MESSAGE_FAILURE);
        }
    }

    public static void addHeaderRow(PrintWriter pw) {
        pw.printf("Name, Phone Number, Email, Address, Weight, Gender\n");
    }

    public static void addClientRows(PrintWriter pw, List<Client> clients) {
        for (Client client : clients) {
            pw.printf("%s, %s, %s, %s, %s, %s\n", client.getName(), client.getPhone(), client.getEmail(),
                    client.getAddress().toString().replaceAll("[^a-zA-Z0-9]", " "),
                    client.getWeight(), client.getGender());
        }
        pw.close();
    }

}
