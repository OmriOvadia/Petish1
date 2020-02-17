package com.moveosoftware.infrastructure.view;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ofer Dan-On on 1/20/2017
 * BaseRecyclerAdapter - Base class providing easy and straightforward all around implementation for RecyclerView.
 */

public abstract class BaseRecyclerAdapter<VH extends BaseViewHolder<T>, T> extends RecyclerView.Adapter<VH> {

    private static final int DEFAULT_MOCK_ITEMS = 10;
    protected List<T> mData;
    protected boolean mDebug;


    /**
     * Inserts new data to the adapter.
     *
     * @param data - List of objects to be inserted to the adapter.
     */
    public void setData(List<T> data) {
        for (T t : data) {
            addItem(t);
        }
    }


    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    /**
     * Adds a single item to the adapter and notifies the view.
     *
     * @param item - new item to be inserted.
     */
    public void addItem(T item) {
        mData.add(item);
        notifyItemInserted(mData.size() - 1);
    }


    /**
     * Removes an item from the adapter at a certain position.
     *
     * @param position - the position of the item to be removed.
     */
    public void remove(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public void removeTogether(List<T> items) {
        mData.removeAll(items);
        notifyDataSetChanged();
    }

    public void remove(List<T> items) {
        for (T item : items) {
            remove(item);
        }
    }

    public void remove(T item) {
        int index = mData.indexOf(item);
        mData.remove(item);
        notifyItemRemoved(index);
    }

    /**
     * Updates a certain item in the adapter if found, if not it will be added.
     *
     * @param t - the item to be updated / added.
     */
    public void update(T t) {
        if (mData.contains(t)) {
            int idx = mData.indexOf(t);
            mData.set(idx, t);
            notifyItemChanged(idx);
        } else
            addItem(t);
    }

    /**
     * Updates both the data and the view in case the item exists.
     * If the item does not exists in the data, nothing happens.
     * @param t
     */
    public void updateIfNeeded(T t) {
        if (mData.contains(t)) {
            int idx = mData.indexOf(t);
            mData.set(idx, t);
            notifyItemChanged(idx);
        }
    }

    /**
     * Updates both the data and the view that correspond to a given list of items
     * @param list - list of items to be updated
     */
    public void update(List<T> list) {
        for (T t : list) {
            update(t);
        }
    }

    public List<T> getData() {
        return mData;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bind(mData.get(position), position);
    }


    public BaseRecyclerAdapter() {
        this(false);
    }

    public BaseRecyclerAdapter(boolean debug) {
        this(debug, DEFAULT_MOCK_ITEMS);
    }

    @SuppressWarnings("TryWithIdenticalCatches")
    public BaseRecyclerAdapter(boolean debug, int mockItemsCount) {
        mData = new ArrayList<>();
        mDebug = debug;
        if (debug) {
            int mockCount = mockItemsCount != 0 ? mockItemsCount : DEFAULT_MOCK_ITEMS;
            for (int i = 0; i < mockCount; i++)
                try {
                    mData.add(getDataType().newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * Adds an item at a specified position and updates the view specifically for the inserted item
     * @param i - the specified position
     * @param item - the item to be added
     */
    public void addItemAt(int i, T item) {
        mData.add(i, item);
        notifyItemInserted(i);
    }

    protected abstract Class<T> getDataType();

    /**
     * Adds a new list of items to the existing data and notifies the view in an explicit method
     * @param items - new list of items to be inserted to the existing data
     */
    public void addAll(List<T> items) {
        mData.addAll(items);
        notifyDataSetChanged();
    }
}
