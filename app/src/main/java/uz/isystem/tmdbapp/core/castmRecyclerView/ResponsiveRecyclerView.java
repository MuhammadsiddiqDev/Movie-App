package uz.isystem.tmdbapp.core.castmRecyclerView;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ResponsiveRecyclerView extends RecyclerView {

    private Context context;
    private int columnCount = 1;
    private boolean pagingEnable = false;
    private LayoutManager layoutManager;
    private RecyclerViewScrollListener viewScrollListener;

    private int previousTotalItemCount = 0;
    private boolean loading = false;

    private int page = 0;

    public ResponsiveRecyclerView(@NonNull Context context) {
        super(context);
        setup(context);
    }

    public ResponsiveRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup(context);
    }

    public ResponsiveRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context);
    }

    private void setup(Context context) {
        if (!isInEditMode()) {
            this.context = context;
            notifyColumnCount();
        }
    }

    private void notifyColumnCount() {

        //Check UI State Landscape or Portrait

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            this.columnCount = columnCount;
        } else {
            this.columnCount = 2 * columnCount;
        }


        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanCount(columnCount);
        }
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
        notifyColumnCount();
    }

    public void enablePagination(boolean pagingEnable) {
        this.pagingEnable = pagingEnable;
    }


    @Override
    public void setLayoutManager(@Nullable LayoutManager layout) {
        super.setLayoutManager(layout);
        this.layoutManager = layout;
    }

    public void setViewScrollListener(RecyclerViewScrollListener viewScrollListener) {
        this.viewScrollListener = viewScrollListener;
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);

        if (viewScrollListener != null) {
            if (dy > 0) {
                viewScrollListener.hideFab();

                if (pagingEnable) {
                    onEndlessScroll();
                }

            } else {
                viewScrollListener.showFab();
            }
        }

    }

    private void onEndlessScroll() {

        int lastVisibleItemPosition = 0;

        int totalItemCount = layoutManager.getItemCount();


        if (layoutManager instanceof GridLayoutManager) {
            lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }


        if (totalItemCount < previousTotalItemCount) {
            previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                loading = true;
            }
        }

        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        if (!loading && lastVisibleItemPosition + 4 > totalItemCount) {

            this.page = page++;

            viewScrollListener.loadRecyclerData(page);

            loading = true;
        }
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        notifyColumnCount();
        if (getAdapter() != null) {
            getAdapter().notifyDataSetChanged();
        }
    }

    //    tugagan->

//    fab state->show,hide

    interface RecyclerViewScrollListener {

        void loadRecyclerData(int page);

        void showFab();

        void hideFab();
    }

}
