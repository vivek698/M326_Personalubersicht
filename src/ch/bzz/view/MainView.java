package ch.bzz.view;

import ch.bzz.dataHandler.DataHandler;
import ch.bzz.model.employees.HRPerson;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainView extends JFrame {
    JTabbedPane tabbedPane = new JTabbedPane();
    HRPerson editor;


    public MainView(){
        setTitle("MainGUI");

        tabbedPane.add("Übersicht", new UebersichtView());
        tabbedPane.add("Zuordnen", new ZuordenenView());
        tabbedPane.add("Personen", new PersonView());
        tabbedPane.addTab("Stammdaten", new Stammdaten());
        tabbedPane.addTab("Logbuch", new LogBookView());

        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex()==1||tabbedPane.getSelectedIndex()==2){
                new Login(1,this);
            }else if (tabbedPane.getSelectedIndex()==3||tabbedPane.getSelectedIndex()==4){
                new Login(2,this);
            }
        });

        add(tabbedPane);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        WindowListener wl = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Saves everything before closing the programm
                DataHandler.getInstance().writeCompanyJSON();
            }
        };
        this.addWindowListener(wl);


        pack();
        setSize(700,700);

        setVisible(true);
    }


    public static void main(String[] args) {
        new MainView();
    }
}
