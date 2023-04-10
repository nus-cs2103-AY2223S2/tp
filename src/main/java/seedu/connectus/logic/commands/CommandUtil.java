package seedu.connectus.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import seedu.connectus.commons.core.Messages;
import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.exceptions.CommandException;
import seedu.connectus.model.Model;
import seedu.connectus.model.person.Person;

/**
 * Contains utility methods used for parsing strings in the various *Command
 * classes.
 */
public class CommandUtil {
    /**
     * Checks if given index is valid on a list.
     */
    public static boolean isIndexValid(Index index, List<?> list) {
        return index.getZeroBased() < list.size();
    }

    /**
     * Converts a set to a list.
     */
    public static <T> List<T> convertSetToList(Set<T> set) {
        return set.stream()
            .sorted().collect(Collectors.toList());
    }

    protected static Person launchWindow(Model model, Index index, Function<Person, String>[] platforms, int repeat)
            throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        var target = lastShownList.get(index.getZeroBased());

        var desktop = Desktop.getDesktop();
        for (int i = 0; i <= repeat; i++) {
            for (var platform : platforms) {
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
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new CommandException(e.getMessage());
            }
        }

        return target;
    }

    protected static Person launchWindow(Model model, Index index, Function<Person, String>[] platforms)
            throws CommandException {
        return launchWindow(model, index, platforms, 0);
    }
}
