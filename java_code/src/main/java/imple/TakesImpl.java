package imple;

import entity.TakesDO;
import req.StudentRequest;
import res.TableDTO;
import service.Takes;
import util.Dbase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class TakesImpl implements Takes {

    @Override
    public TableDTO retrieveTakes(StudentRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from takes");
        if(request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())){
            sql.append(" where ID=").append(request.getSearchKey());
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

                int ID = rs.getInt("ID");
                String course_year = rs.getString("course_year");
                String semester = rs.getString("semester");
                String course_id = rs.getString("course_id");
                int lesson_id = rs.getInt("lesson_id");
                float GPA = rs.getFloat("GPA");

                oneRecord.addElement(ID);
                oneRecord.addElement(course_year);
                oneRecord.addElement(semester);
                oneRecord.addElement(course_id);
                oneRecord.addElement(lesson_id);
                oneRecord.addElement(GPA);

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

    public boolean add(TakesDO takesDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into takes ");
        sql.append("values(?,?,?,?,?,?);");

        Connection conn = null;
        PreparedStatement ps = null;
//        TableDTO returnTDO = new TableDTO();
//        Vector<Vector<Object>> data = new Vector<>();
        try{
            conn = Dbase.getConn();
            ps = conn.prepareStatement(sql.toString());

            ps.setInt(1,takesDO.getId());
            ps.setString(2,takesDO.getCourse_year());
            ps.setString(3,takesDO.getSemester());
            ps.setString(4,takesDO.getCourse_id());
            ps.setInt(5,takesDO.getLesson_id());
            ps.setDouble(6,takesDO.getGPA());

            return ps.executeUpdate() == 1;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Dbase.closePs(ps);
            Dbase.closeConn(conn);
        }
        return false;
    }

    public boolean update(TakesDO takesDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("update takes set lesson_id=?,GPA=? ");
        sql.append("where ID=? and course_year=? and semester=? and course_id=?;");

        Connection conn = null;
        PreparedStatement ps = null;
//        TableDTO returnTDO = new TableDTO();
//        Vector<Vector<Object>> data = new Vector<>();
        try{
            conn = Dbase.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,takesDO.getLesson_id());
            ps.setDouble(2,takesDO.getGPA());
            ps.setInt(3,takesDO.getId());
            ps.setString(4,takesDO.getCourse_year());
            ps.setString(5,takesDO.getSemester());
            ps.setString(6,takesDO.getCourse_id());


            return ps.executeUpdate() == 1;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Dbase.closePs(ps);
            Dbase.closeConn(conn);
        }
        return false;
    }

    public boolean delete(TakesDO takesDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from takes ");
        sql.append("where ID=? and course_year=? and semester=? and course_id=?;");

        Connection conn = null;
        PreparedStatement ps = null;
//        TableDTO returnTDO = new TableDTO();
//        Vector<Vector<Object>> data = new Vector<>();
        try{
            conn = Dbase.getConn();
            ps = conn.prepareStatement(sql.toString());

            ps.setInt(1,takesDO.getId());
            ps.setString(2,takesDO.getCourse_year());
            ps.setString(3,takesDO.getSemester());
            ps.setString(4,takesDO.getCourse_id());

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
