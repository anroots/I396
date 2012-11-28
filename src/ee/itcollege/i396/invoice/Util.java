package ee.itcollege.i396.invoice;

import static org.mockito.Matchers.argThat;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static InvoiceRow withAmount(BigDecimal amount) {

		InvoiceRow row = new InvoiceRow(amount, null);
		InvoiceRowMatcher matcher = new InvoiceRowMatcher(row);

		return argThat(matcher);
	}
	
	public static Date asDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
