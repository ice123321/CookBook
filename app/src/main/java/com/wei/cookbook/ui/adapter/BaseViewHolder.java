package com.wei.cookbook.ui.adapter;

import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.wei.cookbook.ui.BaseActivity;
import com.wei.cookbook.utils.ImageUtils;

import java.io.File;

import butterknife.ButterKnife;


public class BaseViewHolder extends RecyclerView.ViewHolder
{
    private final SparseArray<View> views;
    private ImageUtils mUtils;


    private BaseViewHolder(BaseActivity activity, View iteview)
    {
        super(iteview);
        ButterKnife.bind(this, iteview);
        views = new SparseArray<>();
        mUtils = new ImageUtils(activity);
    }

    public static BaseViewHolder getViewHolder(View iteview, BaseActivity activity)
    {
        return new BaseViewHolder(activity, iteview);
    }


    public <T extends View> T getView(int viewId)
    {
        View view = views.get(viewId);
        if (view == null)
        {
            view = itemView.findViewById(viewId);
            if (view != null)
            {
                views.put(viewId, view);
            }
        }
        return (T) view;
    }


    public BaseViewHolder setImageResource(String imageUrls, @IdRes int viewId)
    {
        if (TextUtils.isEmpty(imageUrls)) return this;
        ImageView view = getView(viewId);
        if (view != null)
        {
            mUtils.setImageResource(imageUrls, view);
        }
        return this;
    }


    public BaseViewHolder setImageFileResource(File file, @IdRes int viewId)
    {
        if (file == null || !file.exists()) return this;
        ImageView view = getView(viewId);
        if (view != null)
        {
            mUtils.setImageFileResource(file, view);
        }
        return this;
    }


    public BaseViewHolder setImageResource(int resourceId, @IdRes int viewId)
    {
        ImageView view = getView(viewId);
        if (view != null)
        {
            view.setImageResource(resourceId);
        }
        return this;
    }


    public BaseViewHolder setCircleImageResource(String imageUrls, @DimenRes int resize, @IdRes int viewId)
    {
        if (TextUtils.isEmpty(imageUrls)) return this;
        ImageView view = getView(viewId);
        if (view != null)
        {
            mUtils.setCircleImageResource(imageUrls, resize, view);
        }
        return this;
    }


    /**
     * 加载圆角图片
     */
    public BaseViewHolder setRoundImageResource(String imageUrls, int round, @IdRes int viewId)
    {
        if (TextUtils.isEmpty(imageUrls)) return this;
        ImageView view = getView(viewId);
        if (view != null)
        {
            mUtils.setRoundImageResource(imageUrls, round, view);
        }
        return this;
    }


    public BaseViewHolder setText(CharSequence text, @IdRes int viewId)
    {
        if (TextUtils.isEmpty(text)) return this;
        TextView view = getView(viewId);
        if (view != null)
        {
            view.setText(text);
        }
        return this;
    }


    public BaseViewHolder setText(SpannableString text, @IdRes int viewId)
    {
        if (text == null) return this;
        TextView view = getView(viewId);
        if (view != null)
        {
            view.setText(text);
        }
        return this;
    }


    public BaseViewHolder setBackgroundColor(@ColorInt int color, @IdRes int viewId)
    {
        View view = getView(viewId);
        if (view != null)
        {
            view.setBackgroundColor(color);
        }
        return this;
    }


    public BaseViewHolder setBackgroundResource(int resId, @IdRes int viewId)
    {
        View view = getView(viewId);
        if (view != null)
        {
            view.setBackgroundResource(resId);
        }
        return this;
    }



    public BaseViewHolder setTextColor(@ColorInt int textColor, @IdRes int viewId)
    {
        TextView view = getView(viewId);
        if (view != null)
        {
            view.setTextColor(textColor);
        }
        return this;
    }



    public BaseViewHolder setVisible(@IdRes int viewId, boolean visible)
    {
        View view = getView(viewId);
        if (view != null)
        {
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
        return this;
    }



    public BaseViewHolder setVisible(@IdRes int viewId, int visible)
    {
        View view = getView(viewId);
        if (view != null)
        {
            view.setVisibility(visible);
        }
        return this;
    }


    public BaseViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener)
    {
        View view = getView(viewId);
        if (view != null && listener != null)
        {
            view.setOnClickListener(listener);
        }
        return this;
    }

}
