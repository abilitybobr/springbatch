package com.study.springbatch.basis.mapper;

import com.study.springbatch.core.db.annotation.SecondaryConnection;
import com.study.springbatch.basis.model.Member;

@SecondaryConnection
public interface StudyOneSecondaryMapper {
    Member selectSecondaryStudyOne(int id);
}
