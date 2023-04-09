package seedu.address.logic.trackereventsystem.observers;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import seedu.address.logic.trackereventsystem.OnLectureEditedEventObserver;
import seedu.address.logic.trackereventsystem.OnModuleEditedEventObserver;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.LecturePredicate;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.navigation.NavigationContext;
import seedu.address.model.navigation.NavigationContext.NavLayer;

/**
 * Represents a navigation observer that responds to on module edited and on
 * lecture edited events.
 */
public class NavigationObserver implements OnModuleEditedEventObserver, OnLectureEditedEventObserver {

    private final Model model;

    public NavigationObserver(Model model) {
        this.model = model;
    }

    @Override
    public void onLectureEdited(ModuleCode moduleCode, ReadOnlyLecture originalLecture, ReadOnlyLecture editedLecture) {
        NavigationContext navContext = this.model.getCurrentNavContext();

        if (isLectureContextAffectedByLectureEdit(navContext, originalLecture)) {

            this.model.navigateBack();

            boolean isLectureDeleted = editedLecture == null;

            if (isLectureDeleted) {
                ReadOnlyModule module = this.model.getListedLecturesByModule();
                this.model.updateFilteredLectureList(new LecturePredicate(module), module);
            } else {
                this.model.navigateToLecFromMod(editedLecture.getName());
            }
        }
    }

    private boolean isLectureContextAffectedByLectureEdit(NavigationContext navContext,
            ReadOnlyLecture originalLecture) {
        return originalLecture != null && navContext.getLayer() == NavLayer.LECTURE
                && navContext.getLectureName().equals(originalLecture.getName());
    }

    @Override
    public void onModuleEdited(ReadOnlyModule originalModule, ReadOnlyModule editedModule) {
        NavigationContext navContext = this.model.getCurrentNavContext();

        boolean isAffected = isLectureContextAffectedByModuleEdit(navContext, originalModule)
                || isModuleContextAffectedByModuleEdit(navContext, originalModule);

        if (isAffected) {
            LectureName lectureName = navContext.getLectureName();
            this.model.navigateToRoot();

            boolean isModuleDeleted = editedModule == null;

            if (isModuleDeleted) {
                this.model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
            } else if (lectureName != null) {
                this.model.navigateTo(editedModule.getCode(), lectureName);
            } else {
                this.model.navigateTo(editedModule.getCode());
            }
        }
    }

    private boolean isLectureContextAffectedByModuleEdit(NavigationContext navContext, ReadOnlyModule originalModule) {
        return originalModule != null && navContext.getLayer() == NavLayer.LECTURE
                && navContext.getModuleCode().equals(originalModule.getCode());
    }

    private boolean isModuleContextAffectedByModuleEdit(NavigationContext navContext, ReadOnlyModule originalModule) {
        return originalModule != null && navContext.getLayer() == NavLayer.MODULE
                && navContext.getModuleCode().equals(originalModule.getCode());
    }
}
