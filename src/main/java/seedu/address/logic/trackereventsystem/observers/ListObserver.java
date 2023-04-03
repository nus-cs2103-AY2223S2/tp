package seedu.address.logic.trackereventsystem.observers;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import seedu.address.logic.trackereventsystem.OnLectureEditedEventObserver;
import seedu.address.logic.trackereventsystem.OnModuleEditedEventObserver;
import seedu.address.logic.trackereventsystem.OnVideoEditedEventObserver;
import seedu.address.model.DisplayListLevel;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.LecturePredicate;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.module.VideoPredicate;
import seedu.address.model.video.Video;

/**
 * Represents a list observer that responds to on module edited and on
 * lecture edited events.
 */
public class ListObserver implements
        OnModuleEditedEventObserver, OnLectureEditedEventObserver, OnVideoEditedEventObserver {

    private final Model model;

    public ListObserver(Model model) {
        this.model = model;
    }

    @Override
    public void onVideoEdited(ModuleCode moduleCode, LectureName lectureName, Video originalVideo, Video editedVideo) {
        ReadOnlyModule curModule = model.getListedLecturesByModule();
        ReadOnlyLecture curLecture = model.getListedVideosByLecture();
        DisplayListLevel curDisplayListLevel = model.getLastListLevel();

        if (isVideosAffectedByVideoEdit(curDisplayListLevel, curModule, curLecture, moduleCode, lectureName)) {
            model.updateFilteredVideoList(new VideoPredicate(curLecture), curModule.getCode(), curLecture);
        }
    }

    private boolean isVideosAffectedByVideoEdit(DisplayListLevel curDisplayListLevel,
            ReadOnlyModule curModule, ReadOnlyLecture curLecture,
            ModuleCode editedInModuleCode, LectureName editedInLectureName) {
        return curDisplayListLevel == DisplayListLevel.VIDEO
                && editedInModuleCode.equals(curModule.getCode())
                && editedInLectureName.equals(curLecture.getName());
    }

    @Override
    public void onLectureEdited(ModuleCode moduleCode, ReadOnlyLecture originalLecture, ReadOnlyLecture editedLecture) {
        ReadOnlyModule curModule = model.getListedLecturesByModule();
        ReadOnlyLecture curLecture = model.getListedVideosByLecture();
        DisplayListLevel curDisplayListLevel = model.getLastListLevel();

        if (isLecturesAffectedByLectureEdit(curDisplayListLevel, curModule, originalLecture, moduleCode)) {
            model.updateFilteredLectureList(new LecturePredicate(curModule), curModule);
        }

        if (isVideosAffectedByLectureEdit(curDisplayListLevel, curModule, curLecture, originalLecture, moduleCode)) {
            model.updateFilteredVideoList(new VideoPredicate(curLecture), curModule.getCode(), curLecture);
        }
    }

    private boolean isLecturesAffectedByLectureEdit(DisplayListLevel curDisplayListLevel,
            ReadOnlyModule curModule, ReadOnlyLecture curLecture,
            ModuleCode editedInModuleCode) {
        return curDisplayListLevel == DisplayListLevel.LECTURE
                && editedInModuleCode.equals(curModule.getCode());
    }

    private boolean isVideosAffectedByLectureEdit(DisplayListLevel curDisplayListLevel,
            ReadOnlyModule curModule, ReadOnlyLecture curLecture,
            ReadOnlyLecture originalLecture, ModuleCode editedInModuleCode) {
        return curDisplayListLevel == DisplayListLevel.VIDEO
                && editedInModuleCode.equals(curModule.getCode())
                && originalLecture.equals(curLecture);
    }

    @Override
    public void onModuleEdited(ReadOnlyModule originalModule, ReadOnlyModule editedModule) {
        ReadOnlyModule curModule = model.getListedLecturesByModule();
        ReadOnlyLecture curLecture = model.getListedVideosByLecture();
        DisplayListLevel curDisplayListLevel = model.getLastListLevel();

        if (isModulesAffectedByModuleEdit(curDisplayListLevel)) {
            model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        }

        if (isLecturesAffectedByModuleEdit(curDisplayListLevel, curModule, originalModule)) {
            if (editedModule == null) { // Module is deleted
                model.updateAllFilteredListAsHidden();
                return;
            }
            model.updateFilteredLectureList(new LecturePredicate(editedModule), editedModule);
        }

        if (isVideosAffectedByModuleEdit(curDisplayListLevel, curModule, originalModule)) {
            if (editedModule == null) { // Module is deleted
                model.updateAllFilteredListAsHidden();
                return;
            }
            model.updateFilteredVideoList(new VideoPredicate(curLecture), editedModule.getCode(), curLecture);
        }
    }

    private boolean isModulesAffectedByModuleEdit(DisplayListLevel curDisplayListLevel) {
        return curDisplayListLevel == DisplayListLevel.MODULE;
    }

    private boolean isLecturesAffectedByModuleEdit(DisplayListLevel curDisplayListLevel,
            ReadOnlyModule curModule, ReadOnlyModule originalModule) {
        return curDisplayListLevel == DisplayListLevel.LECTURE
                && originalModule != null
                && curModule.getCode().equals(originalModule.getCode());
    }

    private boolean isVideosAffectedByModuleEdit(DisplayListLevel curDisplayListLevel,
            ReadOnlyModule curModule, ReadOnlyModule originalModule) {
        return curDisplayListLevel == DisplayListLevel.VIDEO
                && originalModule != null
                && curModule.getCode().equals(originalModule.getCode());
    }
}
