package com.example.jinbangzhu.stickheaderlist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private ListView mListView;
    private int overFlowViewPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.list);

        mListView.setAdapter(new MyAdapter());


        final TextView overFlowTextView = (TextView) findViewById(R.id.tv_text);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d("", "onScrollStateChanged  scrollState = " + scrollState);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d("", String.format("onScrollStateChanged  firstVisibleItem=%d, visibleItemCount=%d totalItemCount=%d", firstVisibleItem, visibleItemCount, totalItemCount));
                View nextView = view.getChildAt(1);

                if (null != nextView) {

                    int position[] = new int[2];
                    nextView.getLocationInWindow(position);
                    int nextViewPositionY = position[1];
                    if (overFlowViewPosition == 0) {
                        overFlowTextView.getLocationInWindow(position);
                        overFlowViewPosition = position[1];
                    }
                    Log.d("", "overViewPositionY = " + overFlowViewPosition);
                    Log.d("", "nextViewPositionY = " + nextViewPositionY);
                    int gap = nextViewPositionY - overFlowViewPosition - overFlowTextView.getHeight();
                    if (gap < 0) {
                        overFlowTextView.setY(gap);
                    } else {
                        overFlowTextView.setY(0);
                    }


//                    TextView nextViewHeader = (TextView) nextView.findViewById(R.id.tv_text);
                    overFlowTextView.setText(String.valueOf(firstVisibleItem));
                }

            }
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_item, parent, false);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.tv_text);
            tv.setText(String.valueOf(position));
            return convertView;
        }


    }
}
