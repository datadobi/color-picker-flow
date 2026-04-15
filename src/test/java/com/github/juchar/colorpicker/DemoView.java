package com.github.juchar.colorpicker;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.awt.Color;

@Route("")
public class DemoView extends VerticalLayout {

  public DemoView() {
    setPadding(true);
    setSizeFull();
    setAlignItems(Alignment.CENTER);
    setJustifyContentMode(JustifyContentMode.CENTER);
    setSpacing(true);

    final ColorPicker colorPicker = new ColorPicker(new Color(255, 0, 255, 150), Color.RED);
    colorPicker.setPinnedInputs(true);
    colorPicker.setPinnedPalettes(true);
    colorPicker.setHexEnabled(false);
    colorPicker.setPalette(Color.RED, Color.GREEN, Color.BLUE);

    add(new H1("Color Picker Demo"));
    add(colorPicker);

    colorPicker.addValueChangeListener(event -> System.out.println("New color: " + event.getValue().toString()));
  }
}
