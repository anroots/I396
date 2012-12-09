package ee.itcollege.i396.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InvoiceRowGenerator {

	private InvoiceRowDao invoiceRowDao;

	public InvoiceRowGenerator() {

	}

	public InvoiceRowGenerator(InvoiceRowDao dao) {
		invoiceRowDao = dao;
	}

	public void generateRowsFor(int amount, Date start, Date end) {
		if (amount <= 0) {
			return;
		}
		List<InvoiceRow> invoiceRows = new ArrayList<InvoiceRow>();
		List<Date> paymentDates = getPaymentDates(start, end);
		int remainingAmount = amount;

		int paymentPart = getPaymentSize(amount, paymentDates.size());

		int i = 0;
		for (; i < paymentDates.size(); i++) {
			if (remainingAmount - paymentPart <= 3)
				break;

			int rowAmount = (int) paymentPart;

			invoiceRows.add(new InvoiceRow(BigDecimal.valueOf(rowAmount),
					paymentDates.get(i)));

			remainingAmount -= rowAmount;
		}

		invoiceRows.add(new InvoiceRow(BigDecimal.valueOf(remainingAmount),
				paymentDates.get(i)));
		saveRows(invoiceRows);
	}

	private void saveRows(List<InvoiceRow> invoiceRows) {
		for (InvoiceRow row : invoiceRows) {
			invoiceRowDao.save(row);
		}
	}

	public int getPaymentSize(int amount, int payments) {
		if (payments <= 0) {
			throw new IllegalArgumentException(
					"There has to be at least one payment");
		}
		if (amount == 0) {
			return 0;
		}

		double expectedPaymentPart = (double) amount / payments;

		while (expectedPaymentPart < 3) {
			expectedPaymentPart *= 2;
		}

		return (int) expectedPaymentPart;
	}

	public List<Date> getPaymentDates(Date start, Date end) {
		if (start.compareTo(end) > 0) {
			throw new IllegalArgumentException(
					"Start date cannot be after the end date.");
		}
		List<Date> dates = new ArrayList<Date>();
		Date current = start;

		while (current.compareTo(end) <= 0) {
			dates.add(current);
			current = getFirstOFNextMonth(current);
		}

		return dates;
	}

	public Date getFirstOFNextMonth(Date current) {
		Calendar dateCal = Calendar.getInstance();
		dateCal.setTime(current);

		int month = dateCal.get(Calendar.MONTH);
		dateCal.set(Calendar.MONTH, ++month);
		dateCal.set(Calendar.DAY_OF_MONTH, 1);

		return dateCal.getTime();
	}

}
