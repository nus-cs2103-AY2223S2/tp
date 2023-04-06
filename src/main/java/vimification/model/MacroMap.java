package vimification.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class MacroMap {

    private Map<String, String> macros;

    public MacroMap() {
        this.macros = new HashMap<>();
    }

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
