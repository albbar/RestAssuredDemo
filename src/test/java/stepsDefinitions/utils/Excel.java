package stepsDefinitions.utils;

public class Excel {
	
	private String excelPath;
	private String excelSheet;
	private boolean containsHeader;
	private int filaLeer;
	
	public Excel(String excelPath, String excelSheet, boolean containsHeader, int filaLeer) {
		super();
		this.excelPath = excelPath;
		this.excelSheet = excelSheet;
		this.containsHeader = containsHeader;
		this.filaLeer = filaLeer;
	}
	
	public String getExcelPath() {
		return excelPath;
	}
	
	public void setExcelPath(String excelPath) {
		this.excelPath = excelPath;
	}
	
	public String getExcelSheet() {
		return excelSheet;
	}
	
	public void setExcelSheet(String excelSheet) {
		this.excelSheet = excelSheet;
	}
	
	public boolean isContainsHeader() {
		return containsHeader;
	}
	
	public void setContainsHeader(boolean containsHeader) {
		this.containsHeader = containsHeader;
	}
	
	public int getFilaLeer() {
		return filaLeer;
	}
	
	public void setFilaLeer(int filaLeer) {
		this.filaLeer = filaLeer;
	}

}
