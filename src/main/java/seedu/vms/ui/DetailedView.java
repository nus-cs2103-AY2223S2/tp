package seedu.vms.ui;

import java.util.function.Function;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.VBox;


/**
 * Graphical display to display the detailed view of a value.
 *
 * @param <T> the type of value to display.
 */
public class DetailedView<T> extends VBox implements Refreshable {
    public static final String STYLE_CLASS = "detailed-view-card";

    private final Function<T, Node> displayFunction;

    private T value = null;
    private volatile boolean isUpdated = false;


    /**
     * Constructs a {@code DetailedView}.
     *
     * @param sourceProperty - value property to bind to.
     * @param displayFunction - a function to convert the value to display to
     *      its graphical representation.
     */
    public DetailedView(ObjectProperty<T> sourceProperty, Function<T, Node> displayFunction) {
        getStyleClass().add(STYLE_CLASS);
        this.displayFunction = displayFunction;
        sourceProperty.addListener(this::handleChange);
    }


    private synchronized void handleChange(ObservableValue<? extends T> ob, T oldValue, T newValue) {
        value = newValue;
        isUpdated = false;
    }


    @Override
    public synchronized void refresh() {
        if (isUpdated) {
            return;
        }

        getChildren().clear();
        if (value != null) {
            getChildren().add(displayFunction.apply(value));
        }
        isUpdated = true;
    }
}
