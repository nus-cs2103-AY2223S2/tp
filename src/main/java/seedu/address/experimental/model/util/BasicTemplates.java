package seedu.address.experimental.model.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.entity.Name;
import seedu.address.model.entity.Stats;
import seedu.address.model.entity.Template;

/**
 * Basic templates to be used
 */
public class BasicTemplates {
    private static List<Template> templates = new ArrayList<>();

    public static List<Template> getBasicTemplates() {
        templates.clear();
        Stats orcStats = new Stats(15, 6, 1);
        Stats elfStats = new Stats(6, 10, 10);
        Stats humanStats = new Stats(7, 9, 9);
        Template orc = new Template(new Name("orc"), orcStats);
        Template elf = new Template(new Name("elf"), elfStats);
        Template human = new Template(new Name("human"), humanStats);
        templates.add(orc);
        templates.add(elf);
        templates.add(human);
        return templates;
    }
}
