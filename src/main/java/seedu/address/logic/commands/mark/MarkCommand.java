package seedu.address.logic.commands.mark;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;

/**
 * Marks a video of a lecture in a module as watched or unwatched
 */
public abstract class MarkCommand extends Command {

    public static final String MESSAGE_EXAMPLE = " vIdEo 1 "
            + PREFIX_MODULE + " CS2040S "
            + PREFIX_LECTURE + " Week 2";

    public static final String COMMAND_WORDS = MarkAsWatchedCommand.COMMAND_WORD
            + "/" + MarkAsUnwatchedCommand.COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORDS
            + ":\n"
            + "Marks the video in the lecture in the module as watched.\n"
            + "Video is identified by the video name.\n"
            + "Module is identified by the module code\n"
            + "Lecture is identified by the lecture name.\n"
            + "Parameters: video name, module code, lecture name\n"
            + "Example: " + COMMAND_WORDS + MESSAGE_EXAMPLE + "\n"
            + Messages.MESSAGE_CAN_DO_MULTIPLE;

    /**
     * Skeletal string for unsuccessful mark / unmark command due to video already being marked / unmarked
     *
     * @param 1 VideoNames
     * @param 2 Command Word (mark / unmark)
     * @param 3 --
     * @param 4 "s" or "". For grammatical correctness
     * @param 5 Lecture Name
     * @param 6 ModuleCode
     */
    public static final String MESSAGE_VIDEO_MARK_NOT_CHANGED = "Video%4$s %1$s"
            + " in Lecture %5$s Module %6$s already %2$s"
            + "ed! No changes made to all specified videos...";

    /**
     * Skeletal string for successful mark / unmark command
     *
     * @param 1 Video Names
     * @param 2 Command Word (mark / unmark)
     * @param 3 Number of videos + " "
     * @param 4 "s" or "". For grammatical correctness
     * @param 5 Lecture Name
     * @param 6 Module Code
     */
    public static final String MESSAGE_MARK_VIDEO_SUCCESS = "Successfully %2$s"
            + "ed %3$sVideo%4$s in Lecture %5$s Module %6$s: %1$s";

}
