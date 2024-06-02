package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceholderTextField extends JTextField implements FocusListener {
    private String placeholder;
    private boolean showingPlaceholder;

    public PlaceholderTextField(String placeholder) {
        super(placeholder);
        this.placeholder = placeholder;
        this.showingPlaceholder = true;
        this.setForeground(Color.GRAY);
        super.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText("");
            this.setForeground(Color.BLACK);
            showingPlaceholder = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText(placeholder);
            this.setForeground(Color.GRAY);
            showingPlaceholder = true;
        }
    }

    @Override
    public String getText() {
        return showingPlaceholder ? "" : super.getText();
    }
}
