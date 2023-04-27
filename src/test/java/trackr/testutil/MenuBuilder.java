package trackr.testutil;

import trackr.model.Menu;
import trackr.model.menu.MenuItem;

/**
 * A utility class to help with building TaskList objects.
 * Example usage: <br>
 *     {@code Menu mb = new MenuBuilder().withTask("Buy X", "Buy Y").build();}
 */
//@@author changgittyhub
public class MenuBuilder {

    private Menu menu;

    public MenuBuilder() {
        menu = new Menu();
    }

    public MenuBuilder(Menu menu) {
        this.menu = menu;
    }

    /**
     * Adds a new {@code MenuItem} to the {@code Menu} that we are building.
     */
    public MenuBuilder withMenuItem(MenuItem menuItem) {
        menu.addItem(menuItem);
        return this;
    }

    public Menu build() {
        return menu;
    }
}
