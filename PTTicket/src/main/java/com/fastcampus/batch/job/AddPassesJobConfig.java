package com.fastcampus.batch.job;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.context.annotation.Bean;

public class AddPassesJobConfig {
  private final JobRepository jobRepository;
  private final PlatformTransactionManager transactionManager;
  private final AddPassesTasklet addPassesTasklet;
  public AddPassesJobConfig(JobRepository jobRepository, 
  PlatformTransactionManager transactionManager, 
  AddPassesTasklet addPassesTasklet) {
    this.jobRepository = jobRepository;
    this.transactionManager = transactionManager;
    this.addPassesTasklet = addPassesTasklet;
  }

  @Bean
  public Job addPassesJob() {
    return new JobBuilder("addPassesJob", jobRepository)
            .start(addPassesStep())
            .build();
  }

  @Bean
  public Step addPassesStep() { // TaskLet을 주입할 예정
    return new StepBuilder("addPassesStep", jobRepository)
            .tasklet(addPassesTasklet, transactionManager)
            .build();
  }
}
