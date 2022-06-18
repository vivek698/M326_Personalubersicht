package ch.bzz.model.company;

import ch.bzz.model.employees.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Get and Converts the Data from the JSON-Files
 *
 * @author Vivek Viruthiyel
 * @version 1.0
 * @since 14.06.2022
 */
public class Company {
    private String companyName;
    private List<Department> departments;
    private JobFunctions jobFunctions;
    private Teams teams;

    public Company(@JsonProperty("name") String name) {
        this.companyName = name;
        departments=new ArrayList<>();
        jobFunctions=new JobFunctions();
        teams =new Teams();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    


    public Department getDepartment(int index) {
        return departments.get(index);
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void removeDepartment(int index) {
        departments.remove(index);
    }

    @JsonIgnore
    public int getNumberOfDepartment(){
        return departments.size();
    }

    public List<Department> getListofDepartment(){
        return departments;
    }

    public void setListofDepartment(List<Department> departments){
        this.departments = departments;
    }



    public Teams getTeams(){
        return teams;
    }

    public void setTeams(Teams teams){
        this.teams=teams;
    }

    public String getTeams(int index) {
        return teams.getDesignation(index);
    }

    public void addTeams(String  function) {
        teams.addDesignation(function);
    }

    public void removeTeams(int index) {
        teams.removeDesignation(index);
    }

    @JsonIgnore
    public int getNumberOfTeams(){
        return teams.getSize();
    }




    public JobFunctions getJobFunctions(){
        return jobFunctions;
    }

    public void setJobFunctions( JobFunctions jobFunctions){
        this.jobFunctions=jobFunctions;
    }

    public String getJobFunction(int index) {
        return jobFunctions.getDesignation(index);
    }

    public void addJobFunction(String  function) {
        jobFunctions.addDesignation(function);
    }

    public void removeJobFunction(int index) {
        jobFunctions.removeDesignation(index);
    }

    @JsonIgnore
    public int getNumberOfJobFunction(){
        return jobFunctions.getSize();
    }



    @JsonIgnore
    public List<Person> getAllPerson(){
        ArrayList<Person> allPerson=new ArrayList<>();
        for (int i = 0; i < departments.size(); i++) {
            allPerson.addAll(departments.get(i).getListOfPersons());
        }
        return allPerson;
    }



    public List<Person> getAllPersonSort(String sortType){
        return sortListOfPerson(getAllPerson(),sortType);
    }

    private List<Person> sortListOfPerson(List<Person> listOfPerson, String sortType){
        Collections.sort(listOfPerson);

        if (sortType.equals("asc")){
            return listOfPerson;
        }else if (sortType.equals("desc")){
            return listOfPerson;
        }
        return null;
    }



    public List<Person> getAllPersonFilteredByDepartment(String departmentName){
        return filterListOfPersonByDepartment(departmentName);
    }

    public List<Person> getAllPersonFilteredByFunktion(String funktion){
        return filterListOfPersonByFunction(getAllPerson(),funktion);
    }

    public List<Person> getAllPersonFilteredByTeams(String teams){
        return filterListOfPersonByTeams(getAllPerson(),teams);
    }

    public List<Person> getAllPersonFilteredByDepartmentAndFunction(String departmentName, String function){
        List<Person> filteredPerson= filterListOfPersonByDepartment(departmentName);
        filteredPerson= filterListOfPersonByFunction(filteredPerson,function);
        return filteredPerson;
    }

    public List<Person> getAllPersonFilteredByDepartmentAndTeams(String departmentName, String teams){
        List<Person> filteredPerson= filterListOfPersonByDepartment(departmentName);
        filteredPerson= filterListOfPersonByTeams(filteredPerson,teams);
        return filteredPerson;
    }

    public List<Person> getAllPersonFilteredByFunctionAndTeams(String function, String teams){
        List<Person> filteredPerson= filterListOfPersonByFunction(getAllPerson(),function);
        filteredPerson= filterListOfPersonByTeams(filteredPerson,teams);
        return filteredPerson;
    }

    public List<Person> getAllPersonFilteredByDepartmentAndFunctionAndTeams(String departmentName,String function,String teams){
        List<Person> filteredPerson= filterListOfPersonByDepartment(departmentName);
        filteredPerson= filterListOfPersonByTeams(filteredPerson,teams);
        filteredPerson= filterListOfPersonByTeams(filteredPerson,teams);
        return filteredPerson;
    }



    public List<Person> getAllPersonSortedFilteredByDepartment(String departmentName,String sortType){
        List<Person> filteredPerson = getAllPersonFilteredByDepartment(departmentName);
        sortListOfPerson(filteredPerson,sortType);
        return filteredPerson;
    }

    public List<Person> getAllPersonSortedFilteredByFunktion(String funktion,String sortType){
        List<Person> filteredPerson = getAllPersonFilteredByFunktion(funktion);
        sortListOfPerson(filteredPerson,sortType);
        return filteredPerson;
    }

    public List<Person> getAllPersonSortedFilteredByTeams(String teams,String sortType){
        List<Person> filteredPerson = getAllPersonFilteredByTeams(teams);
        sortListOfPerson(filteredPerson,sortType);
        return filteredPerson;
    }

    public List<Person> getAllPersonSortedFilteredByDepartmentAndFunction(String departmentName, String function,String sortType){
        List<Person> filteredPerson = getAllPersonFilteredByDepartmentAndFunction(departmentName,function);
        sortListOfPerson(filteredPerson,sortType);
        return filteredPerson;
    }

    public List<Person> getAllPersonSortedFilteredByDepartmentAndTeams(String departmentName, String teams,String sortType){
        List<Person> filteredPerson = getAllPersonFilteredByDepartmentAndTeams(departmentName,teams);
        sortListOfPerson(filteredPerson,sortType);
        return filteredPerson;
    }

    public List<Person> getAllPersonSortedFilteredByFunctionAndTeams(String function, String teams,String sortType){
        List<Person> filteredPerson = getAllPersonFilteredByFunctionAndTeams(function,teams);
        sortListOfPerson(filteredPerson,sortType);
        return filteredPerson;
    }

    public List<Person> getAllPersonSortedFilteredByDepartmentAndFunctionAndTeams(String departmentName,String function,String teams,String sortType){
        List<Person> filteredPerson = getAllPersonFilteredByDepartmentAndFunctionAndTeams(departmentName,function,teams);
        sortListOfPerson(filteredPerson,sortType);
        return filteredPerson;
    }




    private List<Person> filterListOfPersonByDepartment(String departmentName){
        for (Department department:departments) {
            if (department.getName().equals(departmentName)){
                return department.getListOfPersons();
            }
        }
        return null;
    }

    private List<Person> filterListOfPersonByFunction(List<Person> listOfPerson, String jobFunction){
        ArrayList<Person>filteredPerson=new ArrayList<>();
        for (Person person:listOfPerson) {
            if (person.getParticipation().getListOfTeams().contains(jobFunction)){
                filteredPerson.add(person);
            }
        }
        return filteredPerson;
    }

    private List<Person> filterListOfPersonByTeams(List<Person> listOfPerson, String team){
        ArrayList<Person>filteredPerson=new ArrayList<>();
        for (Person person:listOfPerson) {
            if (person.getParticipation().getListOfTeams().contains(team)){
                filteredPerson.add(person);
            }
        }
        return filteredPerson;
    }

}
