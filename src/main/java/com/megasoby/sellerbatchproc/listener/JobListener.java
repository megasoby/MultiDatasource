package com.megasoby.sellerbatchproc.listener;

import com.megasoby.sellerbatchproc.domain.PersonVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobListener.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! Job Finished! Time to verify the result!");
        }

        jdbcTemplate.query("Select first_name, last_name From people",
                (rs, row) -> new PersonVO(
                        rs.getString(1),
                        rs.getString(2))
        ).forEach(personVO -> log.info("Found <" + personVO + "> in the database."));
    }
}
