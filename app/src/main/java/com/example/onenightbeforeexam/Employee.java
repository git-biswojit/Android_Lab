package com.example.onenightbeforeexam;

import java.io.Serializable;

public class Employee implements Serializable {
    String firstName, lastName, email,
            gender, hobbies, country, qualification,
            prevCompanies;
    Integer age,experienceYear;
    Float expectedSalary;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Float getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(Float expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public Integer getExperienceYear() {
        return experienceYear;
    }

    public void setExperienceYear(Integer experienceYear) {
        this.experienceYear = experienceYear;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPrevCompanies() {
        return prevCompanies;
    }

    public void setPrevCompanies(String prevCompanies) {
        this.prevCompanies = prevCompanies;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", country='" + country + '\'' +
                ", qualification='" + qualification + '\'' +
                ", prevCompanies='" + prevCompanies + '\'' +
                ", age=" + age +
                ", experienceYear=" + experienceYear +
                ", expectedSalary=" + expectedSalary +
                '}';
    }
}
