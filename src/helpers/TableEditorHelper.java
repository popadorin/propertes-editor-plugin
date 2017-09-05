package helpers;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class TableEditorHelper {
	
	private Table table;

	public Table makeTableCellsEditable(Table table) {
		this.table = table;
		final TableEditor editor = new TableEditor(table);
	    editor.horizontalAlignment = SWT.LEFT;
	    editor.grabHorizontal = true;
	    table.addListener(SWT.MouseDown, new Listener() {
	    	@Override
	        public void handleEvent(Event event) {
	    		onCellMouseDown(event, editor);
	    	}

	    });
	    
	    return table;		
	}

	private void onCellMouseDown(Event event, TableEditor editor) {
        Rectangle clientArea = table.getClientArea();
        Point pt = new Point(event.x, event.y);
        int index = table.getTopIndex();
        
        while (index < table.getItemCount()) {
        	boolean visible = false;
        	final TableItem item = table.getItem(index);
        	for (int i = 0; i < table.getColumnCount(); i++) {
        		Rectangle rect = item.getBounds(i);
        		if (rect.contains(pt)) {
        			handleEditableItem(editor, item, i);
        		}
        		if (!visible && rect.intersects(clientArea)) {
        			visible = true;
        		}
        	}
        	if (!visible)
        		return;
        	index++;
        }
		
	}
	
	private void handleEditableItem(TableEditor editor, TableItem item, int column) {
		final Text text = new Text(table, SWT.NONE);
		Listener textListener = new Listener() {
			@Override
			public void handleEvent(final Event e) {
				handleItemEvent(e, item, text, column);

			}

		};
		text.addListener(SWT.FocusOut, textListener);
		text.addListener(SWT.Traverse, textListener);
		editor.setEditor(text, item, column);
		text.setText(item.getText(column));
		text.selectAll();
		text.setFocus();
		return;
		
	}

	private void handleItemEvent(Event e, TableItem item, Text text, int column) {
		switch (e.type) {
			case SWT.FocusOut:
				item.setText(column, text.getText());
				text.dispose();
				break;
			case SWT.Traverse:
				switch (e.detail) {
					case SWT.TRAVERSE_RETURN:
						item.setText(column, text.getText());
						// FALL THROUGH
					case SWT.TRAVERSE_ESCAPE:
						text.dispose();
						e.doit = false;
				}
				break;
		}
		
	}
	
}
