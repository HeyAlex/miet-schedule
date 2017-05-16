package heyalex.com.miet_schedule.addnewgroup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.groups.GroupsAdapter;

import static heyalex.com.miet_schedule.util.Preconditions.checkNotNull;

/**
 * Created by alexf on 16.05.2017.
 */

public class AddNewGroupAdapter extends RecyclerView.Adapter<AddNewGroupAdapter.GroupsViewHolder>{

    private Context context;
    private AddNewGroupAdapter.OnGroupClickedListener onGroupClickedListener;
    private final List<String> items = new ArrayList<>();

    @Override
    public AddNewGroupAdapter.GroupsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.groups_item, parent, false);
        return new AddNewGroupAdapter.GroupsViewHolder(root);
    }

    @Override
    public void onBindViewHolder(AddNewGroupAdapter.GroupsViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public interface OnGroupClickedListener {
        void onGroupClickedListener(String groupName);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.context = recyclerView.getContext();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    public AddNewGroupAdapter(AddNewGroupAdapter.OnGroupClickedListener onGroupClickedListener) {
        this.onGroupClickedListener = checkNotNull(onGroupClickedListener);
    }

    public void setItems(List<String> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    /*package*/ class GroupsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.group)
        TextView group;

        public GroupsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final String groupName){
            group.setText(groupName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onGroupClickedListener.onGroupClickedListener(groupName);
                }
            });
        }
    }
}
