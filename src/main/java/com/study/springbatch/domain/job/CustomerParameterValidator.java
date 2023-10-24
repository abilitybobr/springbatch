package com.study.springbatch.domain.job;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class CustomerParameterValidator implements JobParametersValidator {
	@Override
	public void validate(JobParameters parameters) throws JobParametersInvalidException {
		if (!parameters.getString("name").equals("aaaa")) {
			throw new JobParametersInvalidException("name 값이 올바른 값이 아닙니다.");
		}
	}
}
