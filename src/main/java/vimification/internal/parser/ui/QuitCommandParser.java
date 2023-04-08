package vimification.internal.parser.ui;

import vimification.internal.command.ui.QuitCommand;
import vimification.internal.parser.ApplicativeParser;
import vimification.internal.parser.CommandParser;
import vimification.internal.parser.CommandParserUtil;

/**
 * The parser that can parses and creates new {@link QuitCommand}.
 */
public class QuitCommandParser implements CommandParser<QuitCommand> {

    private static final ApplicativeParser<QuitCommand> COMMAND_PARSER =
            ApplicativeParser
                    .of(new QuitCommand())
                    .dropNext(CommandParserUtil.END_OF_COMMAND_PARSER);


    private static final ApplicativeParser<ApplicativeParser<QuitCommand>> INTERNAL_PARSER =
            ApplicativeParser.choice(
                    ApplicativeParser.string("quit"),
                    // ApplicativeParser.string("q"),
                    ApplicativeParser.string("q!"))
                    .constMap(COMMAND_PARSER);

    private static final QuitCommandParser INSTANCE = new QuitCommandParser();

    public static QuitCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<QuitCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
