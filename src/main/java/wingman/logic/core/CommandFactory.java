package wingman.logic.core;

import java.util.Optional;
import java.util.Set;

import wingman.logic.core.exceptions.CommandException;
import wingman.logic.core.exceptions.ParseException;

/**
 * The factory that's responsible for creating a command.
 *
 * @param <T> the type of the command.
 */
public interface CommandFactory<T extends Command> {
    /**
     * Returns the command word of the command. For example, if the command
     * is to add an entity, then the command word may be "add". Every command
     * should have a command word.
     *
     * @return the command word of the command.
     */
    String getCommandWord();

    /**
     * Gets the prefixes of the command. Prefixes are used to identify the
     * arguments of the command. If the command does not have any prefixes,
     * then return {@code Optional.empty()}.
     *
     * @return the prefixes of the command, or {@code Optional.empty()} if
     *     the command does not have any prefixes.
     */
    Optional<Set<String>> getPrefixes();

    /**
     * Creates a command with the given parameters.
     *
     * @param param the parameters of the command.
     * @return the command created.
     * @throws ParseException if the parameters are invalid.
     */
    T createCommand(CommandParam param) throws ParseException, CommandException;
}
