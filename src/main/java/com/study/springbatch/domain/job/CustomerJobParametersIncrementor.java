package com.study.springbatch.domain.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

public class CustomerJobParametersIncrementor implements JobParametersIncrementer {

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-hh");

	@Override
	public JobParameters getNext(JobParameters parameters) {
		String id = sdf.format(new Date());
		return new JobParametersBuilder().addString("customer",id).toJobParameters();
	}
}
