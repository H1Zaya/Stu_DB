package imple;

import entity.AdminDO;
import entity.StuDO;
import req.StudentRequest;
import res.TableDTO;
import service.Student;
import util.Dbase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class StuImpl implements Student {

    public boolean validStu(StuDO stuDO) {
        String userName = stuDO.getStudentName();
        String pwdParam = stuDO.getPwd();
        if(userName==null||pwdParam==null)
            return false;

        String sql = "select pwd from student where student_name = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try{
            conn = Dbase.getConn();
            if(conn==null)
                return false;
            ps = conn.prepareStatement(sql);
            ps.setString(1, stuDO.getStudentName());
            resultSet = ps.executeQuery();
            while(resultSet.next()){
                String pwd = resultSet.getString(1);
                if(stuDO.getPwd().equals(pwd))
                    return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Dbase.closeRs(resultSet);
            Dbase.closePs(ps);
            Dbase.closeConn(conn);
        }

        return false;
    }

    @Override
    public TableDTO retrieveStudents(StudentRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from student ");
        if(request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())){
            sql.append(" where ID=").append(request.getSearchKey()).append(";");
        }
//        sql.append("oder by ID desc");
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
                String student_name = rs.getString("student_name");
                float tot_GPA = rs.getFloat("tot_GPA");
                int tot_credits = rs.getInt("tot_credits");
                oneRecord.addElement(ID);
                oneRecord.addElement(student_name);
                oneRecord.addElement(tot_GPA);
                oneRecord.addElement(tot_credits);
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

    @Override
    public boolean add(StuDO stuDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into student ");
        sql.append("values(?,?,?,?,?);");

        Connection conn = null;
        PreparedStatement ps = null;
//        TableDTO returnTDO = new TableDTO();
//        Vector<Vector<Object>> data = new Vector<>();
        try{
            conn = Dbase.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,stuDO.getId());
            ps.setString(2,stuDO.getStudentName());
            ps.setDouble(3,stuDO.getGpa());
            ps.setInt(4,stuDO.getCredits());
            ps.setString(5,stuDO.getPwd());
            return ps.executeUpdate() == 1;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Dbase.closePs(ps);
            Dbase.closeConn(conn);
        }
        return false;
    }

    public boolean update(StuDO stuDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("update student set student_name=?,tot_GPA=?,tot_credits=?,pwd=? ");
        sql.append("where ID=?;");

        Connection conn = null;
        PreparedStatement ps = null;
//        TableDTO returnTDO = new TableDTO();
//        Vector<Vector<Object>> data = new Vector<>();
        try{
            conn = Dbase.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(5,stuDO.getId());
            ps.setString(1,stuDO.getStudentName());
            ps.setDouble(2,stuDO.getGpa());
            ps.setInt(3,stuDO.getCredits());
            ps.setString(4,stuDO.getPwd());
            return ps.executeUpdate() == 1;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Dbase.closePs(ps);
            Dbase.closeConn(conn);
        }
        return false;
    }

    public boolean delete(StuDO stuDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from student ");
        sql.append("where ID=?;");

        Connection conn = null;
        PreparedStatement ps = null;
//        TableDTO returnTDO = new TableDTO();
//        Vector<Vector<Object>> data = new Vector<>();
        try{
            conn = Dbase.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,stuDO.getId());
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
