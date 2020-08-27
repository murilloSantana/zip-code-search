module com.zip.code.search.modules.domain {
    exports com.zipcode.zipcodesearch.usecase.address.dataprovider;
    exports com.zipcode.zipcodesearch.usecase.address.dataprovider.adapter;
    exports com.zipcode.zipcodesearch.model;

    requires static lombok;
    requires logstash.logback.encoder;
    requires org.slf4j;

    opens com.zipcode.zipcodesearch.usecase.address.chain;
    opens com.zipcode.zipcodesearch.usecase.address.dataprovider;
    opens com.zipcode.zipcodesearch.model;
    opens com.zipcode.zipcodesearch.usecase.address.validator;
}