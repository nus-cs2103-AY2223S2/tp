package taa.logic.parser;

import java.util.function.BiFunction;

import taa.commons.util.StringUtil;
import taa.logic.commands.CsvCommand;
import taa.logic.parser.exceptions.ParseException;
/** The shared elements of {@link ImportCommandParser} and {@link ExportCommandParser}.*/
public abstract class CsvCommandParser implements Parser<CsvCommand> {
    private static final String FORCE_FLAG = "-force";
    private final BiFunction<String, Boolean, CsvCommand> mkCmd;


    protected CsvCommandParser(BiFunction<String, Boolean, CsvCommand> mkCmd) {
        this.mkCmd = mkCmd;
    }

    abstract void throwParseException() throws ParseException;

    @Override
    public CsvCommand parse(String args) throws ParseException {
        final String sanitizedArgs = StringUtil.rmAdjSpace(args).trim();
        if (sanitizedArgs.isEmpty()) {
            throwParseException();
        }
        final String[] tokens = sanitizedArgs.split(" ");
        final boolean isForced = FORCE_FLAG.equals(tokens[0]);
        final int fileIdx = isForced ? 1 : 0;
        if (tokens.length > fileIdx + 1) {
            throwParseException();
        }
        return mkCmd.apply(tokens[fileIdx], isForced);
    }
}
