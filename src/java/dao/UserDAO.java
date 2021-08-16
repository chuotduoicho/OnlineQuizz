/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;

/**
 *
 * @author Admin
 */
public class UserDAO {

    // Function to check username exist before register
    public boolean checkUserNameExist(String userName) throws Exception {
        boolean status = false;
        String sql = "select top 1 userName from [Users]\n"
                + "where userName=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new context.DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next()) {
                status = true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            new DBContext().closeConnection(rs, ps, conn);
        }
        return status;
    }
    
    // Fuction to register new user. Role = 1 for teacher, role = 0 for student
    public void registerNew(String userName,String password,String mail, int role) throws Exception {
        String sql = "insert into [Users] (userName,[password],Email,RoleUser)\n" +
        "values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new context.DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setString(3, mail);
            ps.setInt(4, role);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            new context.DBContext().closeConnection(rs, ps, conn);
        }
    }
    
    // Funtion to check login. If true user name and password return users object. If wrong pass or user name, return null
    public User login(String userName, String password) throws Exception {
        User result = null;
        String sql = "select top 1 * from [Users]\n"
                + "where userName=? and [password] = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new context.DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString("Email");
                int role = rs.getInt("RoleUser");
                result = new User(userName, email, role);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            new DBContext().closeConnection(rs, ps, conn);
        }
        return result;
    }
}
