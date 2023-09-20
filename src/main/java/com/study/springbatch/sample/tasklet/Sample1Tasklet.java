package com.study.springbatch.sample.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.study.springbatch.sample.service.Sample1Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class Sample1Tasklet implements Tasklet {

    private final Sample1Service sample1Service;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info(">>>>>> This is Sample1 Step1"); // batch-job이 실행되면 가장먼저 실행될 step은 log를 기록한다
        sample1Service.saveMember();
        log.info("name >> {}", sample1Service.getMember().getName());
        log.info("age >> {}",sample1Service.getMember().getAge());
        return RepeatStatus.FINISHED;
    }
}
