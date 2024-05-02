package com.java.jdbc.javabean;

/**
 * mysql> desc teacher;
 * +--------+-----------------+------+-----+---------+----------------+
 * | Field  | Type            | Null | Key | Default | Extra          |
 * +--------+-----------------+------+-----+---------+----------------+
 * | id     | int             | NO   | PRI | NULL    | auto_increment |
 * | name   | varchar(20)     | NO   |     | NULL    |                |
 * | age    | int             | YES  |     | NULL    |                |
 * | gender | enum('男','女') | YES  |     | 男      |                |
 * | mobile | varchar(15)     | YES  | UNI | NULL    |                |
 * +--------+-----------------+------+-----+---------+----------------+
 * 5 rows in set (0.03 sec)
 */
public class Teacher {

    private int id;
    private String name;
    private int age;
    private String gender;
    private String mobile;

    public Teacher() {}

    public Teacher(int id, String name, int age, String gender, String mobile) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.mobile = mobile;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
