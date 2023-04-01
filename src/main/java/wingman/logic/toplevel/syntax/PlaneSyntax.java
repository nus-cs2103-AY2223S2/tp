package wingman.logic.toplevel.syntax;

import java.util.Set;

import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.Model;
import wingman.model.ReadOnlyItemManager;
import wingman.model.item.exceptions.DuplicateItemException;
import wingman.model.plane.Plane;

/**
 * The syntax for a plane.
 */
public abstract class PlaneSyntax {
    /**
     * The prefix for model.
     */
    public static final String PREFIX_MODEL = "/m";

    /**
     * The prefix for age.
     */
    public static final String PREFIX_AGE = "/a";

    /**
     * The prefixes.
     */
    public static final Set<String> PREFIXES = Set.of(
            PREFIX_MODEL,
            PREFIX_AGE
    );

    /**
     * Creates a param.
     *
     * @param param the command parameter.
     * @return the plane created.
     * @throws ParseException if the command parameter is not valid.
     */
    public static Plane factory(CommandParam param) throws ParseException {
        String model = param.getNamedValuesOrThrow(PREFIX_MODEL);
        int age = param.getNamedIntOrThrow(PREFIX_AGE);
        return new Plane(model, age);
    }

    /**
     * Adds the given plane to the model.
     *
     * @param model the model to which the plane shall be added.
     * @param plane the plane that which shall be added to the model.
     */
    public static void add(Model model, Plane plane) throws DuplicateItemException {
        model.addPlane(plane);
    }

    /**
     * Returns the manager for planes.
     *
     * @param model the model.
     * @return the manager for planes.
     */
    public static ReadOnlyItemManager<Plane> getManager(Model model) {
        return model.getPlaneManager();
    }

    /**
     * Deletes the plane from the model.
     *
     * @param model the model.
     * @param plane the plane.
     */
    public static void delete(Model model, Plane plane) {
        model.deletePlane(plane);
    }
}
