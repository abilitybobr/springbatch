package com.study.springbatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableBatchProcessing

public class SpringbatchApplication {

	private static final String CONFIG_FILE_NAME = "spring.config.name = application,interface";

	public static void main(String[] args) {

		// SpringApplication.run(SpringbatchApplication.class, args);

		try {
			AtomicReference<Boolean> isJobCommandLineRunnerBased = new AtomicReference<Boolean>(Boolean.FALSE);
			AtomicReference<String> jobName = new AtomicReference<String>();
			Stream<String> argStream = Arrays.stream(args);
			argStream.forEach(arg -> {
				if (arg.startsWith("--job.name")) {
					isJobCommandLineRunnerBased.set(Boolean.TRUE);
					jobName.set(arg.substring(arg.indexOf("=") + 1));
					return;
				}
			});
			if (Boolean.TRUE.equals(isJobCommandLineRunnerBased.get())) {
				List<String> params = new ArrayList<>();
				for (String key : args) {
					params.add(key);
				}
				params.add("requestDate=" + System.currentTimeMillis());
				// params.add("requestDate=" + "고정");
				ConfigurableApplicationContext context = new SpringApplicationBuilder()
					.sources(SpringbatchApplication.class)
					.properties(CONFIG_FILE_NAME)
					.web(WebApplicationType.NONE)
					.run(params.toArray(new String[0]));
					// .run();
				int exitCode = SpringApplication.exit(context);
				System.exit(exitCode);
			} else {
				new SpringApplicationBuilder().sources(SpringbatchApplication.class).properties(CONFIG_FILE_NAME).run(args);
			}
		}catch (Exception e) {
			log.info("", e);
			throw e;
		}
	}

}
