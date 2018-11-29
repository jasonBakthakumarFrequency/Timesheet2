package nz.frequency.timesheet.util;

public class JobDetails {

    private String jobName;
    private String jobDescription;

    public JobDetails() {
    }

    public JobDetails(String jobName, String jobDescription) {
        this.jobName = jobName;
        this.jobDescription = jobDescription;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
