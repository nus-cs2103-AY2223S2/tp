package codoc.logic.parser;

import static codoc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static codoc.logic.parser.CliSyntax.PREFIX_COURSE;
import static codoc.logic.parser.CliSyntax.PREFIX_EMAIL;
import static codoc.logic.parser.CliSyntax.PREFIX_GITHUB;
import static codoc.logic.parser.CliSyntax.PREFIX_LINKEDIN;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD;
import static codoc.logic.parser.CliSyntax.PREFIX_NAME;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL;
import static codoc.logic.parser.CliSyntax.PREFIX_YEAR;

import java.io.File;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import codoc.logic.commands.AddCommand;
import codoc.logic.parser.exceptions.ParseException;
import codoc.model.course.Course;
import codoc.model.module.Module;
import codoc.model.person.Email;
import codoc.model.person.Github;
import codoc.model.person.Linkedin;
import codoc.model.person.Name;
import codoc.model.person.Person;
import codoc.model.person.ProfilePicture;
import codoc.model.person.Year;
import codoc.model.skill.Skill;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand and returns an AddCommand object
     * for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args,
                        PREFIX_NAME,
                        PREFIX_GITHUB, PREFIX_EMAIL,
                        PREFIX_LINKEDIN,
                        PREFIX_SKILL,
                        PREFIX_MOD,
                        PREFIX_COURSE,
                        PREFIX_YEAR
                );

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EMAIL, PREFIX_YEAR, PREFIX_COURSE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        // randomly select a profile pic from the folder
        File[] files =
                new File("src/main/resources/images/avataricons").listFiles();
        assert files != null;
        int randomIndex = new Random().nextInt(files.length);
        String profilePicturePath = files[randomIndex].getAbsolutePath();

        // set the profile pic of the person
        ProfilePicture profilePicture = new ProfilePicture(profilePicturePath);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Github github = ParserUtil.parseGithub(argMultimap.getValue(PREFIX_GITHUB).orElseGet(() -> null));
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Course course = ParserUtil.parseCourse(argMultimap.getValue(PREFIX_COURSE).get());
        Year year = ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get());
        Linkedin linkedin = ParserUtil.parseLinkedin(argMultimap.getValue(PREFIX_LINKEDIN).orElseGet(() -> null));
        Set<Skill> skillList = ParserUtil.parseSkillSet(argMultimap.getAllValues(PREFIX_SKILL));
        Set<Module> moduleSet = ParserUtil.parseModuleSet(argMultimap.getAllValues(PREFIX_MOD));

        Person person = new Person(profilePicture, name, course, year, github, email, linkedin, skillList, moduleSet);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
