package com.dorin.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class PropertiesView extends Composite {
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public PropertiesView(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnKey = new TableColumn(table, SWT.NONE);
		tblclmnKey.setWidth(100);
		tblclmnKey.setText("Key");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("Value");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(0, "Key1");
		tableItem.setText(1, "Value1");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
