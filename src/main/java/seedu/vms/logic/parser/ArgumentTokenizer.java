package seedu.vms.logic.parser;

import seedu.vms.logic.parser.exceptions.ParseException;

/**
 * Tokenizes arguments string of the form: {@code preamble <prefix>value <prefix>value ...}<br>
 *     e.g. {@code some preamble text t/ 11.00 t/12.00 k/ m/ July}  where prefixes are {@code t/ k/ m/}.<br>
 * 1. An argument's value can be an empty string e.g. the value of {@code k/} in the above example.<br>
 * 2. Leading and trailing whitespaces of an argument value will be discarded.<br>
 * 3. An argument may be repeated and all its values will be accumulated e.g. the value of {@code t/}
 *    in the above example.<br>
 */
public class ArgumentTokenizer {
    public static final String MESSAGE_MISSING_FLAG = "Argument flag missing";

    private static final String DELIMITER_PATTERN = "\\s*" + CliSyntax.DELIMITER;


    /**
     * Parses an argument String to an {@link ArgumentMultimap}.
     *
     * @throws ParseException if a flag name is blank.
     */
    public static ArgumentMultimap tokenize(String argString) throws ParseException {
        argString = argString.strip() + " ";

        ArgumentMultimap argMap = new ArgumentMultimap();
        String[] args = argString.split(DELIMITER_PATTERN);

        // preamble
        argMap.put(new Prefix(""), args[0].strip());

        for (int i = 1; i < args.length; i++) {
            addArgument(args[i].strip(), argMap);
        }

        return argMap;
    }


    private static void addArgument(String input, ArgumentMultimap argMap) throws ParseException {
        String[] inputs = input.split("\\s+", 2);

        String flagName = inputs[0];
        if (flagName.isBlank()) {
            throw new ParseException(MESSAGE_MISSING_FLAG);
        }
        Prefix flag = new Prefix(flagName);

        String argValue = "";
        if (inputs.length > 1) {
            argValue = inputs[1];
        }

        argMap.put(flag, argValue);
    }
}
