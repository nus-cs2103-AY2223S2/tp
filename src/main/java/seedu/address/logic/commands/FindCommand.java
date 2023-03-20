package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.CodeContainsKeywordsPredicate;
import seedu.address.model.module.LectureNameContainsKeywordsPredicate;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.VideoNameContainsKeywordsPredicate;

/**
 * Finds and lists all modules/lectures/videos in Le Tracker current context
 * whose moduleCode/lectureName/videoName contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Finds all modules/lectures/videos whose moduleCode/lectureName/videoName contain any of "
        + "the specified keywords (case-insensitive) separated with a space "
        + "and displays them as a list with index numbers.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: \n"
        + "1." + COMMAND_WORD + " CS2040S\n"
        + "2." + COMMAND_WORD + " Week1 Week2\n"
        + "3." + COMMAND_WORD + " Video1 Video2 Video3\n"
        + "4." + COMMAND_WORD + " Topic1 Topic2 /mod ST2334\n";

    private List<String> keywords;

    private ModuleCode moduleCode;

    private LectureName lectureName;

    /**
     * Creates a FindCommand to search from current context.
     * @param keywords
     */
    public FindCommand(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Creates a FindCommand to search for lectures from module context
     * @param moduleCode
     */
    public FindCommand(List<String> keywords, ModuleCode moduleCode) {
        this.keywords = keywords;
        this.moduleCode = moduleCode;
    }

    /**
     * Creates a FindCommand to search for videos from lecture context
     */
    public FindCommand(List<String> keywords, ModuleCode moduleCode, LectureName lectureName) {
        this.keywords = keywords;
        this.moduleCode = moduleCode;
        this.lectureName = lectureName;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (moduleCode != null) {
            if (lectureName != null) {
                model.updateFilteredVideoList(
                    new VideoNameContainsKeywordsPredicate(keywords), model.getLecture(moduleCode, lectureName));
                return new CommandResult(
                    String.format(Messages.MESSAGE_VIDEOS_LISTED_OVERVIEW, model.getFilteredVideoList().size()));
            }
            model.updateFilteredLectureList(
                new LectureNameContainsKeywordsPredicate(keywords), model.getModule(moduleCode));
            return new CommandResult(
                String.format(Messages.MESSAGE_LECTURES_LISTED_OVERVIEW, model.getFilteredLectureList().size()));
        }
        model.updateFilteredModuleList(new CodeContainsKeywordsPredicate(keywords));
        return new CommandResult(
            String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, model.getFilteredModuleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && keywords.equals(((FindCommand) other).keywords)); // state check
    }
}
