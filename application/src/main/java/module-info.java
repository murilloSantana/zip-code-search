module com.zip.code.search.modules.application {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires com.zip.code.search.modules.domain;
    requires spring.beans;
    requires spring.context;


    opens com.zipcode.zipcodesearch;
}