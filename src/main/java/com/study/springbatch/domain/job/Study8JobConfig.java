package com.study.springbatch.domain.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 사용자 Parameter Validtion 학습
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class Study8JobConfig {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job study8() {
		return jobBuilderFactory.get("study8")
			.start(study8Step1())
			//사용자 정의 Validator 사용법
			.validator(new CustomerParameterValidator())
			// 기본 Validation 사용방법
			// .validator(new DefaultJobParametersValidator(new String[] {"name","date"}, new String[]{"count"} ))
			.build();
	}

	@Bean
	public Step study8Step1() {
		return stepBuilderFactory.get("study8Step1")
			.tasklet((stepContribution, chunkContext) -> {
				System.out.println("study8Step1 executed");
				return RepeatStatus.FINISHED;
			})
			.build();
	}

}

