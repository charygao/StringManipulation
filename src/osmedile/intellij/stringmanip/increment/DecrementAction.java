package osmedile.intellij.stringmanip.increment;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import osmedile.intellij.stringmanip.MyApplicationService;
import osmedile.intellij.stringmanip.MyEditorAction;
import osmedile.intellij.stringmanip.utils.StringUtil;
import shaded.org.apache.commons.lang3.StringUtils;

/**
 * @author Olivier Smedile
 * @author Vojtech Krasa
 */
public class DecrementAction extends MyEditorAction {

	public DecrementAction() {
		super(null);
		this.setupHandler(new EditorWriteActionHandler(true) {
			@Override
			public void executeWriteAction(Editor editor, DataContext dataContext) {
				MyApplicationService.setAction(getActionClass());

				// Column mode not supported
				if (editor.isColumnMode()) {
					return;
				}
				final CaretModel caretModel = editor.getCaretModel();

				final int line = caretModel.getLogicalPosition().line;
				final int column = caretModel.getLogicalPosition().column;
				int caretOffset = caretModel.getOffset();

				final SelectionModel selectionModel = editor.getSelectionModel();
				boolean hasSelection = selectionModel.hasSelection();
				if (hasSelection == false) {
					selectionModel.selectLineAtCaret();
				}
				final String selectedText = selectionModel.getSelectedText();

				if (selectedText != null) {
					String[] textParts = StringUtil.splitPreserveAllTokens(selectedText,
							UniversalNumber.UNIVERSAL_NUMBER_REGEX);
					for (int i = 0; i < textParts.length; i++) {
						textParts[i] = new UniversalNumber(textParts[i]).decrement();
					}

					final String newText = StringUtils.join(textParts);
					applyChanges(editor, caretModel, line, column, selectionModel, hasSelection, newText, caretOffset);
				}
			}
		});
	}

	protected void applyChanges(Editor editor, CaretModel caretModel, int line, int column,
								SelectionModel selectionModel, boolean hasSelection, String newText, int caretOffset) {
		editor.getDocument().replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(),
			newText);
	}
}
