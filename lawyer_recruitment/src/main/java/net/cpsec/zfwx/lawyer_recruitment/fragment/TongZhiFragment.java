package net.cpsec.zfwx.lawyer_recruitment.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.cpsec.zfwx.lawyer_recruitment.R;
import net.cpsec.zfwx.lawyer_recruitment.adapter.QingChunFenXiangAdapter;
import net.cpsec.zfwx.lawyer_recruitment.ui.YRecycleview;
import net.cpsec.zfwx.lawyer_recruitment.utils.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class TongZhiFragment extends Fragment implements YRecycleview.OnRefreshAndLoadMoreListener {

    private YRecycleview yRecycleview;
    private QingChunFenXiangAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_tong_zhi, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        yRecycleview = (YRecycleview) v.findViewById(R.id.yrv_sahre_tongzhi);
        adapter = new QingChunFenXiangAdapter(getActivity());
        yRecycleview.setRefreshAndLoadMoreListener(this);
        yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        yRecycleview.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        yRecycleview.setReFreshComplete();
        Toast.prompt(getActivity(), "刷新完成。测试阶段");

    }

    @Override
    public void onLoadMore() {
        yRecycleview.setNoMoreData(true);
        Toast.prompt(getActivity(), "没有更多数据。测试阶段");
    }
}
