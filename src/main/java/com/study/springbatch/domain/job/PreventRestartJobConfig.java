package com.study.springbatch.domain.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Job 메소드 중 preventRestart 테스트
 * preventRestart를 호출하면 false인 상태로 동작 합니다.
 * preventRestart를 미호출 하면 true인 상태로 동작 합니다.
 *
 * preventRestart
 * -. 기본적으로 Job이 실패할경우 재시작이 가능한데 해당값을 false로 주게 되면
 * -. 재시작을 지원하지 않습니다.
 */
@RequiredArgsConstructor
@Slf4j
@Configuration
public class PreventRestartJobConfig {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job preventRestartJob() {
		return jobBuilderFactory.get("preventRestartJob")
			.start(preventRestartStep())
			/**
			 * preventRestart 호출 하면 false로 세팅
			 * preventRestart 미호출 하면 true로 세팅
			 */
			// .preventRestart()
			.build();
	}

	@Bean
	public Step preventRestartStep() {
		return stepBuilderFactory.get("preventRestartStep")
			.tasklet((stepContribution, chunkContext) -> {
				log.info("preventRestartStep 테스트");
				return RepeatStatus.FINISHED;
			})
			.build();
	}
}
