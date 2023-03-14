package seedu.address.model.navigation;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;

import seedu.address.model.Navigation;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;

public class NavigationContext {

    private static final String ROOT_LEVEL_NAME = "mods";

    private final ModuleCode moduleCode;
    private final LectureName lectureName;

    public NavigationContext() {
        moduleCode = null;
        lectureName = null;
    }

    private NavigationContext(ModuleCode moduleCode, LectureName lectureName) {
        this.moduleCode = moduleCode;
        this.lectureName = lectureName;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public LectureName getLectureName() {
        return lectureName;
    }

    public NavigationContext addModule(ModuleCode moduleCode) {
        if (this.moduleCode != null || this.lectureName != null) {
            return this;
        }

        return new NavigationContext(moduleCode, null);
    }

    public NavigationContext addLecture(LectureName lectureName) {
        if (moduleCode == null || this.lectureName != null) {
            return this;
        }

        return new NavigationContext(moduleCode, lectureName);
    }

    public String getModulePrefixArg() {
        if (moduleCode == null) {
            return "";
        }

        return PREFIX_MODULE + " " + moduleCode;
    }

    public String getLecturePrefixArg() {
        if (lectureName == null) {
            return "";
        }

        return PREFIX_LECTURE + " " + lectureName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(ROOT_LEVEL_NAME);

        if (moduleCode != null) {
            sb.append("/").append(moduleCode);
            if (lectureName != null) {
                sb.append("/").append(lectureName);
            }
        }

        return sb.toString();
    }
}
