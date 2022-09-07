package com.example.sunlab;

public class SUNLabRecordModel {
    int StudentID;
    String insideDate;
    String outsideDate;
    String insideTime;
    String outsideTime;

    public SUNLabRecordModel(int StudentID, String insideDate, String outsideDate, String insideTime, String outsideTime) {
        this.StudentID = StudentID;
        this.insideDate = insideDate;
        this.outsideDate = outsideDate;
        this.insideTime = insideTime;
        this.outsideTime = outsideTime;
    }

    public int getStudentID() {
        return StudentID;
    }

    public String getInsideDate() {
        return insideDate;
    }

    public String getOutsideDate() {
        return outsideDate;
    }

    public String getInsideTime() {
        return insideTime;
    }

    public String getOutsideTime() {
        return outsideTime;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public void setInsideDate(String insideDate) {
        this.insideDate = insideDate;
    }

    public void setOutsideDate(String outsideDate) {
        this.outsideDate = outsideDate;
    }

    public void setInsideTime(String insideTime) {
        this.insideTime = insideTime;
    }

    public void setOutsideTime(String outsideTime) {
        this.outsideTime = outsideTime;
    }
}
