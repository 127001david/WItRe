package fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.david.witre.R;

import custom_interface.InitUI;

@SuppressLint("ValidFragment")
public class ShowText extends Fragment implements InitUI {
	private View rootview;

	private TextView txtView;

	private File file;

	public ShowText() {

	}

	public ShowText(final File file) {
		this.file = file;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootview = inflater.inflate(R.layout.fragment_show_text, container,
				false);

		initViews();

		return rootview;
	}

	@Override
	public void initViews() {
		txtView = (TextView) rootview.findViewById(R.id.txt_text);

		initValues();
	}

	@Override
	public void initValues() {
		showTxt();
	}

	private void showTxt() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					InputStream inputStream = new FileInputStream(file);
					final StringBuffer stringBuffer = new StringBuffer();

					if (inputStream != null) {
						InputStreamReader inputreader = new InputStreamReader(
								inputStream);
						BufferedReader buffreader = new BufferedReader(
								inputreader);
						String line;

						while ((line = buffreader.readLine()) != null) {
							stringBuffer.append(line);
							stringBuffer.append("\n");
						}

						inputStream.close();
					}

					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							txtView.setText(stringBuffer.toString());
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}