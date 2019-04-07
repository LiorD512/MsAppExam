package com.dahanlior.msappexam.utils;

import java.util.List;
import java.util.Objects;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<ITEM extends ListItem, VH extends BaseHolder<ITEM>> extends RecyclerView.Adapter<VH>  {

    private List<ITEM> mItems;

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return mItems != null ? mItems.get(position).getId().hashCode() : -1;
    }

    public void setData(final List<ITEM> newItems) {
        if(mItems == null) {
            mItems = newItems;
            if(newItems != null) {
                notifyItemRangeInserted(0, newItems.size());
            }
            return ;
        }
        final List<ITEM> oldItems = mItems;
        mItems = newItems;

        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return oldItems.size();
            }

            @Override
            public int getNewListSize() {
                return newItems != null ? newItems.size() : 0;
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return areItemsSame(oldItems.get(oldItemPosition), newItems.get(newItemPosition));
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return areItemsContentSame(oldItems.get(oldItemPosition), newItems.get(newItemPosition));
            }
        });

        result.dispatchUpdatesTo(this);
    }

    protected boolean areItemsSame(ITEM oldItem, ITEM newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    protected boolean areItemsContentSame(ITEM oldItem, ITEM newItem) {
        return Objects.equals(oldItem,newItem);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.fillViews(getItemAt(position));
    }

    public List<ITEM> getData() {
        return mItems;
    }

    protected ITEM getItemAt(int position) {
        return mItems != null ? mItems.get(position) : null;
    }
}
