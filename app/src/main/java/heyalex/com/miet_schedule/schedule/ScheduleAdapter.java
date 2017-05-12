package heyalex.com.miet_schedule.schedule;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import heyalex.com.miet_schedule.R;

/**
 * Created by mac on 10.05.17.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder>  {

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.newsDate)
        TextView date;

        @BindView(R.id.newsDescription)
        TextView desciption;

        public ScheduleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(){
        }
    }
}
