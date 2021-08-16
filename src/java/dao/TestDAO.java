/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

/**
 *
 * @author Admin
 */
public class TestDAO {
    //Function to save result test in database
    public void recordResultTest(String userName, float score) throws Exception {
        String sql = " insert into Result(userName,score)\n" +
        "values(?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            DecimalFormat value = new DecimalFormat("#.#");
            conn = new context.DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setFloat(2, Float.valueOf(value.format(score)));
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            new context.DBContext().closeConnection(rs, ps, conn);
        }
    }
}
