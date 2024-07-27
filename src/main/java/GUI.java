import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {

    private JTextField userField;
    private JScrollPane userScroll;
    private JLabel tasks;
    private JPanel taskPanel;
    private JScrollPane taskScroll;
    private JButton addButton;
    private JPanel taskComponent;


    public GUI() {

        super("To Do App");
        setSize(Constants.FRAME_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setIconImage(new ImageIcon("src/image/logo.png").getImage());

        addComponents();
    }

    private void addComponents() {

        // text field
        userField = new JTextField();
        userField.setBounds((Constants.FRAME_SIZE.width -
                        Constants.USER_FIELD_SIZE.width) / 2, 15,
                Constants.USER_FIELD_SIZE.width,
                Constants.USER_FIELD_SIZE.height);
        userField.setFont(Fonts.USER_FIELD_FONT);


        add(userField);

        // scroll pane -> text field
        userScroll = new JScrollPane(userField);
        userScroll.setBounds((Constants.FRAME_SIZE.width -
                        Constants.USER_FIELD_SIZE.width) / 2, 15,
                Constants.USER_FIELD_SIZE.width,
                Constants.USER_FIELD_SIZE.height);
        userScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        userScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        userScroll.getHorizontalScrollBar().setUI(new CustomHorizontalScroll());
        add(userScroll);

        // "tasks"
        tasks = new JLabel("Tasks");
        tasks.setBounds((Constants.FRAME_SIZE.width -
                        Constants.TASKS_LABEL_SIZE.width) / 2, 50,
                Constants.TASKS_LABEL_SIZE.width,
                Constants.TASKS_LABEL_SIZE.height);
        tasks.setFont(Fonts.TASKS_FONT);
        add(tasks);

        // panel
        taskPanel = new JPanel();

        // task component
        taskComponent = new JPanel();
        taskComponent.setLayout(new BoxLayout(taskComponent, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponent);

        //scroll panel
        taskScroll = new JScrollPane(taskPanel);
        taskScroll.setBounds((Constants.FRAME_SIZE.width -
                        Constants.TASK_PANEL_SIZE.width) / 2, 100,
                Constants.TASK_PANEL_SIZE.width,
                Constants.TASK_PANEL_SIZE.height);
        taskScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        taskScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        taskScroll.getVerticalScrollBar().setUI(new CustomVerticalScroll());
        add(taskScroll);

        // "add" button
        addButton = new JButton("Add");
        addButton.setBounds((Constants.FRAME_SIZE.width -
                        Constants.ADD_BUTTON_SIZE.width) / 2, 510,
                Constants.ADD_BUTTON_SIZE.width,
                Constants.ADD_BUTTON_SIZE.height);
        addButton.setFont(Fonts.ADD_BUTTON_FONT);
        addButton.setContentAreaFilled(false);
        addButton.setFocusPainted(false);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(this);
        add(addButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Add")) {

            if(userField.getText().length()>0){

                Component component = new Component(taskComponent,
                        userField.getText());
                taskComponent.add(component);
                repaint();
                revalidate();

                // adaptive scroll
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {

                        Rectangle rect = component.getBounds();
                        taskScroll.getViewport().scrollRectToVisible(rect);
                    }
                });
            } else{

                JOptionPane optionPane = new JOptionPane(
                        "<html><body style='font-family: Arial; font-size: 14px; color: black;'>" +
                                                    "<b>Error!</b><br>" +
                                                    "Incorrect input." +
                                                    "</body></html>",
                                                   JOptionPane.INFORMATION_MESSAGE,
                        JOptionPane.DEFAULT_OPTION,
                        null,
                        new Object[]{},
                        null);


                JButton okButton = new JButton("OK");
                okButton.setText("OK");
                okButton.setContentAreaFilled(false);
                okButton.setFocusPainted(false);
                okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                okButton.addActionListener(evt -> {
                    optionPane.setValue(JOptionPane.OK_OPTION);
                });

                optionPane.setOptions(new Object[]{okButton});

                JDialog dialog = optionPane.createDialog("Information");
                dialog.setIconImage(new ImageIcon("src/image/logo.png").getImage());
                dialog.setVisible(true);
            }

        }

    }
}

