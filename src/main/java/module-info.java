module edu.sysagenda {
    // JavaFX módulos base
    requires javafx.controls;
    requires javafx.fxml;

    // JPA e Persistência
    requires jakarta.persistence;
    requires transitive java.sql;
    requires java.naming;

    // Módulos do Hibernate
    requires org.hibernate.orm.core;
    requires org.postgresql.jdbc;

    // Suporte para classes proxy e compilação
    requires java.management;
    requires java.compiler;

    // Exportar pacotes
    exports edu.sysagenda;
    exports edu.sysagenda.model.entity;
    exports edu.sysagenda.persistence;
    exports edu.sysagenda.controller;
    exports edu.sysagenda.utils;

    // Permitir reflexão
    opens edu.sysagenda to javafx.graphics;
    opens edu.sysagenda.controller to javafx.fxml;
    opens edu.sysagenda.model.entity to org.hibernate.orm.core;
    opens edu.sysagenda.utils to org.hibernate.orm.core;
    opens edu.sysagenda.persistence to org.hibernate.orm.core;
}