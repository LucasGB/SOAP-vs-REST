package com.soap.server;

import java.util.Date;
import com.dao.MysqlConn;
import javax.jws.WebService;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.jws.WebParam;

@WebService(endpointInterface = "com.soap.server.Tasks")

public class TasksImpl implements Tasks {

    public void addTask(int priority, String task, String details, Date dueDate, int done, int user) {
        MysqlConn c = new MysqlConn();
        Connection conn = c.getConnection();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String text = df.format(dueDate);
        
        String query = "insert into Tasks(priority, task, details, dueDate, done, user) values (" + priority + ", '" + task + "', '" + details + "', '" + text + "', " + done + ", " + user + ")";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(int completed, String task, Date dueDate, int userID) {
        MysqlConn c = new MysqlConn();
        Connection conn = c.getConnection();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String text = df.format(dueDate);
        
        String query = "delete from Tasks WHERE "
                            + "done = " + completed + " and "
                            + "task = '" + task + "' and "
                            + "dueDate = '" + text + "' and "
                            + "user = " + userID + ";";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTask(int old_completed, String old_task, String old_dueDate, int priority, String task, String details, Date dueDate, int completed, int userID) {
        MysqlConn c = new MysqlConn();
        Connection conn = c.getConnection();
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String text = df.format(dueDate);
        
        String query = "update Tasks "
                        + "SET "
                            + "priority = " + priority + ", "
                            + "task = '" + task + "', "
                            + "details = '" + details + "', "
                            + "dueDate = '" + text + "', "
                            + "done = " + completed + " "
                        + "WHERE "
                            + "done = " + old_completed + " and "
                            + "task = '" + old_task + "' and "
                            + "dueDate = '" + old_dueDate + "' and "
                            + "user = " + userID + ";";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object[] getAllEntries(@WebParam(name="userID")int userID) {
        MysqlConn c = new MysqlConn();
        Connection conn = c.getConnection();

        String query = "SELECT * FROM Tasks WHERE user = " + userID + ";";

        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int rowcount = 0;
            if (rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }
            Object[] entries = new Object[rowcount];

            int i = 0;
            while (rs.next()) {
                entries[i] = new Object[]{
                    rs.getInt("priority"),
                    rs.getString("task"),
                    rs.getString("details"),
                    rs.getString("dueDate"),
                    rs.getInt("done")
                };
                i++;
            }
            

            conn.close();
            return entries;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}
