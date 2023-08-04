package imple;

import entity.CourseDO;
import req.StudentRequest;
import res.TableDTO;
import service.Course;
import util.Dbase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class StuCourseImpl{

    public TableDTO retrieveStuCourse(StudentRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from stu_course");
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
                String course_id = rs.getString("course_id");
                String course_year = rs.getString("course_year");
                String semester = rs.getString("semester");
                int lesson_id = rs.getInt("lesson_id");
                int credits = rs.getInt("credits");

                oneRecord.addElement(ID);
                oneRecord.addElement(course_id);
                oneRecord.addElement(course_year);
                oneRecord.addElement(semester);
                oneRecord.addElement(lesson_id);
                oneRecord.addElement(credits);

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

}
