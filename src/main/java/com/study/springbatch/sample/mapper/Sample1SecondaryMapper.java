package com.study.springbatch.sample.mapper;

import com.study.springbatch.core.db.annotation.SecondaryConnection;
import com.study.springbatch.sample.model.Member;

@SecondaryConnection
public interface Sample1SecondaryMapper {
    Member selectSecondarySample(int id);
}
