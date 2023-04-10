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

    private static final String COMMAND_DOES_NOT_EXIST = "This command does not exist in Quick Contacts.";
    private static final String COMMAND_DESCRIPTION = "Shows program usage instructions.";
    private static final String LIST_OF_AVAILABLE_COMMANDS = "Here are the list of available commands:\n"
        + "Enter `help [command]` to find out more about each command.\n"
        + "1. " + AddCommand.COMMAND_WORD + ": " + AddCommand.COMMAND_DESCRIPTION + "\n"
        + "2. " + EditCommand.COMMAND_WORD + ": " + EditCommand.COMMAND_DESCRIPTION + "\n"
        + "3. " + DeleteCommand.COMMAND_WORD + ": " + DeleteCommand.COMMAND_DESCRIPTION + "\n"
        + "4. " + ClearCommand.COMMAND_WORD + ": " + ClearCommand.COMMAND_DESCRIPTION + "\n"
        + "5. " + FindCommand.COMMAND_WORD + ": " + FindCommand.COMMAND_DESCRIPTION + "\n"
        + "6. " + ListCommand.COMMAND_WORD + ": " + ListCommand.COMMAND_DESCRIPTION + "\n"
        + "7. " + ExportPersonsCommand.COMMAND_WORD + ": " + ExportPersonsCommand.COMMAND_DESCRIPTION + "\n"
        + "8. " + ImportPersonsCommand.COMMAND_WORD + ": " + ImportPersonsCommand.COMMAND_DESCRIPTION + "\n"
        + "9. " + AddMeetingCommand.COMMAND_WORD + ": " + AddMeetingCommand.COMMAND_DESCRIPTION + "\n"
        + "10. " + FindMeetingCommand.COMMAND_WORD + ": " + FindMeetingCommand.COMMAND_DESCRIPTION + "\n"
        + "11. " + EditMeetingsCommand.COMMAND_WORD + ": " + EditMeetingsCommand.COMMAND_DESCRIPTION + "\n"
        + "12. " + DeleteMeetingCommand.COMMAND_WORD + ": " + DeleteMeetingCommand.COMMAND_DESCRIPTION + "\n"
        + "13. " + ExportMeetingsCommand.COMMAND_WORD + ": " + ExportMeetingsCommand.COMMAND_DESCRIPTION + "\n"
        + "14. " + ImportMeetingsCommand.COMMAND_WORD + ": " + ImportMeetingsCommand.COMMAND_DESCRIPTION + "\n"
        + "15. " + SortMeetingCommand.COMMAND_WORD + ": " + SortMeetingCommand.COMMAND_DESCRIPTION + "\n"
        + "16. " + HelpCommand.COMMAND_WORD + ": " + HelpCommand.COMMAND_DESCRIPTION + "\n"
        + "17. " + MarkAsDoneCommand.COMMAND_WORD + ": " + MarkAsDoneCommand.COMMAND_DESCRIPTION + "\n"
        + "18. " + MarkAsNotDoneCommand.COMMAND_WORD + ": " + MarkAsNotDoneCommand.COMMAND_DESCRIPTION + "\n"
        + "19. " + ShowNotDoneCommand.COMMAND_WORD + ": " + ShowNotDoneCommand.COMMAND_DESCRIPTION + "\n"
        + "20. " + ExitCommand.COMMAND_WORD + ": " + ExitCommand.COMMAND_DESCRIPTION + "\n";
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
        case MarkAsDoneCommand.COMMAND_WORD:
            return new CommandResult(MarkAsDoneCommand.MESSAGE_USAGE);
        case MarkAsNotDoneCommand.COMMAND_WORD:
            return new CommandResult(MarkAsNotDoneCommand.MESSAGE_USAGE);
        case ShowNotDoneCommand.COMMAND_WORD:
            return new CommandResult(ShowNotDoneCommand.MESSAGE_USAGE);


        case SHOW_HELP_POPUP:
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false);

        default:
            return new CommandResult(COMMAND_DOES_NOT_EXIST + "\n\n" + LIST_OF_AVAILABLE_COMMANDS);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                && commandWord.equals(((HelpCommand) other).commandWord)); // state check
    }
}
