package helpers;

import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class FileWriterHelper {

	public void writeToFile(IFile file, String content) {
		try {
			FileOutputStream fout = new FileOutputStream(file.getLocation().toOSString(), true);
			fout.write(content.getBytes());
			
			fout.close();
			file.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (IOException e) {
			System.out.println("IOException has occured!");
		} catch (CoreException e) {
			System.out.println("CoreException has occured!");
		}
		
	}
}
