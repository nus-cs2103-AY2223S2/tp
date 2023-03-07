package mycelium.mycelium.ui.common;

import javafx.scene.Node;

/**
 * Funtional Interface for transforming a given item of {@code T} at an index to a {@code UiPart}
 */
@FunctionalInterface
public interface Cardifier<T> {
    UiPart<? extends Node> cardify(T item, int index);
}
