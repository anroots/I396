package ee.itcollege.i396.invoice;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.laughingpanda.beaninject.Inject;
import org.mockito.ArgumentCaptor;

import static ee.itcollege.i396.invoice.Util.*;

public class InvoiceRowGeneratorTest {

	private InvoiceRowDao invoiceRowDao;
	private InvoiceRowGenerator generator;

	@Before
	public void setUp() {
		invoiceRowDao = mock(InvoiceRowDao.class);
		generator = new InvoiceRowGenerator();
		Inject.bean(generator).with(invoiceRowDao);
	}

	@Test
	public void zeroAmountGeneratesNoInvoices() {
		generator
				.generateRowsFor(0, asDate("2012-01-01"), asDate("2012-04-01"));
		verify(invoiceRowDao, never()).save(any(InvoiceRow.class));
	}
	
	@Test
	public void threeAmountGeneratesOneCorrectInvoice() {
		ArgumentCaptor<InvoiceRow> rows = ArgumentCaptor.forClass(InvoiceRow.class);
		
		Date startDate = asDate("2012-01-01");
		Date endDate = asDate("2012-04-01");
		
		generator
				.generateRowsFor(3, startDate, endDate);
		verify(invoiceRowDao).save(rows.capture());
		
		assertTrue(rows.getValue().equals(new InvoiceRow(BigDecimal.valueOf(3), startDate)));
	}
	
	@Test
	public void largerAmountGeneratesCorrectly() {
		ArgumentCaptor<InvoiceRow> rows = ArgumentCaptor.forClass(InvoiceRow.class);
		
		Date startDate = asDate("2012-01-01");
		Date endDate = asDate("2012-04-01");
		
		generator
				.generateRowsFor(11, startDate, endDate);
		verify(invoiceRowDao, times(4)).save(rows.capture());
		
		List<InvoiceRow> calledRows = rows.getAllValues();
		
		// TODO: Finish the test
	}

	@Test(expected=IllegalArgumentException.class)
	public void negativeDateDiffThrows() throws IllegalArgumentException {
		generator
				.generateRowsFor(0, asDate("2012-01-01"), asDate("2011-01-01"));
	}

	@Test
	@Ignore
	public void sampleTest() throws Exception {

		generator.generateRowsFor(10, asDate("2012-02-15"),
				asDate("2012-04-02"));

		verify(invoiceRowDao, times(2)).save(withAmount(new BigDecimal(3)));
		verify(invoiceRowDao).save(withAmount(new BigDecimal(4)));

		// Todo: Refactor, verify no further interactions
		verify(invoiceRowDao, times(3)).save(any(InvoiceRow.class));
	}
}