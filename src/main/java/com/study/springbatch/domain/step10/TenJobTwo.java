package com.study.springbatch.domain.step10;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class TenJobTwo {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job tenJobTwoJob() {
		return jobBuilderFactory.get("tenJobTwo")
			.start(step1())
				.on("COMPLETED")
				.stop()
			.from(step1())
				.on("*")
				.to(step2())
				.on("FAILED")
				.stopAndRestart(step3())
				.end()
			.build();

	}

	@Bean
	public Step step3() {
		return stepBuilderFactory.get("tenStep3")
			.tasklet((stepContribution,chunkContext)->{
				log.info("step3 success");
				return RepeatStatus.FINISHED;
			})
			.build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("tenStep2")
			.tasklet((stepContribution,chunkContext)->{
				log.info("step2 success");
				return RepeatStatus.FINISHED;
			})
			.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("tenStep1")
			.tasklet((stepContribution, chunkContext) -> {
				log.info("step1 success");
				return RepeatStatus.FINISHED;
			})
			.build();
	}
}
