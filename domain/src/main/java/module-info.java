module com.zip.code.search.modules.domain {
    exports com.zipcode.zipcodesearch.usecase.address.finder;
    requires com.zip.code.search.modules.persistence;
    requires static lombok;
    opens com.zipcode.zipcodesearch.usecase.address.chain;
    opens com.zipcode.zipcodesearch.usecase.address.finder;
    opens com.zipcode.zipcodesearch.model;
}