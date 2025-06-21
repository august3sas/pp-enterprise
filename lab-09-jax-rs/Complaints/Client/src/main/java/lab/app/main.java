package lab.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;

public class main {
    private static void sep(){
        System.out.println("");
        System.out.println("==============================");
        System.out.println("");
    }
    public static void main(String[] args){
        Client client = ClientBuilder.newClient();
        String status = client.target("http://localhost:8080/Server-1.0-SNAPSHOT/" +
                        "api/complaints/153/status")
                        .request(MediaType.TEXT_PLAIN)
                        .get(String.class);

        System.out.println("Status: " + status);

        String allComplaints = client.target("http://localhost:8080/Server-1.0-SNAPSHOT/" +
                        "api/complaints")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        sep();
        System.out.println("All complaints: "+ allComplaints);

        String openComplaint = client.target("http://localhost:8080/Server-1.0-SNAPSHOT/" +
                        "api/complaints/153")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        sep();
        System.out.println("Sample open complaint: "+ openComplaint);

        String updateComplaint = openComplaint.replace("\"status\":\"open\"", "\"status\":\"closed\"");
        client.target("http://localhost:8080/Server-1.0-SNAPSHOT/" +
        "api/complaints/153").request(MediaType.APPLICATION_JSON)
                        .put(Entity.entity(updateComplaint, MediaType.APPLICATION_JSON));

        String allOpenComplaints = client.target("http://localhost:8080/Server-1.0-SNAPSHOT/" +
                        "api/complaints?status=open")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        sep();
        System.out.println("All open complaints: "+ allOpenComplaints );
        client.close();
    }
}
