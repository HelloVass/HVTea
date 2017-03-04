package info.hellovass.hvteademo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.hellovass.hv_tea.adapter.recyclerview.CommonAdapter;
import info.hellovass.hv_tea.adapter.recyclerview.ViewHolder;
import info.hellovass.hvteademo.dialog.base.CommonDialog;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2017/2/25.
 */

public class PeroDialogTestActivity extends AppCompatActivity {

  @BindView(R.id.btn_show_dialog) View mShowDialogBtn;

  private CommonDialog mCommonDialog;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_perodialog);
    ButterKnife.bind(this);

    mShowDialogBtn.setOnClickListener(new View.OnClickListener() {

      @Override public void onClick(View v) {

        mCommonDialog = provideCommonDialog();

        mCommonDialog.show();
      }
    });
  }

  private CommonDialog provideCommonDialog() {

    if (mCommonDialog == null) {

      mCommonDialog = new CommonDialog(this);
      mCommonDialog.setItems(
          new CommonAdapter<String>(this, R.layout.listitem_dialog_common, provideOptionList()) {

            @Override protected void convert(ViewHolder holder, String entity, int position) {

              holder.setText(R.id.tv_title, entity);
            }
          }, null);
    }

    return mCommonDialog;
  }

  private List<String> provideOptionList() {

    List<String> options = new ArrayList<>();

    options.add("选项一");
    options.add("选项二");
    options.add("选项三");

    return options;
  }
}
