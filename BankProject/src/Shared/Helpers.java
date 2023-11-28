package Shared;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

public class Helpers {
	public static Date convertToDate(LocalDateTime localDateTime) {
		// Convert LocalDateTime to Instant, then to java.util.Date
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static boolean isNotEmpty(JTextField textField) {
		return textField.getText() != null && !textField.getText().trim().isEmpty();
	}

	public static boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		Pattern pattern = Pattern.compile(emailRegex);
		if (email == null) {
			return false;
		}
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isIntegerValueGreaterThanZero(String stringValue) {
		try {
			double value = Double.parseDouble(stringValue);
			return value > 0d;
		} catch (Exception e) {
			return false; // String does not represent a valid integer
		}
	}

}
