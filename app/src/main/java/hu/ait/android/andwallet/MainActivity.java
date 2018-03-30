package hu.ait.android.andwallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import hu.ait.android.andwallet.Adapter.WalletRecyclerAdapter;
import hu.ait.android.andwallet.Data.Wallet;
import hu.ait.android.andwallet.Touch.WalletItemTouchHelperCallback;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_TOTAL = "KEY_TOTAL";
    public static final String KEY_PROFIT = "KEY_PROFIT";
    public static final String KEY_LOSS = "KEY_LOSS";
    public static final int EMPTY_EXPENSE = 0;
    public static final int EMPTY_AMOUNT = 1;
    public static final int ERR = -1;

    private WalletRecyclerAdapter adapter;
    private ToggleButton tbExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewWallet = (RecyclerView) findViewById(R.id.expense_recycler);
        View view = View.inflate(this, R.layout.wallet_row, null);
        tbExpense = (ToggleButton) findViewById(R.id.tbExpense);
        adapter = new WalletRecyclerAdapter();

        recyclerViewWallet.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewWallet.setHasFixedSize(true);

        ItemTouchHelper.Callback callback = new WalletItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerViewWallet);

        recyclerViewWallet.setAdapter(adapter);

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                EditText etExpense = (EditText) findViewById(R.id.etExpense);
                EditText etAmount = (EditText) findViewById(R.id.etAmount);

                if (etExpense.getText().toString().trim().equalsIgnoreCase("") ||
                        etAmount.getText().toString().trim().equalsIgnoreCase("")) {

                    if(etExpense.getText().toString().trim().equalsIgnoreCase("")) {
                        etExpense.setError(getString(R.string.empty_error_msg));
                    }

                    if(etAmount.getText().toString().trim().equalsIgnoreCase("")) {
                        etAmount.setError(getString(R.string.empty_error_msg));
                    }

                } else {
                    String amount_str = etAmount.getText().toString();
                    int imageResource = 0;
                    int value = 0;

                    if (tbExpense.isChecked()) {
                        imageResource = R.drawable.dollar;
                        value = Integer.parseInt(amount_str);

                    } else {
                        imageResource = R.drawable.down;
                        value = (-1 * Integer.parseInt(amount_str));
                    }

                    adapter.addWallet(new Wallet(etExpense.getText().toString(), "$" + Math.abs(value), value, imageResource));

                    etAmount.setText("");
                    etExpense.setText("");
                }
            }
        });
    }

    public void clearWallet() { adapter.clearWallet(); }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_summary:
                Toast.makeText(this, R.string.budget_summary, Toast.LENGTH_SHORT).show();

                Intent summaryIntent = new Intent(getApplicationContext(), SummaryActivity.class);
                summaryIntent.putExtra(KEY_TOTAL, adapter.getTotal());
                summaryIntent.putExtra(KEY_PROFIT, adapter.getProfit());
                summaryIntent.putExtra(KEY_LOSS, adapter.getLoss());
                startActivity(summaryIntent);

                break;

            case R.id.action_clear:
                Toast.makeText(this, R.string.trash_message, Toast.LENGTH_SHORT).show();
                clearWallet();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}