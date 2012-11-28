package ee.itcollege.i396.invoice;

import java.math.BigDecimal;
import java.util.Date;

class InvoiceRow {

	public final BigDecimal amount;
	public final Date date;

	public InvoiceRow(BigDecimal amount, Date date) {
		this.amount = amount;
		this.date = date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.getClass() == obj.getClass()) {

			InvoiceRow invoiceRow = (InvoiceRow) obj;
			if (invoiceRow.getAmount().equals(this.amount)
					&& invoiceRow.getDate().equals(this.date)) {
				return true;
			}

		}

		return false;
	}
}