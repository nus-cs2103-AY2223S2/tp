package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.NoSuchElementException;

import seedu.address.experimental.model.Model;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Name;
import seedu.address.model.entity.Template;

/**
 * Creates a new character from a template.
 */
public class TemplateCommand extends Command {

    public static final String COMMAND_WORD = "template";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a character using a template. ";

    public static final String MESSAGE_SUCCESS = "New character added: %1$s";
    public static final String MESSAGE_DUPLICATE_ENTITY = "This character name already exists in Reroll";
    public static final String MESSAGE_NO_TEMPLATE = "No such template was found in Reroll!";

    private final Name templateName;
    private final Name newName;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public TemplateCommand(Name templateName, Name newName) {
        requireAllNonNull(templateName, newName);
        this.templateName = templateName;
        this.newName = newName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Entity toAdd = null;
        try {
            toAdd = model.createFromTemplate(newName, templateName);
        } catch (NoSuchElementException e) {
            throw new CommandException(e.getMessage());
        }
        requireNonNull(toAdd);
        if (model.hasEntity(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ENTITY);
        }
        model.addEntity(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TemplateCommand // instanceof handles nulls
                && templateName.equals(((TemplateCommand) other).templateName)
                && newName.equals(((TemplateCommand) other).newName));
    }
}
