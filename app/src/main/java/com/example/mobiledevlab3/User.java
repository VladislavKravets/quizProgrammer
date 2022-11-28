package com.example.mobiledevlab3;

public class User {
    private String fullName;
    private int age;
    private int salary;
    private int rating = 0;

    public User(String fullName, int age, int salary) {
        this.fullName = fullName;
        this.age = age;
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getRating() {
        return rating;
    }

    public void addRating(int rating) {
        this.rating += rating;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", rating=" + rating +
                '}';
    }
}
