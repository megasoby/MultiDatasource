package com.megasoby.sellerbatchproc.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BatchJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob() {
        return jobBuilderFactory.get("batchJob1")
                .incrementer(new RunIdIncrementer())
                .start(batchStep1())
                .next(batchStep2())
                .build();
    }

    private Step batchStep1() {
        return stepBuilderFactory.get("batchStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("==============================");
                        System.out.println("Spring Batch batchStep1 !!!");
                        System.out.println("==============================");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    private Step batchStep2() {
        return stepBuilderFactory.get("batchStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("==============================");
                        System.out.println("Spring Batch batchStep2 !!!");
                        System.out.println("==============================");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

}
