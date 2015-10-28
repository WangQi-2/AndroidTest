package com.wq.androidtest.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.wq.androidtest.R;
import com.wq.androidlibrary.util.ToastUtil;

/**
 * Created by wangqi on 15/9/14.
 */
public class ClipboardActivity extends BaseActivity {

    ClipboardManager mClipboard;
    ClipData mClipData;
    EditText copyField;
    EditText pasteField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipboard);
        mClipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        copyField = (EditText) findViewById(R.id.edittext1);
        copyField.setText("test");
        pasteField = (EditText) findViewById(R.id.edittext2);
    }

    @Override
    protected void findView() {

    }

    public void copy(View view) {
        ToastUtil.showToast(this, "copy");
        String text = copyField.getText().toString();
        ClipData clipData = ClipData.newPlainText("text", text);
        mClipboard.setPrimaryClip(clipData);
    }

    public void paste(View view) {
        ToastUtil.showToast(this, "paste");
        ClipData data = mClipboard.getPrimaryClip();
        ClipData.Item item = data.getItemAt(0);
        String text = item.getText().toString();
        pasteField.setText(pasteField.getText().toString() + " " + text);
    }
}
