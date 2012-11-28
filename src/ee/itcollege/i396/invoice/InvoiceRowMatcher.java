package ee.itcollege.i396.invoice;

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
}