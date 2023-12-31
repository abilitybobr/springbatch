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

@RequiredArgsConstructor
@Slf4j
@Configuration
public class TaskletSampleJobConfig {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job helloJobConfiguration() {
		return jobBuilderFactory.get("taklet1")
			.start(step1())
			.incrementer(new RunIdIncrementer())
			.build();
	}

	public Step step1() {
		return stepBuilderFactory.get("taskletStep1")
			.tasklet(new CustomTasklet())
			.build();
	}
}
