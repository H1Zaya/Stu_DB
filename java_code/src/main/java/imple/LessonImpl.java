package imple;

import entity.CourseDO;
import entity.LessonDO;
import req.StudentRequest;
import res.TableDTO;
import service.Course;
import service.Lesson;
import util.Dbase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class LessonImpl implements Lesson {

    @Override
    public TableDTO retrieveLesson(StudentRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from lesson");
        if(request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())){
            sql.append(" where lesson_id=").append(request.getSearchKey());
        }
//        sql.append("oder by ID desc");
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

                String course_year = rs.getString("course_year");
                String semester = rs.getString("semester");
                int lesson_id = rs.getInt("lesson_id");
                int room_no = rs.getInt("room_no");
                String building = rs.getString("building");
                String teacher = rs.getString("teacher");
                String days = rs.getString("days");

                oneRecord.addElement(course_id);
                oneRecord.addElement(course_year);
                oneRecord.addElement(semester);
                oneRecord.addElement(lesson_id);
                oneRecord.addElement(teacher);
                oneRecord.addElement(days);
                oneRecord.addElement(building);
                oneRecord.addElement(room_no);
                data.addElement(oneRecord);
            }
//            sql.setLength(0);
//            sql.append("select count(*) from student ");
//            if(request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())){
//                sql.append(" where name like '%").append(request.getSearchKey()).append("%'");
//            }
//            ps = conn.prepareStatement()

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

    public boolean add(LessonDO lessonDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into lesson ");
        sql.append("values(?,?,?,?,?,?,?,?);");

        Connection conn = null;
        PreparedStatement ps = null;
//        TableDTO returnTDO = new TableDTO();
//        Vector<Vector<Object>> data = new Vector<>();
        try{
            conn = Dbase.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(5,lessonDO.getTeacher());
            ps.setString(7,lessonDO.getBuilding());
            ps.setInt(8,lessonDO.getRoom_no());
            ps.setString(2,lessonDO.getCourse_id());
            ps.setString(3,lessonDO.getCourse_year());
            ps.setString(4,lessonDO.getSemester());
            ps.setInt(1,lessonDO.getLesson_id());
            ps.setString(6,lessonDO.getDays());
            return ps.executeUpdate() == 1;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Dbase.closePs(ps);
            Dbase.closeConn(conn);
        }
        return false;
    }

    public boolean update(LessonDO lessonDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("update lesson set teacher=?,building=?,room_no=?,days=?");
        sql.append("where course_id=? and course_year=? and semester=? and lesson_id=?;");

        Connection conn = null;
        PreparedStatement ps = null;
//        TableDTO returnTDO = new TableDTO();
//        Vector<Vector<Object>> data = new Vector<>();
        try{
            conn = Dbase.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,lessonDO.getTeacher());
            ps.setString(2,lessonDO.getBuilding());
            ps.setInt(3,lessonDO.getRoom_no());
            ps.setString(5,lessonDO.getCourse_id());
            ps.setString(6,lessonDO.getCourse_year());
            ps.setString(7,lessonDO.getSemester());
            ps.setInt(8,lessonDO.getLesson_id());
            ps.setString(4,lessonDO.getDays());
            return ps.executeUpdate() == 1;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Dbase.closePs(ps);
            Dbase.closeConn(conn);
        }
        return false;
    }

    public boolean delete(LessonDO lessonDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from lesson ");
        sql.append("where course_id=? and course_year=? and semester=? and lesson_id=?;");

        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = Dbase.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,lessonDO.getCourse_id());
            ps.setString(2,lessonDO.getCourse_year());
            ps.setString(3,lessonDO.getSemester());
            ps.setInt(4,lessonDO.getLesson_id());
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
