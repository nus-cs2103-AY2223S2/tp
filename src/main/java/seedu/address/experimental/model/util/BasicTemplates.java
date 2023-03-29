package seedu.address.experimental.model.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.entity.Name;
import seedu.address.model.entity.Template;

/**
 * Basic templates to be used
 */
public class BasicTemplates {
    private static List<Template> templates = new ArrayList<>();

    public static List<Template> getBasicTemplates() {
        templates.clear();
        Template orc = new Template(new Name("orc"), 0.8f, 0.1f, 0.1f, new HashSet<>());
        Template elf = new Template(new Name("elf"), 0.15f, 0.5f, 0.3f, new HashSet<>());
        Template human = new Template(new Name("human"), 0.2f, 0.2f, 0.8f, new HashSet<>());
        templates.add(orc);
        templates.add(elf);
        templates.add(human);
        return templates;
    }
}
