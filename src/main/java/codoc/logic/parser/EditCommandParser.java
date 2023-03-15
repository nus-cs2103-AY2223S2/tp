package codoc.logic.parser;

import static codoc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static codoc.logic.parser.CliSyntax.PREFIX_COURSE;
import static codoc.logic.parser.CliSyntax.PREFIX_EMAIL;
import static codoc.logic.parser.CliSyntax.PREFIX_GITHUB;
import static codoc.logic.parser.CliSyntax.PREFIX_LINKEDIN;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD_ADD;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD_DELETE;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD_NEW;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD_OLD;
import static codoc.logic.parser.CliSyntax.PREFIX_NAME;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL_ADD;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL_DELETE;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL_NEW;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL_OLD;
import static codoc.logic.parser.CliSyntax.PREFIX_YEAR;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import codoc.logic.commands.EditCommand;
import codoc.logic.commands.EditCommand.EditPersonDescriptor;
import codoc.logic.parser.exceptions.ParseException;
import codoc.model.module.Module;
import codoc.model.person.Person;
import codoc.model.skill.Skill;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    private Person protagonist;

    public EditCommandParser(Person protagonist) {
        this.protagonist = protagonist;
    }

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
                        PREFIX_SKILL_ADD, PREFIX_SKILL_DELETE, PREFIX_SKILL_OLD, PREFIX_SKILL_NEW,
                        PREFIX_MOD_ADD, PREFIX_MOD_DELETE, PREFIX_MOD_OLD, PREFIX_MOD_NEW);

        if (args.isEmpty() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        EditPersonDescriptor originalEditPersonDescriptor = new EditPersonDescriptor(protagonist);
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor(originalEditPersonDescriptor);

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

        editSkills(argMultimap, editPersonDescriptor);

        editModules(argMultimap, editPersonDescriptor);

        if (editPersonDescriptor.getSkills().isEmpty() && originalEditPersonDescriptor.getSkills().isPresent()
                && argMultimap.getValue(PREFIX_SKILL_DELETE).isEmpty()) {
            throw new ParseException(EditCommand.MESSAGE_SKILL_DOES_NOT_EXIST);
        }

        if (editPersonDescriptor.getModules().isEmpty() && originalEditPersonDescriptor.getModules().isPresent()
                && argMultimap.getValue(PREFIX_MOD_DELETE).isEmpty()) {
            throw new ParseException(EditCommand.MESSAGE_MOD_DOES_NOT_EXIST);
        }

        if (editPersonDescriptor.equals(originalEditPersonDescriptor)) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return new EditCommand(editPersonDescriptor);
    }

    private void editSkills(ArgumentMultimap argMultimap, EditPersonDescriptor editPersonDescriptor)
            throws ParseException {

        parseSkillsForEdit(argMultimap.getAllValues(PREFIX_SKILL_ADD)).ifPresent(editPersonDescriptor::addSkills);

        if (argMultimap.getValue(PREFIX_SKILL_DELETE).isPresent()) {
            parseSkillsForEdit(argMultimap.getAllValues(PREFIX_SKILL_DELETE))
                    .ifPresent(editPersonDescriptor::deleteSkills);
        }

        if (argMultimap.getValue(PREFIX_SKILL_OLD).isPresent()
                && argMultimap.getValue(PREFIX_SKILL_NEW).isPresent()) {
            List<String> oldSkills = argMultimap.getAllValues(PREFIX_SKILL_OLD);
            List<String> newSkills = argMultimap.getAllValues(PREFIX_SKILL_NEW);
            parseSkillsForUpdate(oldSkills, newSkills, editPersonDescriptor);
        } else if (argMultimap.getValue(PREFIX_SKILL_OLD).isPresent()
                || argMultimap.getValue(PREFIX_SKILL_NEW).isPresent()) {
            throw new ParseException(EditCommand.MESSAGE_INCORRECT_OLD_NEW_SKILL_PREFIX);
        }
    }

    private void editModules(ArgumentMultimap argMultimap, EditPersonDescriptor editPersonDescriptor)
            throws ParseException {

        parseModulesForEdit(argMultimap.getAllValues(PREFIX_MOD_ADD)).ifPresent(editPersonDescriptor::addMods);

        if (argMultimap.getValue(PREFIX_MOD_DELETE).isPresent()) {
            parseModulesForEdit(argMultimap.getAllValues(PREFIX_MOD_DELETE))
                    .ifPresent(editPersonDescriptor::deleteMods);
        }

        if (argMultimap.getValue(PREFIX_MOD_OLD).isPresent()
                && argMultimap.getValue(PREFIX_MOD_NEW).isPresent()) {
            List<String> oldMods = argMultimap.getAllValues(PREFIX_MOD_OLD);
            List<String> newMods = argMultimap.getAllValues(PREFIX_MOD_NEW);
            parseModsForUpdate(oldMods, newMods, editPersonDescriptor);
        } else if (argMultimap.getValue(PREFIX_MOD_OLD).isPresent()
                || argMultimap.getValue(PREFIX_MOD_NEW).isPresent()) {
            throw new ParseException(EditCommand.MESSAGE_INCORRECT_OLD_NEW_MOD_PREFIX);
        }
    }

    /**
     * Parses {@code Collection<String> skills} into a {@code Set<Skill>} if {@code skills} is non-empty. If
     * {@code skills} contain only one element which is an empty string, it will be parsed into a {@code Set<Skill>}
     * containing zero skills.
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
            throw new ParseException(EditCommand.MESSAGE_UNEQUAL_OLD_NEW_SKILLS);
        }
    }
    private void parseModsForUpdate(List<String> oldMods, List<String> newMods,
                                      EditPersonDescriptor editPersonDescriptor) throws ParseException {
        if (newMods.size() == oldMods.size()) {
            Optional<Set<Module>> setOfOldMods = parseModulesForEdit(oldMods);
            Optional<Set<Module>> setOfNewMods = parseModulesForEdit(newMods);
            if (setOfOldMods.isPresent() && setOfNewMods.isPresent()) {
                editPersonDescriptor.updateMods(setOfOldMods.get(), setOfNewMods.get());
            }
        } else {
            throw new ParseException(EditCommand.MESSAGE_UNEQUAL_OLD_NEW_MODS);
        }
    }

}
