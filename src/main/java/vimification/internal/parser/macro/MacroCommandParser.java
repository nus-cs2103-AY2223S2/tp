package vimification.internal.parser.macro;

import vimification.internal.command.macro.AddMacroCommand;
import vimification.internal.command.macro.DeleteMacroCommand;
import vimification.internal.command.macro.ListMacroCommand;
import vimification.internal.command.macro.MacroCommand;
import vimification.internal.parser.ApplicativeParser;
import vimification.internal.parser.CommandParser;
import vimification.internal.parser.CommandParserUtil;
import vimification.internal.parser.ComposedArgumentFlag;
import vimification.internal.parser.LiteralArgumentFlag;
import vimification.internal.parser.ParserException;

/**
 * The parser that can parses and creates new {@link MacroCommand}.
 */
public class MacroCommandParser implements CommandParser<MacroCommand> {

    private static final ApplicativeParser<MacroCommand> ADD_ARG_PARSER =
            ApplicativeParser
                    .skipWhitespaces1()
                    .takeNext(CommandParserUtil.STRING_PARSER)
                    .dropNext(ApplicativeParser.skipWhitespaces1())
                    .combine(CommandParserUtil.STRING_PARSER, AddMacroCommand::new);

    private static final ApplicativeParser<MacroCommand> DELETE_ARG_PARSER =
            ApplicativeParser
                    .skipWhitespaces1()
                    .takeNext(CommandParserUtil.STRING_PARSER)
                    .map(DeleteMacroCommand::new);

    private static final ApplicativeParser<MacroCommand> LIST_ARG_PARSER =
            ApplicativeParser
                    .of(new ListMacroCommand());

    private static final ApplicativeParser<MacroCommand> COMMAND_PARSER =
            CommandParserUtil.MACRO_FLAG_PARSER
                    .flatMap(MacroCommandParser::parseFlag)
                    .dropNext(CommandParserUtil.END_OF_COMMAND_PARSER);

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
            return ADD_ARG_PARSER;
        }
        if (actualFlag.equals(CommandParserUtil.DELETE_MACRO_FLAG)) {
            return DELETE_ARG_PARSER;
        }
        if (actualFlag.equals(CommandParserUtil.LIST_MACRO_FLAG)) {
            return LIST_ARG_PARSER;
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
