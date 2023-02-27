package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.tag.ModuleTag;

/**
 * This class was added to facilitate the Sort Command.
 * We implement the comparator here so that the Sort Command is cleaner.
 */
public class ModuleTagSet implements Comparable<ModuleTagSet> {
    private static final int DISPLAY_LIMIT = 10;

    private Set<ModuleTag> modules;

    /**
     * We want to find the modules in common with the user.
     * As such, we want to keep track of what modules the user has.
     */
    private Set<ModuleTag> commonModules;

    public void ModuleTagSet() {
        modules = new HashSet<>();
        commonModules = new HashSet<>();
    }

    public void add(ModuleTag moduleTag) {
        modules.add(moduleTag);
    }

    public void addAll(Collection<? extends ModuleTag> moduleTags) {
        modules.addAll(moduleTags);
    }

    /**
     * Computes and sets the common modules the person shares with the user.
     */
    public void setCommonModules(Set<ModuleTag> userModuleTags) {
        requireNonNull(userModuleTags);

        // we make a copy so that retainAll does not destroy this set
        commonModules = new HashSet<>(this);

        // finds the intersection between user modules and person modules
        commonModules.retainAll(userModuleTags);
    }

    /**
     * Sets the user to find common modules.
     */
    public Set<ModuleTag> getCommonModules() {
        return commonModules;
    }

    /**
     * Gets the modules not in common with the user.
     */
    public Set<ModuleTag> getUncommonModuleTags() {
        // finds modules that are not in common with the user.
        Set<ModuleTag> uncommonModuleTags = new HashSet<>(this);
        uncommonModuleTags.addAll(this);
        uncommonModuleTags.removeAll(commonModules);
        return uncommonModuleTags;
    }

    @Override
    public String toString() {
        Stream<ModuleTag> commonModulesFirst = Stream.concat(commonModules.stream().sorted(),
                getUncommonModuleTags().stream().sorted());
        String modulesString = commonModulesFirst
                .map(ModuleTag::toString)
                .limit(DISPLAY_LIMIT)
                .collect(Collectors.joining(" | "));
        // whether the size exceeds the display limit
        String ellipsis = size() > DISPLAY_LIMIT ? "..." : "";

        return String.format("%s%s", modulesString, ellipsis);
    }

    /**
     * Compares the sizes of the sets.
     * We do this because we want the size of the set to be the basis of comparison.
     * In other words, if the person shares more relationships with the user,
     * the person is likely to be closer to the user.
     */
    @Override
    public int compareTo(ModuleTagSet otherModuleTagSet) {
        return Integer.compare(commonModules.size(),
                otherModuleTagSet.commonModules.size());
    }
}
