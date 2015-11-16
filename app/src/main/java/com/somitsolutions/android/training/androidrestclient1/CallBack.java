package com.somitsolutions.android.training.androidrestclient1;

public interface CallBack {
	public void onProgress();
	public void onResult(String result);
	public void onCancel();
}

