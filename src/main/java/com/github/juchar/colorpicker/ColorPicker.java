package com.github.juchar.colorpicker;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

import java.awt.*;

@Tag("color-picker")
@NpmPackage(value = "@datadobi/color-picker", version = "2.0.0-beta.4-datadobi4")
@JsModule("@datadobi/color-picker/color-picker.js")
public class ColorPicker extends AbstractSinglePropertyField<ColorPicker, Color>
        implements ColorPickerBaseColor, HasStyle, Focusable<ColorPicker>, HasTheme, HasSize {

    /**
     * Constructs an empty {@code ColorPicker}.
     */
    public ColorPicker() {
        this(null, null);
    }

    /**
     * Constructs a {@code ColorPicker} with an initial value and a previous value.
     *
     * @param initialValue  the initial value
     * @param previousValue the previous value
     */
    public ColorPicker(Color initialValue, Color previousValue) {
        super("value", null, String.class,
                ColorPickerBaseColor.PRESENTATION_TO_MODEL, ColorPickerBaseColor.MODEL_TO_PRESENTATION);
        setValue(initialValue);
        setPreviousValue(previousValue);
    }

    /**
     * Constructs a {@code ColorPicker} with an initial value.
     *
     * @param initialValue the initial value
     */
    public ColorPicker(Color initialValue) {
        this(initialValue, null);
    }
}
