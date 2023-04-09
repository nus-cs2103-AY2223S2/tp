package seedu.address.logic.parser;

import java.util.regex.Pattern;

import seedu.address.logic.commands.TemplateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Name;

// TODO: Improve code quality

/**
 * Parses given {@code String} of arguments in the context of TemplateCommand and returns
 * a TemplateCommand for execution
 */
public class TemplateCommandParser implements Parser<TemplateCommand> {
    @Override
    public TemplateCommand parse(String args) throws ParseException {
        boolean isValidCommand = Pattern.matches("^([\\w]+)(\\s+[\\w]+)+$", args.trim());
        if (!isValidCommand) {
            throw new ParseException("To add");
        }
        String[] split = args.trim().split(" ", 2);
        return new TemplateCommand(split[0], new Name(split[1]));
    }
}
