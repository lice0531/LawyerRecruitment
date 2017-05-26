package net.cpsec.zfwx.lawyer_recruitment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import net.cpsec.zfwx.lawyer_recruitment.R;
import net.cpsec.zfwx.lawyer_recruitment.entity.ShengDetail;
import net.cpsec.zfwx.lawyer_recruitment.utils.LocalDisplay;

import java.util.List;

/**
 * Created by szh on 2017/5/15.
 */

public class XinShengTiWenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ShengDetail> shengList;

    public XinShengTiWenAdapter(Context context, List<ShengDetail> shengList) {
        this.context = context;
        this.shengList = shengList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_tiwen, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).tv_name.setText(shengList.get(position).getUsername());
        ((ViewHolder) holder).tv_question.setText(shengList.get(position).getContent());
        ((ViewHolder) holder).tv_answer.setText(null);
        ((ViewHolder) holder).tv_shijian.setText(shengList.get(position).getAsktime()+"");
       // ((ViewHolder) holder).tv_shijian.setText(shengList.get(position).getAsktime());
        ((ViewHolder) holder).tv_dianzan.setText(shengList.get(position).getPraise()+"");
    }

    @Override
    public int getItemCount() {
        return shengList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RoundedImageView riv_avadar;
        private TextView tv_name, tv_question, tv_answer, tv_shijian, tv_dianzan;

        public ViewHolder(View itemView) {
            super(itemView);
            LocalDisplay.init(itemView.getContext());

            itemView.setOnClickListener(this);

            riv_avadar = (RoundedImageView) itemView.findViewById(R.id.riv_xinsheng_avadar);
            tv_name = (TextView) itemView.findViewById(R.id.tv_xinsheng_name);
            tv_question = (TextView) itemView.findViewById(R.id.tv_xinsheng_question);
            tv_answer = (TextView) itemView.findViewById(R.id.tv_xinsheng_huida);
            tv_shijian = (TextView) itemView.findViewById(R.id.tv_xinsheng_time);
            tv_dianzan = (TextView) itemView.findViewById(R.id.tv_xinsheng_dainzxan);
        }

        @Override
        public void onClick(View v) {
//TODO
        }
    }
}