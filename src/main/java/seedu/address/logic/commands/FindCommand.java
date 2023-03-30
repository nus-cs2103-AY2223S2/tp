package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CONTEXT_USAGE;
import static seedu.address.commons.core.Messages.MESSAGE_LECTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BY_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.LectureNameContainsKeywordsPredicate;
import seedu.address.model.module.LectureTagContainsKeywordsPredicate;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleContainsKeywordsPredicate;
import seedu.address.model.module.ModuleTagContainsKeywordsPredicate;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.module.VideoNameContainsKeywordsPredicate;
import seedu.address.model.module.VideoTagContainsKeywordsPredicate;
import seedu.address.model.video.Video;

/**
 * Finds and lists all modules/lectures/videos in Le Tracker current context
 * whose moduleCode/lectureName/videoName contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD + ":\n"
            + "(1) Finds all modules whose module code or name contain any of the specified keywords.\n"
            + "Parameters: {keyword_1}[, {keyword_2}[, ...]]\n"
            + "Example: " + COMMAND_WORD + " CS2040S\n\n"
            + "(2) Find all modules whose tag(s) contain any of the specified keywords.\n"
            + "Parameters: " + "{keyword_1}[, {keyword_2}[, ...]] " + PREFIX_BY_TAG + "\n"
            + "Example: " + COMMAND_WORD + " Heavy " + PREFIX_BY_TAG + "\n\n"
            + "(3) Finds all lectures of a module whose name contain any of the specified keywords.\n"
            + "Parameters: {keyword_1}[, {keyword_2}[, ...]] " + PREFIX_MODULE + " {module_code}\n"
            + "Example: " + COMMAND_WORD + " Topic 1, Topic 2 " + PREFIX_MODULE + " ST2334\n\n"
            + "(4) Finds all lectures of a module whose tag(s) contain any of the specified keywords.\n"
            + "Parameters: {keyword_1}[, {keyword_2}[, ...]] " + PREFIX_MODULE + " {module_code} "
            + PREFIX_BY_TAG + "\n"
            + "Example: " + COMMAND_WORD + " Heavy " + PREFIX_MODULE + " ST2334 " + PREFIX_BY_TAG + "\n\n"
            + "(5) Finds all videos of a lecture whose name contain any of the specified keywords.\n"
            + "Parameters: {keyword_1}[, {keyword_2}[, ...]] " + PREFIX_MODULE + " {module_code} "
            + PREFIX_LECTURE + " {lecture_name}\n"
            + "Example: " + COMMAND_WORD + " Video 1 " + PREFIX_MODULE + " ST2334 " + PREFIX_LECTURE + " Week 1\n\n"
            + "(6) Finds all videos of a lecture whose tag(s) contain any of the specified keywords.\n"
            + "Parameters: {keyword_1}[, {keyword_2}[, ...]] " + PREFIX_MODULE + " {module_code} "
            + PREFIX_BY_TAG + "\n"
            + "Example: " + COMMAND_WORD + " Video 1 " + PREFIX_MODULE + " ST2334 " + PREFIX_LECTURE + " Week 1 "
            + PREFIX_BY_TAG + "\n\n"
            + MESSAGE_CONTEXT_USAGE;

    // public static final String MESSAGE_USAGE = COMMAND_WORD
    //         + ": Finds all modules/lectures/videos whose moduleCode/lectureName/videoName or tagNames contain any of "
    //         + "the specified keywords (case-insensitive) separated with a comma "
    //         + "and displays them as a list with index numbers.\n"
    //         + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
    //         + "Example: \n"
    //         + "Find by moduleCode/lectureName/videoName: \n"
    //         + "1." + COMMAND_WORD + " CS2040S\n"
    //         + "2." + COMMAND_WORD + " Week 1, Week 2\n"
    //         + "3." + COMMAND_WORD + " Video 1, Video 2, Video 3\n"
    //         + "4." + COMMAND_WORD + " Topic 1, Topic 2 /mod ST2334\n"
    //         + "5." + COMMAND_WORD + " Video 1 /mod ST2334 /lec Week 1\n"
    //         + "Find by tagName: \n"
    //         + "1." + COMMAND_WORD + " Heavy /byTag\n"
    //         + "2." + COMMAND_WORD + " Heavy /byTag /mod ST2334\n"
    //         + "3." + COMMAND_WORD + " Heavy /byTag /mod ST2334 /lec Week 1\n";

    private List<String> keywords;

    private boolean hasByTag;

    private ModuleCode moduleCode;

    private LectureName lectureName;

    /**
     * Creates a FindCommand to search from current context.
     * @param keywords
     * @param hasByTag
     */
    public FindCommand(List<String> keywords, boolean hasByTag) {
        this.keywords = keywords;
        this.hasByTag = hasByTag;
    }

    /**
     * Creates a FindCommand to search for lectures from module context
     * @param keywords
     * @param moduleCode
     * @param hasByTag
     */
    public FindCommand(List<String> keywords, ModuleCode moduleCode, boolean hasByTag) {
        this.keywords = keywords;
        this.moduleCode = moduleCode;
        this.hasByTag = hasByTag;
    }

    /**
     * Creates a FindCommand to search for videos from lecture context
     * @param keywords
     * @param moduleCode
     * @param lectureName
     * @param hasByTag
     */
    public FindCommand(List<String> keywords, ModuleCode moduleCode, LectureName lectureName, boolean hasByTag) {
        this.keywords = keywords;
        this.moduleCode = moduleCode;
        this.lectureName = lectureName;
        this.hasByTag = hasByTag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (moduleCode != null && lectureName != null) {
            if (!model.hasLecture(moduleCode, lectureName)) {
                throw new CommandException(
                    String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, moduleCode));
            }
            return filterByVideoList(model);
        }
        if (moduleCode != null) {
            if (!model.hasModule(moduleCode)) {
                throw new CommandException(
                    String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
            }
            return filterByLectureList(model);
        }
        return filterByModuleList(model);
    }

    private CommandResult filterByVideoList(Model model) {
        Predicate<Video> videoPredicate = hasByTag
                ? new VideoTagContainsKeywordsPredicate(keywords)
                : new VideoNameContainsKeywordsPredicate(keywords);
        model.updateFilteredVideoList(videoPredicate, moduleCode, model.getLecture(moduleCode, lectureName));
        return new CommandResult(
                String.format(Messages.MESSAGE_VIDEOS_LISTED_OVERVIEW, model.getFilteredVideoList().size()));
    }

    private CommandResult filterByLectureList(Model model) {
        Predicate<ReadOnlyLecture> lecturePredicate = hasByTag
                ? new LectureTagContainsKeywordsPredicate(keywords)
                : new LectureNameContainsKeywordsPredicate(keywords);
        model.updateFilteredLectureList(lecturePredicate, model.getModule(moduleCode));
        return new CommandResult(
                String.format(Messages.MESSAGE_LECTURES_LISTED_OVERVIEW, model.getFilteredLectureList().size()));
    }

    private CommandResult filterByModuleList(Model model) {
        Predicate<ReadOnlyModule> modulePredicate = hasByTag
                ? new ModuleTagContainsKeywordsPredicate(keywords)
                : new ModuleContainsKeywordsPredicate(keywords);
        model.updateFilteredModuleList(modulePredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, model.getFilteredModuleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof FindCommand) { // instanceof handles nulls
            FindCommand otherCommand = (FindCommand) other;
            return hasByTag == otherCommand.hasByTag
                    && keywords.equals(otherCommand.keywords);
        }
        return false;
    }
}
