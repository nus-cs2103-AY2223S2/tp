package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class ViewBackupsCommand extends Command {

    public static final String COMMAND_WORD = "viewbackups";

    public static final String MESSAGE_SUCCESS = "Listed all backups";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}
