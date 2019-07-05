package com.soap.client;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import com.soap.server.Tasks;
import com.soap.server.Tasks;

class UsersClient {

    public static void main(String args[ ]) throws Exception {

        URL url = new URL("http://localhost:9876/?wsdl");

        QName qname = new QName("http://server.soap.com/", "UsersImplService");

        // Create, in effect, a factory for the service.
        Service service = Service.create(url, qname);
        // Extract the endpoint interface, the service "port".

        Tasks eif = service.getPort(Tasks.class);
        //eif.insertUser(4, "Yasmin");
        eif.updateTask(4, "Moraski");
        eif.deleteTask(4);
  
   }

}