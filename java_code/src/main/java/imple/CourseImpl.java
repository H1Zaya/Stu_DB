package imple;

import entity.CourseDO;
import entity.StuDO;
import req.StudentRequest;
import res.TableDTO;
import service.Course;
import service.Student;
import util.Dbase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class CourseImpl implements Course {

    @Override
    public TableDTO retrieveCourse(StudentRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from course");
        if(request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())){
            sql.append(" where course_name like '%").append(request.getSearchKey()).append("%'");
        }
        sql.append(";");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO returnTDO = new TableDTO();
        Vector<Vector<Object>> data = new Vector<>();
        try{
            conn = Dbase.getConn();
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while(rs.next()){
                Vector<Object> oneRecord = new Vector<>();

                String course_id = rs.getString("course_id");
                String course_name = rs.getString("course_name");
                String course_year = rs.getString("course_year");
                String semester = rs.getString("semester");
                int credits = rs.getInt("credits");
                String exam = rs.getString("exam");
                String department = rs.getString("department");
                oneRecord.addElement(course_id);
                oneRecord.addElement(course_name);
                oneRecord.addElement(course_year);
                oneRecord.addElement(semester);
                oneRecord.addElement(credits);
                oneRecord.addElement(exam);
                oneRecord.addElement(department);
                data.addElement(oneRecord);
            }


            returnTDO.setData(data);
            return returnTDO;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Dbase.closeRs(rs);
            Dbase.closePs(ps);
            Dbase.closeConn(conn);
        }
        return null;
    }


    public boolean add(CourseDO courseDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into course ");
        sql.append("values(?,?,?,?,?,?,?);");

        Connection conn = null;
        PreparedStatement ps = null;
//        TableDTO returnTDO = new TableDTO();
//        Vector<Vector<Object>> data = new Vector<>();
        try{
            conn = Dbase.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,courseDO.getCourse_id());
            ps.setString(2,courseDO.getCourse_name());
            ps.setString(3,courseDO.getCourse_year());
            ps.setString(4,courseDO.getSemester());
            ps.setInt(5,courseDO.getCredits());
            ps.setString(6,courseDO.getExam());
            ps.setString(7,courseDO.getDepartment());
            return ps.executeUpdate() == 1;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Dbase.closePs(ps);
            Dbase.closeConn(conn);
        }
        return false;
    }

    public boolean update(CourseDO courseDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("update course set course_name=?,credits=?,exam=?,department=? ");
        sql.append("where course_id=? and course_year=? and semester=?;");

        Connection conn = null;
        PreparedStatement ps = null;
//        TableDTO returnTDO = new TableDTO();
//        Vector<Vector<Object>> data = new Vector<>();
        try{
            conn = Dbase.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(5,courseDO.getCourse_id());
            ps.setString(1,courseDO.getCourse_name());
            ps.setString(6,courseDO.getCourse_year());
            ps.setString(7,courseDO.getSemester());
            ps.setInt(2,courseDO.getCredits());
            ps.setString(3,courseDO.getExam());
            ps.setString(4,courseDO.getDepartment());
            return ps.executeUpdate() == 1;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Dbase.closePs(ps);
            Dbase.closeConn(conn);
        }
        return false;
    }

    public boolean delete(CourseDO courseDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from course ");
        sql.append("where course_id=? and course_year=? and semester=?;");

        Connection conn = null;
        PreparedStatement ps = null;
//        TableDTO returnTDO = new TableDTO();
//        Vector<Vector<Object>> data = new Vector<>();
        try{
            conn = Dbase.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,courseDO.getCourse_id());
            ps.setString(2,courseDO.getCourse_year());
            ps.setString(3,courseDO.getSemester());
            return ps.executeUpdate() == 1;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Dbase.closePs(ps);
            Dbase.closeConn(conn);
        }
        return false;
    }


}
