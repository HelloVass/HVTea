package info.hellovass.hvteademo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.hellovass.hv_tea.tag_layout.BaseAdapter;
import info.hellovass.hv_tea.tag_layout.PeroTagLayout;
import info.hellovass.hv_tea.tag_layout.ViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2016/12/26.
 */

public class PeroTagLayoutTestActivity extends AppCompatActivity {

  @BindView(R.id.taglayout) PeroTagLayout mPeroTagLayout;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_perotaglayout_test);
    ButterKnife.bind(this);

    mPeroTagLayout.setAdapter(new BaseAdapter<String>(this, R.layout.tag_pero, generateTags()) {

      @Override protected void convert(ViewHolder holder, int position, String tag) {

        holder.setText(R.id.tv_tag, tag);
      }
    });
  }

  private List<String> generateTags() {

    List<String> tags = new ArrayList<>();

    tags.add("Pero 独家");
    tags.add("美腿");
    tags.add("黑丝");

    return tags;
  }
}
