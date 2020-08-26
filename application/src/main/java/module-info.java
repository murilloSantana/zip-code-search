module com.zip.code.search.modules.application {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires com.zip.code.search.modules.domain;
    requires spring.beans;
    requires spring.context;
    requires java.annotation;
    requires spring.data.jpa;
    requires java.persistence;
    requires static lombok;

    opens com.zipcode.zipcodesearch;
}