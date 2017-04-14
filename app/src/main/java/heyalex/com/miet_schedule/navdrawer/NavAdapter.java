package heyalex.com.miet_schedule.navdrawer;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.util.NavigationUtil;

/**
 * Created by alexf on 14.04.2017.
 */

public class NavAdapter extends RecyclerView.Adapter<NavAdapter.ViewHolder>{

    private Context context;
    private final Integer[] iconId = new Integer[]{R.drawable.calendar,
            R.drawable.newspaper, R.drawable.calendar_clock};

    private List<Integer> data;

    private int mCurrentPos = 0;

    public NavAdapter(Context context) {
        this.context = context;
        this.data = Arrays.asList(iconId);
    }

    public interface OnItemClickedListener{
        void onItemClicked(int position);
    }

    public void setCurrentPos(int currentPos) {
        mCurrentPos = currentPos;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_drawer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public int getItem(int position) {
        return data.get(position);
    }




    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.drawer_item)
        LinearLayout mDrawerItem;
        @BindView(R.id.drawer_icon)
        ImageView mItemImageView;
        @BindView(R.id.drawer_text)
        TextView mItemTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void fillView(int position) {
            mItemImageView.setImageResource(getItem(position));
            mItemTextView.setText(NavigationUtil.drawerList[position]);
            if (position == mCurrentPos) {
                mDrawerItem.setSelected(true);
                mItemImageView.setColorFilter(context.getResources().getColor(R.color.colorPrimary));
                mDrawerItem.setBackgroundColor(Color.parseColor("#f1f1f1"));
                mItemTextView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            } else {
                mDrawerItem.setSelected(false);
                mItemImageView.setColorFilter(context.getResources().getColor(R.color.iron_darker));
                mDrawerItem.setBackgroundColor(Color.parseColor("#ffffff"));
                mItemTextView.setTextColor(context.getResources().getColor(R.color.iron_darker));
            }
        }


    }

}
