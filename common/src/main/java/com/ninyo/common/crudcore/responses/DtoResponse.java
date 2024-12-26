package com.ninyo.common.crudcore.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Setter
@Getter
public class DtoResponse<DTO> {

    List<DTO> data;

    public DtoResponse() {
    }

    public DtoResponse(DTO dto) {
        if (dto != null) {
            this.data = Collections.singletonList(dto);
        }
    }

}
