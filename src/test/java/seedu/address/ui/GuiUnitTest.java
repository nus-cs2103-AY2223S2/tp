package seedu.address.ui;

import java.util.Optional;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.testfx.api.FxRobot;

import javafx.scene.Node;

/**
 * A GUI unit test class for InternBuddy
 *
 * @@author eugenetangkj-reused
 * Adapted and reused from
 * https://github.com/se-edu/addressbook-level4/blob/master/src/test/java/seedu/address/ui/GuiUnitTest.java,
 * https://github.com/AY2223S1-CS2103T-W17-4/tp/blob/master/src/test/java/seedu/phu/ui/GuiUnitTest.java and
 * https://github.com/TestFX/TestFX/blob/master/subprojects/testfx-core/src/main/java/org/testfx/api/FxRobot.java
 * with minor modifications.
 */
public abstract class GuiUnitTest {
    // TODO: Remove this workaround after using JavaFX version 13 or above
    // This is a workaround to solve headless test failure on Windows OS
    // Refer to https://github.com/javafxports/openjdk-jfx/issues/66 for more details.
    static {
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            System.loadLibrary("WindowsCodecs");
        }
        setHeadlessTestModeToTrue();

    }

    @RegisterExtension
    public final UiPartExtension uiPartExtension = new UiPartExtension();

    protected final FxRobot fxRobot = new FxRobot();

    /**
     * Retrieves the {@code query} node owned by the {@code rootNode}.
     *
     * @param query name of the CSS selector of the node to retrieve.
     */
    protected <T extends Node> T getChildNode(Node rootNode, String query) {
        Optional<T> node = fxRobot.from(rootNode).lookup(query).tryQuery();
        return node.orElse(null);
    }

    //@@author eugenetangkj-reused
    //Reused from https://github.com/AY2223S1-CS2103T-W17-4/tp/blob/master/src/test/java/seedu/phu/ui/GuiUnitTest.java
    private static void setHeadlessTestModeToTrue() {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
        System.setProperty("java.awt.headless", "true");
    }


}
