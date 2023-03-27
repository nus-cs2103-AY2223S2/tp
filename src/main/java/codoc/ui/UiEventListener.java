package codoc.ui;

/**
 * Listener used by Ui components to handle events outside commands.
 */
public interface UiEventListener<T> {

    void setListener(T listener);
    T getListener();

}
