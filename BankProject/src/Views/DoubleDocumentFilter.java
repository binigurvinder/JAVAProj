package Views;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DoubleDocumentFilter extends DocumentFilter {
	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
			throws BadLocationException {
		if (string == null) {
			return;
		}

		String text = fb.getDocument().getText(0, fb.getDocument().getLength());
		text += string;
		if (isDouble(text)) {
			super.insertString(fb, offset, string, attr);
		}
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
			throws BadLocationException {
		if (text == null) {
			return;
		}

		String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
		String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);
		if (isDouble(newText)) {
			super.replace(fb, offset, length, text, attrs);
		}
	}

	private boolean isDouble(String text) {
		if (text.isEmpty()) {
			return true;
		}
		// Regex to match a partial or complete double
		return text.matches("-?[0-9]*\\.?[0-9]*");
	}
}