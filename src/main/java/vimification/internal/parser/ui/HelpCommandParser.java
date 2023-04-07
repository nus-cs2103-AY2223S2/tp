package vimification.internal.parser.ui;

import vimification.internal.command.ui.HelpCommand;
import vimification.internal.parser.ApplicativeParser;
import vimification.internal.parser.CommandParser;
import vimification.internal.parser.CommandParserUtil;

/**
 * The parser that can parses and creates new {@link HelpCommand}.
 */
public class HelpCommandParser implements CommandParser<HelpCommand> {

    private static final ApplicativeParser<HelpCommand> COMMAND_PARSER =
            ApplicativeParser
                    .of(new HelpCommand())
                    .dropNext(CommandParserUtil.END_OF_COMMAND_PARSER);

    private static final ApplicativeParser<ApplicativeParser<HelpCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("help")
                    .constMap(COMMAND_PARSER);

    private static final HelpCommandParser INSTANCE = new HelpCommandParser();

    public static HelpCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<HelpCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
