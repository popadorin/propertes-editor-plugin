package com.dorin.editor.secondeditor.editors;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.dorin.views.PropertiesView;

import helpers.FileGetterHelper;
import helpers.FileReaderHelper;
import helpers.FileWriterHelper;

public class PropertiesEditor extends EditorPart {
	private PropertiesView propertiesView;
	private IEditorInput input;
	private Map<String, String> properties = new LinkedHashMap<>();
	private FileReaderHelper fileReader = new FileReaderHelper();
	private FileWriterHelper fileWriter = new FileWriterHelper();
	private FileGetterHelper fileGetter = new FileGetterHelper();
	private IFile file;

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
		
		if (input != null) {
			file = fileGetter.getFile(input);
			String content = fileReader.getFileContent(file);
			
			properties = getProperties(content);
		}
		
        propertiesView = new PropertiesView(parent, SWT.RESIZE, properties);
        propertiesView.layout();
        
	}
	
	@Override
	public void setFocus() {
		System.out.println("setFocus()");
		if (propertiesView != null)
            propertiesView.setFocus();
		
		refreshProperties();
		
		String s = "jora = vasea\n";
		
//		fileWriter.writeToFile(file, s);
		
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



}
