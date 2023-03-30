package seedu.address.logic.trackereventsystem.observers;

import seedu.address.logic.trackereventsystem.OnLectureEditedEventObserver;
import seedu.address.logic.trackereventsystem.OnModuleEditedEventObserver;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
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

        if (isAtLectureAffectedByLectureEdit(navContext, originalLecture)) {
            this.model.navigateBack();
            if (editedLecture != null) {
                this.model.navigateToLecFromMod(editedLecture.getName());
            }
        }
    }

    private boolean isAtLectureAffectedByLectureEdit(NavigationContext navContext, ReadOnlyLecture originalLecture) {
        return originalLecture != null && navContext.getLayer() == NavLayer.LECTURE
                && navContext.getLectureName().equals(originalLecture.getName());
    }

    @Override
    public void onModuleEdited(ReadOnlyModule originalModule, ReadOnlyModule editedModule) {
        NavigationContext navContext = this.model.getCurrentNavContext();

        if (isAtLectureAffectedByModuleEdit(navContext, originalModule)) {
            LectureName lectureName = navContext.getLectureName();
            this.model.navigateToRoot();
            if (editedModule != null) {
                this.model.navigateTo(editedModule.getCode(), lectureName);
            }
        } else if (isAtModuleAffectedByModuleEdit(navContext, originalModule)) {
            this.model.navigateToRoot();
            if (editedModule != null) {
                this.model.navigateToModFromRoot(editedModule.getCode());
            }
        }
    }

    private boolean isAtLectureAffectedByModuleEdit(NavigationContext navContext, ReadOnlyModule originalModule) {
        return originalModule != null && navContext.getLayer() == NavLayer.LECTURE
                && navContext.getModuleCode().equals(originalModule.getCode());
    }

    private boolean isAtModuleAffectedByModuleEdit(NavigationContext navContext, ReadOnlyModule originalModule) {
        return originalModule != null && navContext.getLayer() == NavLayer.MODULE
                && navContext.getModuleCode().equals(originalModule.getCode());
    }
}
