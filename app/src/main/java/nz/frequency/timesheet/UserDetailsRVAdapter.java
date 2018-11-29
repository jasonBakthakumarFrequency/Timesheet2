package nz.frequency.timesheet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import nz.frequency.timesheet.util.JobDetails;
import nz.frequency.timesheet.util.UserDetailsRVContent;

import java.util.List;


//TODO:Needs to be changed to accomodate
public class UserDetailsRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Object> detailsRVList;

    private final int HEADING = 0, CONTENT = 1, JOBSTUFF = 3;

    // Pass in the contact array into the constructor
    public UserDetailsRVAdapter(List<Object> detailsRVList) {
        this.detailsRVList = detailsRVList;
    }


    @Override
    public int getItemViewType(int position) {
        if (detailsRVList.get(position) instanceof UserDetailsRVContent) {
            return CONTENT;
        } else if (detailsRVList.get(position) instanceof String) {
            return HEADING;
        } else if (detailsRVList.get(position) instanceof JobDetails){
            return JOBSTUFF;
        }

        return -1;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case HEADING:
                View v1 = inflater.inflate(R.layout.details_rv_heading, parent, false);
                viewHolder = new HeadingRVViewHolder(v1);
                break;
            case CONTENT:
                View v2 = inflater.inflate(R.layout.details_rv_names, parent, false);
                viewHolder = new ContentRVViewHolder(v2);
                break;
            case JOBSTUFF:
                View v3 = inflater.inflate(R.layout.details_rv_job_details, parent, false);
                viewHolder = new JobDetailsViewHolder(v3);
                break;
            default:
                View v = inflater.inflate(R.layout.details_rv_names, parent, false);
                viewHolder = new ContentRVViewHolder(v);
                break;
        }


        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        // Get the data model based on position

        switch (viewHolder.getItemViewType()) {
            case HEADING:
                HeadingRVViewHolder vh1 = (HeadingRVViewHolder) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            case CONTENT:
                ContentRVViewHolder vh2 = (ContentRVViewHolder) viewHolder;
                configureViewHolder2(vh2, position);
                break;
            case JOBSTUFF:
                JobDetailsViewHolder vh3 = (JobDetailsViewHolder) viewHolder;
                configureViewHolder3(vh3, position);
                break;
            default:
                ContentRVViewHolder vh4 = (ContentRVViewHolder) viewHolder;
                configureViewHolder2(vh4, position);
                break;
        }

    }



    private void configureViewHolder1(HeadingRVViewHolder vh1, int position) {
        String heading = (String) detailsRVList.get(position);
        if (heading != null) {
            vh1.getHeadingTextView().setText(heading);
        }
    }

    private void configureViewHolder2(ContentRVViewHolder vh2, int position) {
        UserDetailsRVContent content = (UserDetailsRVContent) detailsRVList.get(position);
        if (content != null){
            vh2.getContentTextVew().setText(content.getContent());
        }
    }



    private void configureViewHolder3(JobDetailsViewHolder vh3, int position) {
        JobDetails jobDetail = (JobDetails) detailsRVList.get(position);
        if (jobDetail != null){
            vh3.getJobNameTextView().setText(jobDetail.getJobName());
            vh3.getJobDescriptionTextView().setText(jobDetail.getJobDescription());
        }
    }


    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return detailsRVList.size();
    }




}
