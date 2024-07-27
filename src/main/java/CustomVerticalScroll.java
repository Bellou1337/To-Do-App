import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomVerticalScroll extends BasicScrollBarUI {

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return Constants.VERTICAL_SCROLLBAR_SIZE;
    }

    @Override
    protected void configureScrollBarColors() {
        thumbColor = Color.GRAY;
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }
}
