package com.study.springbatch.sample.mapper;

import com.study.springbatch.core.db.annotation.PrimaryConnection;
import com.study.springbatch.sample.model.Member;

@PrimaryConnection
public interface Sample1PrimaryMapper {
    void savePrimarySample(Member member);
}
