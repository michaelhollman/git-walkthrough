/**
 * A "2-dimensional" CSVString. Achieved using a CSVString containing string
 * representations of CSVStrings. Uses a semi-colon as default delimiter.
 * Zero-indexed.
 * 
 * When adding CSVStrings to a CSVStringMatrix, use
 * CSVStringMatrix.addSingleItem(CSVString.toString()).
 * 
 * @author Michael Hollman
 * @version 0.1
 * @see CSVString
 */
public class CSVStringMatrix extends CSVString {

  /**
	 * Creates an empty CSVString matrix.
	 */
	public CSVStringMatrix() {
		super();
		this.useCustomDelim(";");
	}

	/**
	 * @param index
	 *            index of the CSVString to get.
	 * @return the CSVString found at the given index.
	 * @throws IndexOutOfBoundsException
	 *             if i < 0 or i > size().
	 */
	public CSVString getSubCSV(int i) {
		return new CSVString(this.index(i));
	}

}