package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.commitment.Lesson;
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

    private final Set<ModuleTag> modules;

    /**
     * We want to find the modules in common with the user.
     * As such, we want to keep track of what modules the user has.
     */
    private Set<ModuleTag> commonModules;

    private final HashMap<ModuleTag, Set<Lesson>> lessons;

    /**
     * Initialises a new ModuleTagSet.
     */
    public ModuleTagSet() {
        modules = new HashSet<>();
        commonModules = new HashSet<>();
        this.lessons = new HashMap<ModuleTag, Set<Lesson>>();
    }

    /**
     * Adds the module tag to the set of modules.
     * Gives access from outside classes to this set.
     */
    public void add(ModuleTag moduleTag) {
        modules.add(moduleTag);
        if (!this.lessons.containsKey(moduleTag)) {
            this.lessons.put(moduleTag, new HashSet<Lesson>());
        }
    }

    /**
     * Adds all module tags to the set of modules.
     * Gives access from outside classes to this set.
     */
    public void addAll(Collection<? extends ModuleTag> moduleTags) {
        for (ModuleTag tag : moduleTags) {
            if (tag.isBasicTag()) {
                modules.add(tag);
            }
        }
    }

    /**
     * Adds lesson to the HashMap of sets of lessons.
     *
     * @param hash Module to act as the key.
     * @param lesson Lesson to be inserted.
     */
    public void addLesson(ModuleTag hash, Lesson lesson) {
        if (!this.lessons.containsKey(hash)) {
            this.lessons.put(hash, new HashSet<Lesson>());
            // should never come here.
        }
        modules.add(hash);
        this.lessons.get(hash).add(lesson);
    }

    /**
     * Removes the module tag from the set of modules.
     * Gives access from outside classes to this set.
     */
    public void remove(ModuleTag moduleTag) {
        modules.remove(moduleTag);
        this.lessons.remove(moduleTag);
    }

    /**
     * Removes all module tags from the set of modules.
     * Gives access from outside classes to this set.
     */
    public void removeAll(Collection<? extends ModuleTag> moduleTags) {
        for (ModuleTag tag : moduleTags) {
            if (tag.isBasicTag()) {
                this.remove(tag);
            }
        }
    }

    /**
     * Removes a lesson from the HashMap of Set of Lessons.
     * @param hash ModuleTag to act as key
     * @param lesson Lesson to be removed
     */
    public void removeLesson(ModuleTag hash, Lesson lesson) {
        if (!this.lessons.containsKey(hash)) {
            return;
        }
        this.lessons.get(hash).remove(lesson);
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

        // we make a copy so that retainAll does not destroy this set
        commonModules = new HashSet<>(modules);

        // finds the intersection between user modules and person modules
        commonModules.retainAll(userModuleTags);
    }

    /**
     * Returns an immutable module tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ModuleTag> getImmutableModules() {
        return Collections.unmodifiableSet(modules);
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
        Set<ModuleTag> uncommonModuleTags = new HashSet<>(modules);
        uncommonModuleTags.removeAll(commonModules);
        return uncommonModuleTags;
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

    /**
     * Alternative toString method to generate the Lessons as a String.
     * @return String of lessons.
     */
    public String lessonsAsStr() {
        String result = "";
        for (ModuleTag hash: lessons.keySet()) {
            result = result + hash;
            result = result + lessons.get(hash).toString();
            result = result + "\n";
        }
        return result;
    }
}
