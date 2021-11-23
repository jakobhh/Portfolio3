package com.company;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class TrainView {
    GridPane startview;
    Label StartStationLbl;
    Label EndStationLbl;
    Button ExitBtn;
    Button FindStudentGrade;
    Button FindCourseGrade;
    Button FindStudentGPA;
    Button FindCourseGPA;
    ComboBox<String> StudentCB;
    ComboBox<String> CourseCB;
    TextArea textfield;

    ObservableList<String> stations;
    ObservableList<String> courses;

    public TrainView(){
        startview=new GridPane();
        CreateView();
    }

    private void CreateView(){
        startview.setMinSize(400,300);
        startview.setPadding( new Insets(10,10,10,10));
        startview.setHgap(5);
        startview.setVgap(5);

        StartStationLbl=new Label("Select start station:");
        startview.add(StartStationLbl,1,1);
        EndStationLbl= new Label("Select destination:");
        startview.add(EndStationLbl,1,2);

        ExitBtn=new Button("Exit");
        FindStudentGrade=new Button("Student Grades");
        FindCourseGrade=new Button("Course Grades");
        FindStudentGPA=new Button("Student GPA");
        FindCourseGPA=new Button("Course GPA");

        startview.add(FindStudentGPA,1,6);
        startview.add(FindCourseGPA,2,6);
        startview.add(FindStudentGrade,1,5);
        startview.add(FindCourseGrade,2,5);
        startview.add(ExitBtn,20,20);

        StudentCB =new ComboBox<>();
        startview.add(StudentCB,2,1);

        CourseCB = new ComboBox<>();
        startview.add(CourseCB,2,2);

        textfield=new TextArea();
        startview.add(textfield,1,7,15,10);




    }
    public void configure(){
        StudentCB.setItems(stations);
        StudentCB.getSelectionModel().selectFirst();

        CourseCB.setItems(courses);
        CourseCB.getSelectionModel().selectFirst();
    }

    public Parent asParent(){
        return  startview;
    }
}
