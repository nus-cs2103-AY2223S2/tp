package codoc.testutil;

import static codoc.logic.parser.CliSyntax.PREFIX_EMAIL;
import static codoc.logic.parser.CliSyntax.PREFIX_GITHUB;
import static codoc.logic.parser.CliSyntax.PREFIX_LINKEDIN;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD_ADD;
import static codoc.logic.parser.CliSyntax.PREFIX_NAME;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL_ADD;

import java.util.Set;

import codoc.logic.commands.AddCommand;
import codoc.logic.commands.EditCommand.EditPersonDescriptor;
import codoc.model.module.Module;
import codoc.model.person.Person;
import codoc.model.skill.Skill;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_GITHUB + person.getGithub().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_LINKEDIN + person.getLinkedin().value + " ");
        person.getSkills().stream().forEach(
            s -> sb.append(PREFIX_SKILL + s.skillName + " ")
        );
        person.getModules().stream().forEach(
                m -> sb.append(PREFIX_MOD + m.moduleName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getGithub().ifPresent(github -> sb.append(PREFIX_GITHUB).append(github.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getLinkedin().ifPresent(linkedin -> sb.append(PREFIX_LINKEDIN).append(linkedin.value).append(" "));
        if (descriptor.getSkills().isPresent()) {
            Set<Skill> skills = descriptor.getSkills().get();
            if (skills.isEmpty()) {
                sb.append(PREFIX_SKILL_ADD);
            } else {
                skills.forEach(s -> sb.append(PREFIX_SKILL_ADD).append(s.skillName).append(" "));
            }
        }
        if (descriptor.getModules().isPresent()) {
            Set<Module> modules = descriptor.getModules().get();
            if (modules.isEmpty()) {
                sb.append(PREFIX_MOD_ADD);
            } else {
                modules.forEach(m -> sb.append(PREFIX_MOD_ADD).append(m.moduleName).append(" "));
            }
        }
        return sb.toString();
    }
}
