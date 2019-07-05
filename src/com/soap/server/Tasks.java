package com.soap.server;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC) 
public interface Tasks {
    @WebMethod void addTask(int iduser, String nome);
    @WebMethod void updateTask(int iduser, String nome);
    @WebMethod public void deleteTask(int iduser);
    @WebMethod public Object[] getAllEntries(int userID);
}
