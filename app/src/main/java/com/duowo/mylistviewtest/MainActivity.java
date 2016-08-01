package com.duowo.mylistviewtest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private List<Map<String,String>> data;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1、找到list
        list = (ListView) findViewById(R.id.list);
        //2.准备数据
        data = getData();
        //3、填充adapter
        mAdapter = new MyAdapter(this);
        list.setAdapter(mAdapter);
    }

    private List<Map<String,String>> getData() {
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> map;
        for(int i=0;i<15;i++){
            map = new HashMap<>();
            map.put("title","hello"+i);
            list.add(map);
        }
        return list;
    }

//    MyAdapter mAdapter = new MyAdapter(this);

    private class MyAdapter extends BaseAdapter{

        private LayoutInflater mInflater = null;
        private MyAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            /*View item = mInflater.inflate(R.layout.layout,null);
            TextView text = (TextView) item.findViewById(R.id.text);
            text.setText(data.get(i).get("title"));
            return item;*/
            //判断view是否为空,为空则需要创建一个view
            if(view==null){
                holder = new ViewHolder();
                //为item加载布局
                view = mInflater.inflate(R.layout.layout,null);
                holder.text = (TextView) view.findViewById(R.id.text);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }
            holder.text.setText(data.get(i).get("title"));
            return view;
        }
    }

    static class ViewHolder{
        public TextView text;
    }
}
