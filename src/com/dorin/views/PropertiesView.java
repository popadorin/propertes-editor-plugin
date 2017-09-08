package com.dorin.views;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

//import static org.eclipse.team.ui.ISaveableWorkbenchPart.PROP_DIRTY;
//import static org.eclipse.compare.CompareEditorInput.DIRTY_STATE;
import org.eclipse.compare.*;

//import org.eclipse.compare.CompareUI;

import helpers.TableEditorHelper;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class PropertiesView extends Composite {
	private Table table; 
	private String[] titles;
	public boolean isSave = false;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public PropertiesView(Composite parent, int style, Map<String, String> properties) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		titles = new String[] {"Key", "Value", "Actions"};
	    for (int i = 0; i < titles.length; i++) {
	      TableColumn column = new TableColumn(table, SWT.NONE);
	      column.setText(titles[i]);
	    }
		
		if (properties != null) {
			for (String key : properties.keySet()) {
				TableItem tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(0, key);
				tableItem.setText(1, properties.get(key));
				tableItem.setText(2, "-");
			}
		} else {
			System.out.println("properties are null");
		}
		
		for (int i = 0; i < titles.length; i++) {
	        table.getColumn(i).pack();
	    }
		
		
		TableEditorHelper editorHelper = new TableEditorHelper();
		table = editorHelper.makeTableCellsEditable(table);
		
		table.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				
				if ((e.stateMask & SWT.CTRL) != 0 && e.keyCode == 115) {
					System.out.println("save is called!");
				}
				
//				String string = "";
//				//check click together?
//				if ((e.stateMask & SWT.ALT) != 0) string += "ALT - keyCode = " + e.keyCode;
//				if ((e.stateMask & SWT.CTRL) != 0) string += "CTRL - keyCode = " + e.keyCode;
//				if ((e.stateMask & SWT.SHIFT) != 0) string += "SHIFT - keyCode = " + e.keyCode;
//				
//				if(e.keyCode == SWT.BS)
//				{
//					string += "BACKSPACE - keyCode = " + e.keyCode;
//				}
//				
//				if(e.keyCode == SWT.ESC)
//				{
//					string += "ESCAPE - keyCode = " + e.keyCode;
//				}
//				
//				//check characters 
//				if(e.keyCode >=97 && e.keyCode <=122)
//				{
//					string += " " + e.character + " - keyCode = " + e.keyCode;
//				}
//
//				//check digit
//				if(e.keyCode >=48 && e.keyCode <=57)
//				{
//					string += " " + e.character + " - keyCode = " + e.keyCode;
//				}
//				
//				if(!string.equals(""))
//					System.out.println (string);
			}
		});
		
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Notify eclipse on Dirty state;
				System.out.println("DIRTY_STATE: " + CompareEditorInput.DIRTY_STATE);
//				firePropertyChange(DIRTY_STATE);
				if (properties != null) {
					printProperties(properties);
				} else {
					System.out.println("Properties from Composite are null");
				}
				
			}
		});
		btnNewButton.setText("New button");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	private void printProperties(Map<String, String> properties) {
		for (String key : properties.keySet()) {
			System.out.println("key = " + key + ", value = " + properties.get(key));
		}
	}
	
}
