package hu.ait.android.andwallet.Touch;

/**
 * Created by johnc on 10/19/2017.
 */

public interface WalletTouchHelperAdapter {

    void onItemDismiss(int position);

    void onItemMove(int fromPosition, int toPosition);
}

