package com.study.springbatch.sample.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.study.springbatch.sample.service.Sample1Service;
import com.study.springbatch.sample.tasklet.Sample1Tasklet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class Sample1JobConfig {

    private final JobBuilderFactory jobBuilderFactory; // job과 관련된 객체
    private final StepBuilderFactory stepBuilderFactory; // step과 관련된 객체
    private final Sample1Service sample1Service;

    @Bean
    public Job sample1Job() {
        return jobBuilderFactory.get("sample1Job") // sample1Job이라는 이름으로 job을 생성
                .incrementer(new RunIdIncrementer()) // job id를 증가시켜 매번 다른 job instance가 생성되게 함
                .start(sample1Step()) // sample1Step을 시작
                .build();
    }

    @Bean
    public Step sample1Step() {
        return stepBuilderFactory.get("sample1Step") // sample1Step이라는 이름으로 step을 생성
                .tasklet(new Sample1Tasklet(sample1Service))
                .build();
    }
}