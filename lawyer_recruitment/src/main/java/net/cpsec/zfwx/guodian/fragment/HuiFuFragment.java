package net.cpsec.zfwx.guodian.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.activity.TieZiDetailActivity;
import net.cpsec.zfwx.guodian.activity.XiangXiZiLiaoActivity;
import net.cpsec.zfwx.guodian.adapter.CenterTieZiAdapter;
import net.cpsec.zfwx.guodian.entity.ShouCangBean;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.List;
import java.util.Map;


/**
 * 个人中心-发帖-我回复的帖子
 */
public class HuiFuFragment extends BaseFragment implements YRecycleview.OnRefreshAndLoadMoreListener {
    private YRecycleview yRecycleview;
    private CenterTieZiAdapter adapter;
    private boolean isRefreshState = true;//是否刷新
    private List<ShouCangBean.InforBean> inforBeen;
    private List<ShouCangBean.InforBean> moreInforBean;
    private ShouCangBean huiFuBean;
    ShouCangBean.InforBean infor;
    int pos;
String uid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v =inflater.inflate(R.layout.fragment_hui_fu, container, false);
        SharedPreferences sp =getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid= sp.getString("uid","");
        initData();
        initView(v);
        return v;
    }
    private void initView(View v) {
        yRecycleview = (YRecycleview) v.findViewById(R.id.hf_tiezilist);
        yRecycleview.setRefreshAndLoadMoreListener(this);
    }
//MyApplication.UID是存在MyApplication中的假数据，后期改成从偏好设置中拿
    private void initData() {
        RequestMap params = new RequestMap();
        params.put("uid",uid);
        setParams(NetUrl.CENTER_HUIFU_TIEZI, params, 1);
    }
    //数据请求成功后数据处理方法
    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            huiFuBean = JSON.parseObject(response, ShouCangBean.class);
            if (huiFuBean == null) {
                Toast.prompt(getActivity(), "目前没有数据");
            }
            if (isRefreshState) {
                yRecycleview.setReFreshComplete();
                inforBeen = huiFuBean.getInfor();
                Debugging.debugging("positionLists      =   " + (huiFuBean.getInfor().toString()));
            } else {
                moreInforBean = huiFuBean.getInfor();
                inforBeen.addAll(moreInforBean);
            }
            setAdapter();
        } catch (Exception e) {
            Toast.prompt(getActivity(), "数据异常");
        }
    }
    private void setAdapter() {
        if (isRefreshState && null != inforBeen) {
            adapter = new CenterTieZiAdapter(getActivity(), inforBeen);
            yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

        adapter.setOnTitleClickListener(new CenterTieZiAdapter.OnTitleClickListener() {
            @Override
            public void onTitleClick(String id, int position) {
                Intent intent = new Intent(getActivity(), TieZiDetailActivity.class);
                Debugging.debugging("position+++++++++++++++++++++++" + position);
                infor = inforBeen.get(position);
                pos = infor.getId();
                Bundle bundle = new Bundle();
                Log.e("回复界面的artical_id", "artical_id: "+pos);
                bundle.putString("artical_id", pos + "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        adapter.setHeadClickListener(new CenterTieZiAdapter.OnHeadClickListener() {
            @Override
            public void onHeadClick(String id, int position) {
                Intent intent = new Intent(getActivity(), XiangXiZiLiaoActivity.class);
                infor = inforBeen.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("phone", infor.getPhone());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter.setOnPicClickListener(new CenterTieZiAdapter.OnPicClickListener() {
            @Override
            public void onPicClick(String id, int position) {
                Intent intent = new Intent(getActivity(), TieZiDetailActivity.class);
                infor = inforBeen.get(position);
                pos = infor.getId();
                Bundle bundle = new Bundle();
                bundle.putString("artical_id", pos + "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter.setOnLLClickListener(new CenterTieZiAdapter.OnLLClickListener() {
            @Override
            public void onLLClick(String id, int position) {
                Intent intent = new Intent(getActivity(), TieZiDetailActivity.class);
                infor = inforBeen.get(position);
                pos = infor.getId();
                Bundle bundle = new Bundle();
                bundle.putString("artical_id", pos + "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        isRefreshState = true;
        yRecycleview.setReFreshComplete();
        initData();
        //    Toast.prompt(getActivity(), "刷新完成。测试阶段");
    }

    @Override
    public void onLoadMore() {
        isRefreshState = false;
        initData();
        yRecycleview.setNoMoreData(true);
        //Toast.prompt(getActivity(), "没有更多数据。测试阶段");
    }

}
