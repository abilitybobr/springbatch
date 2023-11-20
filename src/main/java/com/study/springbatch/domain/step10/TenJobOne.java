package com.study.springbatch.domain.step10;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.support.state.StepState;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class TenJobOne {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job tenJobOneJob() {
		return jobBuilderFactory.get("tenJobOne")
			.start(step1())
				.on("COMPLETED")
				.to(step2())
				.on("FAILED")
				.to(step3())
			.from(step1())
				.on("FAILED")
				.end()
			.from(step2())
				.on("COMPLETED")
				.to(step4())
				.next(step5())
				.end()
			.incrementer(new RunIdIncrementer())
			.build();
	}

	@Bean
	public Step step5() {
		return stepBuilderFactory.get("tenStepFive")
			.tasklet((stepContribution, chunkContext) -> {
				log.info("tenStepFive 호출됨");
				return RepeatStatus.FINISHED;
			}).build();
	}

	@Bean
	public Step step4() {
		return stepBuilderFactory.get("tenStepFour")
			.tasklet((stepContribution, chunkContext) -> {
				log.info("tenStepFour 호출됨");
				return RepeatStatus.FINISHED;
			}).build();
	}

	@Bean
	public Step step3() {
		return stepBuilderFactory.get("tenStepThree")
			.tasklet((stepContribution, chunkContext) -> {
			log.info("tenStepThree 호출됨");
			return RepeatStatus.FINISHED;
		}).build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("tenStepTwo")
			.tasklet((stepContribution,chunkContext) -> {
			log.info("tenStepTwo 호출됨");
			// return RepeatStatus.FINISHED;
				throw new RuntimeException("tenStepTwo Failed");
		}).build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("tenStepOne")
			.tasklet(((stepContribution, chunkContext) -> {
				log.info("tenStepOne 호출됨.");
				return RepeatStatus.FINISHED;
			}))
			.build();

	}
}
