package net.cpsec.zfwx.lawyer_recruitment.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.cpsec.zfwx.lawyer_recruitment.R;
import net.cpsec.zfwx.lawyer_recruitment.adapter.GalleryAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WenTiXiangQictivity extends Activity {

    private RecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private List<Integer> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wen_ti_xiang_qi_ctivity);

        initDatas();
//得到控件
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview_horizontal);
//设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
//设置适配器
        mAdapter = new GalleryAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initDatas() {
        mDatas = new ArrayList<>(Arrays.asList(
                R.mipmap.icon_shangchuan, R.mipmap.icon_shangchuan, R.mipmap.icon_shangchuan,
                R.mipmap.icon_shangchuan, R.mipmap.icon_shangchuan, R.mipmap.icon_shangchuan,
                R.mipmap.icon_shangchuan, R.mipmap.icon_shangchuan, R.mipmap.icon_shangchuan));
    }



}
