package vimification.internal.parser.logic;

import vimification.internal.command.logic.AddCommand;
import vimification.internal.parser.ApplicativeParser;
import vimification.internal.parser.ArgumentCounter;
import vimification.internal.parser.CommandParser;
import vimification.internal.parser.CommandParserUtil;
import vimification.internal.parser.Pair;
import vimification.model.task.Task;

/**
 * The parser can parses and creates new {@link AddCommand}.
 */
public class AddCommandParser implements CommandParser<AddCommand> {

    private static final ApplicativeParser<AddCommand> COMMAND_PARSER =
            CommandParserUtil.TITLE_PARSER
                    .flatMap(AddCommandParser::parseArguments)
                    .dropNext(CommandParserUtil.END_OF_COMMAND_PARSER);

    private static final ApplicativeParser<ApplicativeParser<AddCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("a")
                    .takeNext(ApplicativeParser.skipWhitespaces1())
                    .constMap(COMMAND_PARSER);

    private static final AddCommandParser INSTANCE = new AddCommandParser();

    private AddCommandParser() {}

    private static ApplicativeParser<AddCommand> parseArguments(String title) {
        Task task = new Task(title);
        ArgumentCounter counter = new ArgumentCounter(
                Pair.of(CommandParserUtil.LABEL_FLAG, Integer.MAX_VALUE),
                Pair.of(CommandParserUtil.PRIORITY_FLAG, 1),
                Pair.of(CommandParserUtil.DEADLINE_FLAG, 1));

        ApplicativeParser<Void> flagParser = ApplicativeParser.choice(
                CommandParserUtil.LABEL_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.LABEL_PARSER)
                        .consume(task::addLabel),
                CommandParserUtil.PRIORITY_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.PRIORITY_PARSER)
                        .consume(task::setPriority),
                CommandParserUtil.DEADLINE_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.DEADLINE_PARSER)
                        .consume(task::setDeadline));

        return ApplicativeParser
                .skipWhitespaces1()
                .takeNext(flagParser.sepBy1(ApplicativeParser.skipWhitespaces1()))
                .optional()
                .constMap(new AddCommand(task));
    }

    public static AddCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<AddCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
