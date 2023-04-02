package seedu.connectus.logic.commands;

import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MESSAGE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_WHATSAPP;

import java.util.function.Function;

import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.exceptions.CommandException;
import seedu.connectus.model.Model;
import seedu.connectus.model.person.Person;

/**
 * Opens the specified platform with prefilled message to the specified person.
 */
public class ChatCommand extends Command {

    public static final String COMMAND_WORD = "chat";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens the chat window of the specified platform "
            + "with prefilled message to the specified person. "
            + "Due to platform constraints, only WhatsApp is supported. "
            + "If stored WhatsApp handle is an 8-digit number, Singapore country code \"65\" will be "
            + "automatically added when generating the link. "
            + "To ensure WhatsApp response, the link would be triggered twice: immediately and 2 seconds later.\n"
            + "Parameters: PERSON_INDEX (must be a positive integer) "
            + PREFIX_SOCMED_WHATSAPP + " "
            + PREFIX_MESSAGE + "MESSAGE\n"
            + "Example: " + COMMAND_WORD + " 1 wa/ m/Hello!";

    public static final String MESSAGE_SUCCESS = "Opened chat window with prefilled message %2$s for %1$s";

    private final Index targetIndex;
    private final Function<Person, String>[] targetPlatforms;
    private final String message;

    /**
     * Creates a ChatCommand to open the specified platform with prefilled message to the specified person.
     */
    @SafeVarargs
    public ChatCommand(Index targetIndex, String message, Function<Person, String>... targetPlatforms) {
        this.targetIndex = targetIndex;
        this.targetPlatforms = targetPlatforms;
        this.message = message;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(
            String.format(MESSAGE_SUCCESS, CommandUtil.launchWindow(model, targetIndex, targetPlatforms, 1), message));
    }
}
