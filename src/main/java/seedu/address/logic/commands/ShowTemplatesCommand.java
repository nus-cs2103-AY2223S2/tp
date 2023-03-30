package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import seedu.address.commons.util.StringUtil;
import seedu.address.experimental.model.Model;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Creates a new character from a template.
 */
public class ShowTemplatesCommand extends Command {

    public static final String COMMAND_WORD = "templates";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<String> templateNames = model.getTemplates();
        requireNonNull(templateNames);
        String templates = StringUtils.join(templateNames, " | ");
        return new CommandResult(templates);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ShowTemplatesCommand;
    }
}
