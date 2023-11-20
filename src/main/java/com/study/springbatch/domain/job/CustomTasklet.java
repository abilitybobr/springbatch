package com.study.springbatch.domain.job;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("================ tasklet 1 complete ======================");
		// throw new Exception("오류가 발생해여 재시작 해주나???");
		int error = 5/0;
		return RepeatStatus.FINISHED;
	}
}
