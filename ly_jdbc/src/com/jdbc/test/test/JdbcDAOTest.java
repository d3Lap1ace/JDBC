package com.jdbc.test.test;

import com.jdbc.test.dao.JdbcDAO;
import com.jdbc.test.javabean.Student;
import com.jdbc.test.javabean.Teacher;
import org.junit.Test;

import java.util.List;

public class JdbcDAOTest {

    @Test
    public void test2() {
        JdbcDAO<Student> studentJdbcDAO = new JdbcDAO<>(Student.class);
        List<Student> list = studentJdbcDAO.getList("select * from student");
        for (Student student : list) {
            System.out.println(student);
        }

        JdbcDAO<Teacher> teacherJdbcDAO = new JdbcDAO<>(Teacher.class);
        Teacher bean = teacherJdbcDAO.getBean("select * from teacher");
        System.out.println(bean);
    }

    @Test
    public void test1() {
        /*
        JdbcDAO dao = new JdbcDAO();
        List<Teacher> list = dao.getList("select * from teacher");
        for (Teacher teacher : list) {
            System.out.println(teacher);
        }
        System.out.println("*********************");
        Teacher bean = dao.getBean("select * from teacher where id = ?", 3);
        System.out.println(bean);

        dao.getValue("select avg(age) from teacher");

        int rows = dao.update("insert into teacher (name, age, gender, mobile) values (?, ?, ?, ?)", "王刚", 28, "男", "23423423");
        System.out.println(rows + " rows");
        */
    }

}
