package javaBasic;

public class Topic_01_Data_type {
    static  int studentNumber;
    static boolean statusValue;
    String studentName = "Automation FC";
    public static void main(String[] args) {

        System.out.println(studentNumber);
        System.out.println(statusValue);
    }

    //Getter (Hàm lấy dữ liệu)
    public String getStudentName(){
        return this.studentName;
    }
    //Setter (Hàm gán dữ liệu)
    public void setStudentNumber(String stdName) {
        this.studentName = stdName;
    }
}
