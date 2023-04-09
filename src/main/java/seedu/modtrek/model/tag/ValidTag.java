package seedu.modtrek.model.tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Enum class that houses all valid tags and some useful methods for it.
 */
public enum ValidTag {

    // Values
    UNIVERSITY_LEVEL_REQUIREMENTS,
    COMPUTER_SCIENCE_FOUNDATION,
    COMPUTER_SCIENCE_BREADTH_AND_DEPTH,
    IT_PROFESSIONALISM,
    MATHEMATICS_AND_SCIENCES,
    UNRESTRICTED_ELECTIVES,

    // Short-forms Accepted
    ULR, CSF, CSBD, ITP, MS, UE;

    /**
     * Retrieves the long form string of all the requirements.
     * @return The long form string of all the requirements.
     */
    public static List<String> getTags() {
        List<String> tags = new ArrayList<>();

        tags.add("COMPUTER_SCIENCE_FOUNDATION");
        tags.add("MATHEMATICS_AND_SCIENCES");
        tags.add("UNRESTRICTED_ELECTIVES");
        tags.add("IT_PROFESSIONALISM");
        tags.add("UNIVERSITY_LEVEL_REQUIREMENTS");
        tags.add("COMPUTER_SCIENCE_BREADTH_AND_DEPTH");

        return tags;
    }

    /**
     * Retrieves short form version of the requirement.
     *
     * @param tagName the long-form version of the requirement
     * @return ValidTag as the short-form
     */
    public static ValidTag getShortForm(String tagName) {
        tagName = tagName.replace(" ", "_").toUpperCase();
        ValidTag tag = ValidTag.valueOf(tagName);
        switch (tag) {
        case UNIVERSITY_LEVEL_REQUIREMENTS:
            return ULR;
        case COMPUTER_SCIENCE_FOUNDATION:
            return CSF;
        case COMPUTER_SCIENCE_BREADTH_AND_DEPTH:
            return CSBD;
        case IT_PROFESSIONALISM:
            return ITP;
        case MATHEMATICS_AND_SCIENCES:
            return MS;
        case UNRESTRICTED_ELECTIVES:
            return UE;
        default:
            return tag;
        }
    }

    /**
     * Retrieves total credit per requirement.
     *
     * @param tagName the short-form version of the requirement
     * @return ValidTag as the long-form
     */
    public static ValidTag getLongForm(String tagName) {
        tagName = tagName.replace(" ", "_").toUpperCase();
        ValidTag tag = ValidTag.valueOf(tagName);
        switch (tag) {
        case ULR:
            return UNIVERSITY_LEVEL_REQUIREMENTS;
        case CSF:
            return COMPUTER_SCIENCE_FOUNDATION;
        case CSBD:
            return COMPUTER_SCIENCE_BREADTH_AND_DEPTH;
        case ITP:
            return IT_PROFESSIONALISM;
        case MS:
            return MATHEMATICS_AND_SCIENCES;
        case UE:
            return UNRESTRICTED_ELECTIVES;
        default:
            return tag;
        }
    }

    /**
     * Retrieves color for a given tag.
     * @param tagName The name of the given tag.
     * @return The color for the given tag.
     */
    public static String getColor(String tagName) {
        Map<ValidTag, String> mappedColors = Map.of(
                ULR, "red",
                CSF, "blue",
                CSBD, "purple",
                ITP, "orange",
                MS, "green",
                UE, "yellow");
        ValidTag tag = ValidTag.getShortForm(tagName);
        return mappedColors.get(tag);
    }
}
