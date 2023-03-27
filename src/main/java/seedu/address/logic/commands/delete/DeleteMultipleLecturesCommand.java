package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.MultipleEventsParser;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;

/**
 * Deletes multiple lectures identified using their respective lecture names
 * multiple lectures specified are to exist within the same module
 * If one or more of the lectures do not exist within the same module, nothing is deleted.
 */
public class DeleteMultipleLecturesCommand extends DeleteCommand {

    public static final String MESSAGE_SUCCESS = "%1$S Lectures deleted from Module %2$s: \n%3$s";

    private final ModuleCode moduleCode;
    private final ArrayList<LectureName> targetLectureNames;

    /**
     * Creates an executable command that deletes multiple lectures of {@code lectureNames}
     * @param moduleCode Module Code of module that all lectures to be deleted are within
     * @param lectureNames any number of lecture names to identify lectures to delete in a select module
     */
    public DeleteMultipleLecturesCommand(ModuleCode moduleCode, LectureName ... lectureNames) {
        this.moduleCode = moduleCode;

        ArrayList<LectureName> lectureNamesArr = new ArrayList<LectureName>();
        for (LectureName each: lectureNames) {
            lectureNamesArr.add(each);
        }

        this.targetLectureNames = lectureNamesArr;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!(model.hasModule(this.moduleCode))) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_DOES_NOT_EXIST, this.moduleCode));
        }
        ArrayList<LectureName> invalidLectureNames = new ArrayList<>();
        for (LectureName each: this.targetLectureNames) {
            if (!model.hasLecture(this.moduleCode, each)) {
                invalidLectureNames.add(each);
            }
        }

        if (invalidLectureNames.size() == 0) {
            for (LectureName each:this.targetLectureNames) {
                DeleteLectureCommand dlc = new DeleteLectureCommand(this.moduleCode, each);
                dlc.execute(model);
            }

            return new CommandResult(String.format(MESSAGE_SUCCESS,
                    this.targetLectureNames.size(),
                    this.moduleCode,
                    MultipleEventsParser.convertArrayListToString(this.targetLectureNames)));
        } else {
            throw new CommandException(String.format((
                        invalidLectureNames.size() == 1
                        ? Messages.MESSAGE_LECTURE_DOES_NOT_EXIST
                        : Messages.MESSAGE_LECTURES_DO_NOT_EXIST
                    ),
                    MultipleEventsParser.convertArrayListToString(invalidLectureNames),
                    this.moduleCode));
        }
    }
}
