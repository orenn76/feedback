package com.ninyo.common.crudcore.responses;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonPropertyOrder({ "metaData", "data" })
public class ListResponse<DTO> extends DtoResponse<DTO> {

    MetaData metaData;

    public ListResponse() {
    }

    public ListResponse(List<DTO> data) {
        int size = data.size();
        this.metaData = new MetaData(size);
        this.data = data;
    }

    public ListResponse(long count, List<DTO> data) {
        super();
        this.metaData = new MetaData(count);
        this.data = data;
    }

}
