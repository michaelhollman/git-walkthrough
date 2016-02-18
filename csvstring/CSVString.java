/**
 * Holds a set of information using Strings (and only strings). Functions
 * similarly to a primative ArrayList or stack. Holds all information in one
 * massive String, separating each element with a delimiter (by default a comma,
 * hence CSV).
 * 
 * CSVStrings are zero indexed!!!
 * 
 * @author Michael Hollman
 * @version 0.6
 * 
 */
public class CSVString {

  private String delim;;
	private String str;

	/**
	 * Creates an empty CSVString.
	 */
	public CSVString() {
		delim = ",";
		str = "";
	}

	/**
	 * Constructs an empty CSVString with {@code x} number of empty elements.
	 * Calling {@link #addSingleItem(String)} or {@link #addManually(String)}
	 * will append to the end, leaving {@code x} number of empty elements
	 * untouched.
	 * 
	 * @param x
	 *            number of empty elements in the CSV.
	 */
	public CSVString(int x) {
		this();
		for (int i = 0; i < x - 1; i++) {
			str = str + delim;
		}
	}

	/**
	 * Constructs a CSVString with the provided string. Values must already be
	 * separated by the default delimiter. Use caution when using this
	 * constructor.
	 * 
	 * @param str
	 *            a "pre-formatted" CSVString, with values already separated by
	 *            the delimiter.
	 */
	public CSVString(String str) {
		this();
		this.str = str;
	}

	/**
	 * Use a custom string for the delimiter. Use with extreme caution.
	 * 
	 * @param delim
	 *            custom delimiter.
	 */
	protected void useCustomDelim(String delim) {
		this.delim = delim;
	}

	/**
	 * Add another element to the CSV. Note: Do not include any delimiting
	 * characters in the parameter.
	 * 
	 * @param str
	 *            Single element to add to the end of the CSV.
	 */
	public void addSingleItem(String str) {
		if (this.size() == 0) {
			this.str = str;
		} else {
			this.str = this.str + delim + str;
		}
	}

	/**
	 * Counts the number of elements in the CSVString. Similar to Array.length.
	 * 
	 * @return the number of elements in the CSVString.
	 */
	public int size() {
		String partialStr = str;
		if (partialStr.indexOf(delim) != -1) {
			int count = 1; // delim found, so size is at least 1
			while (partialStr.indexOf(delim) != -1) {
				partialStr = partialStr.substring(partialStr.indexOf(delim) + 1,
						partialStr.length());
				count++;
			}
			return count;
		} else { // no delims found
			if (partialStr.isEmpty()) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	/**
	 * Returns the String value of the String element at the given index (zero
	 * indexing).
	 * 
	 * @param x
	 *            The index of the String to return.
	 * @return the String value at index x.
	 * @throws IndexOutOfBoundsException
	 *             if x < 0 or x >= {@link #size()}.
	 */
	public String index(int x) {
		String partialStr = str;
		if (x >= 0 && x < size()) { // within bounds
			if (x == 0) {
				if (size() > 1) { // more than one substring
					partialStr = partialStr.substring(0, partialStr.indexOf(delim));
				} // else only one substring, return partialStr
			} else { // (x > 0)
				for (int i = 0; i < x; i++) { // chop the front off
					partialStr = partialStr.substring(partialStr.indexOf(delim) + 1,
							partialStr.length());
				}
				if (x + 1 < this.size()) { // chop the end off, if needed
					partialStr = partialStr.substring(0, partialStr.indexOf(delim));
				}
			}
			return partialStr;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * Changes the value of the String element and the given index (zero
	 * indexing).
	 * 
	 * @param x
	 *            Index of the element to change.
	 * @param newValue
	 *            new String value of element.
	 * @throws IndexOutOfBoundsException
	 *             if x < 0 or x >= {@link #size()}.
	 */
	public void setIndex(int x, String newValue) {
		String front = "";
		String end = "";
		if (x >= 0 && x < size()) { // within bounds
			for (int i = 0; i < x; i++) {
				front = front + index(i) + delim;
			}
			for (int i = x + 1; i < size(); i++) {
				end = end + delim + index(i);
			}
			str = front + newValue + end;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * @return true if, and only if, {@link #size()} is 0.
	 */
	public boolean isEmpty() {
		return str.isEmpty();
	}

	/**
	 * Compares given string to the elements of the CSVString. Uses
	 * {@link String#equals(Object)}.
	 * 
	 * @param s
	 *            The String element to find in the CSVString.
	 * @return true if the give string exactly matches any of the elements of
	 *         the CSVString.
	 */
	public boolean contains(String s) {
		boolean found = false;
		for (int i = 0; i < size() && !found; i++) {
			found = index(i).equals(s);
		}
		return found;
	}

	/**
	 * Searches the CSVString for the exact string provided. Returns it's index.
	 * 
	 * @param s
	 *            String to search for.
	 * @return the FIRST index of the given string, or -1 if it cannot be found.
	 */
	public int indexOf(String s) {
		int index = -1;
		if (this.contains(s)) {
			boolean found = false;
			for (int i = 0; i < size() && !found; i++) {
				if (index(i).equals(s)) {
					index = i;
					found = true;
				}
			}
		} // didn't contain s, index still = -1
		return index;
	}

	/**
	 * @return the exact string that contains all the information for the
	 *         CSVString, including delimiters.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return str;
	}
}