package com.application;

import java.util.Arrays;

public class DisabledConnector extends Exception {

	public DisabledConnector(String string) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DisabledConnector [getMessage()=" + getMessage() + ", getLocalizedMessage()=" + getLocalizedMessage()
				+ ", getCause()=" + getCause() + ", toString()=" + super.toString() + ", fillInStackTrace()="
				+ fillInStackTrace() + ", getStackTrace()=" + Arrays.toString(getStackTrace()) + ", getSuppressed()="
				+ Arrays.toString(getSuppressed()) + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3165413711984772444L;

}
