package ch.bzz.test;

import ch.bzz.view.ListMaker;

import javax.swing.*;

public class ListMaker_Test {
    public static void main(String[] args) {
        run();
    }

    public static void run(){
        JFrame jFrame = new JFrame();


        ListMaker listMaker = new ListMaker("PersonView", 1);
        jFrame.add(listMaker);


        jFrame.pack();
        jFrame.setVisible(true);
    }
}
