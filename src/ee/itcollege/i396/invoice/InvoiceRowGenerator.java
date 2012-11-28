package ee.itcollege.i396.invoice;

import java.util.Date;

public class InvoiceRowGenerator {

	private InvoiceRowDao invoiceRowDao;

	public InvoiceRowGenerator() {
		
	}
	
	public InvoiceRowGenerator(InvoiceRowDao dao) {
		invoiceRowDao = dao;
	}

	public void generateRowsFor(int amount, Date start, Date end) {
		if (start.compareTo(end) > 0) {
			throw new IllegalArgumentException(
					"Start date cannot be after the end date.");
		}
	}

}
