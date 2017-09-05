package com.dorin.editor.secondeditor.editors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.filesystem.URIUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.ILocationProvider;
import org.eclipse.ui.part.EditorPart;

import com.dorin.views.PropertiesView;

public class PropertiesEditor extends EditorPart {
	private PropertiesView propertiesView;
	private IEditorInput input;
	private Map<String, String> properties = new LinkedHashMap<>();

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		System.out.println("doSave()");
		
	}
	
	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		System.out.println("doSaveAs()");
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		System.out.println("init()");
		setSite(site);
        setInput(input);
        this.input = input;
  	
	}
	
	@Override
	public boolean isDirty() {
		System.out.println("isDirty()");
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isSaveAsAllowed() {
		System.out.println("isSaveAsAllowed()");
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void createPartControl(Composite parent) {
		System.out.println("createPartControl()");
		
		String content = getFileContent();
		properties = getProperties(content);
		
        propertiesView = new PropertiesView(parent, SWT.RESIZE, properties);
        
        propertiesView.layout();
        
		
	}
	
	@Override
	public void setFocus() {
		System.out.println("setFocus()");
		if (propertiesView != null)
            propertiesView.setFocus();
		
		refreshProperties();
//		if (properties != null) {
//			for (String key : properties.keySet()) {
//				System.out.println("key = " + key + ", value = " + properties.get(key));
//			}
//		}
	
	}
	
	private void refreshProperties() {
		Composite parent = propertiesView.getParent();
		propertiesView.dispose();
		createPartControl(parent);
		propertiesView.getParent().layout();
	}

	private LinkedHashMap<String, String> getProperties(String content) {
		if (content == null) {
			System.out.println("content is null");
			return null;
		}
		LinkedHashMap<String, String> properties = new LinkedHashMap<>();
		String[] lines = content.split("\\R");
		for (String line : lines) {
			String key = line.split("\\=")[0];
			String value = line.split("\\=")[1];
			properties.put(key, value);
		}
		
		return properties;
	}

	private String getFileContent() {
		String content = null;
        
        IFile ifile = getFileFromEditorInput(input);
        if (ifile != null) {
        	System.out.println("ifile is not null hurraaay");
        	System.out.println(ifile.getName());
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
