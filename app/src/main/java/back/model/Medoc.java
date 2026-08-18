package back.model;

public class Medoc {

	private String m_medocNumber;
	private String m_medocDesignation;
	private float m_medocUnitPrice;
	private int m_medocStock;
	private boolean m_medocIsActive;
	private String m_medocType;

	public Medoc(
		String medocNumber,
		String medocDesignation,
		float medocUnitPrice,
		int medocStock,
		boolean medocIsActive,
		String medocType
	) {
		m_medocNumber = medocNumber;
		m_medocDesignation = medocDesignation;
		m_medocUnitPrice = medocUnitPrice;
		m_medocStock = medocStock;
		m_medocIsActive = medocIsActive;
		m_medocType = medocType;
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

	 public float getMedocUnitPrice() {
	 	return this.m_medocUnitPrice;
	 }

	 public boolean getMedocIsActive() {
	 	return this.m_medocIsActive;
	 }

	 public int getMedocStock() {
	 	return this.m_medocStock;
	 }

	 public String getMedocType() {
	 	return this.m_medocType;
	 }
}