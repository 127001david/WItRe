package com.david.witre;

import java.util.ArrayList;
import java.util.List;

import tools.Log;

import android.support.v4.app.Fragment;
import custom_interface.ReplaceFragment;

public class FragmentStack {
	private static FragmentStack fragmentStack;

	private ReplaceFragment replaceFragment;
	private List<Fragment> fragmentList;

	private FragmentStack(final ReplaceFragment replaceFragment) {
		this.replaceFragment = replaceFragment;
		this.fragmentList = new ArrayList<Fragment>();
	}

	public static FragmentStack getFragmentStack(
			final ReplaceFragment replaceFragment) {
		if (null == fragmentStack) {
			Log.show("new stack");
			fragmentStack = new FragmentStack(replaceFragment);

			return fragmentStack;
		} else {
			Log.show("get stack");
			return fragmentStack;
		}
	}

	public void push(Fragment fragment) {
		if (null != fragment) {
			Log.show("push");
			fragmentList.add(fragment);

			replaceFragment.replaceFragment(null, fragment);
		}
	}

	public Fragment pop() {
		Fragment fragment = getTop();

		if (null != fragment) {
			Log.show("pop");
			int size = fragmentList.size();

			fragmentList.remove(size - 1);

			replaceFragment.replaceFragment(fragment, getTop());

			return fragment;
		}

		return null;
	}

	public Fragment getTop() {
		if (null != fragmentList && 0 < fragmentList.size()) {
			Log.show("get top");
			int size = fragmentList.size();

			return fragmentList.get(size - 1);
		}

		return null;
	}

	public boolean empty() {
		Log.show("empty");

		if (null != fragmentList && 0 < fragmentList.size())
			return false;
		else
			return true;
	}

	public void releas() {
		FragmentStack.fragmentStack = null;
	}
}