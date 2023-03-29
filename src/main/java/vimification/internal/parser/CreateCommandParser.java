package vimification.internal.parser;

import java.time.LocalDateTime;

import vimification.internal.command.logic.AddCommand;
import vimification.model.oldcode.Deadline;
import vimification.model.oldcode.Todo;

public class CreateCommandParser implements LogicCommandParser<AddCommand> {

    private static final ApplicativeParser<Todo> TODO_PARSER = ApplicativeParser
            .string("todo")
            .takeNext(ApplicativeParser.skipWhitespaces1())
            .takeNext(ApplicativeParser.untilEof().map(String::strip))
            .map(Todo::new);

    private static final ApplicativeParser<LocalDateTime> DATE_TIME_PARSER = ApplicativeParser
            .nonWhitespaces()
            .map(ignore -> LocalDateTime.now()); // TODO: Fix after we agree on the format.

    private static final ApplicativeParser<Deadline> DEADLINE_PARSER = ApplicativeParser
            .string("deadline")
            .takeNext(ApplicativeParser.skipWhitespaces1())
            .takeNext(ApplicativeParser.until("/").map(String::strip))
            .combine(DATE_TIME_PARSER, Deadline::new);

    private static final ApplicativeParser<AddCommand> COMMAND_PARSER = ApplicativeParser
            .choice(TODO_PARSER, DEADLINE_PARSER)
            .dropNext(ApplicativeParser.skipWhitespaces())
            .dropNext(ApplicativeParser.eof())
            .map(AddCommand::new);

    private static final ApplicativeParser<ApplicativeParser<AddCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .skipWhitespaces()
                    .takeNext(ApplicativeParser.string("i"))
                    .takeNext(ApplicativeParser.skipWhitespaces1())
                    .constMap(COMMAND_PARSER);

    private static final CreateCommandParser INSTANCE = new CreateCommandParser();

    private CreateCommandParser() {}

    public static CreateCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<AddCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
