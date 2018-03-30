package hu.ait.android.andwallet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by johnc on 10/20/2017.
 */

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView tvProfit = (TextView) findViewById(R.id.tvProfit);
        TextView tvLoss = (TextView) findViewById(R.id.tvLoss);
        TextView tvTotal = (TextView) findViewById(R.id.tvTotal);

        if (getIntent().hasExtra(MainActivity.KEY_PROFIT)) {
            tvProfit.setText("Profit: " + getIntent().getIntExtra(MainActivity.KEY_PROFIT, 0) + "$");
        }

        if (getIntent().hasExtra(MainActivity.KEY_LOSS)) {
            tvLoss.setText("Loss: " + getIntent().getIntExtra(MainActivity.KEY_LOSS, 0) + "$");
        }

        if (getIntent().hasExtra(MainActivity.KEY_TOTAL)) {
            tvTotal.setText("Total: " + getIntent().getIntExtra(MainActivity.KEY_TOTAL, 0) + "$");
        }
    }
}
