package com.study.springbatch.domain.job;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.job.DefaultJobParametersExtractor;
import org.springframework.batch.core.step.job.JobParametersExtractor;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Configuration
public class JobStepSampleJobConfig {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job JobSampleJob() {
		return jobBuilderFactory.get("parentJob1")
			.start(jobStep(null))// null값으로 설정하면 DI로 자동 주입됨.
			.incrementer(new RunIdIncrementer())
			.build();
	}

	/**
	 * JobStep을 호출하게 되면 해당 Step에서는 Tasklet을 만들수 없음.
	 * 코딩시 문법 오류가 발생하는데 맞는 정책인지 좀더 고민
	 * @param jobLauncher
	 * @return
	 */
	@Bean
	public Step jobStep(JobLauncher jobLauncher) {
		return stepBuilderFactory.get("parentStep1")
			.job(childJob())
			.parametersExtractor(jobParametersExtractor())
			.listener(new StepExecutionListener() {
				@Override
				public void beforeStep(StepExecution stepExecution) {
					stepExecution.getExecutionContext().putString("name","Hong Gil Dong");
					log.info("Step을 실행하기전 실행되는 메소드 beforeStep");
				}

				@Override
				public ExitStatus afterStep(StepExecution stepExecution) {
					log.info("Step실행을 완료후 실행하는 메소드 afterStep");
					return null;
				}
			})
			.build();

	}

	/**
	 * Step의 ExecutionContext에 name의 Key를 갖는 키밸류 값을 꺼내
	 * JobStep의 JobParameters로 만들어서 전달 한다.
	 * @return
	 */
	private JobParametersExtractor jobParametersExtractor() {
		DefaultJobParametersExtractor extractor = new DefaultJobParametersExtractor();
		extractor.setKeys(new String[]{"name"});
		return extractor;
	}

	/**
	 * JobStep에서 해당 Job을 호출하여 실행한다.
	 * @return
	 */
	@Bean
	public Job childJob() {
		return jobBuilderFactory.get("childJob")
			.start(childStep())
			.build();

	}

	/**
	 * JobStep에서 호출한 Job에서 호출 되는 Step
	 * @return
	 */
	@Bean
	public Step childStep() {
		return stepBuilderFactory.get("childStep1")
			.tasklet(((stepContribution, chunkContext) -> {
				JobParameters jobParameters = stepContribution.getStepExecution().getJobParameters();
				log.info("Job 에서 전달된 이름은 {} 입니다.",jobParameters.getString("name"));
				return RepeatStatus.FINISHED;
			}))
			.build();
	}

}
