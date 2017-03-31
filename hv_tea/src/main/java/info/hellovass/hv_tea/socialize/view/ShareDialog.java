package info.hellovass.hv_tea.socialize.view;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import info.hellovass.hv_tea.R;
import info.hellovass.hv_tea.adapter.recyclerview.CommonAdapter;
import info.hellovass.hv_tea.adapter.recyclerview.ViewHolder;
import info.hellovass.hv_tea.adapter.recyclerview.base.MultiViewTypeAdapter;
import info.hellovass.hv_tea.dialog.CommonDialog;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2017/3/31.
 */

public class ShareDialog extends CommonDialog {

  private ChooseSharePlatformListener mSharePlatformListener;

  public ShareDialog(Context context) {
    super(context);
    init();
  }

  public ShareDialog(Context context, int themeResId) {
    super(context, themeResId);
    init();
  }

  protected ShareDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
    super(context, cancelable, cancelListener);
    init();
  }

  private void init() {

    View shareView = getLayoutInflater().inflate(R.layout.dialog_share, null);

    RecyclerView recyclerView = (RecyclerView) shareView.findViewById(R.id.recyclerview);

    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.addItemDecoration(
        new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    recyclerView.setHasFixedSize(true);

    CommonAdapter<Target> adapter =
        new CommonAdapter<Target>(getContext(), R.layout.listitem_dialog_share,
            provideActionList()) {

          @Override protected void convert(ViewHolder holder, Target target, int position) {

            holder.setImageResource(R.id.iv_img, target.iconResId);
            holder.setText(R.id.tv_title, target.title);
          }
        };
    adapter.setOnItemClickListener(new MultiViewTypeAdapter.OnItemClickListener<Target>() {

      @Override public void onItemClick(View view, Target target, int position) {

        if (mSharePlatformListener == null) {

          return;
        }

        switch (position) {

          case 0:
            mSharePlatformListener.shareToWeChatFriend();
            break;

          case 1:
            mSharePlatformListener.shareToWechatCircle();
            break;

          case 2:
            mSharePlatformListener.shareToSina();
            break;

          case 3:
            mSharePlatformListener.copyUrl();
            break;

          default:
            break;
        }

        ShareDialog.this.dismiss();
      }
    });

    recyclerView.setAdapter(adapter);

    setContent(shareView);
  }

  private List<Target> provideActionList() {

    List<Target> targetList = new ArrayList<>();

    targetList.add(new Target(R.drawable.umeng_socialize_wechat, "微信好友"));
    targetList.add(new Target(R.drawable.umeng_socialize_wxcircle, "微信朋友圈"));
    targetList.add(new Target(R.drawable.umeng_socialize_sina, "新浪微博"));
    targetList.add(new Target(R.drawable.umeng_socialize_copyurl, "复制链接"));

    return targetList;
  }

  public ShareDialog setSharePlatformListener(ChooseSharePlatformListener sharePlatformListener) {
    mSharePlatformListener = sharePlatformListener;
    return this;
  }

  public interface ChooseSharePlatformListener {

    void shareToWeChatFriend();

    void shareToWechatCircle();

    void shareToSina();

    void copyUrl();
  }

  private static class Target {

    public int iconResId;

    public String title;

    public Target(int iconResId, String title) {
      this.iconResId = iconResId;
      this.title = title;
    }
  }
}
