package seedu.address.model.navigation;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;

/**
 * Represents a context or location.
 */
public class NavigationContext {

    private static final String ROOT_LEVEL_NAME = "/r";

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
        if (getLayer() != NavLayer.ROOT) {
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
        if (getLayer() != NavLayer.MODULE) {
            return this;
        }

        return new NavigationContext(moduleCode, lectureName);
    }

    public NavLayer getLayer() {
        final int rootLayerBitMask = 0;
        final int moduleLayerBitMask = 1;
        final int lectureLayerBitMask = 3;

        int layerMask = getCurrentLayerBitMask();
        switch (layerMask) {
        case rootLayerBitMask:
            return NavLayer.ROOT;
        case moduleLayerBitMask:
            return NavLayer.MODULE;
        case lectureLayerBitMask:
            return NavLayer.LECTURE;
        default:
            return NavLayer.INVALID;
        }
    }

    private int getCurrentLayerBitMask() {
        int moduleCodeBit = moduleCode == null ? 0 : 1;
        int lectureNameBit = (lectureName == null ? 0 : 1) << 1;
        return moduleCodeBit + lectureNameBit;
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

    public String getCommandPrefixes() {
        if (getLayer() == NavLayer.ROOT) {
            return ROOT_LEVEL_NAME;
        } else {
            return getModulePrefixArg() + " " + getLecturePrefixArg();
        }
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

    /**
     * Represents different nav layers.
     */
    public enum NavLayer {
        INVALID(0), ROOT(1), MODULE(2), LECTURE(3);

        public static final int LOWEST_LAYER_ID = 1;
        public static final int HIGHEST_LAYER_ID = 3;

        private final int layer;

        private NavLayer(int layer) {
            this.layer = layer;
        }

        public int getLayerId() {
            return layer;
        }
    }
}
