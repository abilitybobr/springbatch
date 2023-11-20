package com.study.springbatch.domain.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class TaskletSample2JobConfig {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job helloJob() {
		return jobBuilderFactory.get("taskletSample2")
			.start(step1())
			.incrementer(new RunIdIncrementer())
			.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("taskletStep2")
			.tasklet(new CustomTasklet())
			.startLimit(3)	//taskletStep2는 3번만 실행이 가능하다.
			.build();
	}
}
