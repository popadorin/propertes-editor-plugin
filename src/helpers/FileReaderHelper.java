package helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;


public class FileReaderHelper {
	
	public String getFileContent(IFile ifile) {
		String content = null;
        
        if (ifile != null) {
        	try {
				InputStream contents = ifile.getContents();
				BufferedReader reader = new BufferedReader(new InputStreamReader(contents));
				StringBuilder result = new StringBuilder();
				String line;
				while((line = reader.readLine()) != null) {
				    result.append(line + "\n");
				}
				content = result.toString();
				
			} catch (CoreException e) {
				System.out.println("CoreException on getting the contents of ifile!");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IOException on reading the contents of ifile");
				e.printStackTrace();
			}
        } else {
        	System.out.println("ifile is null");
        }
       
	    return content;
	}
	


}
