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
 * Adapter that used in {@link NavDrawerActivity}
 */
/*package*/ class NavAdapter extends RecyclerView.Adapter<NavAdapter.ViewHolder> {

    private static final int WHITE_COLOR = Color.parseColor("#f1f1f1");
    private static final int GRAY_COLOR = Color.parseColor("#ffffff");
    private static final int SELECTED_COLOR = Color.parseColor("#2196F3");
    private static final int UNSELECTED_COLOR = Color.parseColor("#4b4b4b");
    /**
     * Icons for navigation list items
     */
    private final Integer[] iconId = new Integer[]{R.drawable.calendar,
            R.drawable.newspaper, R.drawable.calendar_clock};
    private Context context;
    private List<Integer> data;
    private int mCurrentPos = 0;
    /**
     * Callback that navigation items has clicked
     */
    private OnNavClickedListener clickedListener;


    /**
     * Create a new instance of {@link NavAdapter}
     *
     * @param context         where adapter was created
     * @param clickedListener to receive callbacks
     */
    /*package*/ NavAdapter(Context context, OnNavClickedListener clickedListener) {
        this.context = context;
        this.data = Arrays.asList(iconId);
        this.clickedListener = clickedListener;
        notifyDataSetChanged();
    }

    public int getCurrentPos() {
        return mCurrentPos;
    }

    /*package*/ void setCurrentPos(int currentPos) {
        mCurrentPos = currentPos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_drawer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.fillView(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /*package*/ int getItem(int position) {
        return data.get(position);
    }

    /**
     * Callback for the view that created this adapter.
     */
    /*package*/ interface OnNavClickedListener {
        void onNavClicked(int position);
    }

    /*package*/ class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.drawer_item)
        LinearLayout mDrawerItem;
        @BindView(R.id.drawer_icon)
        ImageView mItemImageView;
        @BindView(R.id.drawer_text)
        TextView mItemTextView;

        /*package*/ ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /*package*/ void fillView(int position) {
            mItemImageView.setImageResource(getItem(position));
            mItemTextView.setText(NavigationUtil.drawerList[position]);
            if (position == mCurrentPos) {
                mDrawerItem.setSelected(true);
                mItemImageView.setColorFilter(SELECTED_COLOR);
                mDrawerItem.setBackgroundColor(WHITE_COLOR);
                mItemTextView.setTextColor(SELECTED_COLOR);
            } else {
                mDrawerItem.setSelected(false);
                mItemImageView.setColorFilter(UNSELECTED_COLOR);
                mDrawerItem.setBackgroundColor(GRAY_COLOR);
                mItemTextView.setTextColor(UNSELECTED_COLOR);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCurrentPos(getAdapterPosition());
                    clickedListener.onNavClicked(mCurrentPos);
                    notifyDataSetChanged();
                }
            });
        }

    }

}
