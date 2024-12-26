package com.ninyo.common.crudcore.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MetaData {

    //The total count of items
    long count;

    public MetaData() {
        this.count = 0;
    }

    public MetaData(long count) {
        this.count = count;
    }

}
