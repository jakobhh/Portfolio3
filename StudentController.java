package com.company;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController {
    StudentView view;
    StudentModel model;

    public StudentController(StudentView v, StudentModel m) throws SQLException {
        this.view=v;
        this.model=m;
        this.view.ExitBtn.setOnAction(e-> Platform.exit());
        this.model.connectToStudentData();
        this.model.CreateStatement();
        this.view.students=getStudents();
        this.view.courses=getCourses();
        this.view.FindStudentGrade.setOnAction(e->HandlerPrintStudentGrades(view.StudentCB.getValue(), view.textfield));
        this.view.FindCourseGrade.setOnAction(e->HandlerPrintCourseGrades(view.CourseCB.getValue(), view.textfield));
        this.view.FindStudentGPA.setOnAction(e->HandlerPrintStudentGPA(view.StudentCB.getValue(), view.textfield));
        this.view.FindCourseGPA.setOnAction(e->HandlerPrintCourseGPA(view.CourseCB.getValue(), view.textfield));
        view.configure();
    }

    public void HandlerPrintStudentGrades(String sName, TextArea txtArea){
        txtArea.clear();
        txtArea.appendText(sName + ":\n");
        try {
            ArrayList<StudentInfo> sGrade = model.QueryStudentGrades(sName);
            for (int i = 0; i < sGrade.size(); i++) {
                txtArea.appendText(i+1 + ". Course: " + sGrade.get(i).courseQuery + " Grade: " + sGrade.get(i).gradeQuery + "\n");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void HandlerPrintStudentGPA(String sNameGPA, TextArea txtArea){
        txtArea.clear();
        txtArea.appendText(sNameGPA + ":\n");
        try {
            ArrayList<StudentInfo> sGPA = model.QueryStudentGPA(sNameGPA);
            System.out.println(sGPA.size());
            for (int i = 0; i < sGPA.size(); i++) {
                txtArea.appendText("GPA: " + sGPA.get(i).gradeQuery);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void HandlerPrintCourseGPA(String cNameGPA, TextArea txtArea){
        txtArea.clear();
        txtArea.appendText(cNameGPA + ":\n");
        try {
            ArrayList<CourseInfo> cGPA = model.QueryCourseGPA(cNameGPA);
            System.out.println(cGPA.size());
            for (int i = 0; i < cGPA.size(); i++) {
                txtArea.appendText("GPA: " + cGPA.get(i).C_gradeQuery);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void HandlerPrintCourseGrades(String cName, TextArea txtArea){
        txtArea.clear();
        txtArea.appendText(cName + ":\n");
        try {
            ArrayList<CourseInfo> cGrade = model.QueryCourseGrades(cName);
            for (int i = 0; i < cGrade.size(); i++) {
                txtArea.appendText(i+1 + ". Student Name: " + cGrade.get(i).C_studentQuery + " Grade: " + cGrade.get(i).C_gradeQuery + "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<String> getStudents() throws SQLException {
        ArrayList<String> students= model.SQLQueryStudentNames();
        ObservableList<String> studentnames= FXCollections.observableArrayList(students);
        return studentnames;
    }
    public ObservableList<String> getCourses() throws SQLException {
        ArrayList<String> courses= model.SQLQueryCourseNames();
        ObservableList<String> coursenames= FXCollections.observableArrayList(courses);
        return coursenames;
    }
}
