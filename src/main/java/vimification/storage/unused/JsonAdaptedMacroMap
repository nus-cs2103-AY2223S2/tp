package vimification.storage;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import vimification.model.MacroMap;

public class JsonAdaptedMacroMap {

    private final Map<String, String> macros;

    @JsonCreator
    public JsonAdaptedMacroMap(@JsonProperty("macros") Map<String, String> macros) {
        if (macros == null) {
            macros = Map.of();
        }
        this.macros = macros;
    }

    public JsonAdaptedMacroMap(MacroMap source) {
        macros = Map.copyOf(source.getMapping());
    }

    public MacroMap toModelType() {
        return new MacroMap(macros);
    }

    @Override
    public String toString() {
        return "JsonAdaptedMacroMap [macros=" + macros + "]";
    }
}
