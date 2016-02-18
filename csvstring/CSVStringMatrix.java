public class CSVStringMatrix extends CSVString {

	public CSVStringMatrix() {
		super();
        
        
		this.useCustomDelim(";" ) ;
	}

	public CSVString getSubCSV(int i) 
    {
		
        return new CSVString(this.index(i));
	}

}