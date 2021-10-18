module com.oop.moneymanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires java.sql;
    requires com.jfoenix;
    opens com.oop.moneymanager to javafx.fxml;
    opens com.oop.moneymanager.view to javafx.fxml;
    opens com.oop.moneymanager.model to org.hibernate.orm.core;
    opens com.oop.moneymanager.utils to org.hibernate.orm.core;
    exports com.oop.moneymanager;
}