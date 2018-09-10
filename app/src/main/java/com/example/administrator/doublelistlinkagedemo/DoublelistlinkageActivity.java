package com.example.administrator.doublelistlinkagedemo;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoublelistlinkageActivity extends AppCompatActivity implements CheckListener {

    @BindView(R.id.rv_sort)
    RecyclerView rvSort;
    @BindView(R.id.lin_fragment)
    FrameLayout linFragment;

    private static final String TAG = "DoublelistlinkageActivi";

    private Context mContext;
    private SortAdapter mSortAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private SortDetailFragment mSortDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doublelistlinkage);
        ButterKnife.bind(this);
        mContext = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        initData();
    }

    private void initView() {
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        rvSort.setLayoutManager(mLinearLayoutManager);
        // 系统默认样式？
        DividerItemDecoration decoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        rvSort.addItemDecoration(decoration);
    }

    private void initData() {
        String[] classify = getResources().getStringArray(R.array.country);
        List<String> list = Arrays.asList(classify);
        Log.e(TAG, "initView: " + list);
        mSortAdapter = new SortAdapter(mContext, list, new RvListener() {
            @Override
            public void onItemClick(int id, int position) {
                if (mSortDetailFragment != null) {
                    setRightChecked(position);
                }
            }
        });
        rvSort.setAdapter(mSortAdapter);
        // 创建右侧列表
        createFragment();
    }

    /**
     * 创建右侧列表
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void createFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        mSortDetailFragment = new SortDetailFragment();
        mSortDetailFragment.setListener(this);
        fragmentTransaction.add(R.id.lin_fragment, mSortDetailFragment);
        fragmentTransaction.commit();
    }

    /**
     * 右侧列表选中位置
     */
    private void setRightChecked(int position) {
        // 选中左侧
        mSortAdapter.setCheckedPosition(position);
        // 移动右侧位置
        mSortDetailFragment.setData(position * 5 + position);
    }

    /**
     * 左侧列表选中位置
     *
     * @param position
     */
    private void setChecked(int position) {
        mSortAdapter.setCheckedPosition(position);
        moveToCenter(position);
    }

    /**
     * 将当前选中的item居中（跟随移动）
     *
     * @param position
     */
    private void moveToCenter(int position) {
        //将点击的position转换为当前屏幕上可见的item的位置以便于计算距离顶部的高度，从而进行移动居中
        View childAt = rvSort.getChildAt(position - mLinearLayoutManager.findFirstVisibleItemPosition());
        if (childAt != null) {
            int y = (childAt.getTop() - rvSort.getHeight() / 2);
            rvSort.smoothScrollBy(0, y);
        }
    }

    @Override
    public void check(int position) {
        setChecked(position);
    }
}
