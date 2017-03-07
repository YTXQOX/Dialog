package com.ljstudio.pangpang.dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.ljstudio.pangpang.dialog.adapter.CommonListAdapter;

import java.util.Arrays;
import java.util.List;


public class OptionsDialog extends BaseDialog implements CommonListAdapter.ViewFormater<String> {

    private ListView listView;
    private TextView titleView;

    private List<String> items;
    private int selected;

    private int selectedColor;
    private int selectedArrowImage;
    private int defaultColor;

    private IListener listener;

    public OptionsDialog(Context context, IListener listener, String title, String... options) {
        this(context, listener, title, -1, options);
    }

    public OptionsDialog(Context context, final IListener listener, String title, int selected, String... options) {
        this(context, listener, title, selected, Color.parseColor("#FF6A00"), R.mipmap.dialog_option_selected, options);
    }

    public OptionsDialog(Context context, final IListener listener, String title, int selected, int tintColor, int selectedArrowImage, String... options) {
        super(context, R.layout.dialog_options);

        this.listener = listener;
        this.selected = selected;
        listView = (ListView) findViewById(R.id.listView);
        titleView = (TextView) findViewById(R.id.txtTitle);
        items = Arrays.asList(options);
        listView.setAdapter(new CommonListAdapter<>(context, items, null, R.layout.dialog_item_option, 0, this));
        setTitle(title);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (listener != null) {
                    listener.onSelected(i, items.get(i));
                }
                dismiss();
            }
        });

        this.selectedArrowImage = selectedArrowImage;
        defaultColor = Color.parseColor("#333333");
        selectedColor = tintColor;
    }

    private void setTitle(String title) {
        if (title != null) {
            titleView.setText(title);
        }
    }

    @Override
    protected void onCancel() {
        if (listener != null) {
            listener.onCancel();
        }
    }

    @Override
    public void formatItemView(CommonListAdapter<String> adapter, String item, View view, int index, boolean group) {
        TextView txt = (TextView) view.findViewById(R.id.txtOption);
        txt.setText(item);
        ImageView imgArrow = (ImageView) view.findViewById(R.id.imgSelected);
        imgArrow.setVisibility(index == selected ? View.VISIBLE : View.INVISIBLE);
        if (index == selected) {
            imgArrow.setImageResource(selectedArrowImage);
        }
        txt.setTextColor(index == selected ? selectedColor : defaultColor);
    }


    public static interface IListener {
        void onCancel();

        void onSelected(int index, String option);
    }

}
