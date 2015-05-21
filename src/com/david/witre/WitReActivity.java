package com.david.witre;

import tools.Log;
import tools.StringOption;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import custom_interface.ReplaceFragment;
import fragment.Folder;

public class WitReActivity extends FragmentActivity implements ReplaceFragment {
	private FragmentStack fragmentStack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wit_re);
		initViews();
	}

	private void initViews() {
		initValues();
	}

	private void initValues() {
		Folder folder = new Folder(StringOption.AbsolutePath);

		fragmentStack = FragmentStack.getFragmentStack(this);

		fragmentStack.push(folder);
	}

	@Override
	protected void onDestroy() {
		Log.show("destroy");
		fragmentStack.releas();

		super.onDestroy();
	}

	@Override
	public void replaceFragment(Fragment forFragment, Fragment topFragment) {
		if (null != topFragment) {
			FragmentTransaction fragmentManager = getSupportFragmentManager()
					.beginTransaction();

			if (!topFragment.isAdded()) {
				fragmentManager.add(R.id.fragment_layout, topFragment, null)
						.show(topFragment);

				if (null != forFragment)
					fragmentManager.hide(forFragment);
			} else {
				fragmentManager.show(topFragment);

				if (null != forFragment) {
					fragmentManager.hide(forFragment);
					fragmentManager.remove(forFragment);
				}
			}

			fragmentManager.commitAllowingStateLoss();
		} else {
			finish();
		}
	}

	@Override
	public void onBackPressed() {
		Fragment fragment = fragmentStack.pop();

		if (null == fragment || fragmentStack.empty())
			finish();
	}
}