package com.study.springbatch.domain.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * incrementer 기능 테스트
 *
 */
@RequiredArgsConstructor
@Slf4j
@Configuration
public class IncrementJobConfig {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job incrementJob() {
		return jobBuilderFactory.get("incrementJob")
			.start(incrementStep())
			//사용자 정의 패턴
			// .incrementer(new CustomerJobParametersIncrementor())
			//기본 패턴
			.incrementer(new RunIdIncrementer())
			.build();
	}

	@Bean
	public Step incrementStep() {
		return stepBuilderFactory.get("incrementStep")
			.tasklet((stepContribution, chunkContext) -> {
				log.info("확인 Job 기능: increment");
				return RepeatStatus.FINISHED;
			})
			.build();
	}

}
