package seedu.address.model.entity;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.entity.Character.CharacterBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import seedu.address.model.entity.exceptions.DuplicateTemplateException;

/**
 * Class which stores a list of pre-determined character templates
 */
public class Template {
    private final HashMap<String, CharacterBuilder> templates;

    /**
     * Initialize list of character templates
     */
    private Template() {
        this.templates = new HashMap<>();
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
        Name template = new Name("template");
        CharacterBuilder orcTemplate = new CharacterBuilder(template).setStats(orcStats);
        CharacterBuilder elfTemplate = new CharacterBuilder(template).setStats(elfStats);
        CharacterBuilder humanTemplate = new CharacterBuilder(template).setStats(humanStats);
        presetTemplates.addTemplate("orc", orcTemplate);
        presetTemplates.addTemplate("elf", elfTemplate);
        presetTemplates.addTemplate("human", humanTemplate);
        return presetTemplates;
    }

    /**
     * Checks if a template with the given name exists
     * @param toCheck name of template
     * @return existence check
     */
    public boolean contains(String toCheck) {
        requireNonNull(toCheck);
        return templates.containsKey(toCheck);
    }

    /**
     * Adds a new character template
     * @param toAdd template to add
     * @throws DuplicateTemplateException if template with same name already exists
     */
    public void addTemplate(String templateName, CharacterBuilder toAdd) throws DuplicateTemplateException {
        requireAllNonNull(templateName, toAdd);
        if (this.contains(templateName)) {
            throw new DuplicateTemplateException();
        } else {
            templates.put(templateName, toAdd);
        }
    }

    /**
     * Returns a list of all template names
     * @return the list
     */
    public List<String> list() {
        Set<String> templateSet = this.templates.keySet();
        return new ArrayList<>(templateSet);
    }
    /**
     * Primitive version of generating character from template.
     */
    public Character generateCharacter(Name characterName, String templateName) throws NoSuchElementException {
        requireAllNonNull(characterName, templateName);
        CharacterBuilder builder = new CharacterBuilder(characterName);
        CharacterBuilder template = this.templates.get(templateName);
        if (template == null) {
            throw new NoSuchElementException("Template does not exist!");
        }
        builder.copy(template);
        return builder.build();
    }
}
