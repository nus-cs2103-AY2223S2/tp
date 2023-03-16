package seedu.address.model.module;

import java.util.function.Predicate;

import seedu.address.model.lecture.LectureName;

/**
 * Tests that a {@code Module}'s {@code Code}, {@code LectureName} matches user's input.
 */
public class ModuleLectureContainsKeywordsPredicate implements Predicate<ReadOnlyModule> {
    private final ModuleCode moduleCode;
    private final LectureName lectureName;

    /**
     * Creates a ModuleLectureContainsKeywordsPredicate
     * @param code
     * @param lectureName
     */
    public ModuleLectureContainsKeywordsPredicate(String code, String lectureName) {
        this.moduleCode = new ModuleCode(code);
        this.lectureName = new LectureName(lectureName);
    }

    public ModuleCode getModuleCode() {
        return this.moduleCode;
    }

    public LectureName getLectureName() {
        return this.lectureName;
    }

    @Override
    public boolean test(ReadOnlyModule module) {
        return this.moduleCode.equals(module.getCode())
            && module.getLectureList().stream()
                .anyMatch(lecture -> lectureName.equals(lecture.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleLectureContainsKeywordsPredicate // instanceof handles nulls
                && moduleCode.equals(((ModuleLectureContainsKeywordsPredicate) other).moduleCode))
                    && lectureName.equals(((ModuleLectureContainsKeywordsPredicate) other).lectureName); // state check
    }
}
