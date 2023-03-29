package vimification.storage;

import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import vimification.model.MacroMap;

public class JsonAdaptedMacroMap {

    private final Map<String, String> definedMacros;

    @JsonCreator
    public JsonAdaptedMacroMap(@JsonProperty("definedMacros") Map<String, String> definedMacros) {
        if (definedMacros == null) {
            definedMacros = Map.of();
        }
        this.definedMacros = definedMacros;
    }

    public JsonAdaptedMacroMap(MacroMap source) {
        definedMacros = source.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public MacroMap toModelType() {
        return new MacroMap(definedMacros);
    }

    @Override
    public String toString() {
        return "JsonAdpatedMacroMap: " + definedMacros;
    }

}
