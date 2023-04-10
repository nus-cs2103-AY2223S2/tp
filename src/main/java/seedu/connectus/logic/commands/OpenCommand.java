package seedu.connectus.logic.commands;

import static seedu.connectus.commons.util.CollectionUtil.isAnyNonNull;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_INSTAGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_TELEGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_WHATSAPP;

import java.util.Arrays;
import java.util.function.Function;

import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.exceptions.CommandException;
import seedu.connectus.model.Model;
import seedu.connectus.model.person.Person;

/**
 * Command to launch social media app/website to person's homepage
 */
public class OpenCommand extends Command {

    public static final String COMMAND_WORD = "open";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Opens the social media page of the person identified "
        + "by the index number used in the displayed person list. "
        + "At least one platform should be present, otherwise an error would be given. \n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_SOCMED_INSTAGRAM + "] "
        + "[" + PREFIX_SOCMED_TELEGRAM + "] "
        + "[" + PREFIX_SOCMED_WHATSAPP + "]\n"
        + "Example: " + COMMAND_WORD + " 1 " + PREFIX_SOCMED_INSTAGRAM;

    public static final String MESSAGE_SUCCESS = "Opened social media for %1$s";

    private final Index targetIndex;
    private final Function<Person, String>[] targetPlatforms;

    /**
     * Constructor
     */
    @SafeVarargs
    public OpenCommand(Index targetIndex,
                       Function<Person, String>... targetPlatforms) {
        this.targetIndex = targetIndex;
        this.targetPlatforms = targetPlatforms;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Person target = CommandUtil.launchWindow(model, targetIndex, targetPlatforms);

        return new CommandResult(String.format(MESSAGE_SUCCESS, target));
    }

    /**
     * Checks if the command hold any platforms at all
     */
    public boolean isBlank() {
        return !isAnyNonNull((Object[]) targetPlatforms);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof OpenCommand
            && ((OpenCommand) other).targetIndex.equals(this.targetIndex)
            && Arrays.equals(((OpenCommand) other).targetPlatforms, this.targetPlatforms));
    }
}
