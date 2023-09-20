package com.study.springbatch.domain.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.study.springbatch.domain.service.StudyBatchOneService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class StudyOneTasklet implements Tasklet {

    private final StudyBatchOneService studyBatchOneService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info(">>>>>> This is StudyOne Step"); // batch-job이 실행되면 가장먼저 실행될 step은 log를 기록한다
        studyBatchOneService.saveMember();
        log.info("name >> {}", studyBatchOneService.getMember().getName());
        log.info("age >> {}", studyBatchOneService.getMember().getAge());
        return RepeatStatus.FINISHED;
    }
}
