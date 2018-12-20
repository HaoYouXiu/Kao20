package com.bwei.model.kao20;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.model.kao20.Bean.CarBean;
import com.bwei.model.kao20.adapter.CarRecyclerAdapter;
import com.bwei.model.kao20.mvp.CarPresenter;
import com.bwei.model.kao20.mvp.CarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements CarView {

    @BindView(R.id.txt_recycler)
    RecyclerView txtRecycler;
    @BindView(R.id.car_box)
    CheckBox carBox;
    @BindView(R.id.car_zong)
    TextView carZong;
    @BindView(R.id.car_mai)
    TextView carMai;
    private List<CarBean.DataBean> mList = new ArrayList<>();


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage( msg );
            switch (msg.what) {
                case 1:
                    setAdapte();
                    break;
            }
        }
    };
    private CarRecyclerAdapter mAdapter;
    private Object mTotal;
    private List<CarBean.DataBean.ListBean> mList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind( this );

        setAdapte();

        //p层
        CarPresenter presenter = new CarPresenter( this );
        presenter.car( "71" );

    }

    private void setAdapte() {
        //设置布局故那里去
        LinearLayoutManager layoutManager = new LinearLayoutManager( this );
        layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        txtRecycler.setLayoutManager( layoutManager );
        //适配器
        mAdapter = new CarRecyclerAdapter( this, mList );
        txtRecycler.setAdapter( mAdapter );

        //接收接口
        mAdapter.setOnCar( new CarRecyclerAdapter.onCarItemClick() {
            @Override
            public void onCheBoxClick(View view, int i) {
                getTotal();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void oncheAddClick(View view, int i) {
                getTotal();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void oncheSubClick(View view, int i) {
                getTotal();
                mAdapter.notifyDataSetChanged();
            }
        } );
    }

    //总价
    public void getTotal() {
        int num = 0;
        double tatol = 0;
        for (int i = 0; i < mList.size(); i++) {
            List<CarBean.DataBean.ListBean> list = mList.get( i ).getList();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSelected() == 1){
                    num += list.get(j).getNum();
                    tatol += list.get(j).getNum() * list.get( j ).getPrice();
                }
            }
        }
        carZong.setText( "总价:￥" + tatol );
    }

    @OnClick(R.id.txt_recycler)
    public void onViewClicked() {

    }

    @Override
    public void carSuccess(CarBean success) {
        mList = success.getData();
        mHandler.sendEmptyMessage( 1 );
    }

    @Override
    public void carFail(String carfail) {
        Toast.makeText( this, carfail, Toast.LENGTH_SHORT ).show();
    }

    @OnClick({R.id.car_box, R.id.car_mai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.car_box:
                //全选
                for (int i = 0; i < mAdapter.getItemCount(); i++) {
                    if (carBox.isChecked()){
                        mList.get( i ).setIsChacked(1);
                    }else {
                        mList.get( i ).setIsChacked( 0 );
                    }
                    mList1 = mList.get( i ).getList();
                    for (int j = 0; j < mList1.size(); j++) {
                        if (carBox.isChecked()){
                            mList1.get( j ).setSelected(1);
                        }else {
                            mList1.get( j ).setSelected(0);
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                    getTotal();
                }
                break;
            case R.id.car_mai:
                break;
        }
    }
}
