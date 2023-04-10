package wingman.logic.core;

import static java.util.Objects.requireNonNull;

import java.util.Deque;
import java.util.List;
import java.util.Optional;

import wingman.logic.core.exceptions.CommandException;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.OperationMode;

/**
 * The group of commands.
 */
public class CommandGroup extends FactoryParser {
    /**
     * The mode at which the command is executed.
     */
    private final OperationMode mode;

    /**
     * The list of factories responsible for creating commands.
     * Since we won't be having too many commands, using a list would be
     * faster than using a map.
     */
    private final List<CommandFactory<?>> factories;

    /**
     * Creates a command group with the given mode and list of factories. A
     * command groups provides a logical grouping of the commands. Which
     * command group to use is determined by the current mode of the
     * software, hence this software being "modal". The specific resolution
     * of the command is handled inside the command group via its array of
     * factories.
     *
     * @param mode      the mode at which the command is executed.
     * @param factories the list of factories responsible for creating commands.
     */
    public CommandGroup(OperationMode mode, List<CommandFactory<?>> factories) {
        this.mode = mode;
        this.factories = factories;
    }

    /**
     * Parses the command and returns the resulting command object.
     *
     * @param tokens the tokens of the command.
     */
    public Command parse(Deque<String> tokens) throws ParseException, CommandException {
        requireNonNull(tokens);
        if (tokens.isEmpty()) {
            throw new ParseException("Tokens is empty");
        }
        final Optional<Command> result = this.parseFactory(tokens);
        if (result.isEmpty()) {
            throw new ParseException("Unknown command in " + this.mode);
        }
        return result.get();
    }

    /**
     * Returns the mode at which the command is executed.
     *
     * @return the mode at which the command is executed.
     */
    public OperationMode getOperationMode() {
        return mode;
    }

    @Override
    protected List<CommandFactory<?>> getFactories() {
        return factories;
    }
}
