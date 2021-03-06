package ch.bzz.test;

import ch.bzz.model.employees.HRPerson;
import ch.bzz.model.employees.Person;
import ch.bzz.model.log.LogBook;
import ch.bzz.model.log.UserAction;

import java.io.IOException;

public class LogTest {


    public static void main(String[]args) throws IOException {
        HRPerson hrperson = new HRPerson("Max", "Muster", 0);
        Person person = new Person("Maila", "Maurer");

        hrperson.writeLogEntry(UserAction.CREATE_PERSON, person);
        hrperson.writeLogEntry(UserAction.SET_ASSIGNMENT, person);
        hrperson.writeLogEntry(UserAction.CHANGE_VALUE, person);
        hrperson.writeLogEntry(-1, person);
        hrperson.writeLogEntry(UserAction.DELETE_PERSON, person);

        LogBook.getLogBookInstance().printLog();

    }
}
