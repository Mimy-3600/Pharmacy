package back.model;

import java.math.BigDecimal;

public class Medoc {

	private String m_medocNumber;
	private String m_medocDesignation;
	private BigDecimal m_medocUnitPrice;
	private int m_medocStock;
	private boolean m_medocIsActive;

	public Medoc(
		String medocNumber,
		String medocDesignation,
		BigDecimal medocUnitPrice,
		int medocStock,
		boolean medocIsActive
	) {
		m_medocNumber = medocNumber;
		m_medocDesignation = medocDesignation;
		m_medocUnitPrice = medocUnitPrice;
		m_medocStock = medocStock;
		m_medocIsActive = medocIsActive;
	}


	/**
	 * 
	 *  GETTERS
	 * 
	 * */
	 public String getMedocNumber() {
	 	return this.m_medocNumber;
	 }

	 public String getMedocDesignation() {
	 	return this.m_medocDesignation;
	 }

	 public BigDecimal getMedocUnitPrice() {
	 	return this.m_medocUnitPrice;
	 }

	 public boolean getMedocIsActive() {
	 	return this.m_medocIsActive;
	 }

	 public int getMedocStock() {
	 	return this.m_medocStock;
	 }
}