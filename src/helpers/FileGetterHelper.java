package helpers;

import java.net.URI;

import org.eclipse.core.filesystem.URIUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.editors.text.ILocationProvider;

public class FileGetterHelper {
	
	public IFile getFile(IEditorInput input) {
		return getFileFromEditorInput(input);
	}
	
	private IFile getFileFromEditorInput(IEditorInput input) {
	    if (input == null)
	      return null;
	  
	    if (input instanceof IFileEditorInput)
	  	    return ((IFileEditorInput)input).getFile();

	    IPath path = getPathFromEditorInput(input);
	    if (path == null) 
		    return null;
	    
	    return ResourcesPlugin.getWorkspace().getRoot().getFile(path);
	}

	private IPath getPathFromEditorInput(IEditorInput input) {
	    if (input instanceof ILocationProvider) 
	    	return ((ILocationProvider)input).getPath(input);
		    
	    if (input instanceof IURIEditorInput) {
	    	URI uri = ((IURIEditorInput)input).getURI();
	    	if (uri != null) {
	    		IPath path = URIUtil.toPath(uri);
	    		if (path != null)
	    			return path;
	    	}
	    }

	   return null;
	}

}
