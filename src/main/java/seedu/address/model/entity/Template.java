package seedu.address.model.entity;

import seedu.address.model.entity.exceptions.DuplicateTemplateException;
import seedu.address.model.person.exceptions.DuplicatePersonException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A template class to create new Characters.
 */
public class Template {
    private final ArrayList<Character> templates;

    public static Template getPresetTemplates() {
        Template presetTemplates = new Template();
        Stats orcStats = new Stats(15, 6, 1);
        Stats elfStats = new Stats(6, 10, 10);
        Stats humanStats = new Stats(7, 9, 9);
        Character orcTemplate = new Character(new Name("orc"), orcStats);
        Character elfTemplate = new Character(new Name("elf"), elfStats);
        Character humanTemplate = new Character(new Name("human"), humanStats);
        presetTemplates.addTemplate(orcTemplate);
        presetTemplates.addTemplate(elfTemplate);
        presetTemplates.addTemplate(humanTemplate);
        return presetTemplates;
    }
    /**
     * Initialize list of character templates
     */
    private Template() {
        this.templates = new ArrayList<>();
    }

    public boolean contains(Name toCheck) {
        requireNonNull(toCheck);
        return templates.stream().anyMatch(t -> t.getName().equals(toCheck));
    }

    public boolean contains(Character toCheck) {
        requireAllNonNull(toCheck);
        return templates.stream().anyMatch(toCheck::isSameEntity);
    }

    public void addTemplate(Character toAdd) throws DuplicatePersonException {
        requireNonNull(toAdd);
        if (this.contains(toAdd)) {
            throw new DuplicateTemplateException();
        } else {
            templates.add(toAdd);
        }
    }

    public List<String> list() {
        ArrayList<String> list = new ArrayList<>();
        this.templates.forEach(t -> list.add(t.getName().fullName));
        return list;
    }
    /**
     * Primitive version of generating character from template.
     */
    public Character generateCharacter(Name characterName, Name templateName) throws NoSuchElementException {
        requireAllNonNull(characterName, templateName);
        Character c = templates.stream()
                .filter(t -> t.getName().equals(templateName))
                .findFirst()
                .orElse(null);
        if (c == null) {
            throw new NoSuchElementException("Template does not exist!");
        }
        return new Character(characterName, c.getStats(), c.getLevel(), c.getXP(), c.getInventory(), c.getTags());
    }
}
