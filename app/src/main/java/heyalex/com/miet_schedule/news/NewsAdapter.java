package heyalex.com.miet_schedule.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import heyalex.com.miet_schedule.NewsModel;
import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.navdrawer.NavAdapter;


/**
 * Created by alexf on 16.09.2016.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {


    public NewsAdapter(Context context) {
        super(context);
    }

    public void updateData(List<NewsModel> newsEntityList, boolean append) {
        if (!append) {
            mData.clear();
        }
        mData.addAll(newsEntityList);
        notifyDataSetChanged();
    }


    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    protected void onClickItem(int position) {
        super.onClickItem(position);
    }

    public class NewsViewHolder extends CommonViewHolder {

        @BindView(R.id.newsDate)
        TextView date;

        @BindView(R.id.newsDescription)
        TextView desciption;

        @BindView(R.id.newsImage)
        ImageView image;

        @BindView(R.id.newsTitle)
        TextView title;

        public NewsViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void fillView(int position) {
            NewsModel newsModel = getItem(position);

            title.setText(newsModel.getTitle());
            desciption.setText(newsModel.getDescription());
            date.setText(newsModel.getDate());
            Glide.with(mContext).load(newsModel.getImageUrl()).centerCrop().into(image);
        }
    }
}
