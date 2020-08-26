module com.zip.code.search.modules.domain {
    exports com.zipcode.zipcodesearch.usecase.address.finder;
    exports com.zipcode.zipcodesearch.adapter;
    exports com.zipcode.zipcodesearch.model;

    requires static lombok;
    opens com.zipcode.zipcodesearch.usecase.address.chain;
    opens com.zipcode.zipcodesearch.usecase.address.finder;
    opens com.zipcode.zipcodesearch.model;
}