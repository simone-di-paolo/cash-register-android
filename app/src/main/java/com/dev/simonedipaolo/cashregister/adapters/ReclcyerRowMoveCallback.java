package com.dev.simonedipaolo.cashregister.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Simone Di Paolo on 06/08/2022.
 */
public class ReclcyerRowMoveCallback extends ItemTouchHelper.Callback {

    private RecyclerViewRowTouchHelperContract touchHelperContract;

    public ReclcyerRowMoveCallback(RecyclerViewRowTouchHelperContract touchHelperContract) {
        this.touchHelperContract = touchHelperContract;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(dragFlag, 0);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        this.touchHelperContract.onRowMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());

        return true;
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if(viewHolder instanceof RepartiSettingsAdapter.RepartiViewHolder) {

                RepartiSettingsAdapter.RepartiViewHolder myViewHolder = (RepartiSettingsAdapter.RepartiViewHolder) viewHolder;
                touchHelperContract.onRepartiSettingsAdapterRowSelected(myViewHolder);

            } else if (viewHolder instanceof TipologiaRepartiSettingsAdapter.RepartiViewHolder) {

                TipologiaRepartiSettingsAdapter.RepartiViewHolder myViewHolder = (TipologiaRepartiSettingsAdapter.RepartiViewHolder) viewHolder;
                touchHelperContract.onTipologiaRepartiSettingsRowSelected(myViewHolder);

            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        if(viewHolder instanceof RepartiSettingsAdapter.RepartiViewHolder) {
            RepartiSettingsAdapter.RepartiViewHolder myViewHolder = (RepartiSettingsAdapter.RepartiViewHolder) viewHolder;
            touchHelperContract.onRepartiSettingsAdapterRowClear(myViewHolder);
        } else if (viewHolder instanceof TipologiaRepartiSettingsAdapter.RepartiViewHolder) {
            TipologiaRepartiSettingsAdapter.RepartiViewHolder myViewHolder = (TipologiaRepartiSettingsAdapter.RepartiViewHolder) viewHolder;
            touchHelperContract.onTipologiaRepartiSettingsRowClear(myViewHolder);
        }

    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    public interface RecyclerViewRowTouchHelperContract {
        void onRowMoved(int from, int to);

        void onRepartiSettingsAdapterRowSelected(RepartiSettingsAdapter.RepartiViewHolder myViewHolder);
        void onTipologiaRepartiSettingsRowSelected(TipologiaRepartiSettingsAdapter.RepartiViewHolder myViewHolder);

        void onRepartiSettingsAdapterRowClear(RepartiSettingsAdapter.RepartiViewHolder myViewHolder);
        void onTipologiaRepartiSettingsRowClear(TipologiaRepartiSettingsAdapter.RepartiViewHolder myViewHolder);
    }
}
