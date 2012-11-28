package ee.itcollege.i396.invoice;

import static org.mockito.Matchers.argThat;

import java.math.BigDecimal;

public class Util {

	public static InvoiceRow withAmount(BigDecimal amount) {

		InvoiceRow row = new InvoiceRow(amount, null);
		InvoiceRowMatcher matcher = new InvoiceRowMatcher(row);

		return argThat(matcher);
	}
}
