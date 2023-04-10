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

        if (isAffectedByLectureEdit(navContext, originalLecture)) {

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

    private boolean isAffectedByLectureEdit(NavigationContext navContext,
            ReadOnlyLecture originalLecture) {
        return originalLecture != null && originalLecture.getName().equals(navContext.getLectureName());
    }

    @Override
    public void onModuleEdited(ReadOnlyModule originalModule, ReadOnlyModule editedModule) {
        NavigationContext navContext = this.model.getCurrentNavContext();

        if (isAffectedByModuleEdit(navContext, originalModule)) {
            LectureName lectureName = navContext.getLectureName();
            this.model.navigateToRoot();

            boolean isModuleDeleted = editedModule == null;
            boolean isLectureContext = lectureName != null;

            if (isModuleDeleted) {
                this.model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
            } else if (isLectureContext) {
                this.model.navigateTo(editedModule.getCode(), lectureName);
            } else {
                this.model.navigateTo(editedModule.getCode());
            }
        }
    }

    private boolean isAffectedByModuleEdit(NavigationContext navContext, ReadOnlyModule originalModule) {
        return originalModule != null && originalModule.getCode().equals(navContext.getModuleCode());
    }
}
