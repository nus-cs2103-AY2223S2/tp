package codoc.logic.parser;

import static codoc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static codoc.logic.parser.CliSyntax.PREFIX_COURSE;
import static codoc.logic.parser.CliSyntax.PREFIX_EMAIL;
import static codoc.logic.parser.CliSyntax.PREFIX_GITHUB;
import static codoc.logic.parser.CliSyntax.PREFIX_LINKEDIN;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD_ADD;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD_DELETE;
import static codoc.logic.parser.CliSyntax.PREFIX_NAME;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL_ADD;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL_DELETE;
import static codoc.logic.parser.CliSyntax.PREFIX_YEAR;
import static java.util.Objects.requireNonNull;

import codoc.logic.commands.EditCommand;
import codoc.logic.commands.EditCommand.EditPersonDescriptor;
import codoc.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand and returns an EditCommand object
     * for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COURSE, PREFIX_YEAR,
                        PREFIX_LINKEDIN, PREFIX_EMAIL, PREFIX_GITHUB,
                        PREFIX_SKILL_ADD, PREFIX_SKILL_DELETE, PREFIX_SKILL,
                        PREFIX_MOD_ADD, PREFIX_MOD_DELETE, PREFIX_MOD);

        if (args.isEmpty() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_GITHUB).isPresent()) {
            editPersonDescriptor.setGithub(ParserUtil.parseGithub(argMultimap.getValue(PREFIX_GITHUB).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_COURSE).isPresent()) {
            editPersonDescriptor.setCourse(ParserUtil.parseCourse(argMultimap.getValue(PREFIX_COURSE).get()));
        }
        if (argMultimap.getValue(PREFIX_YEAR).isPresent()) {
            editPersonDescriptor.setYear(ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get()));
        }
        if (argMultimap.getValue(PREFIX_LINKEDIN).isPresent()) {
            editPersonDescriptor.setLinkedin(ParserUtil.parseLinkedin(argMultimap.getValue(PREFIX_LINKEDIN).get()));
        }
        if (argMultimap.getValue(PREFIX_SKILL_DELETE).isPresent()) {
            editPersonDescriptor.setSkillsRemoved(ParserUtil.parseSkillSet(argMultimap
                    .getAllValues(PREFIX_SKILL_DELETE)));
        }
        if (argMultimap.getValue(PREFIX_SKILL_ADD).isPresent()) {
            editPersonDescriptor.setSkillsAdded(ParserUtil.parseSkillSet(argMultimap.getAllValues(PREFIX_SKILL_ADD)));
        }
        if (argMultimap.getValue(PREFIX_SKILL).isPresent()) {
            editPersonDescriptor.setSkillsFinal(ParserUtil.parseSkillSet(argMultimap.getAllValues(PREFIX_SKILL)));
        }
        if (argMultimap.getValue(PREFIX_MOD_DELETE).isPresent()) {
            editPersonDescriptor.setModulesRemoved(ParserUtil.parseModuleSet(argMultimap
                    .getAllValues(PREFIX_MOD_DELETE)));
        }
        if (argMultimap.getValue(PREFIX_MOD_ADD).isPresent()) {
            editPersonDescriptor.setModulesAdded(ParserUtil.parseModuleSet(argMultimap.getAllValues(PREFIX_MOD_ADD)));
        }
        if (argMultimap.getValue(PREFIX_MOD).isPresent()) {
            editPersonDescriptor.setModulesFinal(ParserUtil.parseModuleSet(argMultimap.getAllValues(PREFIX_MOD)));
        }
        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(editPersonDescriptor);
    }

}
