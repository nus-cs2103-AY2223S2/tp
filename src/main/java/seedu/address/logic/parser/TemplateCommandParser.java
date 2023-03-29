package seedu.address.logic.parser;

import seedu.address.logic.commands.TemplateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Name;

import java.util.regex.Pattern;

// TODO: Improve code quality
public class TemplateCommandParser implements Parser<TemplateCommand> {
    public TemplateCommand parse(String args) throws ParseException {
        boolean isValidCommand = Pattern.matches("^(\\s+[\\w]+)+\\|(\\s+[\\w]+)+$", args.trim());
        String[] split = args.trim().split("\\|");
        return new TemplateCommand(new Name(split[0]), new Name(split[1]));
    }
}
