package com.github.juchar.colorpicker;

import com.github.juchar.colorpicker.util.PaletteUtils;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.function.SerializableFunction;
import elemental.json.JsonArray;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Base class for the color picker. Prevents repeating all the same stuff over and over again.
 *
 * @param <T> The model type (how a color is represented on the server-side)
 */
public interface ColorPickerBase<T> extends HasElement {

  PropertyDescriptor<Boolean, Boolean> PINNED_INPUTS_PROPERTY
      = PropertyDescriptors.propertyWithDefault("pinnedInputs", false);
  PropertyDescriptor<Boolean, Boolean> PINNED_PALETTES_PROPERTY
      = PropertyDescriptors.propertyWithDefault("pinnedPalettes", false);
  PropertyDescriptor<Boolean, Boolean> DISABLE_HEX_PROPERTY
      = PropertyDescriptors.propertyWithDefault("disableHex", false);
  PropertyDescriptor<Boolean, Boolean> DISABLE_RGB_PROPERTY
      = PropertyDescriptors.propertyWithDefault("disableRgb", false);
  PropertyDescriptor<Boolean, Boolean> DISABLE_HSL_PROPERTY
      = PropertyDescriptors.propertyWithDefault("disableHsl", false);
  PropertyDescriptor<Boolean, Boolean> DISABLE_ALPHA_PROPERTY
      = PropertyDescriptors.propertyWithDefault("disableAlpha", false);
  PropertyDescriptor<Boolean, Boolean> DISABLE_SL_PROPERTY
      = PropertyDescriptors.propertyWithDefault("disableSl", false);
  PropertyDescriptor<Double, Double> STEP_ALPHA_PROPERTY
      = PropertyDescriptors.propertyWithDefault("stepAlpha", 0.01d);
  PropertyDescriptor<Double, Double> STEP_HSL_PROPERTY
      = PropertyDescriptors.propertyWithDefault("stepHsl", 1d);
  PropertyDescriptor<String, String> PREVIOUS_VALUE_PROPERTY
      = PropertyDescriptors.propertyWithDefault("previousValue", "");

  /**
   * Get the previous color that is shown to the user.
   *
   * @return The previous color
   */
  default T getPreviousValue() {
    return getPresentationToModel().apply(PREVIOUS_VALUE_PROPERTY.get(this));
  }

  /**
   * Set the previous value that is shown to the user.
   *
   * @param color The previous color
   */
  default void setPreviousValue(T color) {
    PREVIOUS_VALUE_PROPERTY.set(this, getModelToPresentation().apply(color));
  }

  /**
   * Check if inputs are pinned.
   *
   * @return {@code true} if the inputs are pinned, {@code false} else
   */
  default boolean hasPinnedInputs() {
    return PINNED_INPUTS_PROPERTY.get(this);
  }

  /**
   * Set the inputs to be pinned or not.
   *
   * @param pinnedInputs {@code true} if inputs should be pinned, {@code false} else
   */
  default void setPinnedInputs(boolean pinnedInputs) {
    PINNED_INPUTS_PROPERTY.set(this, pinnedInputs);
  }

  /**
   * Check if palettes are pinned.
   *
   * @return {@code true} if the palettes are pinned, {@code false} else
   */
  default boolean hasPinnedPalettes() {
    return PINNED_PALETTES_PROPERTY.get(this);
  }

  /**
   * Set the palettes to be pinned or not.
   *
   * @param pinnedPalettes {@code true} if palettes should be pinned, {@code false} else
   */
  default void setPinnedPalettes(boolean pinnedPalettes) {
    PINNED_PALETTES_PROPERTY.set(this, pinnedPalettes);
  }

  /**
   * Check if hex values are enabled.
   *
   * @return {@code true} if hex values are enabled, {@code false} else
   */
  default boolean isHexEnabled() {
    return !DISABLE_HEX_PROPERTY.get(this);
  }

  /**
   * Enable or disable hex values.
   *
   * @param enabled {@code true} if hex values should be enabled, {@code false} else
   */
  default void setHexEnabled(boolean enabled) {
    DISABLE_HEX_PROPERTY.set(this, !enabled);
  }

  /**
   * Check if rgb values are enabled.
   *
   * @return {@code true} if hex values are enabled, {@code false} else
   */
  default boolean isRgbEnabled() {
    return !DISABLE_RGB_PROPERTY.get(this);
  }

  /**
   * Enable or disable rgb values.
   *
   * @param enabled {@code true} if rgb values should be enabled, {@code false} else
   */
  default void setRgbEnabled(boolean enabled) {
    DISABLE_RGB_PROPERTY.set(this, !enabled);
  }

  /**
   * Check if hsl values are enabled.
   *
   * @return {@code true} if hex values are enabled, {@code false} else
   */
  default boolean isHslEnabled() {
    return !DISABLE_HSL_PROPERTY.get(this);
  }

  /**
   * Enable or disable hsl values.
   *
   * @param enabled {@code true} if hsl values should be enabled, {@code false} else
   */
  default void setHslEnabled(boolean enabled) {
    DISABLE_HSL_PROPERTY.set(this, !enabled);
  }

  /**
   * Check if alpha values are enabled.
   *
   * @return {@code true} if alpha values are enabled, {@code false} else
   */
  default boolean isAlphaEnabled() {
    return !DISABLE_ALPHA_PROPERTY.get(this);
  }

  /**
   * Enable or disable alpha values.
   *
   * @param enabled {@code true} if alpha values should be enabled, {@code false} else
   */
  default void setAlphaEnabled(boolean enabled) {
    DISABLE_ALPHA_PROPERTY.set(this, !enabled);
  }

  /**
   * Check if sl values are enabled.
   *
   * @return {@code true} if sl values are enabled, {@code false} else
   */
  default boolean isSlEnabled() {
    return !DISABLE_SL_PROPERTY.get(this);
  }

  /**
   * Enable or disable sl values.
   *
   * @param enabled {@code true} if sl values should be enabled, {@code false} else
   */
  default void setSlEnabled(boolean enabled) {
    DISABLE_SL_PROPERTY.set(this, !enabled);
  }

  /**
   * Specifies the allowed number intervals of the alpha value.
   *
   * @return the currently allowed interval
   */
  default double getStepAlpha() {
    return STEP_ALPHA_PROPERTY.get(this);
  }

  /**
   * Specifies the allowed number intervals of the alpha value.
   *
   * @param step the interval
   */
  default void setStepAlpha(double step) {
    STEP_ALPHA_PROPERTY.set(this, step);
  }

  /**
   * Specifies the allowed number intervals of the hsl value.
   *
   * @return the currently allowed interval
   */
  default double getStepHsl() {
    return STEP_HSL_PROPERTY.get(this);
  }

  /**
   * Specifies the allowed number intervals of the hsl value.
   *
   * @param step the interval
   */
  default void setStepHsl(double step) {
    STEP_HSL_PROPERTY.set(this, step);
  }

  /**
   * Get the color palettes the user can pick colors of. Each list inside the collection represents
   * a palette.
   *
   * @return The palettes
   */
  default List<List<T>> getPalettes() {
    return PaletteUtils.jsonToPalettes((JsonArray) getElement().getPropertyRaw("palettes"),
        getPresentationToModel());
  }

  /**
   * Set the color palettes the user can pick colors of. Each collection inside the collection
   * represents a palette.
   *
   * @param palettes The color palettes
   */
  default void setPalettes(Collection<Collection<T>> palettes) {
    getElement().setPropertyJson("palettes",
        PaletteUtils.palettesToJson(palettes, getModelToPresentation()));
  }

  /**
   * Set the a color palette the user can pick colors of.
   *
   * @param palette The color palette
   */
  default void setPalette(Collection<T> palette) {
    setPalettes(Collections.singletonList(palette));
  }

  /**
   * Set the a color palette the user can pick colors of.
   *
   * @param colors The colors of the palette
   */
  @SuppressWarnings("unchecked")
  default void setPalette(T... colors) {
    setPalette(Arrays.asList(colors));
  }

  /**
   * Used internally to convert the presentation value to the model value.
   */
  SerializableFunction<String, T> getPresentationToModel();

  /**
   * Used internally to convert the model value to the presentation value.
   */
  SerializableFunction<T, String> getModelToPresentation();
}
