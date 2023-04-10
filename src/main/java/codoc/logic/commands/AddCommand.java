package codoc.logic.commands;

import static codoc.logic.parser.CliSyntax.PREFIX_COURSE;
import static codoc.logic.parser.CliSyntax.PREFIX_EMAIL;
import static codoc.logic.parser.CliSyntax.PREFIX_GITHUB;
import static codoc.logic.parser.CliSyntax.PREFIX_LINKEDIN;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD;
import static codoc.logic.parser.CliSyntax.PREFIX_NAME;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL;
import static codoc.logic.parser.CliSyntax.PREFIX_YEAR;
import static java.util.Objects.requireNonNull;

import codoc.logic.commands.exceptions.CommandException;
import codoc.model.Model;
import codoc.model.person.Person;

/**
 * Adds a person to CoDoc.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to CoDoc. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_COURSE + "COURSE "
            + PREFIX_YEAR + "YEAR "
            + "[" + PREFIX_GITHUB + "GITHUB] "
            + "[" + PREFIX_LINKEDIN + "LINKEDIN]\n"
            + "[" + PREFIX_SKILL + "SKILL]\n"
            + "[" + PREFIX_MOD + "MOD]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_COURSE + "1 "
            + PREFIX_YEAR + "2 "
            + PREFIX_GITHUB + "j0hn-Do3 "
            + PREFIX_LINKEDIN + "linkedin.com/in/j0hn-Do3 "
            + PREFIX_SKILL + "python "
            + PREFIX_SKILL + "java"
            + PREFIX_MOD + "AY2223S2 CS2100"
            + PREFIX_MOD + "AY2122S1 CS1101S";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in CoDoc";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        if (model.getFilteredPersonList().size() == 0) {
            model.setProtagonist(toAdd);
        }
        model.addPerson(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
