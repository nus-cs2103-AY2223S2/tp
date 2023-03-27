package seedu.connectus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_INSTAGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_TELEGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_WHATSAPP;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.function.Function;

import seedu.connectus.commons.core.Messages;
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
        + "by the index number used in the displayed person list.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_SOCMED_INSTAGRAM + "] "
        + "[" + PREFIX_SOCMED_TELEGRAM + "] "
        + "[" + PREFIX_SOCMED_WHATSAPP + "]\n"
        + "Example: " + COMMAND_WORD + " 1 " + PREFIX_SOCMED_INSTAGRAM;

    public static final String MESSAGE_OPEN_SOCMED_SUCCESS = "Opened social media for %1$s";

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
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        var target = lastShownList.get(targetIndex.getZeroBased());

        var desktop = Desktop.getDesktop();
        for (var platform : targetPlatforms) {
            if (platform == null) {
                continue;
            }
            URI uri;
            try {
                var link = platform.apply(target);
                if (link.isBlank()) {
                    throw new CommandException(Messages.MESSAGE_PERSON_FIELD_NOT_PRESENT);
                }
                uri = new URI(link);
                desktop.browse(uri);
            } catch (URISyntaxException | IOException e) {
                throw new CommandException(e.getMessage());
            }
        }

        return new CommandResult(String.format(MESSAGE_OPEN_SOCMED_SUCCESS, target));
    }
}
