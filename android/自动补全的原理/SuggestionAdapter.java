package com.example.xiyougame;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class SuggestionAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private SuggestionDBManage mDbManage;
    private ArrayFilter mFilter;
    private List<SuggestionItem> mFilterItems = new ArrayList<SuggestionItem>();// 过滤后的item

    public SuggestionAdapter(Context context, SuggestionDBManage dbManage) {
        this.context = context;
        mDbManage = dbManage;
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }

    private class SuggestionAsyncTask extends AsyncTask<String, Void, List<SuggestionItem>>{

        @Override
        protected List<SuggestionItem> doInBackground(String... params) {
            List<SuggestionItem> suggestionItems = mDbManage.query(params[0]);
            return suggestionItems;
        }

        @Override
        protected void onPostExecute(List<SuggestionItem> result) {
            mFilterItems = result;
            notifyDataSetChanged();
            super.onPostExecute(result);
        }        
        
    }
    
    private class ArrayFilter extends Filter {

        @Override //   prefix过滤数据的约束
        protected FilterResults performFiltering(CharSequence prefix) {
            String prefixString = prefix.toString().toLowerCase();
            startSuggestionsAsync(prefixString);
            return null;
        }

        private void startSuggestionsAsync(String prefixString) {
            new SuggestionAsyncTask().execute(prefixString.toString());
        }

        @Override
        protected void publishResults(CharSequence constraint,
                FilterResults results) {}

    }

    @Override
    public int getCount() {
        return mFilterItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mFilterItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(
                    R.layout.simple_list_item_for_autocomplete, null);
            holder.url = (TextView) convertView.findViewById(R.id.url);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.url.setText(mFilterItems.get(position).getUrl());
        holder.title.setText(mFilterItems.get(position).getTitle());
        return convertView;
    }

    class ViewHolder {
        TextView url;
        TextView title;
    }
}