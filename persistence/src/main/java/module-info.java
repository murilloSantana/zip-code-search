module com.zip.code.search.modules.persistence {
    exports com.zipcode.zipcodesearch.repository;
    exports com.zipcode.zipcodesearch.entity;

    requires com.zip.code.search.modules.domain;
    requires spring.data.jpa;
    requires java.persistence;
    requires spring.context;
    requires spring.beans;
    requires java.annotation;
}