package com.fastcampus.batch.job;

import com.fastcampus.batch.repository.pass.PassEntity;
import com.fastcampus.batch.repository.pass.PassStatus;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class PassJobConfig {
    private static final int CHUNK_SIZE = 1000;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final EntityManagerFactory entityManagerFactory;

    public PassJobConfig(JobRepository jobRepository, 
                        PlatformTransactionManager transactionManager, 
                        EntityManagerFactory entityManagerFactory) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public Job expirePassesJob() {
        return new JobBuilder("expirePassesJob", jobRepository)
                .start(expirePassesStep())
                .build();
    }

    @Bean
    public Step expirePassesStep() {
        return new StepBuilder("expirePassesStep", jobRepository)
                .<PassEntity, PassEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(expirePassesItemReader())
                .processor(expirePassesItemProcessor())
                .writer(expirePassesItemWriter())
                .build();
    }

    @Bean
    public JpaCursorItemReader<PassEntity> expirePassesItemReader() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("status", PassStatus.PROGRESSED);
        parameters.put("endedAt", LocalDateTime.now());

        return new JpaCursorItemReaderBuilder<PassEntity>()
                .name("expirePassesItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT p FROM PassEntity p WHERE p.status = :status AND p.endedAt <= :endedAt")
                .parameterValues(Map.of("status",PassStatus.PROGRESSED,"endedAt",LocalDateTime.now()))
                .build();
    }

    @Bean
    public ItemProcessor<PassEntity, PassEntity> expirePassesItemProcessor() {
        return passEntity -> {
            passEntity.setStatus(PassStatus.EXPIRED);
            passEntity.setExpiredAt(LocalDateTime.now());
            return passEntity;
        };
    }

    @Bean
    public ItemWriter<PassEntity> expirePassesItemWriter() {
        return items -> {
            items.forEach(item -> {
                // EntityManager를 통한 저장
                entityManagerFactory.createEntityManager().merge(item);
            });
        };
    }
} 