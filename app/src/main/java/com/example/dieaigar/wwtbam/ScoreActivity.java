package com.example.dieaigar.wwtbam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import databases.SQLHelper;

public class ScoreActivity extends AppCompatActivity {
    static int rows = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("LOCALTAB");
        spec.setIndicator("Local");
        spec.setContent(R.id.tab_layout_included);
        host.addTab(spec);

        spec = host.newTabSpec("FRIENDSTAB");
        spec.setIndicator("Friends");
        spec.setContent(R.id.tab_layout_included);
        host.addTab(spec);

        ArrayList<HashMap<String, String>> scores = SQLHelper.getInstance(this).getScores();
        for(int i=0; i<scores.size(); i++) {
            Log.d("DB DEBUG", scores.get(i).toString());
        }

        addRow(10, "Pablo");
        addRow(100, "Diego");
    }

    private void addRow(int score, String name){
        TableLayout tl = (TableLayout) findViewById(R.id.score_table);
        TableRow row = new TableRow(this);

        TextView tvName = new TextView(this);
        tvName.setText(name);
        TextView tvScore = new TextView(this);
        tvScore.setText(score + "");

        SQLHelper.getInstance(this).addScore(tvName.getText().toString(), tvScore.getText().toString());

        row.addView(tvName);
        row.addView(tvScore);

        tl.addView(row, rows++);
    }
}
