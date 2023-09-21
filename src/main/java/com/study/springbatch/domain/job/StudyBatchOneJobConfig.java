package com.study.springbatch.domain.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.study.springbatch.domain.service.StudyBatchOneService;
import com.study.springbatch.domain.tasklet.StudyOneTasklet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DataBase를 기본으로 연결하여 Select를 할수 있는 기본 샘플
 */
@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class StudyBatchOneJobConfig {

    private final JobBuilderFactory jobBuilderFactory; // job과 관련된 객체
    private final StepBuilderFactory stepBuilderFactory; // step과 관련된 객체
    private final StudyBatchOneService studyBatchOneService;

    @Bean
    public Job studyBatchOneJob() {
        return jobBuilderFactory.get("studyBatchOneJob") // studyBatchOneJob이라는 이름으로 job을 생성
                .incrementer(new RunIdIncrementer()) // job id를 증가시켜 매번 다른 job instance가 생성되게 함
                .start(studyOneStep()) // StudyOneStep을 시작
                .build();
    }

    @Bean
    public Step studyOneStep() {
        return stepBuilderFactory.get("studyOneStep") // studyOneStep이라는 이름으로 step을 생성
                .tasklet(new StudyOneTasklet(studyBatchOneService))
                .build();
    }
}