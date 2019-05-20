package com.internship;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class IgnoreUtils {

	public static final String DEFAULT_IGNORE_FILE_NAME = ".ignore";

	public static List<File> getNotIgnoredFiles(File dir) throws IOException {
		return getNotIgnoredFiles(dir, DEFAULT_IGNORE_FILE_NAME);
	}

	public static List<File> getNotIgnoredFiles(File dir, String ignoreFileName) throws IOException {
		if (!dir.isDirectory())
			throw new IllegalArgumentException("Not a directory");

		List<File> result = new ArrayList<>();
		Set<File> ignored = getPathsToIgnore(dir, ignoreFileName);

		getFiles(dir, result, ignored);

		return result;
	}

	public static Set<File> getPathsToIgnore(File dir) throws IOException {
		return getPathsToIgnore(dir, DEFAULT_IGNORE_FILE_NAME);
	}

	public static Set<File> getPathsToIgnore(File dir, String ignoreFileName) throws IOException {
		if (!dir.isDirectory())
			throw new IllegalArgumentException("Not a directory");

		File ignoreList = getFileWithIgnoredList(dir, ignoreFileName);
		if (!(ignoreList.exists() && ignoreList.isFile()))
			return new HashSet<>();

		Scanner sc = new Scanner(ignoreList);

		Set<File> pathsToIgnore = new TreeSet<>();
		while (sc.hasNextLine()) {
			String tempLine = sc.nextLine().trim();
			if (tempLine.startsWith("#") || tempLine.isEmpty()) {
				continue;
			}

			Path tempFile = Paths.get(dir.getPath(), tempLine).toAbsolutePath();
			if (!Files.exists(tempFile))
				continue;

			File absoluteFile = tempFile.normalize().toFile().getAbsoluteFile();
			pathsToIgnore.add(absoluteFile);
		}

		return pathsToIgnore;
	}

	private static void getFiles(File curDir, List<File> collector, Set<File> pathsToIgnore) {
		File[] children = curDir.listFiles();
		if (children == null)
			return;

		for (File file : children) {
			if (pathsToIgnore.contains(file.getAbsoluteFile()))
				continue;

			if (file.isFile()) {
				collector.add(file);
			} else {
				getFiles(file, collector, pathsToIgnore);
			}
		}
	}

	private static File getFileWithIgnoredList(File curDir, String name) {
		Path ignorePath = Paths.get(curDir.getPath()).resolve(name);
		return new File(ignorePath.toUri());
	}
}
