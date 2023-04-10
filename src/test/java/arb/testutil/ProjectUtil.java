package arb.testutil;

import static arb.logic.parser.CliSyntax.PREFIX_CLIENT;
import static arb.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_PRICE;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import arb.logic.commands.project.AddProjectCommand;
import arb.logic.commands.project.EditProjectCommand.EditProjectDescriptor;
import arb.logic.commands.project.FindProjectCommand;
import arb.model.client.predicates.NameContainsKeywordsPredicate;
import arb.model.project.Project;
import arb.model.tag.Tag;

/**
 * A utility class for Project.
 */
public class ProjectUtil {

    /**
     * Returns an add project command string for adding the {@code project}.
     */
    public static String getAddProjectCommand(Project project, String commandWord) {
        assert AddProjectCommand.getCommandWords().contains(commandWord);
        return commandWord + " " + getProjectDetails(project);
    }

    /**
     * Returns a find project command string to find a project.
     */
    public static String getFindProjectCommand(List<String> tags, List<String> names, String commandWord) {
        assert FindProjectCommand.getCommandWords().contains(commandWord);
        StringBuilder sb = new StringBuilder();
        sb.append(commandWord + " ");
        tags.stream().forEach(
            t -> sb.append(PREFIX_TAG + t + " ")
        );
        names.stream().forEach(
            n -> sb.append(PREFIX_NAME + n + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code project}'s details.
     */
    public static String getProjectDetails(Project project) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + project.getTitle().fullTitle + " ");
        sb.append(PREFIX_DEADLINE + project.getDeadline().toString() + " ");
        sb.append(PREFIX_PRICE + project.getPrice().getPrice() + " ");
        project.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditProjectDescriptor}'s details.
     */
    public static String getEditProjectDescriptorDetails(EditProjectDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTitle().ifPresent(title -> sb.append(PREFIX_NAME).append(title.fullTitle).append(" "));
        descriptor.getDeadline().ifPresent(deadline -> sb.append(PREFIX_DEADLINE
                + deadline.map(d -> d.toString()).orElse("") + " "));
        descriptor.getPrice().ifPresent(price -> sb.append(PREFIX_PRICE
                + price.map(p -> p.getPrice().toString()).orElse("") + " "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG + " ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getClientNamePredicate().isPresent()) {
            Optional<NameContainsKeywordsPredicate> optionalPredicate = descriptor.getClientNamePredicate().get();
            if (!optionalPredicate.isPresent()) {
                sb.append(PREFIX_CLIENT + " ");
            } else {
                String[] keywords = optionalPredicate.get().keywordsToString().split(", ");
                for (String k : keywords) {
                    sb.append(PREFIX_CLIENT + k + " ");
                }
            }
        }
        return sb.toString();
    }
}
