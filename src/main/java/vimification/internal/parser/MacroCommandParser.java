package vimification.internal.parser;

import vimification.internal.command.macro.AddMacroCommand;
import vimification.internal.command.macro.DeleteMacroCommand;
import vimification.internal.command.macro.ListMacroCommand;
import vimification.internal.command.macro.MacroCommand;

public class MacroCommandParser implements CommandParser<MacroCommand> {

    private static final ApplicativeParser<MacroCommand> COMMAND_PARSER =
            CommandParserUtil.MACRO_FLAG_PARSER
                    .dropNext(ApplicativeParser.skipWhitespaces1())
                    .flatMap(MacroCommandParser::parseFlag)
                    .dropNext(ApplicativeParser.skipWhitespaces())
                    .dropNext(ApplicativeParser.eof());

    private static final ApplicativeParser<ApplicativeParser<MacroCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("macro")
                    .takeNext(ApplicativeParser.skipWhitespaces1())
                    .constMap(COMMAND_PARSER);

    private static final MacroCommandParser INSTANCE = new MacroCommandParser();

    private MacroCommandParser() {}

    private static final ApplicativeParser<MacroCommand> parseFlag(ComposedArgumentFlag flag) {
        LiteralArgumentFlag actualFlag = flag.getActualFlag();
        if (actualFlag.equals(CommandParserUtil.ADD_MACRO_FLAG)) {
            return CommandParserUtil.STRING_PARSER
                    .dropNext(ApplicativeParser.skipWhitespaces1())
                    .combine(CommandParserUtil.STRING_PARSER, AddMacroCommand::new);
        }
        if (actualFlag.equals(CommandParserUtil.DELETE_MACRO_FLAG)) {
            return CommandParserUtil.STRING_PARSER.map(DeleteMacroCommand::new);
        }
        if (actualFlag.equals(CommandParserUtil.LIST_MACRO_FLAG)) {
            return CommandParserUtil.STRING_PARSER.map(ignore -> new ListMacroCommand());
        }
        throw new ParserException("Should not reach here!");
    }

    public static MacroCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<MacroCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }

}
