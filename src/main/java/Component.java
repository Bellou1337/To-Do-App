import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Component extends JPanel implements ActionListener {

    private JPanel addComponent;
    private GridBagConstraints gbc;
    private JCheckBox checkBoxComponent;
    private JTextPane textComponent;
    private JButton deleteButtonComponent;
    private JScrollPane taskScroll;

    public Component(JPanel taskComponent,String task){

        this.addComponent = taskComponent;

        //set Layout
        setLayout(new GridBagLayout());

        //text component
        textComponent = new JTextPane();
        textComponent.setEditable(false);
        textComponent.setPreferredSize(Constants.TEXT_COMPONENT_SIZE);
        textComponent.setFont(new Font("Dialog", Font.PLAIN, 16));
        textComponent.setCaret(new DefaultCaret(){

            @Override
            public void paint(Graphics g) {
                // Do nothing
            }
        });
        textComponent.setText(task);

        //text component scroll
        taskScroll = new JScrollPane(textComponent);
        taskScroll.setPreferredSize(Constants.TEXT_COMPONENT_SIZE);
        taskScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        taskScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        taskScroll.getHorizontalScrollBar().setUI(new CustomHorizontalScroll());

        // check box
        checkBoxComponent = new JCheckBox();
        checkBoxComponent.setPreferredSize(Constants.CHECKBOX_COMPONENT_SIZE);
        checkBoxComponent.addActionListener(this);

        // delete button
        deleteButtonComponent = new JButton("X");
        deleteButtonComponent.setPreferredSize
                (Constants.DELETE_BUTTON_SIZE);
        deleteButtonComponent.setContentAreaFilled(false);
        deleteButtonComponent.setFocusPainted(false);
        deleteButtonComponent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButtonComponent.addActionListener(this);

        //grid
        getGrid();

        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.insets = Constants.CHECKBOX_INSETS;
        add(checkBoxComponent,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = Constants.TEXT_COMPONENT_INSETS;
        add(taskScroll,gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.insets = Constants.DELETE_BUTTON_INSETS;
        add(deleteButtonComponent,gbc);
    }
    private void getGrid() { gbc = new GridBagConstraints(); }

    @Override
    public void actionPerformed(ActionEvent e) {

        //text style
        if(e.getSource() == checkBoxComponent){

            StyledDocument doc = textComponent.getStyledDocument();
            Style style = textComponent.addStyle("StrikethroughStyle",
                    null);

            if(checkBoxComponent.isSelected()){

                StyleConstants.setStrikeThrough(style,
                        checkBoxComponent.isSelected());
            } else{

                StyleConstants.setStrikeThrough(style,
                        false);
            }


            doc.setCharacterAttributes(0, textComponent.getText().length(), style, false);
            addComponent.repaint();
            addComponent.revalidate();

        } else if(e.getSource() == deleteButtonComponent){

            addComponent.remove(this);
            addComponent.repaint();
            addComponent.revalidate();
        }
    }
}
