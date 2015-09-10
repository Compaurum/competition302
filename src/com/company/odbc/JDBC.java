package com.company.odbc;

import com.extendedsystems.jdbc.advantage.ADSException;

import java.sql.*;
/**
 * Created by compaurum on 09.07.2015.
 */
public class JDBC {

    public static void main(String [] args) throws SQLException, ClassNotFoundException {


        Class.forName("com.extendedsystems.jdbc.advantage.ADSDriver");

        // Connection conn = DriverManager.getConnection("jdbc:extendedsystems:advantage://server1:6262/test/data;user=user1;password=up1");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:extendedsystems:advantage://backupsrv:6262;catalog=\\\\backupsrv\\d$\\CopyADS\\06062015\\DataBase\\globino.add");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        if (conn == null) {
            System.out.println("Нет соединения с БД!");
            System.exit(0);
        }
        Statement stmt = null;
        ResultSet rs = null;
        try {

            stmt = conn.createStatement();
            Thread.sleep(5000);
            rs = stmt.executeQuery("SELECT name FROM client");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("i am trying");
            System.out.println(rs.first());
            System.out.println(rs.isFirst());
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            System.out.println("YES");
        }catch (NullPointerException e){
            System.out.println("NULL");
        }
        finally {
            stmt.close();
            conn.close();
        }


        /**
         * stmt.close();
         * При закрытии Statement автоматически закрываются
         * все связанные с ним открытые объекты ResultSet
         */

    }
}
