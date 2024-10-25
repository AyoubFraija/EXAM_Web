package org.example.ejb;

import org.example.ejb.entity.CD;
import org.example.ejb.service.CDService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;

public class AdminApp extends JFrame {
    private CDService cdService;

    public AdminApp() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("jndi.properties"));
            InitialContext ctx = new InitialContext(props);
            cdService = (CDService) ctx.lookup("java:global/my-library/CDService");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Admin - Gestion des CDs");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton addButton = new JButton("Ajouter CD");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add CD logic
            }
        });

        JButton updateButton = new JButton("Modifier CD");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update CD logic
            }
        });

        JButton deleteButton = new JButton("Supprimer CD");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete CD logic
            }
        });

        JPanel panel = new JPanel();
        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        add(panel, BorderLayout.SOUTH);

        if (cdService != null) {
            List<CD> cds = cdService.listAllCDs();
            JList<CD> cdJList = new JList<>(cds.toArray(new CD[0]));
            add(new JScrollPane(cdJList), BorderLayout.CENTER);
        } else {
            System.err.println("CDService is not initialized.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminApp().setVisible(true);
            }
        });
    }
}