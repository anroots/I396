package ee.itcollege.i396.invoice;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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
	public void generateRowsZeroAmountGeneratesNoInvoices() {
		generator
				.generateRowsFor(0, asDate("2012-01-01"), asDate("2012-04-01"));
		verify(invoiceRowDao, never()).save(any(InvoiceRow.class));
	}
	
	@Test
	public void generateRowsThreeAmountGeneratesOneCorrectInvoice() {
		ArgumentCaptor<InvoiceRow> rows = ArgumentCaptor.forClass(InvoiceRow.class);
		
		Date startDate = asDate("2012-01-01");
		Date endDate = asDate("2012-03-01");
		
		generator
				.generateRowsFor(3, startDate, endDate);
		verify(invoiceRowDao).save(rows.capture());
		
		assertTrue(rows.getValue().equals(new InvoiceRow(BigDecimal.valueOf(3), startDate)));
	}
	
	@Test
	public void generateRowsLargerAmountGeneratesCorrectly() {
		ArgumentCaptor<InvoiceRow> rows = ArgumentCaptor.forClass(InvoiceRow.class);
		
		Date startDate = asDate("2012-02-15");
		Date endDate = asDate("2012-04-02");
		
		generator
				.generateRowsFor(10, startDate, endDate);
		verify(invoiceRowDao, times(3)).save(rows.capture());
		
		List<InvoiceRow> calledRows = rows.getAllValues();
		
		List<InvoiceRow> expectedRows = new ArrayList<InvoiceRow>();
		expectedRows.add(new InvoiceRow(BigDecimal.valueOf(3), asDate("2012-02-15")));
		expectedRows.add(new InvoiceRow(BigDecimal.valueOf(3), asDate("2012-03-01")));
		expectedRows.add(new InvoiceRow(BigDecimal.valueOf(4), asDate("2012-04-01")));
		
		assertThat(calledRows, is(expectedRows));
	}

	@Test(expected=IllegalArgumentException.class)
	public void getPaymentDatesNegativeDateDiffThrows() throws IllegalArgumentException {
		generator
				.getPaymentDates(asDate("2012-01-01"), asDate("2011-01-01"));
	}

	@Test
	public void getPaymentDatesSingleDateReturnsSame() {
		Date date = asDate("2012-01-01");
		List<Date> dates = generator.getPaymentDates(date, date);
		
		assertThat(dates.size(), is(1));
		assertThat(dates.get(0), is(date));
	}
	
	@Test
	public void getPaymentDatesMultipleDateIsCorrect() {
		Date startDate = asDate("2012-01-08");
		Date endDate = asDate("2012-04-01");
		
		List<Date> expectedDates = new ArrayList<Date>();
		expectedDates.add(startDate);
		expectedDates.add(asDate("2012-02-01"));
		expectedDates.add(asDate("2012-03-01"));
		expectedDates.add(endDate);
		
		List<Date> paymentDates = generator.getPaymentDates(startDate, endDate);
		
		assertThat(paymentDates, is(expectedDates));
	}
	
	@Test 
	public void getFirstOfNextMonthIsCorrect() {
		Date date = asDate("2012-01-08");
		assertThat(generator.getFirstOFNextMonth(date), is(asDate("2012-02-01")));
		
		date = asDate("2012-01-01");
		assertThat(generator.getFirstOFNextMonth(date), is(asDate("2012-02-01")));
	}
	
	@Test
	public void getPaymentSizeZeroAmountReturnsZero() {
		assertThat(generator.getPaymentSize(0, 2), is(0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getPaymentSizeLessThanOnePaymentsThrows() {
		generator.getPaymentSize(2, 0);
	}
	
	@Test
	public void getPaymentSizeNormallyReturnsThreeOrMore() {
		assertTrue(generator.getPaymentSize(2, 5) >= 3);
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