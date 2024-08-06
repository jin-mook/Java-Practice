package com.hello.batch.job;

import org.springframework.batch.item.data.AbstractPaginatedDataItemReader;

import java.util.Iterator;

public class Reader extends AbstractPaginatedDataItemReader<String> {


    @Override
    protected Iterator<String> doPageRead() {
        return null;
    }
}
