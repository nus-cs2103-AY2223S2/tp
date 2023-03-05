package seedu.address.logic.commands.student;

import seedu.address.logic.commands.Command;

import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public abstract class StudentCommand extends Command {
    public static final String COMMAND_WORD = "student";

    public static final String MESSAGE_USAGE = COMMAND_WORD;
}
