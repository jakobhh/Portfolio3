package com.company;
import java.sql.*;
import java.util.ArrayList;

public class StudentModel {
    Connection conn=null;
    String url=null;
    Statement stmt=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;

    StudentModel(String url){
        this.url=url;
    }

    public void connectToStudentData() throws SQLException {
        conn= DriverManager.getConnection(url);
    }

    public void CreateStatement() throws SQLException{
        this.stmt=conn.createStatement();
    }

    public ArrayList<String> SQLQueryStudentNames() throws SQLException{
        ArrayList<String> students=new ArrayList<>();
        String sql = "Select studentName from students;";
        rs=stmt.executeQuery(sql);
        while(rs!=null && rs.next()){
            String name=rs.getString(1);
            System.out.println(name);
            students.add(name);
        }
        return students;
    }
    public ArrayList<String> SQLQueryCourseNames() throws SQLException{
        ArrayList<String> courses=new ArrayList<>();
        String sql = "Select courseName from courses;";
        rs=stmt.executeQuery(sql);
        while(rs!=null && rs.next()){
            String name=rs.getString(1);
            System.out.println(name);
            courses.add(name);
        }
        return courses;
    }

    public ArrayList<StudentInfo> QueryStudentGPA(String sName) throws SQLException{
        ArrayList<StudentInfo> studentInfos=new ArrayList<>();
        String sql="SELECT S.studentName, AVG(SC.grade) as sGPA "
                  +"FROM StudentCourse as SC "
                  +"NATURAL JOIN Students as S "
                  +"WHERE S.studentName = ? "
                  +"GROUP BY S.studentName;";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,sName);
        rs=pstmt.executeQuery();
        while (rs!=null && rs.next()){
            String studentQ = rs.getString("studentName");
            Double gradeQ = rs.getDouble("sGPA");
            StudentInfo si = new StudentInfo(studentQ, null, gradeQ);
            studentInfos.add(si);
        }
        return studentInfos;
    }

    public ArrayList<CourseInfo> QueryCourseGPA(String cName) throws SQLException{
        ArrayList<CourseInfo> courseInfos=new ArrayList<>();
        String sql="SELECT C.courseName, AVG(SC.grade) as cGPA "
                +"FROM StudentCourse as SC "
                +"NATURAL JOIN Courses as C "
                +"WHERE C.courseName = ? "
                +"GROUP BY C.courseName;";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,cName);
        rs=pstmt.executeQuery();
        while (rs!=null && rs.next()){
            String C_courseQ = rs.getString("courseName");
            Double C_gradeQ = rs.getDouble("cGPA");
            CourseInfo ci = new CourseInfo(null, C_courseQ, C_gradeQ);
            courseInfos.add(ci);
        }
        return courseInfos;
    }


    public ArrayList<StudentInfo> QueryStudentGrades(String sName) throws SQLException{
        ArrayList<StudentInfo> studentInfos=new ArrayList<>();
        String sql="SELECT S.studentName, C.courseName, SC.grade "
                  +"FROM Students as S "
                  +"NATURAL JOIN StudentCourse as SC "
                  +"NATURAL JOIN Courses as C "
                  +"WHERE S.studentName = ?;";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,sName);
        rs=pstmt.executeQuery();
        while (rs!=null && rs.next()){
            String studentQ = rs.getString("studentName");
            String courseQ = rs.getString("courseName");
            Double gradeQ = rs.getDouble("grade");
            System.out.println("Student name: " + studentQ + " Course: " + courseQ + " Grade: " + gradeQ);
            StudentInfo si = new StudentInfo(studentQ, courseQ, gradeQ);
            studentInfos.add(si);
        }
        return studentInfos;
    }

    public ArrayList<CourseInfo> QueryCourseGrades(String cName) throws SQLException{
        ArrayList<CourseInfo> courseInfos=new ArrayList<>();
        String sql="SELECT C.courseName, S.studentName, SC.grade "
                +"FROM Students as S "
                +"NATURAL JOIN StudentCourse as SC "
                +"NATURAL JOIN Courses as C "
                +"WHERE C.courseName = ?;";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,cName);
        rs=pstmt.executeQuery();
        while (rs!=null && rs.next()){
            String C_studentQ = rs.getString("studentName");
            String C_courseQ = rs.getString("courseName");
            Double C_gradeQ = rs.getDouble("grade");
            System.out.println("Course: " + C_studentQ + " Student name: " + C_courseQ + " Grade: " + C_gradeQ);
            CourseInfo ci = new CourseInfo(C_studentQ, C_courseQ, C_gradeQ);
            courseInfos.add(ci);
        }
        return courseInfos;

    }

}
class StudentInfo {
    String studentQuery = null;
    String courseQuery = null;
    Double gradeQuery = null;

    StudentInfo(String studentQ, String courseQ, Double gradeQ) {
        this.studentQuery = studentQ;
        this.courseQuery = courseQ;
        this.gradeQuery = gradeQ;
    }
}

class CourseInfo {
    String C_studentQuery = null;
    String C_courseQuery = null;
    Double C_gradeQuery = null;

    CourseInfo(String C_studentQ, String C_courseQ, Double C_gradeQ) {
        this.C_studentQuery = C_studentQ;
        this.C_courseQuery = C_courseQ;
        this.C_gradeQuery = C_gradeQ;
    }
}
