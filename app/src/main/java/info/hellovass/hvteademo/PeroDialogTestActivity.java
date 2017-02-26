package info.hellovass.hvteademo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.hellovass.hvteademo.dialog.CommonDialog;

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
      mCommonDialog.setTitle("通用 Dialog");
      mCommonDialog.setMessage("寒风让身体颤抖着，却听到了歌声—— \n"
          + "就像是合着，夕阳下的音乐室里，我弹着的吉他一般。 就像是合着，隔壁的教室内，不知是谁演奏的钢琴一般。 \n"
          + "屋顶上传来的，如同铃儿般的清澈的高声， 将我们那支离破碎的三重旋律，联系在了一起。 \n"
          + "开始的季节，已是深秋。 彼时，那个Ta恋上了那个Ta。 \n"
          + "无论是谁，都那样努力过。 无论是谁，都怀着强烈的心意向前迈去。 无论是谁，都那样不懈地、一往无前地、诚实地—— 去得到，那留存于心底的，无可替代的那一瞬间。 \n"
          + "所以彼时，那个Ta无可救药地恋上了那个Ta。 但却是迟了一步的，绝不可以的那份感情。 \n"
          + "然后冬天——不断飘落堆积的雪，终会盖去所有的罪恶。 最终春天——和融化的雪一起，下达所有的惩罚。寒风让身体颤抖着，却听到了歌声—— \n");
      mCommonDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

        @Override public void onClick(DialogInterface dialog, int which) {

          dialog.dismiss();
        }
      });
      mCommonDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
        @Override public void onClick(DialogInterface dialog, int which) {

          dialog.dismiss();
        }
      });
    }

    return mCommonDialog;
  }
}
