package adapter;

import item.FileItem;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.david.witre.R;

public class FileAdapter extends BaseAdapter {
	private Activity activity;
	private ArrayList<FileItem> list;

	public FileAdapter(Activity activity, final ArrayList<FileItem> list) {
		this.activity = activity;
		this.list = list;
	}

	@Override
	public int getCount() {
		if (null != list)
			return list.size();

		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (null != list && 0 < list.size())
			return list.get(position);

		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		FileHolder holder;

		if (null == convertView) {
			convertView = activity.getLayoutInflater().inflate(
					R.layout.item_file, null);
			holder = new FileHolder();

			holder.fileNameTextView = (TextView) convertView
					.findViewById(R.id.filename_text);
			holder.fileImageView = (ImageView) convertView
					.findViewById(R.id.file_image);

			convertView.setTag(holder);
		} else {
			holder = (FileHolder) convertView.getTag();
		}

		FileItem item = list.get(position);
		String fileName = item.getFileName();
		int type = item.getFileType();

		switch (type) {
		case FileItem.Type_Folder:
			holder.fileImageView.setImageResource(R.drawable.format_folder);

			break;
		case FileItem.Type_File:
			holder.fileImageView.setImageResource(R.drawable.format_unkown);

			break;
		case FileItem.Type_Zip:
			holder.fileImageView.setImageResource(R.drawable.format_zip);

			break;
		case FileItem.Type_Music:
			holder.fileImageView.setImageResource(R.drawable.format_music);

			break;
		case FileItem.Type_Media:
			holder.fileImageView.setImageResource(R.drawable.format_media);

			break;
		case FileItem.Type_Picture:
			holder.fileImageView.setImageResource(R.drawable.format_picture);

			break;
		case FileItem.Type_Txt:
			holder.fileImageView.setImageResource(R.drawable.format_text);

			break;
		case FileItem.Type_Doc:
			holder.fileImageView.setImageResource(R.drawable.format_word);

			break;
		case FileItem.Type_PPt:
			holder.fileImageView.setImageResource(R.drawable.format_ppt);

			break;
		case FileItem.Type_Pdf:
			holder.fileImageView.setImageResource(R.drawable.format_pdf);

			break;
		case FileItem.Type_Apk:
			holder.fileImageView.setImageResource(R.drawable.format_apk);

			break;
		case FileItem.Type_Db:
			holder.fileImageView.setImageResource(R.drawable.format_text);

			break;
		case FileItem.Type_Html:
			holder.fileImageView.setImageResource(R.drawable.format_html);

			break;
		case FileItem.Type_Xml:
			holder.fileImageView.setImageResource(R.drawable.format_text);

			break;

		default:
			holder.fileImageView.setImageResource(R.drawable.format_unkown);

			break;
		}

		holder.fileNameTextView.setText(fileName);

		return convertView;
	}

	class FileHolder {
		TextView fileNameTextView;
		ImageView fileImageView;
	}
}