package vimification.internal.parser;

import vimification.internal.command.ui.QuitCommand;

public class QuitCommandParser implements CommandParser<QuitCommand> {

    private static final ApplicativeParser<ApplicativeParser<QuitCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("quit")
                    .takeNext(ApplicativeParser.skipWhitespaces())
                    .takeNext(ApplicativeParser.eof())
                    .constMap(ApplicativeParser.of(new QuitCommand()));

    private static final QuitCommandParser INSTANCE = new QuitCommandParser();

    public static QuitCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<QuitCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
