package com.wei.cookbook.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.wei.cookbook.R;
import com.wei.cookbook.model.FoodBean;
import com.wei.cookbook.net.FoodPresenter;
import com.wei.cookbook.ui.adapter.BaseDelegateAdapter;
import com.wei.cookbook.ui.adapter.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

/**

 * 功能：菜谱详情
 */

public class FoodDetailActivity extends BaseActivity<FoodPresenter>
{
        @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private FoodBean mData = null;

    private DelegateAdapter mAdapter;
    private BaseDelegateAdapter mDetailAdapter, mStepAdapter, mCommentsTitleAdapter, mCommentsAdapter;

    @Override
    protected int getLayoutResource()
    {
        return R.layout.activity_food_detail;
    }

    @Override
    protected void setStatusBarColor()
    {

    }

    @Override
    protected FoodPresenter createPresenter()
    {
        return new FoodPresenter(this, this);
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        super.initView(savedInstanceState);
        mData = getIntent().getParcelableExtra(FoodDetailActivity.class.getSimpleName());
        setLeftBack();
        VirtualLayoutManager manager = new VirtualLayoutManager(mActivity);
        manager.setInitialPrefetchItemCount(5);
        mRecyclerView.setLayoutManager(manager);
        //关闭动效提升效率
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        //Item高度固定，避免浪费资源
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(5);

        mAdapter = new DelegateAdapter(manager);
        mRecyclerView.setAdapter(mAdapter);

        initAdapter();
    }

    private void initAdapter()
    {
        /*菜品介绍*/
        mDetailAdapter = new BaseDelegateAdapter<FoodBean>(this, R.layout.layout_food_detail_introduce)
        {
            @Override
            public void convert(BaseViewHolder holder, int position, FoodBean data)
            {
                List<String> list = data.getAlbums();
                holder.setCircleImageResource((list != null && list.size() != 0) ? list.get(0) : "",
                        R.dimen.dp_62, R.id.item_image)
                        .setText(data.getTitle(), R.id.item_title)
                        .setText(data.getTags(), R.id.item_descript)
                        .setText(data.getImtro(), R.id.item_text);
                TextView introduce = holder.getView(R.id.layout).findViewById(R.id.item_introduce);
                introduce.setText(getString(R.string.tv_introduce));
            }
        };
        mAdapter.addAdapter(mDetailAdapter);
        mDetailAdapter.setData(mData);

        /*菜品做法步骤*/
        mStepAdapter = new BaseDelegateAdapter<FoodBean>(this, R.layout.layout_food_detail_steps)
        {
            @Override
            public void convert(BaseViewHolder holder, int position, FoodBean data)
            {

                StringBuilder builder = new StringBuilder();
                if (data.getSteps() != null && (!data.getSteps().isEmpty()))
                {
                    for (FoodBean.StepsBean step : data.getSteps())
                    {
                        builder.append(step.getStep())
                                .append("\n");
                    }
                }
                holder.setText(builder, R.id.item_text);
                TextView introduce = holder.getView(R.id.layout).findViewById(R.id.item_introduce);
                introduce.setText(getString(R.string.tv_steps));
            }
        };
        mAdapter.addAdapter(mStepAdapter);
        mStepAdapter.setData(mData);



        };






    }





