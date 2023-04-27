package vimification.internal.parser.logic;

import vimification.common.core.Index;
import vimification.internal.command.logic.InsertCommand;
import vimification.internal.command.logic.InsertRequest;
import vimification.internal.parser.ApplicativeParser;
import vimification.internal.parser.ArgumentCounter;
import vimification.internal.parser.CommandParser;
import vimification.internal.parser.CommandParserUtil;
import vimification.internal.parser.Pair;

/**
 * The parser that can parses and creates new {@link InsertCommand}.
 */
public class InsertCommandParser implements CommandParser<InsertCommand> {

    private static final ApplicativeParser<InsertCommand> COMMAND_PARSER =
            CommandParserUtil.ONE_BASED_INDEX_PARSER
                    .flatMap(InsertCommandParser::parseArguments)
                    .dropNext(CommandParserUtil.END_OF_COMMAND_PARSER);

    private static final ApplicativeParser<ApplicativeParser<InsertCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("i") // insert
                    .takeNext(ApplicativeParser.skipWhitespaces1())
                    .constMap(COMMAND_PARSER);

    private static final InsertCommandParser INSTANCE = new InsertCommandParser();

    private static ApplicativeParser<InsertCommand> parseArguments(Index index) {
        InsertRequest request = new InsertRequest();
        ArgumentCounter counter = new ArgumentCounter(
                Pair.of(CommandParserUtil.LABEL_FLAG, Integer.MAX_VALUE),
                Pair.of(CommandParserUtil.DEADLINE_FLAG, 1));

        ApplicativeParser<Void> flagParser = ApplicativeParser.choice(
                CommandParserUtil.LABEL_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.LABEL_PARSER)
                        .consume(label -> request.getInsertedLabels().add(label)),
                CommandParserUtil.DEADLINE_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.DEADLINE_PARSER)
                        .consume(request::setInsertedDeadline));

        return ApplicativeParser
                .skipWhitespaces1()
                .takeNext(flagParser.sepBy1(ApplicativeParser.skipWhitespaces1()))
                .constMap(new InsertCommand(index, request));
    }

    private InsertCommandParser() {}

    public static InsertCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<InsertCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
