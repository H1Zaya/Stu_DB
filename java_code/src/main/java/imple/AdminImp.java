package imple;

import entity.AdminDO;
import service.Admin;
import util.Dbase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminImp implements Admin {
    @Override
    public boolean validAdmin(AdminDO adminDO) {
        String userName = adminDO.getUserName();
        String pwdParam = adminDO.getPwd();
        if(userName==null||pwdParam==null)
            return false;

        String sql = "select pwd from admin where userName = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try{
            conn = Dbase.getConn();
            if(conn==null)
                return false;
            ps = conn.prepareStatement(sql);
            ps.setString(1, adminDO.getUserName());
            resultSet = ps.executeQuery();
            while(resultSet.next()){
                String pwd = resultSet.getString(1);
                if(adminDO.getPwd().equals(pwd))
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
}
