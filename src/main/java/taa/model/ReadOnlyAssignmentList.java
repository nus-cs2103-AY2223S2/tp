package taa.model;

import java.util.ArrayList;
import java.util.function.Supplier;

import taa.model.assignment.Assignment;

/**
 * Unmodifiable view of an assignment list.
 */
public interface ReadOnlyAssignmentList extends Supplier<Assignment[]> {
}
