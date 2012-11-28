package ee.itcollege.i396.invoice;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.laughingpanda.beaninject.Inject;
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
	@Ignore
	public void sampleTest() throws Exception {

		generator.generateRowsFor(10, asDate("2012-02-15"),
				asDate("2012-04-02"));

		verify(invoiceRowDao, times(2)).save(withAmount(new BigDecimal(3)));
		verify(invoiceRowDao)
				.save(withAmount(new BigDecimal(4)));

		// Todo: Refactor, verify no further interactions
		verify(invoiceRowDao,times(3)).save(any(InvoiceRow.class));
	}
}