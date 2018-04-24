package cn.com.hungry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.com.hungry.drawable.TestDrawable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.text_view);
        TestDrawable testDrawable = new TestDrawable();
        textView.setBackgroundDrawable(testDrawable);
    }
}
