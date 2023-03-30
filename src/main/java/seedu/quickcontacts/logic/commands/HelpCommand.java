package seedu.quickcontacts.logic.commands;

import seedu.quickcontacts.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private static final String COMMAND_DOES_NOT_EXIST = "Command does not exist";
    private static final String SHOW_HELP_POPUP = "";
    private final String commandWord;

    public HelpCommand() {
        this.commandWord = SHOW_HELP_POPUP;
    }

    public HelpCommand(String commandWord) {
        this.commandWord = commandWord;
    }


    @Override
    public CommandResult execute(Model model) {
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new CommandResult(AddCommand.MESSAGE_USAGE);

        case EditCommand.COMMAND_WORD:
            return new CommandResult(EditCommand.MESSAGE_USAGE);

        case DeleteCommand.COMMAND_WORD:
            return new CommandResult(DeleteCommand.MESSAGE_USAGE);

        case ClearCommand.COMMAND_WORD:
            return new CommandResult(ClearCommand.MESSAGE_USAGE);

        case FindCommand.COMMAND_WORD:
            return new CommandResult(FindCommand.MESSAGE_USAGE);

        case ListCommand.COMMAND_WORD:
            return new CommandResult(ListCommand.MESSAGE_USAGE);

        case ExitCommand.COMMAND_WORD:
            return new CommandResult(ExitCommand.MESSAGE_USAGE);

        case HelpCommand.COMMAND_WORD:
            return new CommandResult(HelpCommand.MESSAGE_USAGE);

        case AddMeetingCommand.COMMAND_WORD:
            return new CommandResult(AddMeetingCommand.MESSAGE_USAGE);

        case FindMeetingCommand.COMMAND_WORD:
            return new CommandResult(FindMeetingCommand.MESSAGE_USAGE);

        case ViewMeetingsCommand.COMMAND_WORD:
            return new CommandResult(ViewMeetingsCommand.MESSAGE_USAGE);

        case EditMeetingsCommand.COMMAND_WORD:
            return new CommandResult(EditMeetingsCommand.MESSAGE_USAGE);

        case DeleteMeetingCommand.COMMAND_WORD:
            return new CommandResult(DeleteMeetingCommand.MESSAGE_USAGE);

        case ExportPersonsCommand.COMMAND_WORD:
            return new CommandResult(ExportPersonsCommand.MESSAGE_USAGE);

        case ImportPersonsCommand.COMMAND_WORD:
            return new CommandResult(ImportPersonsCommand.MESSAGE_USAGE);

        case SortMeetingCommand.COMMAND_WORD:
            return new CommandResult(SortMeetingCommand.MESSAGE_USAGE);

        case ExportMeetingsCommand.COMMAND_WORD:
            return new CommandResult(ExportMeetingsCommand.MESSAGE_USAGE);

        case ImportMeetingsCommand.COMMAND_WORD:
            return new CommandResult(ImportMeetingsCommand.MESSAGE_USAGE);

        case SHOW_HELP_POPUP:
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false);

        default:
            return new CommandResult(COMMAND_DOES_NOT_EXIST);
        }
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                && commandWord.equals(((HelpCommand) other).commandWord)); // state check
    }
}
