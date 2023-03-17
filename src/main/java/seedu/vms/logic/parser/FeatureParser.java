package seedu.vms.logic.parser;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.vms.commons.core.Messages;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.basic.HelpCommand;
import seedu.vms.logic.parser.exceptions.ParseException;


/** A parser to parse the command of a feature. */
public abstract class FeatureParser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");


    /**
     * Parses the given CLI input to a {@code Command}.
     *
     * @param commandInput - the command input to parse.
     * @throws ParseException - if a command cannot be parsed.
     */
    public ParseResult parse(String commandInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(commandInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        String commandWord = matcher.group("commandWord");
        String argString = matcher.group("arguments");
        ArgumentMultimap args = ArgumentTokenizer.tokenize(argString);

        Command command = parseCommand(commandWord, args);
        Optional<CommandMessage> message = formParseMessage(args);

        return message.map(msg -> new ParseResult(command, msg))
                .orElseGet(() -> new ParseResult(command));
    }


    private Optional<CommandMessage> formParseMessage(ArgumentMultimap args) {
        List<Map.Entry<Prefix, List<String>>> unusedArgs = args.getUnusedArgs();
        if (unusedArgs.isEmpty()) {
            return Optional.empty();
        }

        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Prefix, List<String>> entry : unusedArgs) {
            Prefix prefix = entry.getKey();
            System.out.println(prefix);
            for (String arg : entry.getValue()) {
                builder.append(String.format("\n%s%s %s",
                        CliSyntax.DELIMITER,
                        prefix.toString(),
                        arg));
            }
        }

        String message = String.format("The following arguments are unused:%s", builder.toString());
        return Optional.ofNullable(new CommandMessage(message, CommandMessage.State.WARNING));
    }


    protected abstract Command parseCommand(String commandWord, ArgumentMultimap argument) throws ParseException;
}
