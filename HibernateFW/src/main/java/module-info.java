module com.example.hibernatefw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;
    requires mysql.connector.java;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens com.example.hibernatefw to javafx.fxml;
    exports com.example.hibernatefw;
    exports com.example.hibernatefw.model;
    opens com.example.hibernatefw.model;
    exports com.example.hibernatefw.controllers;
    opens com.example.hibernatefw.controllers to javafx.fxml;
    exports com.example.hibernatefw.dao;
}