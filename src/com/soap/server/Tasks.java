package com.soap.server;

import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC) 
public interface Tasks {
    @WebMethod public void addTask(int priority, String task, String details, Date dueDate, int done, int user);
    @WebMethod public void updateTask(int iduser, String nome);
    @WebMethod public void deleteTask(int iduser);
    @WebMethod public Object[] getAllEntries(int userID);
}
