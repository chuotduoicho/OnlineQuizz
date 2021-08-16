/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author Admin
 */
public class DBContext {
    static InitialContext initCxt;
    static Context envirCxt;
    static String serverName;
    static String port;
    static String username;
    static String password;
    static String dbName;

    static {
        try {
            initCxt = new InitialContext();
            envirCxt = (Context) initCxt.lookup("java:comp/env");
            // get value of parameter in context file
            serverName = (String) envirCxt.lookup("severName");
            port = (String) envirCxt.lookup("port");
            username = (String) envirCxt.lookup("username");
            password = (String) envirCxt.lookup("password");
            dbName = (String) envirCxt.lookup("dbName");
//            System.out.println("read");
        } catch (Exception ex) {
            try {
                throw ex;
            } catch (Exception ex1) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    
    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection("jdbc:sqlserver://" + serverName + ":" + port + ";databaseName=" + dbName, username, password);
    }

    // Close connection
    public static void closeConnection(ResultSet rs, PreparedStatement ps, Connection con) throws SQLException { 
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
}
