package com.study.springbatch.basis.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.springbatch.basis.mapper.StudyOnePrimaryMapper;
import com.study.springbatch.basis.mapper.StudyOneSecondaryMapper;
import com.study.springbatch.basis.model.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudyBatchOneService {

    private final StudyOnePrimaryMapper studyOnePrimaryMapper;
    private final StudyOneSecondaryMapper studyOneSecondaryMapper;

    @Transactional(rollbackFor = Exception.class)
    public void saveMember() {
        studyOnePrimaryMapper.savePrimaryStudyOne(new Member());
    }

    public Member getMember() {
        return studyOneSecondaryMapper.selectSecondaryStudyOne(0);
    }
}
