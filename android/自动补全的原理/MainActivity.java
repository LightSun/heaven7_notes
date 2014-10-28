package com.example.xiyougame;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener{
    
    private AutoCompleteTextView mAutoCompleteTextView;
    private Button mSaveButton;
    private SuggestionDBManage mUrlDBManage;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mUrlDBManage = new SuggestionDBManage(getApplicationContext());
        
        mAutoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autotextview);
        mAutoCompleteTextView.setText(""); 
        mAutoCompleteTextView.setOnItemClickListener(this);
        
        SuggestionAdapter adapter = new SuggestionAdapter(this, mUrlDBManage);
        mAutoCompleteTextView.setAdapter(adapter);
        
        mSaveButton = (Button)findViewById(R.id.savaurl);
        mSaveButton.setOnClickListener(new OnClickListener() {
            //这部分代码只是为了添加初始数据，只考虑http://开头的地址
            @Override
            public void onClick(View v) {
                String prefix = "http://";
                String urlString = mAutoCompleteTextView.getText().toString();
                if (!urlString.startsWith(prefix)) {
                    mUrlDBManage.insert(new SuggestionItem(prefix + urlString, "unknow"));
                }
                else {
                    mUrlDBManage.insert(new SuggestionItem(urlString, "unknow"));
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        mUrlDBManage.close();
        super.onDestroy();
    }

 
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
        TextView urlteTextView = (TextView)view.findViewById(R.id.title);
        mAutoCompleteTextView.setText(urlteTextView.getText());
    }

}