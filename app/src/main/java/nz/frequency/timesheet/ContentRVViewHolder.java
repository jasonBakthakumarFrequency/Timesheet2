package nz.frequency.timesheet;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ContentRVViewHolder extends RecyclerView.ViewHolder {

    public TextView contentTextVew;

    public ContentRVViewHolder(View v) {
        super(v);
        contentTextVew = (TextView) v.findViewById(R.id.textView2);
    }


    public TextView getContentTextVew() {
        return contentTextVew;
    }

    public void setContentTextVew(TextView contentTextVew) {
        this.contentTextVew = contentTextVew;
    }



}
