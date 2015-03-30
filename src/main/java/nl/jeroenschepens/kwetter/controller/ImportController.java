package nl.jeroenschepens.kwetter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.operations.NoSuchJobException;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.JobInstance;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ImportController {

	private static final String JOB_NAME = "TweetJob";

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void startTweetImport() {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		Properties props = new Properties();
		props.setProperty("jsonUrl", url);
		jobOperator.start(JOB_NAME, props);
	}

	public List<JobExecution> getJobs() {
		try {
			JobOperator jobOperator = BatchRuntime.getJobOperator();
			List<JobInstance> jobInstances = jobOperator.getJobInstances(
					JOB_NAME, 0, 100);
			List<JobExecution> jobs = new ArrayList<>();
			for (JobInstance jobInstance : jobInstances) {
				jobs.addAll(jobOperator.getJobExecutions(jobInstance));
			}
			return jobs;
		} catch (NoSuchJobException jx) {
			return null;
		}
	}
}