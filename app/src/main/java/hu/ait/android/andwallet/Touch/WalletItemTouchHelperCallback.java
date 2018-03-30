package hu.ait.android.andwallet.Touch;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by johnc on 10/19/2017.
 */

public class WalletItemTouchHelperCallback extends ItemTouchHelper.Callback{

    private WalletTouchHelperAdapter walletTouchHelperAdapter;

    public WalletItemTouchHelperCallback(WalletTouchHelperAdapter walletTouchHelperAdapter) {
        this.walletTouchHelperAdapter = walletTouchHelperAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        walletTouchHelperAdapter.onItemMove(
                viewHolder.getAdapterPosition(),
                target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        walletTouchHelperAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
