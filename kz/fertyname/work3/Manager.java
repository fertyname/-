package kz.fertyname.work3;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import kz.fertyname.util.Util;

public class Manager extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField loginField;
	private JPasswordField passwordField;
	private JButton loginButton;
    public Manager() {
        setTitle("Login form");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Логин: "), gbc);

        gbc.gridx = 1;
        loginField = new JTextField(15);
        panel.add(loginField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Пароль: "), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        loginButton = new JButton("Авторизация");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String password = new String(passwordField.getPassword());
                
        		Util.m_Print("default", "Логин: " + login, 20);
        		Util.m_Print("default", "Пароль: " + password, 20);
                
                if(login.equals("fertyname") &&  password.equals("123123")) {
            		Util.m_Print("default", "Удачная авторизация", 20);
                }else {
            		Util.m_Print("default", "Неудачная авторизация", 20);
                }
            }
        });
        panel.add(loginButton, gbc);

        add(panel);
    }
}
