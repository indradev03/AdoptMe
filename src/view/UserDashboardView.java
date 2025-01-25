package src.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserDashboardView {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel leftPanel;

    public UserDashboardView() {
        frame = new JFrame("User Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        frame.setLayout(null);

        topPanel = new JPanel();
        topPanel.setBackground(new Color(34, 177, 76));
        topPanel.setBounds(0, 0, 900, 70);
        topPanel.setLayout(null);

        leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBounds(0, 70, 240, 440);
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        leftPanel.setLayout(null);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBounds(260, 90, 600, 400);
        mainPanel.setLayout(new GridLayout(2, 2, 20, 20));
    }

    public void addProfileButton(JButton button) {
        topPanel.add(button);
    }

    public void addUsernameLabel(JLabel label) {
        topPanel.add(label);
    }

    public void addLogoutButton(JButton button) {
        topPanel.add(button);
    }

    public void addLeftPanel(JPanel panel) {
        frame.add(panel);
    }

    public void addMainPanel(JPanel panel) {
        frame.add(panel);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public void addNavButton(JButton button) {
        leftPanel.add(button);
    }

    public void updateMainPanel(List<JButton> buttons) {
        mainPanel.removeAll();
        for (JButton button : buttons) {
            mainPanel.add(button);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }

}
