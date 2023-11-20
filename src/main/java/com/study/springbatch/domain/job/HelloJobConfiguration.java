// package com.study.springbatch.domain.job;
//
// import java.util.Date;
//
// import org.springframework.batch.core.Job;
// import org.springframework.batch.core.JobParameters;
// import org.springframework.batch.core.Step;
// import org.springframework.batch.core.StepContribution;
// import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
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
// public class HelloJobConfiguration {
//
// 	private final JobBuilderFactory jobBuilderFactory;
// 	private final StepBuilderFactory stepBuilderFactory;
//
// 	// 잡 구성
// 	@Bean
// 	public Job springBatchHello() {
// 		return jobBuilderFactory.get("springBatchHello")
// 			.start(springBatchHelloStep1())
// 			.build();
// 	}
//
// 	// 스텝 구성
// 	@Bean
// 	public Step springBatchHelloStep1() {
// 		return stepBuilderFactory.get("springBatchHelloStep1")
// 			.tasklet(new Tasklet() {
// 				// 스텝이 하는 일 구성
// 				@Override
// 				public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
// 					JobParameters jobParameters = contribution.getStepExecution().getJobExecution().getJobParameters();
//
// 					// 파라미터가 잘 들어왔는지 확인
// 					Date date = jobParameters.getDate("date");
// 					// Double age = jobParameters.getDouble("age");
// 					Long seq = jobParameters.getLong("seq");
// 					String name = jobParameters.getString("name");
// 					String strAge = jobParameters.getString("age");
// 					log.info("date ={}", date);
// 					// log.info("age ={}", age);
// 					log.info("age ={}", strAge);
// 					log.info("seq ={}", seq);
// 					log.info("name ={}", name);
// 					// log.info("strAge ={}", strAge);
//
// 					return RepeatStatus.FINISHED;
// 				}
// 			})
// 			.build();
// 	}
// }