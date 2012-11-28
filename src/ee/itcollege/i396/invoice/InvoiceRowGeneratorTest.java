package ee.itcollege.i396.invoice;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.laughingpanda.beaninject.Inject;

public class InvoiceRowGeneratorTest {

    @Test
    public void sampleTest() throws Exception {

        InvoiceRowDao invoiceRowDao = mock(InvoiceRowDao.class);

        InoviceRowGenerator generator = new InoviceRowGenerator();

        Inject.bean(generator).with(invoiceRowDao);

        generator.generateRowsFor(10, asDate("2012-02-15"), asDate("2012-04-02"));

        verify(invoiceRowDao, times(2)).save(argThat(getMatcherForSum(new BigDecimal(3))));
        verify(invoiceRowDao).save(argThat(getMatcherForSum(new BigDecimal(4))));

        // verify that there are no more calls
    }

    private Matcher<InvoiceRow> getMatcherForSum(BigDecimal bigDecimal) {

        // create matcher

        return null;
    }

    public static Date asDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}