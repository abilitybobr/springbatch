package com.study.springbatch.domain.mapper;

import com.study.springbatch.core.db.annotation.SecondaryConnection;
import com.study.springbatch.domain.model.Member;

@SecondaryConnection
public interface StudyOneSecondaryMapper {
    Member selectSecondaryStudyOne(int id);
}
