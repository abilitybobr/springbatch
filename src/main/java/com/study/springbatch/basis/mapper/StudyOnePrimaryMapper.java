package com.study.springbatch.basis.mapper;

import com.study.springbatch.core.db.annotation.PrimaryConnection;
import com.study.springbatch.basis.model.Member;

@PrimaryConnection
public interface StudyOnePrimaryMapper {
    void savePrimaryStudyOne(Member member);
}
