package com.internship;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		File curDir = new File(System.getProperty("user.dir"));
		Path curDirAbsPath = Paths.get(curDir.getAbsolutePath());

		List<File> files;
		try {
			files = IgnoreUtils.getNotIgnoredFiles(curDir);
		} catch (IOException e) {
			System.out.println("Error occurred while retrieving ignore list: " + e.getMessage());
			return;
		}

		for (File file : files) {
			System.out.println("./" + curDirAbsPath.relativize(file.toPath()));
		}
	}


}
