package vimification.internal.parser.ui;

import vimification.internal.command.ui.RefreshCommand;
import vimification.internal.parser.ApplicativeParser;
import vimification.internal.parser.CommandParser;
import vimification.internal.parser.CommandParserUtil;

/**
 * The parser that can parses and creates new {@link RefreshCommand}.
 */
public class RefreshCommandParser implements CommandParser<RefreshCommand> {

    private static final ApplicativeParser<RefreshCommand> COMMAND_PARSER =
            ApplicativeParser
                    .of(new RefreshCommand())
                    .dropNext(CommandParserUtil.END_OF_COMMAND_PARSER);

    private static final ApplicativeParser<ApplicativeParser<RefreshCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("refresh")
                    .constMap(COMMAND_PARSER);

    private static final RefreshCommandParser INSTANCE = new RefreshCommandParser();

    public static RefreshCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<RefreshCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
