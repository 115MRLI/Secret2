package com.example.secret.view.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.secret.R;
import com.example.secret.mvp.contract.MainContract;
import com.example.secret.mvp.presenter.MainPresenter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;


public class MainActivity extends BaseActivity implements MainContract.View {
    private MainContract.Presenter presenter;

    @Override
    protected void initVariables(Bundle bundle) {
        //绑定P类
        presenter = new MainPresenter(getActivity(), this, bundle);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activiy_main);
    }

    @Override
    protected void loadData() {

            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Document doc = Jsoup.connect("http://930hh.com/").get();
//                        Element element = doc.body();
//                        String url = element.baseUri();
                        String title = doc.html();
                        Log.e("html3", title);
//                        Log.e("html3", url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("html3", e.getMessage());
                    }
                }
            }.start();



    }
}