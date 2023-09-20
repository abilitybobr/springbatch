package com.study.springbatch.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "샘플 Primary DB 예제")
public class StudyOnePrimary {

    @ApiModelProperty(notes = "샘플 상품번호")
    private String itemNo;

    @ApiModelProperty(notes = "샘플 상품명")
    private String itemNm1;
}
