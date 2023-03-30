package seedu.address.model.entity;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import seedu.address.model.entity.exceptions.DuplicateTemplateException;

/**
 * Class which stores a list of pre-determined character templates
 */
public class Template {
    private final ArrayList<Character> templates;

    /**
     * Initialize list of character templates
     */
    private Template() {
        this.templates = new ArrayList<>();
    }

    /**
     * Fill with pre-determined templates
     * @return The wrapper class
     */
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
     * Checks if a template with the given name exists
     * @param toCheck name of template
     * @return existence check
     */
    public boolean contains(Name toCheck) {
        requireNonNull(toCheck);
        return templates.stream().anyMatch(t -> t.getName().equals(toCheck));
    }

    /**
     * Checks if a character template with the same name exists
     * @param toCheck Character to be added
     * @return existence check
     */
    public boolean contains(Character toCheck) {
        requireAllNonNull(toCheck);
        return contains(toCheck.getName());
    }

    /**
     * Adds a new character template
     * @param toAdd template to add
     * @throws DuplicateTemplateException if template with same name already exists
     */
    public void addTemplate(Character toAdd) throws DuplicateTemplateException {
        requireNonNull(toAdd);
        if (this.contains(toAdd)) {
            throw new DuplicateTemplateException();
        } else {
            templates.add(toAdd);
        }
    }

    /**
     * Returns a list of all template names
     * @return the list
     */
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
