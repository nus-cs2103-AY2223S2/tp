package vimification.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Responsible for keeping track of all macros registed during the runtime of the application.
 * <p>
 * Each macro string will be mapped to a command string. The macro will be replaced by the
 * corresponding command string when the user input is being parsed.
 */
public class MacroMap {

    private Map<String, String> macros;

    /**
     * Creates a new, empty {@code MacroMap}.
     */
    public MacroMap() {
        this.macros = new HashMap<>();
    }

    /**
     * Creates a new {@code MacroMap} with the given mappings.
     *
     * @param macros the mappings between macros and command strings
     */
    public MacroMap(Map<String, String> macros) {
        this.macros = new HashMap<>(macros);
    }

    public Optional<String> get(String macro) {
        return Optional.ofNullable(macros.get(macro));
    }

    public void put(String macro, String command) {
        macros.put(macro, command);
    }

    public void remove(String macro) {
        macros.remove(macro);
    }

    public boolean isEmpty() {
        return macros.isEmpty();
    }

    public Map<String, String> getMapping() {
        return Collections.unmodifiableMap(macros);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MacroMap)) {
            return false;
        }
        MacroMap otherMap = (MacroMap) other;
        return Objects.equals(macros, otherMap.macros);
    }
}
