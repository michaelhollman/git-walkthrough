public class CSVString {

  private String delim;;
	private String str;

	public CSVString() {
		delim = ",";
		str = "";
	}

	public CSVString(int x) {
		this();
		for (int i = 0; i < x - 1; i++) {
			str = str + delim;
		}
	}

	public CSVString(String str) {
		this();
		this.str = str;
	}

	protected void useCustomDelim(String delim) {
		this.delim = delim;
	}

	public void addSingleItem(String str) {
		if (this.size() == 0) {
			this.str = str;
		} else {
			this.str = this.str + delim + str;
		}
	}

/// conflict!!
fooowefklasdhjflaksjd
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

	public boolean isEmpty() {
		return str.isEmpty();
	}

	public boolean contains(String s) {
		boolean found = false;
		for (int i = 0; i < size() && !found; i++) {
			found = index(i).equals(s);
		}
		return found;
	}

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
		}
		return index;
	}

	@Override
	public String toString() {
		return str;
	}
}