package Ejercicios;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private final String name;
    private final int age;
    private final String gender;

    public Person(String name, int age, String gender) {
        assert(age>0);
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public double[] averageAgePerGender(List<Person> persons){
        double[] ages = {0,0};
        int numberOfMales = 0;
        int numberOfFemales = 0;
        if(persons.isEmpty()){
            throw new EmptyListException("List is empty");
        }
        for (int i = 0 ; i < persons.size() ; i++){
            if(persons.get(i).getGender().equals("Male")){
                ages[0]=ages[0] + persons.get(i).getAge();
                numberOfMales++;
            }
            if(persons.get(i).getGender().equals("Female")){
                ages[1]=ages[1] + persons.get(i).getAge();
                numberOfFemales++;
            }
        }
        ages[0] = ages[0] / numberOfMales;
        ages[1] = ages[1] / numberOfFemales;
        return ages;
    }
}
