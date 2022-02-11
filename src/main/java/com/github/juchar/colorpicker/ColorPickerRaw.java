package com.github.juchar.colorpicker;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

@Tag("color-picker")
@NpmPackage(value = "@datadobi/color-picker", version = "2.0.0-beta.4-datadobi4")
@JsModule("@datadobi/color-picker/color-picker.js")
public class ColorPickerRaw extends AbstractSinglePropertyField<ColorPickerRaw, String>
        implements ColorPickerBaseRaw, HasStyle, Focusable<ColorPickerRaw>, HasTheme, HasSize {

    /**
     * Constructs an empty {@code ColorPicker}.
     */
    public ColorPickerRaw() {
        this(null, null);
    }

    /**
     * Constructs a {@code ColorPicker} with an initial value and a previous value.
     *
     * @param initialValue  the initial value
     * @param previousValue the previous value
     */
    public ColorPickerRaw(String initialValue, String previousValue) {
        super("value", null, true);
        setValue(initialValue);
        setPreviousValue(previousValue);
    }

    /**
     * Constructs a {@code ColorPicker} with an initial value.
     *
     * @param initialValue the initial value
     */
    public ColorPickerRaw(String initialValue) {
        this(initialValue, null);
    }
}
