package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FitBookModel;
import seedu.address.model.client.Client;

public class ExportCommand extends Command {
    String fileName = "FitBook";
    @Override
    public CommandResult execute(FitBookModel model) throws CommandException, FileNotFoundException {
        requireNonNull(model);
        List<Client> clients = model.getFilteredClientList().stream().collect(Collectors.toList());
        File csv = new File("FitBook.csv");
        try {
            PrintWriter pw = new PrintWriter(csv);
            pw.printf("Name, Gender\n");
            for (Client client : clients) {
                pw.printf("%s, %s \n", client.getName(), client.getGender());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("cant");
        }
    return new CommandResult("done");
    }
}
