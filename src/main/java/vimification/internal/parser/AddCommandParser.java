package vimification.internal.parser;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import vimification.internal.command.logic.AddCommand;
import vimification.model.task.Priority;
import vimification.model.task.Task;


public class AddCommandParser implements CommandParser<AddCommand> {

    private static final ArgumentFlag LABEL_FLAG =
            new ArgumentFlag("-l", "--label", Integer.MAX_VALUE);

    private static final ArgumentFlag PRIORITY_FLAG = new ArgumentFlag("-p", "--priority");

    private static final ArgumentFlag DEADLINE_FLAG = new ArgumentFlag("-d", "--deadline");

    private static final ApplicativeParser<ArgumentFlag> LABEL_FLAG_PARSER =
            CommandParserUtil.flag(LABEL_FLAG);

    private static final ApplicativeParser<ArgumentFlag> PRIORITY_FLAG_PARSER =
            CommandParserUtil.flag(PRIORITY_FLAG);

    private static final ApplicativeParser<ArgumentFlag> DEADLINE_FLAG_PARSER =
            CommandParserUtil.flag(DEADLINE_FLAG);

    private static final ApplicativeParser<Priority> PRIORITY_PARSER =
            CommandParserUtil.PRIORITY_PARSER;

    private static final ApplicativeParser<String> LABEL_PARSER =
            CommandParserUtil.STRING_PARSER;

    private static final ApplicativeParser<LocalDateTime> DEADLINE_PARSER = ApplicativeParser
            .nonWhitespaces1()
            .map(ignore -> LocalDateTime.now());

    private static final ApplicativeParser<Task> TASK_PARSER = CommandParserUtil.STRING_PARSER
            .map(Task::new) // description
            .flatMap(AddCommandParser::parseArguments);

    private static final ApplicativeParser<AddCommand> COMMAND_PARSER = TASK_PARSER
            .dropNext(ApplicativeParser.skipWhitespaces())
            .dropNext(ApplicativeParser.eof())
            .map(AddCommand::new);

    private static final ApplicativeParser<ApplicativeParser<AddCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("a")
                    .takeNext(ApplicativeParser.skipWhitespaces1())
                    .constMap(COMMAND_PARSER);

    private static final AddCommandParser INSTANCE = new AddCommandParser();

    private AddCommandParser() {}

    private static ApplicativeParser<Task> parseArguments(Task task) {
        ArgumentCounter counter = new ArgumentCounter(LABEL_FLAG, PRIORITY_FLAG, DEADLINE_FLAG);
        return ApplicativeParser.choice(
                LABEL_FLAG_PARSER.consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(LABEL_PARSER)
                        .consume(task::addLabel),
                PRIORITY_FLAG_PARSER.consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(PRIORITY_PARSER)
                        .consume(task::setPriority),
                DEADLINE_FLAG_PARSER.consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(DEADLINE_PARSER)
                        .consume(task::setDeadline))
                .sepBy(ApplicativeParser.skipWhitespaces1())
                .constMap(task);
    }

    public static AddCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<AddCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
