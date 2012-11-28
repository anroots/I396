package ee.itcollege.i396.invoice;

import java.math.BigDecimal;

import org.hamcrest.Matcher;
import org.mockito.ArgumentMatcher;

/**
 * Matcher for two instances of the InvoiceRow class
 * 
 * @author anroots
 */
class InvoiceRowMatcher extends ArgumentMatcher<InvoiceRow> {

	private InvoiceRow invoiceRow;

	public InvoiceRowMatcher(InvoiceRow i) {
		super();
		invoiceRow = i;
	}

	@Override
	public boolean matches(Object o) {
		return ((InvoiceRow) o).equals(invoiceRow);
	}

	public static Matcher<InvoiceRow> getMatcherForSum(BigDecimal bigDecimal) {

		InvoiceRow row = new InvoiceRow(bigDecimal, null);
		return new InvoiceRowMatcher(row);

	}
}