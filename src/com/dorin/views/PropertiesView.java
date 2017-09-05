package com.dorin.views;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import helpers.TableEditorHelper;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class PropertiesView extends Composite {
	private Table table; 
	private String[] titles;
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
			}
		} else {
			System.out.println("properties are null");
		}
		
		for (int i = 0; i < titles.length; i++) {
	        table.getColumn(i).pack();
	    }
		
		
		TableEditorHelper editorHelper = new TableEditorHelper();
		table = editorHelper.makeTableCellsEditable(table);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (properties != null) {
					//imiimimimim
				}
			}
		});
		btnNewButton.setText("New button");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
}
