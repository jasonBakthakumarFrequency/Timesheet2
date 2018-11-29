package nz.frequency.timesheet.util;

//This one is for GSON Object Parsing
public class UserDetails {

    String UserID;
    String UserName;
    String PhoneNumber;
    String ProjectName;
    String ContractorName;
    String JobName;
    String JobDescription;

    public UserDetails(String userID, String userName, String phoneNumber, String projectName, String contractorName, String jobName, String jobDescription) {
        this.UserID = userID;
        this.UserName = userName;
        this.PhoneNumber = phoneNumber;
        this.ProjectName = projectName;
        this.ContractorName = contractorName;
        this.JobName = jobName;
        this.JobDescription = jobDescription;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        this.UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        this.ProjectName = projectName;
    }

    public String getContractorName() {
        return ContractorName;
    }

    public void setContractorName(String contractorName) {
        this.ContractorName = contractorName;
    }

    public String getJobName() {
        return JobName;
    }

    public void setJobName(String jobName) {
        this.JobName = jobName;
    }

    public String getJobDescription() {
        return JobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.JobDescription = jobDescription;
    }
}
