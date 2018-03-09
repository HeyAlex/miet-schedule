package com.hey.mietunoff.mietunofficial.groups;

import android.content.Context;
import android.os.Build;
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
import com.hey.mietunoff.mietunofficial.R;
import heyalex.com.miet_schedule.ScheduleModel;

import static com.hey.mietunoff.mietunofficial.util.Preconditions.checkNotNull;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder> {

    private final List<ScheduleModel> items = new ArrayList<>();
    private Context context;
    private GroupsAdapter.OnGroupClickedListener onGroupClickedListener;
    private boolean isOreo = false;

    public GroupsAdapter(OnGroupClickedListener onGroupClickedListener) {
        this.onGroupClickedListener = checkNotNull(onGroupClickedListener);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            isOreo = true;
        }
    }

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

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.context = recyclerView.getContext();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    public void setItems(List<ScheduleModel> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public interface OnGroupClickedListener {

        void onGroupClickedListener(ScheduleModel scheduleModel);

        void onAddNewStaticIcon(String group);

        void onRequestWidgetConfigure(String group);

        void onDeleteGroup(String group);
    }

    /*package*/ class GroupsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.group)
        TextView group;

        public GroupsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final ScheduleModel groupModel) {
            final String groupName = groupModel.getGroup();
            group.setText(groupName);

            itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v,
                                                ContextMenu.ContextMenuInfo menuInfo) {
                    menu.add(context.getString(R.string.menu_delete_group, groupName))
                            .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    onGroupClickedListener.onDeleteGroup(groupName);
                                    items.remove(groupModel);
                                    notifyDataSetChanged();
                                    return false;
                                }
                            });
                    menu.add(context.getString(R.string.menu_add_static_icon, groupName))
                            .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    onGroupClickedListener.onAddNewStaticIcon(groupName);
                                    return true;
                                }
                            });
                    if (isOreo) {
                        menu.add(context.getString(R.string.menu_add_widget_on_homescreen))
                                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem item) {
                                        onGroupClickedListener.onRequestWidgetConfigure(groupName);
                                        return true;
                                    }
                                });
                    }
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
