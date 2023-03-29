package vimification.internal.parser;

import vimification.internal.command.logic.UndoCommand;

public class UndoCommandParser implements CommandParser<UndoCommand> {

    private static final ApplicativeParser<ApplicativeParser<UndoCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("undo")
                    .takeNext(ApplicativeParser.skipWhitespaces())
                    .takeNext(ApplicativeParser.eof())
                    .constMap(ApplicativeParser.of(new UndoCommand()));

    private static final UndoCommandParser INSTANCE = new UndoCommandParser();

    public static UndoCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<UndoCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
