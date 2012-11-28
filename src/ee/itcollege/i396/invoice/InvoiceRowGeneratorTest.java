package ee.itcollege.i396.invoice;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.laughingpanda.beaninject.Inject;
import static ee.itcollege.i396.invoice.InvoiceRowMatcher.getMatcherForSum;

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
	public void sampleTest() throws Exception {

		generator.generateRowsFor(10, asDate("2012-02-15"),
				asDate("2012-04-02"));

		verify(invoiceRowDao, times(2)).save(
				argThat(getMatcherForSum(new BigDecimal(3))));
		verify(invoiceRowDao)
				.save(argThat(getMatcherForSum(new BigDecimal(4))));

		// verify that there are no more calls
	}

	

	public static Date asDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}