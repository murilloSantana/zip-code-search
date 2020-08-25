module com.zip.code.search.modules.domain {
    exports com.zipcode.zipcodesearch.usecase;

    requires static lombok;

    opens com.zipcode.zipcodesearch.entity;
}