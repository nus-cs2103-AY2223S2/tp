package wingman.logic.core;

import static java.util.Objects.requireNonNull;

import java.util.Deque;
import java.util.List;
import java.util.Optional;

import wingman.logic.core.exceptions.CommandException;
import wingman.logic.core.exceptions.ParseException;

/**
 * The parser that delegates the parsing of the commands to the specific
 * factories.
 */
public abstract class FactoryParser {
    /**
     * Gets the list of factories that this parser handles.
     *
     * @return the list of factories that this parser handles.
     */
    protected abstract List<CommandFactory<?>> getFactories();

    /**
     * Parses the given tokens and returns the corresponding command. If
     * there is no command that matches the tokens, an empty optional is
     * returned.
     *
     * @param tokens the tokens of the command.
     * @return the command that matches the tokens.
     * @throws ParseException if the command is invalid.
     */
    public Optional<Command> parseFactory(Deque<String> tokens)
            throws ParseException, CommandException {
        requireNonNull(tokens);
        if (tokens.isEmpty()) {
            throw new ParseException("Tokens is empty");
        }
        String commandWord = tokens.peek();
        for (CommandFactory<?> factory : getFactories()) {
            if (commandWord.equals(factory.getCommandWord())) {
                tokens.pop();
                final CommandParam param =
                        CommandParam.from(tokens, factory.getPrefixes());
                return Optional.of(factory.createCommand(param));
            }
        }
        return Optional.empty();
    }
}
