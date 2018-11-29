package nz.frequency.timesheet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import nz.frequency.timesheet.util.JobDetails;
import nz.frequency.timesheet.util.UserDetailsRVContent;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsVerificationActivity extends AppCompatActivity {

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


    //This is another test Method

    private ArrayList<Object> getSampleComplexDataset() {

        ArrayList<Object> items = new ArrayList<>();

        for(int i=0; i<40; i++){
            //Make sure to send the data to these things in the right order
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
