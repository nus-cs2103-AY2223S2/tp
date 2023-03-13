package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_ADD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_DELETE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_NEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_OLD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL_ADD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL_DELETE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL_NEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL_OLD;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.skill.Skill;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    private Person protagonist;

    public EditCommandParser(Person protagonist) {
        this.protagonist = protagonist;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_SKILL_ADD, PREFIX_SKILL_DELETE, PREFIX_SKILL_OLD, PREFIX_SKILL_NEW,
                        PREFIX_MOD_ADD, PREFIX_MOD_DELETE, PREFIX_MOD_OLD, PREFIX_MOD_NEW, PREFIX_MODULE);

        if (args.isEmpty() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        EditPersonDescriptor originalEditPersonDescriptor = new EditPersonDescriptor(protagonist);
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor(originalEditPersonDescriptor);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseSkillsForEdit(argMultimap.getAllValues(PREFIX_SKILL_ADD)).ifPresent(editPersonDescriptor::addSkills);

        if (argMultimap.getValue(PREFIX_SKILL_DELETE).isPresent()) {
            parseSkillsForEdit(argMultimap.getAllValues(PREFIX_SKILL_DELETE))
                    .ifPresent(editPersonDescriptor::deleteSkills);
        }
        parseModulesForEdit(argMultimap.getAllValues(PREFIX_MODULE)).ifPresent(editPersonDescriptor::setModules);

        if (argMultimap.getValue(PREFIX_SKILL_OLD).isPresent()
                && argMultimap.getValue(PREFIX_SKILL_NEW).isPresent()) {
            List<String> oldSkills = argMultimap.getAllValues(PREFIX_SKILL_OLD);
            List<String> newSkills = argMultimap.getAllValues(PREFIX_SKILL_NEW);
            parseSkillsForUpdate(oldSkills, newSkills, editPersonDescriptor);
        } else if (argMultimap.getValue(PREFIX_SKILL_OLD).isPresent()
                || argMultimap.getValue(PREFIX_SKILL_NEW).isPresent()) {
            throw new ParseException(EditCommand.MESSAGE_INCORRECT_OLD_NEW_PREFIX);
        }

        if (editPersonDescriptor.getSkills().isEmpty()) {
            throw new ParseException(EditCommand.MESSAGE_SKILL_DOES_NOT_EXIST);
        }
        if (editPersonDescriptor.equals(originalEditPersonDescriptor)) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> skills} into a {@code Set<Skill>} if {@code skills} is non-empty.
     * If {@code skills} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Skill>} containing zero skills.
     */
    private Optional<Set<Skill>> parseSkillsForEdit(Collection<String> skills) throws ParseException {
        assert skills != null;

        if (skills.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> skillSet = skills.size() == 1 && skills.contains("") ? Collections.emptySet() : skills;
        return Optional.of(ParserUtil.parseSkills(skillSet));
    }
    private Optional<Set<Module>> parseModulesForEdit(Collection<String> modules) throws ParseException {
        assert modules != null;

        if (modules.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> moduleList = modules.size() == 1 && modules.contains("") ? Collections.emptySet() : modules;
        return Optional.of(ParserUtil.parseModules(moduleList));
    }

    private void parseSkillsForUpdate(List<String> oldSkills, List<String> newSkills,
                                      EditPersonDescriptor editPersonDescriptor) throws ParseException {
        if (newSkills.size() == oldSkills.size()) {
            Optional<Set<Skill>> setOfOldSkills = parseSkillsForEdit(oldSkills);
            Optional<Set<Skill>> setOfNewSkills = parseSkillsForEdit(newSkills);
            if (setOfOldSkills.isPresent() && setOfNewSkills.isPresent()) {
                editPersonDescriptor.updateSkills(setOfOldSkills.get(), setOfNewSkills.get());
            }
        } else {
            throw new ParseException("The number of old skills not equal to number of new skills");
        }
    }

}
