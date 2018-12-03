package nz.frequency.timesheet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nz.frequency.timesheet.util.Constants;
import nz.frequency.timesheet.util.JobDetails;
import nz.frequency.timesheet.util.UserDetails;
import nz.frequency.timesheet.util.UserDetailsRVContent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsVerificationActivity extends AppCompatActivity {


    //get your settings back

    RecyclerView detailsRV;
    TextView helloUserTextView;
    Button sendVerificationButton;
    TextView verificationPromptTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_verification);
        setTitle(R.string.user_details);
        detailsRV = (RecyclerView) findViewById(R.id.detailsRV);
        helloUserTextView = (TextView) findViewById(R.id.hello_user_text);
        sendVerificationButton = (Button) findViewById(R.id.button);
        verificationPromptTextView = (TextView) findViewById(R.id.textView5);


    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = this.getSharedPreferences(Constants.SHARED_PREFERENCES_KEY_FOR_THE_APP,Context.MODE_PRIVATE);
        String responseData = preferences.getString(Constants.PHONE_NUMBER_RESPONSE_DATA, "missing");
        if (responseData != null && (!responseData.equalsIgnoreCase("missing"))) {
             //Removing the " " in the beginning and the end of the response data. Also removing escape characters to process data
             responseData = responseData.substring(1, responseData.length() - 1);
             responseData = responseData.replace("\\","");
             //Creating the new GSON Object. And getting the Object List
             Gson gson = new Gson();
             Type userListType = new TypeToken<ArrayList<UserDetails>>(){}.getType();
             List<UserDetails> userDetailsList = gson.fromJson(responseData, userListType);

             //Storing the USer ID in Shared Preferences
             SharedPreferences.Editor editor = preferences.edit();
             editor.putString(Constants.USER_ID, userDetailsList.get(0).getUserID());
             editor.putString(Constants.USER_PHONE, userDetailsList.get(0).getPhoneNumber());
             editor.apply();

             //The User Name is also updated and all.
             helloUserTextView.setText(String.format("Hello %s", userDetailsList.get(0).getUserName()));

             UserDetailsRVAdapter adapter = new UserDetailsRVAdapter(parseObtainedListInOrderAndSend(userDetailsList));
            // Attach the adapter to the Recycler View to populate items
             detailsRV.setAdapter(adapter);
            // Set layout manager to position the items
             detailsRV.setLayoutManager(new LinearLayoutManager(this));
             //Retrofit might not be the best option.

        }

        else{
            detailsRV.setVisibility(View.GONE);
            verificationPromptTextView.setText(R.string.verification_fatal_failure);
            //Cheers we've done the right thing

        }


    }



    //The method that parses the obtained user list and create a much better UI experience
    private List<Object> parseObtainedListInOrderAndSend(List<UserDetails> userDetailsList) {
        //The order in which data is loaded onto theListOfObjects is quite important as that is the order in which they will be displayed. We are presorting things here.
        List<Object> theListOfObjects = new ArrayList<Object>();
        List<String> projectlist = new ArrayList<String>();
        List<String> contractorList = new ArrayList<String>();
        List<JobDetails> jobDetails = new ArrayList<JobDetails>();
        for (UserDetails userDetail: userDetailsList) {
            projectlist.add(userDetail.getProjectName());
            contractorList.add(userDetail.getContractorName());
            jobDetails.add(new JobDetails(userDetail.getJobName(),userDetail.getJobDescription()));
        }
        //Configuring the heading
        theListOfObjects.add("Project");
        //Getting the content. Order is quite important
        for (String project : projectlist){
            theListOfObjects.add(new UserDetailsRVContent(project));
        }


        //Actually commenting code makes things better
        theListOfObjects.add("Contractor");
        //Getting the content.


        for (String contractor : contractorList){
            theListOfObjects.add(new UserDetailsRVContent(contractor));
        }
        //Configuring the heading
        theListOfObjects.add("Jobs Assigned");
        //Getting the content.
        theListOfObjects.addAll(jobDetails);

        return theListOfObjects;
    }







    @Override
    public void onBackPressed() {
        Intent goHomeIntent = new Intent(Intent.ACTION_MAIN);
        goHomeIntent.addCategory(Intent.CATEGORY_HOME);
        goHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(goHomeIntent);
    }

}
