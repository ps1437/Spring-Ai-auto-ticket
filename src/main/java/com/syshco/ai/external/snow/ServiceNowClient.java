package com.syshco.ai.external.snow;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ServiceNowClient {

    private final List<SoftwareCatalogItem> catalog = List.of(
            new SoftwareCatalogItem("SW123", "Java 17 LTS", "Long Term Support version of Java"),
            new SoftwareCatalogItem("SW124", "Java 21 LTS", "Latest Long Term Support version of Java"),
            new SoftwareCatalogItem("SW125", "Node.js 20", "JavaScript runtime environment"),
            new SoftwareCatalogItem("SW126", "Node.js 18", "Previous stable version of Node.js"),
            new SoftwareCatalogItem("SW127", "VS Code", "Lightweight code editor"),
            new SoftwareCatalogItem("SW128", "IntelliJ IDEA", "Full-featured Java IDE"),
            new SoftwareCatalogItem("SW129", "Eclipse IDE", "Open-source IDE for Java and other languages"),
            new SoftwareCatalogItem("SW130", "Postman", "API development and testing tool"),
            new SoftwareCatalogItem("SW131", "Git", "Version control system"),
            new SoftwareCatalogItem("SW132", "Docker", "Containerization platform"),
            new SoftwareCatalogItem("SW133", "Kubernetes CLI", "Kubernetes command-line tool (kubectl)"),
            new SoftwareCatalogItem("SW134", "Python 3.12", "Latest version of Python programming language"),
            new SoftwareCatalogItem("SW135", "Go 1.21", "Go programming language"),
            new SoftwareCatalogItem("SW136", "Maven", "Java build automation tool"),
            new SoftwareCatalogItem("SW137", "Gradle", "Build automation tool for Java and other languages"),
            new SoftwareCatalogItem("SW138", "Slack", "Team communication and collaboration tool"),
            new SoftwareCatalogItem("SW139", "Zoom", "Video conferencing software"),
            new SoftwareCatalogItem("SW140", "Terraform", "Infrastructure as Code tool"),
            new SoftwareCatalogItem("SW141", "Ansible", "IT automation tool")
    );

    public List<SoftwareCatalogItem> fetchSoftwareCatalog() {
        return catalog;
    }


        public SoftwareTicket createTicket(String softwareName, String requestedBy) {
            int rand = new Random().nextInt(9000) + 1000;
            String ticketNumber = "RTM-" + rand;
            return new SoftwareTicket(ticketNumber, softwareName, requestedBy, "Created");
        }

}
