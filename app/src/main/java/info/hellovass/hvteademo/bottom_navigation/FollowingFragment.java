package info.hellovass.hvteademo.bottom_navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import info.hellovass.hvteademo.R;

/**
 * Created by hello on 2017/3/23.
 */

public class FollowingFragment extends Fragment {

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_following, container, false);
    ButterKnife.bind(this, view);

    return view;
  }
}
