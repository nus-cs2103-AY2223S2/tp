package trackr.logic.commands;
import static java.util.Objects.requireNonNull;

import java.util.List;

import trackr.logic.commands.exceptions.CommandException;
import trackr.logic.parser.TrackrParser;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Model;

/**
 * Uploads csv information to trackr
 */
public class UploadCsvCommand extends Command {

    public static final String COMMAND_WORD = "upload_csv";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Allows you to upload "
            + "a csv file and convert it to orders, tasks and or contacts.";

    public static final String MESSAGE_SUCCESS = "Csv file successfully uploaded";
    private List<String> commands;

    /**
     * Creates an UploadCSVCommand
     */
    public UploadCsvCommand(List<String> commands) {
        requireNonNull(commands);
        this.commands = commands;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        TrackrParser trackrParser = new TrackrParser();
        for (int i = 0; i < commands.size(); i++) {
            Command command = trackrParser.parseCommand(commands.get(i));
            command.execute(model);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof UploadCsvCommand
                && commands.equals(((UploadCsvCommand) other).commands);
    }
}
