package item;

import java.io.File;

public class FileItem implements Comparable<FileItem> {
	public static final int Type_Folder = 0;
	public static final int Type_File = 1;
	public static final int Type_Zip = 2;
	public static final int Type_Music = 3;
	public static final int Type_Media = 4;
	public static final int Type_Picture = 5;
	public static final int Type_Txt = 6;
	public static final int Type_Doc = 7;
	public static final int Type_PPt = 8;
	public static final int Type_Pdf = 9;
	public static final int Type_Apk = 10;
	public static final int Type_Db = 11;
	public static final int Type_Html = 12;
	public static final int Type_Xml = 13;

	private File file;
	private int type;

	public void setFile(final File file) {
		this.file = file;
		setFileType();
	}

	private void setFileType() {
		String fileName = getFileName();

		if (file.isDirectory()) {
			type = Type_Folder;
		} else {
			if (fileName.endsWith(".zip"))
				type = Type_Zip;
			else if (fileName.endsWith(".mp3"))
				type = Type_Music;
			else if (fileName.endsWith(".mp4") || fileName.endsWith(".avi")
					|| fileName.endsWith(".rmvb") || fileName.endsWith(".3gp"))
				type = Type_Media;
			else if (fileName.endsWith(".png") || fileName.endsWith(".jpg")
					|| fileName.endsWith(".jpeg"))
				type = Type_Picture;
			else if (fileName.endsWith(".txt"))
				type = Type_Txt;
			else if (fileName.endsWith(".doc"))
				type = Type_Doc;
			else if (fileName.endsWith(".pos"))
				type = Type_PPt;
			else if (fileName.endsWith(".pdf"))
				type = Type_Pdf;
			else if (fileName.endsWith(".apk"))
				type = Type_Apk;
			else if (fileName.endsWith(".db"))
				type = Type_Db;
			else if (fileName.endsWith(".html"))
				type = Type_Html;
			else if (fileName.endsWith(".xml"))
				type = Type_Xml;
			else
				type = Type_File;
		}
	}

	public File getFile() {
		return file;
	}

	public String getFileName() {
		return file.getName();
	}

	public String getFilePath() {
		return file.getAbsolutePath();
	}

	public int getFileType() {
		return type;
	}

	@Override
	public int compareTo(FileItem another) {
		if (getFile().isDirectory() && another.getFile().isFile())
			return -1;
		else if (getFile().isFile() && another.getFile().isDirectory())
			return 1;

		return getFileName().compareTo(another.getFileName());
	}
}