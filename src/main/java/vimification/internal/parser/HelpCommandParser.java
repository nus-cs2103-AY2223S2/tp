package vimification.internal.parser;

import vimification.internal.command.ui.HelpCommand;

public class HelpCommandParser implements CommandParser<HelpCommand> {

    private static final ApplicativeParser<ApplicativeParser<HelpCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("help")
                    .takeNext(ApplicativeParser.skipWhitespaces())
                    .takeNext(ApplicativeParser.eof())
                    .constMap(ApplicativeParser.of(new HelpCommand()));

    private static final HelpCommandParser INSTANCE = new HelpCommandParser();

    public static HelpCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<HelpCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
