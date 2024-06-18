package view;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
/**
 * Um JTextField personalizado com funcionalidade de texto de espaço reservado (placeholder).
 * Quando o campo de texto está focado, o texto do espaço reservado desaparece,
 * reaparecendo quando o campo perde o foco e nenhum texto foi digitado.
 */
public class PlaceholderTextField extends JTextField implements FocusListener {
    private String placeholder;
    private boolean showingPlaceholder;

    /**
     * Constrói um PlaceholderTextField com o texto do espaço reservado fornecido.
     * @param placeholder O texto inicial do espaço reservado a ser exibido.
     */
    public PlaceholderTextField(String placeholder) {
        super(placeholder);
        this.placeholder = placeholder;
        this.showingPlaceholder = true;
        this.setForeground(Color.GRAY);
        super.addFocusListener(this);
    }
    /**
     * Chamado quando o campo de texto ganha foco.
     * Se estiver mostrando o espaço reservado, limpa-o para entrada do usuário.
     * @param e O FocusEvent associado ao ganho de foco.
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText("");
            this.setForeground(Color.BLACK);
            showingPlaceholder = false;
        }
    }
    /**
     * Chamado quando o campo de texto perde foco.
     * Se nenhum texto foi digitado, exibe o texto do espaço reservado.
     * @param e O FocusEvent associado à perda de foco.
     */
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

	public boolean isShowingPlaceholder() {
		return showingPlaceholder;
	}

	public void setFormatterFactory(DefaultFormatterFactory formatterFactory) {
		// TODO Auto-generated method stub
		
	}
}
