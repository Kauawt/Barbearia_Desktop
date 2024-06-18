package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * Um componente personalizado para exibir imagens com diferentes modos de redimensionamento, a fim de personalizar o sistema.
 */
public class JPictureBox extends JComponent {

    private Icon icon = null;
    private final Dimension dimension = new Dimension(100, 100);
    private Image image = null;
    private ImageIcon ii = null;
    private SizeMode sizeMode = SizeMode.STRETCH;
    private int newHeight, newWidth, originalHeight, originalWidth;
    
    /**
     * Construtor padrão que inicializa o componente com dimensões padrão e configurações iniciais.
     */
    public JPictureBox() {
        JPictureBox.this.setPreferredSize(dimension);
        JPictureBox.this.setOpaque(false);
        JPictureBox.this.setSizeMode(SizeMode.STRETCH);
    }

    /**
     * Método sobrescrito para desenhar o componente com a imagem de acordo com o modo de redimensionamento escolhido.
     *
     * @param g O contexto gráfico usado para desenhar o componente.
     */
    @Override
    public void paintComponent(Graphics g) {
        if (ii != null) {
            switch (getSizeMode()) {
                case NORMAL:
                    g.drawImage(image, 0, 0, ii.getIconWidth(), ii.getIconHeight(), null);
                    break;
                case ZOOM:
                    aspectRatio();
                    g.drawImage(image, 0, 0, newWidth, newHeight, null);
                    break;
                case STRETCH:
                    g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
                    break;
                case CENTER:
                    g.drawImage(image, (int) (this.getWidth() / 2) - (int) (ii.getIconWidth() / 2), (int) (this.getHeight() / 2) - (int) (ii.getIconHeight() / 2), ii.getIconWidth(), ii.getIconHeight(), null);
                    break;
                default:
                    g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        }
    }
    /**
     * Obtém o ícone atualmente definido para o componente (seleiconado sempre pela pasta icones)
     *
     * @return O ícone atual.
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * Define o ícone a ser exibido no componente e realiza as operações necessárias de atualização.
     *
     * @param icon O novo ícone a ser definido.
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
        ii = (ImageIcon) icon;
        image = ii.getImage();
        originalHeight = ii.getIconHeight();
        originalWidth = ii.getIconWidth();
        revalidate();
        repaint();
    }

    public SizeMode getSizeMode() {
        return sizeMode;
    }

    public void setSizeMode(SizeMode sizeMode) {
        this.sizeMode = sizeMode;
    }
    /**
     * Enumeração que define os diferentes modos de redimensionamento suportados pelo componente.
     */
    public enum SizeMode {
        NORMAL,
        STRETCH,
        CENTER,
        ZOOM
    }
    /**
     * Método privado para calcular as novas dimensões da imagem mantendo a proporção original.
     */
    private void aspectRatio() {
        if (ii != null) {
            newHeight = this.getHeight();
            newWidth = (originalWidth * newHeight) / originalHeight;
        }
    }

}
