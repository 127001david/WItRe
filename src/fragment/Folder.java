package fragment;

import item.FileItem;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import tools.GetMIMEType;
import tools.Log;

import adapter.FileAdapter;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.david.witre.FragmentStack;
import com.david.witre.R;

import custom_interface.ReplaceFragment;

@SuppressLint("ValidFragment")
public class Folder extends Fragment implements OnItemClickListener {
	private View rootView;

	private ListView fileListView;

	private FileAdapter fileAdapter;
	private ArrayList<FileItem> fileList;

	private String filePath;

	public Folder() {

	}

	public Folder(final String filePath) {
		this.filePath = filePath;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_folder, container, false);

		Log.show("create view");

		initViews();

		return rootView;
	}

	@Override
	public void onStart() {
		Log.show("start");
		super.onStart();
	}

	private void initViews() {
		fileListView = (ListView) rootView.findViewById(R.id.file_list);

		initValues();
	}

	private void initValues() {
		fileList = new ArrayList<FileItem>();

		fileListView.setOnItemClickListener(this);

		new InitFileList().execute(filePath);
	}

	private class InitFileList extends AsyncTask<String, Integer, Integer> {

		@Override
		protected Integer doInBackground(String... params) {
			String filePath = params[0];

			File file = new File(filePath);

			if (null == file || null == file.listFiles())
				return 0;

			for (File f : file.listFiles()) {
				if (!f.getName().startsWith(".")) {
					FileItem item = new FileItem();

					item.setFile(f);

					fileList.add(item);
				}
			}

			Collections.sort(fileList);

			fileAdapter = new FileAdapter(getActivity(), fileList);

			return 1;
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);

			switch (result) {
			case 0:
				Toast.makeText(getActivity(), "空", Toast.LENGTH_SHORT).show();

				break;
			case 1:
				fileListView.setAdapter(fileAdapter);

				break;

			default:
				break;
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int postion,
			long arg3) {
		FileItem fileItem = fileList.get(postion);

		switch (arg0.getId()) {
		case R.id.file_list: {

			switch (fileItem.getFileType()) {
			case FileItem.Type_Folder: {
				Folder folder = new Folder(fileItem.getFilePath());

				FragmentStack.getFragmentStack((ReplaceFragment) getActivity())
						.push(folder);

				break;
			}
			case FileItem.Type_Xml: {
				ShowText showText = new ShowText(fileItem.getFile());

				FragmentStack.getFragmentStack((ReplaceFragment) getActivity())
						.push(showText);

				break;
			}
			case FileItem.Type_Db: {
				ShowText showText = new ShowText(fileItem.getFile());

				FragmentStack.getFragmentStack((ReplaceFragment) getActivity())
						.push(showText);

				break;
			}

			default: {
				Intent intent = new Intent();
				// 获取文件file的MIME类型
				String type = GetMIMEType.getMIMEType(fileItem.getFile());

				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setAction(Intent.ACTION_VIEW);

				intent.setDataAndType(Uri.fromFile(fileItem.getFile()), type);

				try {
					startActivity(intent);
				} catch (ActivityNotFoundException e) {
					Toast.makeText(getActivity(), "抱歉未找到打开该文件的软件",
							Toast.LENGTH_SHORT).show();
				}

				break;
			}
			}

			break;
		}

		default:
			break;
		}
	}
}