package com.soap.server;

import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC) 
public interface Tasks {
    @WebMethod public void addTask(int priority, String task, String details, Date dueDate, int done, int user);
    @WebMethod public void updateTask(int old_completed, String old_task, String old_dueDate, int priority, String task, String details, Date dueDate, int completed, int userID);
    @WebMethod public void deleteTask(int completed, String task, Date dueDate, int userID);
    @WebMethod public Object[] getAllEntries(@WebParam(name="userID")int userID);
}
