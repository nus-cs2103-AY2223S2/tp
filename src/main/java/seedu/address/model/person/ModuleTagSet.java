package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
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
    /**
     * How many modules you want to display in the toString() method
     * before it comes out as ellipses.
     */
    private static final int DISPLAY_LIMIT = 10;

    private final HashMap<String, ModuleTag> modules;

    /**
     * We want to find the modules in common with the user.
     * As such, we want to keep track of what modules the user has.
     */
    private Set<ModuleTag> commonModules;

    /**
     * Initialises a new ModuleTagSet.
     */
    public ModuleTagSet() {
        modules = new HashMap<>();
        commonModules = new HashSet<>();
    }

    /**
     * Adds the module tag to the set of modules.
     * Gives access from outside classes to this set.
     */
    public void add(ModuleTag moduleTag) {
        String tagName = moduleTag.tagName;
        if (!containsKey(tagName)) {
            modules.put(tagName, moduleTag);
            return;
        }

        ModuleTag mergedModuleTag = modules.get(tagName).mergeWith(moduleTag);
        modules.put(tagName, mergedModuleTag);
    }

    /**
     * Adds all module tags to the set of modules.
     * Gives access from outside classes to this set.
     */
    public void addAll(Collection<? extends ModuleTag> moduleTags) {
        for (ModuleTag tag : moduleTags) {
            add(tag);
        }
    }

    /**
     * Removes the module tag from the set of modules.
     * Gives access from outside classes to this set.
     */
    public void remove(ModuleTag moduleTag) {
        assert canRemove(moduleTag);

        String tagName = moduleTag.tagName;
        if (!containsKey(tagName)) {
            return;
        }

        ModuleTag existingModuleTag = modules.get(tagName);
        existingModuleTag.removeLessons(moduleTag.getImmutableLessons());

        if (existingModuleTag.getImmutableLessons().size() == 0) {
            modules.remove(tagName);
        }
    }

    /**
     * Removes all module tags from the set of modules.
     * Gives access from outside classes to this set.
     */
    public void removeAll(Collection<? extends ModuleTag> moduleTags) {
        moduleTags.stream()
                .filter(this::canRemove)
                .forEach(this::remove);
    }

    /**
     * Returns whether the tag set contains the key.
     */
    public boolean containsKey(String tagName) {
        return modules.containsKey(tagName);
    }

    /**
     * Returns whether the tag can be removed.
     */
    public boolean canRemove(ModuleTag moduleTag) {
        return modules.containsKey(moduleTag.tagName)
                && modules.get(moduleTag.tagName).containsLessons(moduleTag.getImmutableLessons());
    }

    /**
     * Returns the number of modules.
     */
    public int size() {
        return modules.size();
    }

    /**
     * Computes and sets the common modules the person shares with the user.
     */
    public void setCommonModules(Set<ModuleTag> userModuleTags) {
        requireNonNull(userModuleTags);

        commonModules = userModuleTags.stream()
                .filter(mt -> containsKey(mt.tagName))
                .collect(Collectors.toSet());
    }

    /**
     * Returns an immutable module tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ModuleTag> getImmutableModules() {
        return Set.copyOf(modules.values());
    }

    /**
     * Returns an immutable module tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Modules are those in common with the user.
     */
    public Set<ModuleTag> getImmutableCommonModules() {
        return Collections.unmodifiableSet(commonModules);
    }

    /**
     * Returns an immutable module tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Modules are those not in common with the user.
     */
    public Set<ModuleTag> getUncommonModuleTags() {
        // finds modules that are not in common with the user.
        return modules.values().stream()
                .filter(mt -> !commonModules.contains(mt))
                .collect(Collectors.toSet());
    }

    /**
     * Returns the number of common modules.
     */
    public int getNumberOfCommonModules() {
        return commonModules.size();
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
        String ellipsis = modules.size() > DISPLAY_LIMIT ? "..." : "";

        return String.format("%s%s", modulesString, ellipsis);
    }

    /**
     * Compares the sizes of the sets.
     * We do this because we want the size of the set intersection to be the basis of comparison.
     * In other words, if the person shares more modules with the user,
     * the person is likely to be closer to the user.
     */
    @Override
    public int compareTo(ModuleTagSet otherModuleTagSet) {
        return Integer.compare(getNumberOfCommonModules(),
                otherModuleTagSet.getNumberOfCommonModules());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof ModuleTagSet)) {
            return false;
        }

        ModuleTagSet otherModuleTagSet = (ModuleTagSet) other;
        return modules.equals(otherModuleTagSet.modules);
    }

    /**
     * Alternative toString method to generate the Lessons as a String.
     * @return String of lessons.
     */
    public String lessonsAsStr() {
        String result = "";
        for (ModuleTag hash : modules.values()) {
            result = result + hash.getImmutableLessons().toString();
            result = result + "\n";
        }
        return result;
    }
}
