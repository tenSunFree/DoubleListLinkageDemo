package com.example.administrator.doublelistlinkagedemo;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

// TODO:需要修改
public class SortDetailFragment extends Fragment {

    @BindView(R.id.rv)
    RecyclerView mRv;
    Unbinder unbinder;
    private static final String TAG = "SortDetailFragment";
    private ClassifyDetailAdapter mAdapter;
    private GridLayoutManager mManager;
    private List<SortBean> mDatas = new ArrayList<>();
    private ItemHeaderDecoration mDecoration;
    private int mIndex = 0;
    private Context mContext;
    private CheckListener checkListener;
    /**
     * item滚动相关
     */
    private boolean move = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort_detail, container, false);
        mContext = getActivity();
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setListener(CheckListener listener) {
        this.checkListener = listener;
    }

    public void setData(int n) {
        Log.e(TAG, "跳转到第n个item: " + n);
        mRv.stopScroll();
        mIndex = n;
        smoothMoveToPosition(n);
    }

    private void initView() {

        mManager = new GridLayoutManager(mContext, 3);
        // 设置列数，如果是标题是一列否则是3列
        mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mDatas.get(position).isTitle() ? 3 : 1;
            }
        });
        mRv.setLayoutManager(mManager);
        mAdapter = new ClassifyDetailAdapter(mContext, mDatas, new RvListener() {
            @Override
            public void onItemClick(int id, int position) {
                String content = "";
                switch (id) {
                    case R.id.root:
                        content = "title";
                        break;
                    case R.id.content:
                        content = "content";
                        break;
                    default:
                        break;
                }
                // Snackbar snackbar = Snackbar.make(mRv, "当前点击的是" + content + ":" + mDatas.get(position).getName(), Snackbar.LENGTH_SHORT);
                // View mView = snackbar.getView();
                // mView.setBackgroundColor(Color.BLUE);
                // TextView text = mView.findViewById(android.support.design.R.id.snackbar_text);
                // text.setTextColor(Color.WHITE);
                // text.setTextSize(25);
                // snackbar.show();
            }
        });
        mRv.setAdapter(mAdapter);

        // 装饰器
        mDecoration = new ItemHeaderDecoration(mContext, mDatas);
        mRv.addItemDecoration(mDecoration);

        // 滚动监听
        mRv.addOnScrollListener(new RecyclerViewListener());
        mDecoration.setCheckListener(checkListener);
        initData(mContext.getResources().getStringArray(R.array.country));
    }

    private void initData(final String[] data) {
        for (int i = 0; i < data.length; i++) {
            SortBean titleBean = new SortBean(String.valueOf(i), null);
            //头部设置为true
            titleBean.setTitle(true);
            titleBean.setTag(String.valueOf(i));
            titleBean.setCountry(data[i]);
            mDatas.add(titleBean);
            for (int j = 0; j < 5; j++) {
                String[] classify = getResources().getStringArray(R.array.philippines);
                List<String> list = Arrays.asList(classify);
                SortBean sortBean = new SortBean(String.valueOf(i + "行" + j + "个"), list.get(j));
                sortBean.setTag(String.valueOf(i));
                mDatas.add(sortBean);
            }
        }
        mAdapter.notifyDataSetChanged();
        mDecoration.setData(mDatas);
    }

    private void smoothMoveToPosition(int n) {
        int firstItem = mManager.findFirstVisibleItemPosition();
        int lastItem = mManager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRv.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRv.getChildAt(n - firstItem).getTop();
            mRv.scrollBy(0, top);
        } else {
            mRv.scrollToPosition(n);
            move = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class RecyclerViewListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (move && newState == RecyclerView.SCROLL_STATE_IDLE) {
                move = false;
                int n = mIndex - mManager.findFirstVisibleItemPosition();
                if (0 <= n && n < mRv.getChildCount()) {
                    int top = mRv.getChildAt(n).getTop();
                    mRv.smoothScrollBy(0, top);
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (move) {
                move = false;
                int n = mIndex - mManager.findFirstVisibleItemPosition();
                if (0 <= n && n < mRv.getChildCount()) {
                    int top = mRv.getChildAt(n).getTop();
                    mRv.scrollBy(0, top);
                }
            }
        }
    }
}
