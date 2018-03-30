package hu.ait.android.andwallet.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import hu.ait.android.andwallet.Data.Wallet;
import hu.ait.android.andwallet.R;
import hu.ait.android.andwallet.Touch.WalletTouchHelperAdapter;

/**
 * Created by johnc on 10/19/2017.
 */

public class WalletRecyclerAdapter extends RecyclerView.Adapter<WalletRecyclerAdapter.ViewHolder> implements WalletTouchHelperAdapter {

    private static final int ROOT = 0;
    private int total = 0;

    private List<Wallet> walletList;

    public WalletRecyclerAdapter() {
        walletList = new LinkedList<Wallet>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View walletRow = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.wallet_row, parent, false);
        System.out.println("STEP 1");

        return new ViewHolder(walletRow);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        System.out.println("STEP 3");
        Wallet walletData = walletList.get(position);
        holder.tvExpense.setText(walletData.getExpenseTitle());
        holder.tvAmount.setText(walletData.getAmount());
        holder.ivExpense.setImageResource(walletData.getImageResource());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvExpense;
        private TextView tvAmount;
        private ImageView ivExpense;

        public ViewHolder(View itemView) {
            super(itemView);

            tvExpense = itemView.findViewById(R.id.tvExpense);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            ivExpense = itemView.findViewById(R.id.ivExpense);
            System.out.println("STEP 2");
        }
    }

    @Override
    public int getItemCount() {
        return walletList.size();
    }

    @Override
    public void onItemDismiss(int position) {
        walletList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(walletList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(walletList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public void addWallet(Wallet wallet) {
        walletList.add(ROOT, wallet);
        notifyDataSetChanged();
    }

    public void clearWallet() {
        walletList.clear();
        notifyDataSetChanged();
    }

    public int getTotal() {
        int sum = 0;
        for (int i = 0; i < walletList.size(); i++) {
            sum += walletList.get(i).getValue();
        }
        return sum;
    }

    public int getLoss() {
        int loss = 0;
        for (int i = 0; i < walletList.size(); i++) {
            if (walletList.get(i).getValue() < 0) {
                loss += walletList.get(i).getValue();
            }
        }

        return Math.abs(loss);
    }

    public int getProfit() {
        int profit = 0;
        for (int i = 0; i < walletList.size(); i++) {
            if (walletList.get(i).getValue() > 0) {
                profit += walletList.get(i).getValue();
            }
        }

        return profit;
    }
}
