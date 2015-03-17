package dto;

import java.time.LocalDate;

/**
 * Class used to transfer data related to an Availability entity
 * @author stefan
 *
 */
public class AvailabilityDTO {

	private LocalDate fromDate;
	private LocalDate toDate;
	
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
}
