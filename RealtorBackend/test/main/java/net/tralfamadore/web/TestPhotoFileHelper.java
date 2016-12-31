package net.tralfamadore.web;

import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.modules.junit4.PowerMockRunner;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@RunWith(PowerMockRunner.class)
public class TestPhotoFileHelper {
	@Mock
	private FileUploadEvent fileUploadEvent;
	
	@Mock
	private UploadedFile uploadedFile;
	
	private PhotoFileHelper photoHelper;
	
	@Before
	public void init() {
		photoHelper = new PhotoFileHelper();
	}
	
	@Test
	public void testWriteUploadedFile() throws Exception {
		when(fileUploadEvent.getFile()).thenReturn(uploadedFile);
		when(uploadedFile.getFileName()).thenReturn("image.jpg");
		doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            assertEquals(args.length, 1);
            assertEquals(args[0], "/tmp"  + File.separator + "image.jpg");
            return null;
        }).when(uploadedFile).write(anyString());
		
		String name = photoHelper.writeUploadedFile(fileUploadEvent, "/tmp");
		assertEquals(name, "image.jpg");
	}

	@Test
	public void testCopyFile() throws IOException {
		File file = new File("./file");
		if(!file.createNewFile())
		    throw new IOException("Can't create file");
		
		photoHelper.copyFile(file.getAbsolutePath(), "/tmp", file.getName());
		
		if(!file.delete())
            throw new IOException("Can't delete file");

		file = new File("/tmp", "file");
		
		assertTrue(file.exists());

        if(!file.delete())
            throw new IOException("Can't delete file");
	}
}
