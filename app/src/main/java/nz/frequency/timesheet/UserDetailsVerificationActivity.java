package nz.frequency.timesheet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nz.frequency.timesheet.util.Constants;
import nz.frequency.timesheet.util.JobDetails;
import nz.frequency.timesheet.util.UserDetails;
import nz.frequency.timesheet.util.UserDetailsRVContent;
import org.jetbrains.anko.ToastsKt;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsVerificationActivity extends AppCompatActivity {


    //get your settings back


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_verification);
        setTitle(R.string.user_details);
        RecyclerView detailsRV = (RecyclerView) findViewById(R.id.detailsRV);
        // Create adapter passing in the sample user data
        UserDetailsRVAdapter adapter = new UserDetailsRVAdapter(getSampleComplexDataset());
        // Attach the adapter to the recyclerview to populate items
        detailsRV.setAdapter(adapter);
        // Set layout manager to position the items
        detailsRV.setLayoutManager(new LinearLayoutManager(this));
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
             //Creating the new GSON Object. And getting the response
             Gson gson = new Gson();
             Type userListType = new TypeToken<ArrayList<UserDetails>>(){}.getType();
             //The list of user details was amazing
             List<UserDetails> userDetailsList = gson.fromJson(responseData, userListType);
             Toast.makeText(this, userDetailsList.get(1).getJobDescription(), Toast.LENGTH_LONG).show();



        }


    }


    //This is another test Method

    private ArrayList<Object> getSampleComplexDataset() {

        ArrayList<Object> items = new ArrayList<>();

        for(int i=0; i<40; i++){
            //The order in which you send these things are quite important.
            items.add("Job I guess");
            items.add(new UserDetailsRVContent("Well hey. This is just another content"));
            items.add(new JobDetails("Indiana Jonees", "Worst movie of all time"));
        }

        return items;
    }



    @Override
    public void onBackPressed() {
        Intent goHomeIntent = new Intent(Intent.ACTION_MAIN);
        goHomeIntent.addCategory(Intent.CATEGORY_HOME);
        goHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(goHomeIntent);
    }
}
