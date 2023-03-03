package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Deque;

import seedu.address.logic.commands.exceptions.ParseException;

/**
 * The group of commands.
 */
public class CommandGroup {
    /**
     * The mode at which the command is executed.
     */
    private final OperationMode mode;

    /**
     * The list of factories responsible for creating commands.
     * Since we won't be having too many commands, using a list would be
     * faster than using a map.
     */
    private final CommandFactory<?>[] factories;

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
    public CommandGroup(OperationMode mode, CommandFactory<?>[] factories) {
        this.mode = mode;
        this.factories = factories;
    }

    /**
     * Parses the command and returns the resulting command object.
     *
     * @param tokens the tokens of the command.
     */
    public Command parse(Deque<String> tokens) throws ParseException {
        requireNonNull(tokens);
        assert tokens.size() > 0 : "tokens should not be empty";

        String commandWord = tokens.pop();
        for (CommandFactory<?> factory : factories) {
            if (commandWord.equals(factory.getCommandWord())) {
                final CommandParam param =
                    CommandParam.from(tokens, factory.getPrefixes());
                return factory.createCommand(param);
            }
        }
        throw new ParseException("Unknown command");
    }

    /**
     * Returns the mode at which the command is executed.
     *
     * @return the mode at which the command is executed.
     */
    public OperationMode getOperationMode() {
        return mode;
    }
}
