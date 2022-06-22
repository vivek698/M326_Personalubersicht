package ch.bzz.view;


import ch.bzz.facade.ViewComponent;
import ch.bzz.model.employees.HRPerson;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


public class Login extends JDialog{

    private JComboBox<String> hrPersonComboBox = new JComboBox<>();

    private JPasswordField passwordField = new JPasswordField(15);

    private List <HRPerson> listOfHRPerson=new ArrayList<>();


    public Login(int pane,MainView mainView) {
        super();


        setTitle("Login");
        setModal(true);
        setLayout(new GridLayout(3, 2,15,15));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        if (pane==1){
            listOfHRPerson=ViewComponent.getInstance().getAllHRPerson();
        }else if (pane==2){
            listOfHRPerson=ViewComponent.getInstance().getAllHRPersonMode1();
        }


        for (HRPerson person:listOfHRPerson) {
            hrPersonComboBox.addItem(person.getFullName());
        }

        add(new JLabel("Username:"));
        add(hrPersonComboBox);

        add(new JLabel("Password"));
        add(passwordField);

        JButton cancel = new JButton("Abbrechen");
        add(cancel);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.tabbedPane.setSelectedIndex(0);
                dispose();
            }
        });
        JButton submit = new JButton("Weiter");
        add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HRPerson person= listOfHRPerson.get(  hrPersonComboBox.getSelectedIndex()  );
                if (person.getPassword().equals(new String(passwordField.getPassword()))){
                    dispose();
                }else{
                    passwordField.setText("");
                }
            }
        });

        pack();
        setVisible(true);
    }
}
