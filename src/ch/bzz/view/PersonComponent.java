package ch.bzz.view;

import ch.bzz.facade.ViewComponent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import static java.awt.BorderLayout.*;

public class PersonComponent extends JPanel{
    private JLabel pltzh1 = new JLabel(" ");
    private JLabel pltzh2 = new JLabel(" ");

    private JPanel personPanel = new JPanel(new BorderLayout());
    private JPanel uebersichtLabelPanel = new JPanel(new FlowLayout());
    private JPanel personUebersichtPanel = new JPanel(new BorderLayout());
    ListMaker listMaker;
    private JLabel uebersicht = new JLabel("Übersicht:");

    //Detail

    private JPanel detailPanel = new JPanel(new BorderLayout());
    private JPanel inDeatilPabel = new JPanel(new BorderLayout());
    private JPanel namePanel = new JPanel(new FlowLayout());
    private JPanel abteilungPanel = new JPanel(new FlowLayout());
    private JPanel pictureLabelPanel = new JPanel(new FlowLayout());
    private JPanel picturePanel = new JPanel(new FlowLayout());

    private JLabel name = new JLabel("Name:");
    private JLabel abteilung = new JLabel("Abteilung:");
    private JLabel pictureLabel = new JLabel("");

    private JPanel nameGrid = new JPanel(new GridLayout(1,2));
    private JPanel abteilungGrid = new JPanel(new GridLayout(1,2));
    private JPanel pictureGrid = new JPanel(new GridLayout(1,2));
    private JLabel pltzh3 = new JLabel();

    private JTextField nameField = new JTextField();
    private JTextField abteilungField = new JTextField();

    public PersonComponent(JPanel extentionPanel, int index){
       listMaker = new ListMaker("PersonView" ,index);

        setLayout(new BorderLayout());
        personPanel.setBorder(BorderFactory.createTitledBorder("Personen"));

        uebersicht.setBackground(Color.GRAY);
        uebersichtLabelPanel.add(uebersicht);
        uebersichtLabelPanel.setBackground(Color.LIGHT_GRAY);
        uebersichtLabelPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        personUebersichtPanel.add(uebersichtLabelPanel, NORTH);
        personUebersichtPanel.add(listMaker, CENTER);
        personUebersichtPanel.add(pltzh1, SOUTH);


        //Detail

        nameField.setEditable(false);
        abteilungField.setEditable(false);

        namePanel.add(name);
        abteilungPanel.add(abteilung);
        pictureLabelPanel.add(pltzh2);
        picturePanel.add(pictureLabel);
        pictureLabel.setIcon(new ImageIcon("data/img/standart.png"));

        nameGrid.add(namePanel);
        nameGrid.add(nameField);
        pictureGrid.add(pltzh3);
        pictureGrid.add(picturePanel);
        abteilungGrid.add(abteilungPanel);
        abteilungGrid.add(abteilungField);

        inDeatilPabel.add(nameGrid, NORTH);
        inDeatilPabel.add(pictureGrid, CENTER);
        inDeatilPabel.add(abteilungGrid, SOUTH);


        detailPanel.add(inDeatilPabel, NORTH);
        detailPanel.add(extentionPanel, CENTER);
        detailPanel.setBorder(BorderFactory.createTitledBorder("Detail:"));


        personPanel.add(detailPanel, CENTER);
        personPanel.add(personUebersichtPanel, WEST);

        add(personPanel, CENTER);
        setVisible(true);


        listMaker.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                setFieldText();
                changePicture();
            }
        });


    }

    public void setFieldText(){
        nameField.setText(listMaker.getSelectedFullName());

        for (int i = 0; i< ViewComponent.getInstance().getDepartmentList().size(); i++) {
            for (int j = 0; j<ViewComponent.getInstance().getDepartmentList().get(i).getNumberOfMembers(); j++)
                if(ViewComponent.getInstance().getDepartmentList().get(i).getMember(j).getFullName().equals(listMaker.getSelectedFullName()))
                    abteilungField.setText(ViewComponent.getInstance().getDepartmentList().get(i).getName());
        }
        ViewComponent.getInstance().changer();
    }

    public void changePicture(){
        if (ViewComponent.getInstance().getAllPersonOfCompany().get(listMaker.getIndex()).getImgPath()==null){
            pictureLabel.setIcon(new ImageIcon("data/img/standart.png"));
        }else {
            pictureLabel.setIcon(new ImageIcon(ViewComponent.getInstance().getAllPersonOfCompany().get(listMaker.getIndex()).getImgPath()));
        }
        ViewComponent.getInstance().changer();
    }
}
