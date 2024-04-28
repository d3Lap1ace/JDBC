package com.jdbc.test.javabean;

/**
 * mysql> desc student;
 * +--------+-----------------+------+-----+---------+----------------+
 * | Field  | Type            | Null | Key | Default | Extra          |
 * +--------+-----------------+------+-----+---------+----------------+
 * | id     | int             | NO   | PRI | NULL    | auto_increment |
 * | name   | varchar(20)     | YES  | UNI | NULL    |                |
 * | age    | int             | YES  |     | NULL    |                |
 * | gender | enum('男','女') | YES  |     | 男      |                |
 * | master | int             | YES  |     | NULL    |                |
 * +--------+-----------------+------+-----+---------+----------------+
 * 5 rows in set (0.00 sec)
 */
public class Student {

    private int id;
    private String name;
    private int age;
    private String gender;
    private int master;

    public Student() {}

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
}
