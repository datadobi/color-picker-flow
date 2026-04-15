package com.github.juchar.colorpicker.util;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class PaletteUtils {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private PaletteUtils() {
    // Prevent instantiation
  }

  public static <T> ArrayNode paletteToJson(Collection<T> palette,
                                            Function<T, String> toPresentation) {
    final List<String> convertedPalette = palette.stream().map(toPresentation).toList();
    final ArrayNode jsonPalette = OBJECT_MAPPER.createArrayNode();
    for (String s : convertedPalette) {
      jsonPalette.add(s);
    }

    return jsonPalette;
  }

  public static <T> ArrayNode palettesToJson(Collection<Collection<T>> palettes,
      Function<T, String> toPresentation) {
    final List<Collection<T>> convertedPalettes = new ArrayList<>(palettes);
    final ArrayNode jsonPalettes = OBJECT_MAPPER.createArrayNode();

    for (Collection<T> palette : convertedPalettes) {
      jsonPalettes.add(paletteToJson(palette, toPresentation));
    }

    return jsonPalettes;
  }

  public static ArrayNode palettesToJson(Collection<Collection<String>> palettes) {
    return palettesToJson(palettes, Function.identity());
  }

  public static <T> List<T> jsonToPalette(ArrayNode jsonPalette, Function<String, T> toColor) {
    List<T> palette = new ArrayList<>(jsonPalette.size());

    for (int i = 0; i < jsonPalette.size(); i++) {
      palette.add(toColor.apply(jsonPalette.get(i).asString()));
    }

    return palette;
  }

  public static <T> List<List<T>> jsonToPalettes(ArrayNode jsonPalettes,
      Function<String, T> toColor) {
    List<List<T>> palettes = new ArrayList<>(jsonPalettes.size());

    for (int i = 0; i < jsonPalettes.size(); i++) {
      palettes.add(jsonToPalette((ArrayNode) jsonPalettes.get(i), toColor));
    }

    return palettes;
  }

  public static List<List<String>> jsonToPalettes(ArrayNode jsonPalettes) {
    return jsonToPalettes(jsonPalettes, Function.identity());
  }
}
