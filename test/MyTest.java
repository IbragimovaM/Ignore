import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

import static com.internship.IgnoreUtils.getNotIgnoredFiles;
import static com.internship.IgnoreUtils.getPathsToIgnore;
import static java.util.Arrays.*;
import static org.junit.Assert.assertEquals;

public class MyTest
{
	private final File testDataDir = new File("testdata/test/");

	private Set<File> ignoredEntriesTestExpected;
	private List<File> notIgnoredFilesTestExpected;

	@Before
	public void init() {
		ignoredEntriesTestExpected = new TreeSet<>(asList(
				testDataDir.toPath().resolve(Paths.get("keys")).toFile().getAbsoluteFile(),
				testDataDir.toPath().resolve(Paths.get("config/local.txt")).toFile().getAbsoluteFile(),
				testDataDir.toPath().resolve(Paths.get("someDir/innerDir3/innerFile31.txt")).toFile().getAbsoluteFile()
		));

		notIgnoredFilesTestExpected = new ArrayList<>(asList(
				new File("testdata/test/.ignore"),
				new File("testdata/test/someDir/innerDir2/innerDir2.txt"),
				new File("testdata/test/someDir/innerDir3/innerFile32.csv"),
				new File("testdata/test/someDir/file2.txt"),
				new File("testdata/test/someDir/innerDir1/innerFile1.txt"),
				new File("testdata/test/someDir/file1.txt"),
				new File("testdata/test/someDir/file3.cfg"),
				new File("testdata/test/config/global.txt"),
				new File("testdata/test/someFIle.csv")
		));
	}

	@Test
	public void testIgnoredEntries() throws Exception
	{
		assertEquals(ignoredEntriesTestExpected, getPathsToIgnore(testDataDir));
	}

	@Test
	public void testCollectNotIgnoredFiles() throws Exception
	{
		assertEquals(notIgnoredFilesTestExpected, getNotIgnoredFiles(testDataDir));
	}
}
