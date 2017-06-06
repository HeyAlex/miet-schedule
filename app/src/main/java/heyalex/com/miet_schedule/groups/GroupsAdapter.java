package heyalex.com.miet_schedule.groups;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleModel;

import static heyalex.com.miet_schedule.util.Preconditions.checkNotNull;

/**
 * Created by mac on 09.05.17.
 */
public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder> {

    private Context context;
    private GroupsAdapter.OnGroupClickedListener onGroupClickedListener;
    private final List<ScheduleModel> items = new ArrayList<>();

    @Override
    public GroupsAdapter.GroupsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.groups_item, parent, false);
        return new GroupsViewHolder(root);
    }

    @Override
    public void onBindViewHolder(GroupsViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public interface OnGroupClickedListener {

        void onGroupClickedListener(ScheduleModel scheduleModel);

        void onAddNewStaticIcon(String group);

        void onDeleteGroup(String group);
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

    public GroupsAdapter(OnGroupClickedListener onGroupClickedListener) {
        this.onGroupClickedListener = checkNotNull(onGroupClickedListener);
    }

    public void setItems(List<ScheduleModel> items) {
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

        public void bind(final ScheduleModel groupModel) {
            group.setText(groupModel.getGroup());

            itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v,
                                                ContextMenu.ContextMenuInfo menuInfo) {
                    menu.add("Удалить группу " + groupModel.getGroup())
                            .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            items.remove(groupModel);
                            onGroupClickedListener.onDeleteGroup(groupModel.getGroup());
                            notifyDataSetChanged();
                            return false;
                        }
                    });
                    menu.add("Добавить на рабочий стол " + groupModel.getGroup())
                            .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            onGroupClickedListener.onAddNewStaticIcon(groupModel.getGroup());
                            return false;
                        }
                    });
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onGroupClickedListener.onGroupClickedListener(groupModel);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemView.showContextMenu();
                    return true;
                }
            });
        }

    }
}
