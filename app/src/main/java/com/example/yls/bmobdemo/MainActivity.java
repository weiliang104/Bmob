package com.example.yls.bmobdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity implements  onDelListener{
        private RecyclerView mRecyclerView;
        private List<Person> personList = new ArrayList<>(); //假设这块内存地址是0xABCD
        private MyAdapter mAdapter;
        private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //第一：默认初始化
        Bmob.initialize(this, "26b40d61346b8acfb31cf0d785337151");


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new MyAdapter(personList, MainActivity.this);

        LinearLayoutManager lm = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(mAdapter);
        register= (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });

    }

    private void deleteOne() {

        Person p = new Person();
        p.delete("13fd2f557b", new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
                    ((TextView)findViewById(R.id.txt)).setText("success");
                }else{
                    ((TextView)findViewById(R.id.txt)).setText(e.toString());
                }
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        queryAll();
    }

    private void queryAll() {


        BmobQuery<Person> query = new BmobQuery<>();
        query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> list, BmobException e) {
                if(e == null){
//                    personList.clear();;
//                    personList.addAll(list);
                    personList = list;  // 改变指向0XABEF
                    mAdapter.changData(personList);
                    ((TextView)findViewById(R.id.txt)).setText("数据返回 " + personList.size());
//                    Toast.makeText(MainActivity.this, "数据返回 ", Toast.LENGTH_LONG).show();
//                    Log.i("Bmob", "数据返回 " + personList.size());
                }else{
                    Log.e("queryAll", e.toString());
                }
            }
        });
    }

    private void queryOne() {
        BmobQuery<Person> query = new BmobQuery<>();
        query.getObject("a0ed538f94", new QueryListener<Person>() {
            @Override
            public void done(Person person, BmobException e) {
                if(e == null){
                    ((TextView)findViewById(R.id.txt)).setText(person.getName() + "  " + person.getAddress());
                }else{
                    ((TextView)findViewById(R.id.txt)).setText(e.toString());
                }
            }
        });
    }

//

    @Override
    public void del(String name) {
            // 操作数据库实现删除
    }

            @Override
            public void refresh() {
                queryAll();
            }
}
