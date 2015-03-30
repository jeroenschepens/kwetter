package nl.jeroenschepens.kwetter.controller;

import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
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
}