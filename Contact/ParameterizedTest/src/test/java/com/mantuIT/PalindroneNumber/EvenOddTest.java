package com.mantuIT.PalindroneNumber;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class EvenOddTest {


@ParameterizedTest
@CsvFileSource(resources = "data.csv", numLinesToSkip=1)
	public void evenOroddTest(String input ,String expected)
	{
		EvenOdd evenOdd =new EvenOdd();
		String actual = evenOdd.evenOddNum(Integer.parseInt(input));
		
		assertEquals(expected, actual);
	}

}
