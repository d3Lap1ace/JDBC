package com.java.jdbc.javabean;

/**
*    @classname Student
*    @description 
*    @Auther d3Lap1ace
*    @Time 2024/04/20  13:下午1:59
*    @Version 1.0
*                        From the Laplace Demon 
*/
public class Student {
    private int id;
    private String name;
    private int age;
    private String gender;
    private int master;

    public Student(){};

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", master=" + master +
                '}';
    }

    public Student(int id, String name, int age, String gender, int master) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.master = master;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getMaster() {
        return master;
    }

    public void setMaster(int master) {
        this.master = master;
    }
}
