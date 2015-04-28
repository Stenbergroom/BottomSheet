package com.stenbergroom.bottomsheet.app;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.cocosw.bottomsheet.BottomSheet;
import com.cocosw.query.CocoQuery;

/**
 * Created by Sten on 28.04.2015.
 */
public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private CocoQuery q;
    private int action;
    private ArrayAdapter<String> adapter;
    private BottomSheet sheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(getIntent().getBooleanExtra("style", false)){
            setTheme(R.style.StyleTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_main);
        action = getIntent().getFlags();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        q = new CocoQuery(this);
        setTitle(getIntent().getStringExtra("title"));
        String items[] = new String[]{"John Smith", "Roy Jones", "Petr Ivanov", "Vasya Pupkin", "Vasiliy Palkin"};
        q.id(R.id.listView)
                .adapter(adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, items))
                .itemClicked(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showDialog(position);
    }

    private Drawable getRoundedBitmap(int imageId){
        Bitmap src = BitmapFactory.decodeResource(getResources(), imageId);
        Bitmap dst;
        if(src.getWidth() >= src.getHeight()){
            dst = Bitmap.createBitmap(src, src.getWidth() / 2 - src.getHeight() / 2, 0, src.getHeight(), src.getHeight());
        }else{
            dst = Bitmap.createBitmap(src, 0, src.getHeight() / 2 - src.getWidth() / 2, src.getWidth(), src.getWidth());
        }
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), dst);
        roundedBitmapDrawable.setCornerRadius(dst.getWidth() / 2);
        roundedBitmapDrawable.setAntiAlias(true);
        return roundedBitmapDrawable;
    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int position, Bundle args) {
        return super.onCreateDialog(position, args);
    }
}
