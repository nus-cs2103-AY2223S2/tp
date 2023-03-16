package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * The service locator for locating already instantiated services. This can
 * be used to help with dependency injection and create effective isolation
 * between different classes.
 */
public class GetUtils {
    private static final String SERVICE_NOT_REGISTERED_MESSAGE =
        "Service %s not registered.";
    private static final String SERVICE_ALREADY_REGISTERED_MESSAGE =
        "Service %s has already been registered.";
    private static final String CAST_FAILED_MESSAGE = "Casting failed: %s";
    private static final String DELETE_IF_CONTAINS_MESSAGE =
        "Deletes the service %s for %s.";
    private static final String INSTANTIATE_LAZY_VARIABLE_MESSAGE =
        "Instantiating lazy service %s for %s.";
    private static final String CLEAR_MESSAGE =
        "Clearing the contents of GetUtils.";

    /**
     * For storing services that has already been instantiated
     */
    private static final Map<Class<?>, Object> services = new HashMap<>();

    /**
     * For getting the services that has been registered as lazy
     */
    private static final Map<Class<?>, Supplier<?>> servicesSuppliers =
        new HashMap<>();

    private static final Logger logger = LogsCenter.getLogger(GetUtils.class);

    /**
     * Clears the services stored in this service locator.
     */
    public static void clear() {
        logger.info(CLEAR_MESSAGE);
        services.clear();
        servicesSuppliers.clear();
    }

    /**
     * Gets the instance of the corresponding class
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> clazz) {
        requireNonNull(clazz);
        if (services.containsKey(clazz)) {
            try {
                return (T) services.get(clazz);
            } catch (ClassCastException e) {
                throw new GetException(String.format(CAST_FAILED_MESSAGE,
                    e.getMessage()));
            }
        }
        if (servicesSuppliers.containsKey(clazz)) {
            T result;
            try {
                result = (T) servicesSuppliers.get(clazz).get();
                logger.info(String.format(INSTANTIATE_LAZY_VARIABLE_MESSAGE,
                    result.toString(), clazz.getName()));
            } catch (ClassCastException e) {
                throw new GetException(String.format(CAST_FAILED_MESSAGE,
                    e.getMessage()));
            }
            servicesSuppliers.remove(clazz);
            services.put(clazz, result);
            return result;
        }
        throw new GetException(String.format(SERVICE_NOT_REGISTERED_MESSAGE,
            clazz.getName()));
    }

    /**
     * Throws the {@link GetException} if contains the key already.
     *
     * @param key the key.
     * @param <T> the type of the value.
     */
    private static <T> void throwIfContains(Class<T> key) {
        if (servicesSuppliers.containsKey(key)) {
            throw new GetException(
                String.format(
                    SERVICE_ALREADY_REGISTERED_MESSAGE,
                    servicesSuppliers.get(key).get().toString()
                )
            );
        }
        if (services.containsKey(key)) {
            throw new GetException(
                String.format(SERVICE_ALREADY_REGISTERED_MESSAGE,
                    services.get(key).toString())
            );
        }
    }

    /**
     * Deletes the existing value if it is already contained.
     *
     * @param key the key.
     * @param <T> the type of the value.
     */
    public static <T> void delete(Class<T> key) {
        if (servicesSuppliers.containsKey(key)) {
            logger.info(String.format(DELETE_IF_CONTAINS_MESSAGE,
                servicesSuppliers.get(key).get().toString(), key.getName()));
            servicesSuppliers.remove(key);
        }
        if (services.containsKey(key)) {
            logger.info(String.format(DELETE_IF_CONTAINS_MESSAGE,
                services.get(key).toString(), key.getName()));
            services.remove(key);
        }
    }

    /**
     * Puts the value of the key to the service locator. This method will
     * throw if the service is already registered. In most cases, allowing
     * throwing would be the desired behavior. However, if you do not want
     * this behavior, use {@link GetUtils#putForce(Class, Object)} instead.
     *
     * @param key   the class of the value
     * @param value the value
     * @param <T>   the type of the value
     */
    public static <T> void put(Class<T> key, T value) {
        requireNonNull(key);
        requireNonNull(value);
        throwIfContains(key);
        services.put(key, value);
    }

    /**
     * Puts the value into the services, overriding all previous entries.
     *
     * @param key   the class of the service.
     * @param value the value of the service.
     * @param <T>   the type of the service.
     */
    public static <T> void putForce(Class<T> key, T value) {
        delete(key);
        services.put(key, value);
    }

    /**
     * Puts a lazy instantiation of the service. The service would only be
     * instantiated when it is used.
     *
     * @param key      the key.
     * @param supplier the supplier for the value.
     * @param <T>      the type of the value.
     */
    public static <T> void putLazy(Class<T> key, Supplier<T> supplier) {
        requireNonNull(key);
        requireNonNull(supplier);
        throwIfContains(key);
        servicesSuppliers.put(key, supplier);
    }

    /**
     * Puts a lazy instantiation of the service and replace the existing ones
     * with the one provided by the supplier.
     *
     * @param key      the key.
     * @param supplier the supplier for the value.
     * @param <T>      the type of the value.
     */
    public static <T> void putLazyForce(Class<T> key, Supplier<T> supplier) {
        requireNonNull(key);
        requireNonNull(supplier);
        delete(key);
        servicesSuppliers.put(key, supplier);
    }

    /**
     * The exception thrown when trying to get something
     */
    public static class GetException extends RuntimeException {
        /**
         * Creates a get exception with the given message
         */
        public GetException(String message) {
            super(message);
        }
    }
}
