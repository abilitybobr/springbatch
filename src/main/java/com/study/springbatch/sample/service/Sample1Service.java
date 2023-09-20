package com.study.springbatch.sample.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.springbatch.sample.mapper.Sample1PrimaryMapper;
import com.study.springbatch.sample.mapper.Sample1SecondaryMapper;
import com.study.springbatch.sample.model.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Sample1Service {

    private final Sample1PrimaryMapper sample1PrimaryMapper;
    private final Sample1SecondaryMapper sample1SecondaryMapper;

    @Transactional(rollbackFor = Exception.class)
    public void saveMember() {
        sample1PrimaryMapper.savePrimarySample(new Member());
    }

    public Member getMember() {
        return sample1SecondaryMapper.selectSecondarySample(0);
    }
}
