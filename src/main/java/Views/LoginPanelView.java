package Views;

import Controllers.UserController;
import Models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class LoginPanelView extends JFrame {
    private UserController controller = new UserController();

    public JPanel loginPanel;
    private JTextField usernameField;
    private JButton submitLogin;
    private JPanel formPanel;
    private JLabel passwordLabel;
    private JTextField passwordField;
    private JLabel userNameLabel;

    private JFrame frame;

    public LoginPanelView() {
        frame = new JFrame("LoginPanelView");
        frame.setContentPane(loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(0, 0, 600, 600);
        frame.setVisible(true);

        submitLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setEmail(usernameField.getText());
                user.setNewPassword(passwordField.getText());

                if (controller.loginUser(user) != null) {
                    frame.setVisible(false);
                    MenuView.start();
                } else {
                    JOptionPane.showMessageDialog(loginPanel, "Usuário ou senha incorreta!");
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
