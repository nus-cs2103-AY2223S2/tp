package vimification.internal.parser;

import vimification.internal.command.view.RefreshCommand;

public class RefreshCommandParser implements CommandParser<RefreshCommand> {

    private static final ApplicativeParser<ApplicativeParser<RefreshCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("refresh")
                    .takeNext(ApplicativeParser.skipWhitespaces())
                    .takeNext(ApplicativeParser.eof())
                    .constMap(ApplicativeParser.of(new RefreshCommand()));

    private static final RefreshCommandParser INSTANCE = new RefreshCommandParser();

    public static RefreshCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<RefreshCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
