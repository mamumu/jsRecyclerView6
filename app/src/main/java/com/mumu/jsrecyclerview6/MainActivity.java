package com.mumu.jsrecyclerview6;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_test)
    RecyclerView rvTest;
    @BindView(R.id.srl_test)
    SmartRefreshLayout srlTest;

    private TestAdapter mTestAdapter;
    private ArrayList<TestEntity.ResultBean> mResult = new ArrayList<>();
    private ArrayList<MultiItemEntity> mList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 2; i++) {
            TestEntity.ResultBean resultBean = new TestEntity.ResultBean();
            resultBean.setTitle1("小狗" + i);
            resultBean.setTitle2("小猫" + i);
            List<TestEntity.ResultBean.ListBean> list = new ArrayList<>();
            for (int j = 0; j < 13; j++) {
                TestEntity.ResultBean.ListBean listBean = new TestEntity.ResultBean.ListBean();
                listBean.setMessage1("小小小狗" + j);
                listBean.setMessage2("小小小猫" + j);
                list.add(listBean);
            }
            resultBean.setList(list);
            resultBean.setSubItems(list);
            mResult.add(resultBean);
            mList.add(resultBean);
        }
    }

    private void initView() {
        refreshView();
        smartRefreshView();
    }

    /**
     * 刷新消息列表
     */
    private void refreshView() {
        //1,加载空布局文件，便于第五步适配器在没有数据的时候加载
        View emptyView = View.inflate(this, R.layout.empty_view, null);
        //2，设置LayoutManager,LinearLayoutManager表示竖直向下
        rvTest.setLayoutManager(new LinearLayoutManager(this));
        //3，初始化一个无数据的适配器
        mTestAdapter = new TestAdapter(null);
        //4，绑定recyclerView和适配器
        //5，动画加载，默认关闭
//        mTestAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        rvTest.setAdapter(mTestAdapter);
        //6，给recyclerView设置空布局
        mTestAdapter.setEmptyView(emptyView);
        //7,展开所以
        mTestAdapter.expandAll();
        mTestAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d("aaa",position+"");
                switch (adapter.getItemViewType(position)){
                    case TestAdapter.TYPE_LEVEL_0:
                        switch (view.getId()){
                            case R.id.item_title1:
                                Toast.makeText(MainActivity.this,  "333333",
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.item_title2:
                                Toast.makeText(MainActivity.this,  "444444",
                                        Toast.LENGTH_SHORT).show();
                                break;
                        }
                        break;
                    case TestAdapter.TYPE_LEVEL_1:
                        switch (view.getId()){
                            case R.id.item_message1:
                                Toast.makeText(MainActivity.this,  "66666",
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.item_message2:
                                Toast.makeText(MainActivity.this,  "777777",
                                        Toast.LENGTH_SHORT).show();
                                break;
                        }

                        break;
                }
            }
        });
    }

    /**
     * MainActivity中增加下拉刷新和上拉加载的监听方法
     */
    private void smartRefreshView() {
        srlTest.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //下拉刷新,一般添加调用接口获取数据的方法
                getData(2);
                //结束下拉刷新
                refreshLayout.finishRefresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //上拉加载，一般添加调用接口获取更多数据的方法
                getData(3);
                //结束上拉加载，展示没有更多数据
//                refreshLayout.finishLoadMoreWithNoMoreData();
                //结束上拉加载
                refreshLayout.finishLoadMore();
            }
        });
    }

    /**
     * 获取数据的方法
     * 该方法纯属展示各种效果，实际应用时候请自己根据需求做判断即可
     *
     * @param mode 模式：1为刚开始进来加载数据 空数据 2为下拉刷新 3为上拉加载
     */
    private void getData(int mode) {
        //添加临时数据，一般直接从接口获取
        switch (mode) {
            case 1:
                break;
            case 2:
                mList.clear();
                initData();
                //更新数据
                mTestAdapter.setNewData(mList);
                break;
            case 3:
                initData();
                //更新数据
                mTestAdapter.setNewData(mList);
                break;
        }
    }
}
