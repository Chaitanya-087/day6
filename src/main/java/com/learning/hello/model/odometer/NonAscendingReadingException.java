package com.learning.hello.model.odometer;

public class NonAscendingReadingException extends ReadingException {
		private static final long serialVersionUID = -467598733481709430L;

		public NonAscendingReadingException(String message) {
			super(message);
		}
}
