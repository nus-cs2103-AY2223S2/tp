package vimification.internal.parser;

import vimification.commons.core.Index;
import vimification.internal.commands.logic.DeleteCommand;

public class DeleteCommandParser implements LogicCommandParser<DeleteCommand> {

    private static final ApplicativeParser<Index> INDEX_PARSER =
            ApplicativeParser.nonWhitespaces().flatMap(indexStr -> {
                try {
                    int indexInt = Integer.parseInt(indexStr);
                    Index index = Index.fromOneBased(indexInt);
                    return ApplicativeParser.of(index);
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    return ApplicativeParser.fail();
                }
            });

    private static final ApplicativeParser<DeleteCommand> COMMAND_PARSER = INDEX_PARSER
            .dropNext(ApplicativeParser.skipWhitespaces())
            .dropNext(ApplicativeParser.eof())
            .map(DeleteCommand::new);

    private static final ApplicativeParser<ApplicativeParser<DeleteCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .skipWhitespaces()
                    .takeNext(ApplicativeParser.string("d"))
                    .takeNext(ApplicativeParser.skipWhitespaces1())
                    .constMap(COMMAND_PARSER);

    private static final DeleteCommandParser INSTANCE = new DeleteCommandParser();

    private DeleteCommandParser() {}

    public static DeleteCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<DeleteCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
