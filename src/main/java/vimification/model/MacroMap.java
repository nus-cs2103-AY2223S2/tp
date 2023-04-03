package vimification.model;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.HashMap;

public class MacroMap {

    private Map<String, String> definedMacros;

    public MacroMap() {
        this.definedMacros = new HashMap<>();
    }

    public MacroMap(Map<String, String> definedMacros) {
        this.definedMacros = new HashMap<>(definedMacros);
    }

    public Optional<String> get(String macro) {
        return Optional.ofNullable(definedMacros.get(macro));
    }

    public void put(String macro, String command) {
        definedMacros.put(macro, command);
    }

    public void remove(String macro) {
        definedMacros.remove(macro);
    }

    public Set<Map.Entry<String, String>> entrySet() {
        return definedMacros.entrySet();
    }

    public boolean isEmpty() {
        return definedMacros.isEmpty();
    }

    @Override
    public String toString() {
        return "MacroMap [definedMacros=" + definedMacros + "]";
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
        return definedMacros.equals(otherMap.definedMacros);
    }
}
