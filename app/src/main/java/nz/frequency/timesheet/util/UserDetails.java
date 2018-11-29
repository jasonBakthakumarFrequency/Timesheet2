package nz.frequency.timesheet.util;

public class UserDetails {

    String userID;
    String userName;
    String phoneNumber;
    String projectName;
    String contractorName;
    String jobName;
    String jobDescription;

    public UserDetails() {
    }

    public UserDetails(String userID, String userName, String phoneNumber, String projectName, String contractorName, String jobName, String jobDescription) {
        this.userID = userID;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.projectName = projectName;
        this.contractorName = contractorName;
        this.jobName = jobName;
        this.jobDescription = jobDescription;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
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
