package info.hellovass.hv_tea.adapter.viewgroup.util;

import android.view.View;
import android.view.ViewGroup;
import info.hellovass.hv_tea.adapter.viewgroup.base.MultiItemTypeAdapter;
import info.hellovass.hv_tea.adapter.viewgroup.base.ViewGroupAdapter;

/**
 * Created by hello on 2017/2/28.
 */

public final class ViewGroupHelper {

  public static void addViews(ViewGroup viewGroup, ViewGroupAdapter adapter) {

    addViews(viewGroup, adapter, true, null, null);
  }

  public static void addViews(final ViewGroup viewGroup, ViewGroupAdapter adapter,
      boolean ifRemoveAllViews, final MultiItemTypeAdapter.OnItemClickListener onItemClickListener,
      final MultiItemTypeAdapter.OnItemLongClickListener onItemLongClickListener) {

    if (viewGroup == null || adapter == null) {

      return;
    }

    if (ifRemoveAllViews && viewGroup.getChildCount() > 0) {

      viewGroup.removeAllViews();
    }

    int count = adapter.getCount();

    for (int index = 0; index < count; index++) {

      final View itemView = adapter.getView(viewGroup, index);

      viewGroup.addView(itemView);

      if (onItemClickListener != null && !itemView.isClickable()) {

        final int position = index;

        itemView.setOnClickListener(new View.OnClickListener() {

          @Override public void onClick(View v) {

            onItemClickListener.onItemClick(viewGroup, itemView, position);
          }
        });
      }

      if (onItemLongClickListener != null && !itemView.isLongClickable()) {

        final int position = index;

        itemView.setOnLongClickListener(new View.OnLongClickListener() {

          @Override public boolean onLongClick(View v) {

            return onItemLongClickListener.onItemLongClick(viewGroup, itemView, position);
          }
        });
      }
    }
  }
}
