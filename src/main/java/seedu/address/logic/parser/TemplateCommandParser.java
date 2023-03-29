package seedu.address.logic.parser;

import seedu.address.logic.commands.TemplateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Name;

public class TemplateCommandParser implements Parser<TemplateCommand> {
    public TemplateCommand parse(String args) throws ParseException {
        return new TemplateCommand(new Name("orc"), new Name("test"));
    }
}
