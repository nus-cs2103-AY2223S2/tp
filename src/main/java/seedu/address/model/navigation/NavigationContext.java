package seedu.address.model.navigation;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import seedu.address.model.Navigation;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;

/**
 * Represents a context or location.
 */
public class NavigationContext {

    private static final String ROOT_LEVEL_NAME = "mods";

    private final ModuleCode moduleCode;
    private final LectureName lectureName;

    /**
     * Instantiates a navigation context that represents the root location.
     */
    public NavigationContext() {
        this(null, null);
    }

    private NavigationContext(ModuleCode moduleCode, LectureName lectureName) {
        this.moduleCode = moduleCode;
        this.lectureName = lectureName;
    }

    /**
     * Returns the module code associated with the navigation context.
     * @return the module code if not root, otherwise null
     */
    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    /**
     * Returns the lecture name associated with the navigation context.
     * @return the lecture name if at lecture layer, otherwise null
     */
    public LectureName getLectureName() {
        return lectureName;
    }

    /**
     * Returns a new immutable NavigationContext with the specified module code.
     * @param moduleCode added module code
     * @return new immutable NavigationContext with the specified module code
     */
    public NavigationContext addModule(ModuleCode moduleCode) {
        if (this.moduleCode != null || this.lectureName != null) {
            return this;
        }

        return new NavigationContext(moduleCode, null);
    }

    /**
     * Returns a new immutable NavigationContext with the specified lecture name.
     * @param lectureName added lecture name
     * @return new immutable NavigationContext with the specified lecture name
     */
    public NavigationContext addLecture(LectureName lectureName) {
        if (moduleCode == null || this.lectureName != null) {
            return this;
        }

        return new NavigationContext(moduleCode, lectureName);
    }

    /**
     * Returns the module prefix argument associated with the context.
     * @return module prefix argument
     */
    public String getModulePrefixArg() {
        if (moduleCode == null) {
            return "";
        }

        return PREFIX_MODULE + " " + moduleCode;
    }

    /**
     * Returns the leture prefix argument associated with the context.
     * @return lecture prefix argument
     */
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof NavigationContext)) {
            return false;
        }

        NavigationContext other = (NavigationContext) obj;

        boolean moduleEquals = (moduleCode == null && other.moduleCode == null)
                || (moduleCode.equals(other.moduleCode));

        boolean lectureEquals = (lectureName == null && other.lectureName == null)
                || (lectureName.equals(other.lectureName));

        return moduleEquals && lectureEquals;
    }
}
