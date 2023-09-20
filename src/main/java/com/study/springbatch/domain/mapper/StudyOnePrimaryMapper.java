package com.study.springbatch.domain.mapper;

import com.study.springbatch.core.db.annotation.PrimaryConnection;
import com.study.springbatch.domain.model.Member;

@PrimaryConnection
public interface StudyOnePrimaryMapper {
    void savePrimaryStudyOne(Member member);
}
