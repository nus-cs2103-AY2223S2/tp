package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_CONTEXT_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

/**
 * Marks a video of a lecture in a module as watched or unwatched
 */
public abstract class MarkCommand extends Command {

    public static final String MESSAGE_EXAMPLE = " Video 1, Video 2 "
            + PREFIX_MODULE + " CS2040S "
            + PREFIX_LECTURE + " Week 2";

    public static final String COMMAND_WORDS = MarkAsWatchedCommand.COMMAND_WORD
            + "/" + MarkAsUnwatchedCommand.COMMAND_WORD;

    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORDS
            + ": Marks one or more videos of a lecture as watched or not watched.\n"
            + "Parameters: "
            + "{video_name_1}[, {video_name_2}[, ...]] "
            + PREFIX_MODULE + " {module_code} "
            + PREFIX_LECTURE + " {lecture_name}\n"
            + "Examples:\n"
            + MarkAsWatchedCommand.COMMAND_WORD + MESSAGE_EXAMPLE + "\n"
            + MarkAsUnwatchedCommand.COMMAND_WORD + MESSAGE_EXAMPLE + "\n\n"
            + MESSAGE_CONTEXT_USAGE;

    public static final String MESSAGE_VIDEO_MARK_NOT_CHANGED = "Video %1$s already %2$s" + "ed! No change...";

    public static final String MESSAGE_MARK_VIDEO_SUCCESS = "Successfully %2$s" + "ed Video: %1$s";

}
