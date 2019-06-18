package com.wei.cookbook.ui.adapter;

import android.content.res.Resources;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.wei.cookbook.R;
import com.wei.cookbook.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>
{
    protected BaseActivity mActivity;
    protected Resources mResources;
    private final int resourceId;
    protected int lastPosition = -1;
    private List<T> mList;
    private View mEmpty;


    private RecyclerView mRecyclerView;

    /*无数据空布局标签*/
    private static final int EMPTY = -1;


    public BaseAdapter(BaseActivity activity, int resourceId)
    {
        this.mActivity = activity;
        mResources = activity.getApplication().getResources();
        this.resourceId = resourceId;
        mList = new ArrayList<>();
        lastPosition = setLastPosition();
    }



    public void bindRecyclerView(RecyclerView recyclerView)
    {
        if (recyclerView == null)
        {
            throw new NullPointerException("The charge RecyclerView bound to Adapter cannot be empty");
        }
        this.mRecyclerView = recyclerView;
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
                if (!mActivity.isDestroyed())
                {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    {
                        Glide.with(mActivity).resumeRequests();
                    } else
                    {
                        Glide.with(mActivity).pauseRequests();
                    }
                }
            }
        });
        mRecyclerView.setAdapter(this);
    }


    public void scrollToPosition(int position)
    {
        if (mRecyclerView == null)
        {
            throw new NullPointerException("Please bind the RecyclerView of the adapter!");
        }
        mRecyclerView.scrollToPosition(position);
    }



    public List<T> getData()
    {
        return mList;
    }



    public void setData(List<T> list)
    {
        mList.clear();
        mList.addAll(list == null ? new ArrayList<T>() : list);
        notifyDataSetChanged();
    }



    public void setData(List<T> list, @IntRange(from = 0) int index)
    {
        if (list == null || list.isEmpty()) return;
        if (index < 0 || index >= mList.size())
        {
            index = mList.size();
        }
        mList.addAll(index, list);
        notifyItemRangeInserted(index, list.size());
    }



    public void setData(T t, @IntRange(from = 0) int index)
    {
        if (t == null) return;
        if (index >= mList.size())
        {
            index = mList.size();
        }
        mList.add(index, t);
        notifyItemInserted(index);
    }


    public void setEmptyView(View view)
    {
        if (view == null)
        {
            throw new NullPointerException("The provided empty layout is empty");
        }
        this.mEmpty = view;
        notifyDataSetChanged();
    }

    /*单选的选中下标*/
    public int getLastPosition()
    {
        return lastPosition;
    }

    protected int setLastPosition()
    {
        return -1;
    }

    /*获取单选选中的实体对象*/
    public T getSelectedData()
    {
        if (lastPosition >= 0 && lastPosition < mList.size())
        {
            return mList.get(lastPosition);
        }
        return null;
    }


    private T getItem(@IntRange(from = 0) int position)
    {
        if (mList.isEmpty()) return null;
        return mList.get(position);
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager)
        {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
            {
                @Override
                public int getSpanSize(int position)
                {
                    return mList.isEmpty() ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull BaseViewHolder holder)
    {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && mList.isEmpty())
        {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        if (mList.isEmpty())
        {
            return EMPTY;
        } else
        {
            return super.getItemViewType(position);
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View layout = null;
        if (mList.isEmpty())
        {
            layout = mEmpty == null ? LayoutInflater.from(mActivity.getApplicationContext()).inflate(R.layout.layout_empty, parent, false) : mEmpty;
        } else
        {
            layout = LayoutInflater.from(mActivity.getApplicationContext()).inflate(resourceId, parent, false);
        }
        BaseViewHolder holder = BaseViewHolder.getViewHolder(layout, mActivity);
        return holder;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position)
    {
        if (mList.isEmpty() && getItemViewType(position) == EMPTY)
        {
            return;
        }
        int layoutPosition = holder.getLayoutPosition();
        convert(holder, layoutPosition, getItem(layoutPosition));
    }


    @Override
    public int getItemCount()
    {
        return mList.isEmpty() ? 1 : mList.size();
    }



    public abstract void convert(BaseViewHolder holder, int position, T t);


}
