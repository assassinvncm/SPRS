package com.config;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.server.JobActivator;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobRunrConfig {
	
//	@Bean
//	public StorageProvider storageProvider(JobMapper jobMapper) {
//	    InMemoryStorageProvider storageProvider = new InMemoryStorageProvider();
//	    storageProvider.setJobMapper(jobMapper);
//	    return storageProvider;
//	}
//	
//	@Bean
//    public JobScheduler initJobRunr(StorageProvider storageProvider, ApplicationContext applicationContext) {
//        return JobRunr.configure()
//        		.useStorageProvider(storageProvider)
//                .useJobActivator(applicationContext::getBean)
//                .useBackgroundJobServer()
//                .useDashboard()
//                .initialize();
//    }
	
}
