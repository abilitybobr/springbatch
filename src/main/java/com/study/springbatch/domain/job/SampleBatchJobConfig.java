// package com.study.springbatch.domain.job;
//
// import org.springframework.batch.core.Job;
// import org.springframework.batch.core.Step;
// import org.springframework.batch.core.StepContribution;
// import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
// import org.springframework.batch.core.configuration.annotation.JobScope;
// import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
// import org.springframework.batch.core.scope.context.ChunkContext;
// import org.springframework.batch.core.step.tasklet.Tasklet;
// import org.springframework.batch.repeat.RepeatStatus;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
//
// @Slf4j
// @Configuration
// @RequiredArgsConstructor
// public class SampleBatchJobConfig {
//
// 	// Job을 생성하는 빌더 팩토리로, Job을 생성할 때 new Job이 아니라 jobBuilderFactory을 사용해 쉽게 Job을 생성합니다.
// 	private final JobBuilderFactory jobBuilderFactory;
// 	// JobBuilderFactory와 같은 맥락입니다.
// 	private final StepBuilderFactory stepBuilderFactory;
//
// 	@Bean
// 	public Job sampleJob(){
// 		return jobBuilderFactory.get("sampleJob") // sampleJob 생성합니다.
// 			.start(sampleStep1()) // Job의 첫번째 Step으로 helloStep1을 등록합니다.
// 			// .next(helloStep2()) // helloStep1을 수행한 이후 다음 Step을 등록합니다.
// 			.build();
// 	}
//
// 	@Bean
// 	public Step sampleStep1() {
// 		return stepBuilderFactory.get("sampleStep1") // helloStep1을 생성합니다.
// 			.tasklet(new Tasklet() { // Step의 작업 내용 Tasklet을 정의합니다.
// 				@Override
// 				public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
// 					System.out.println("sampleStep1");
// 					// Step은 기본적으로 Tasklet을 무한 반복시킵니다.
// 					// 따라서 null이나 RepeatStatus.FINISHED를 반환해줘야 1번만 Tasklet을 실행합니다.
// 					return RepeatStatus.FINISHED;
// 				}
// 			})
// 			.build();
// 	}
//
// 	/**
// 	 * helloStep2를 Bean으로 등록하게 되면
// 	 * 오류가 발생하고 있음.
// 	 * // TODO: 2023/10/07 원인 찾아야 함.
// 	 */
// 	// helloStep1과 같은 맥락입니다.
// 	// @Bean
// 	// public Step helloStep2() {
// 	// 	return stepBuilderFactory.get("helloStep2")
// 	// 		.tasklet(new Tasklet() {
// 	// 			@Override
// 	// 			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
// 	// 				System.out.println("hellStep2");
// 	// 				return RepeatStatus.FINISHED;
// 	// 			}
// 	// 		})
// 	// 		.build();
// 	// }
//
// }
