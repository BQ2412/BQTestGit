package com.example.transaction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TransactionTest {
	private static ArrayList<Transaction> testledger;
	private static Transaction transaction;

	/*
	 * Ledger:  A -> S 
	 * 		    B -> F 
	 * 			D -> L 
	 * 			A -> D 
	 * 			C -> L 
	 * 			X -> Z 
	 * 			D -> X
	 */
	@BeforeAll
	public static void setUp() {
		transaction = new Transaction("", "", java.sql.Timestamp.valueOf("2020-01-01 07:00:00.0"));
		testledger = new ArrayList<Transaction>();
		
		testledger.add(new Transaction("A", "S", java.sql.Timestamp.valueOf("2020-01-01 07:01:00.0")));
		testledger.add(new Transaction("B", "F", java.sql.Timestamp.valueOf("2020-01-01 07:02:00.0")));
		testledger.add(new Transaction("D", "L", java.sql.Timestamp.valueOf("2020-01-01 07:03:00.0")));
		testledger.add(new Transaction("A", "D", java.sql.Timestamp.valueOf("2020-01-01 07:04:00.0")));
		testledger.add(new Transaction("C", "L", java.sql.Timestamp.valueOf("2020-01-01 07:05:00.0")));
		testledger.add(new Transaction("X", "Z", java.sql.Timestamp.valueOf("2020-01-01 07:06:00.0")));
		testledger.add(new Transaction("D", "X", java.sql.Timestamp.valueOf("2020-01-01 07:07:00.0")));
		testledger.add(new Transaction("B", "Q", java.sql.Timestamp.valueOf("2020-01-01 07:08:00.0")));
		testledger.add(new Transaction("Q", "Q", java.sql.Timestamp.valueOf("2020-01-01 07:09:00.0")));
		testledger.add(new Transaction("Q", "R", java.sql.Timestamp.valueOf("2020-01-01 07:10:00.0")));
	}

	@AfterAll
	public static void tearDown() {
		testledger = null;
		transaction = null;
	}

	/*
	 * A -> A = True
	 */
	@Test
	void testSameAccountTrue() {
		assertTrue(transaction.validTransaction("A", "A", testledger));
	}

	/*
	 * Z -> A = False
	 */
	@Test
	void testInvalidSource() {
		assertFalse(transaction.validTransaction("Z", "A", testledger));
	}
	
	/*
	 * B -> Z = False
	 */
	@Test
	void testInvalidDestination() {
		assertFalse(transaction.validTransaction("B", "Z", testledger));
	}
	
	/*
	 * A -> S = True
	 */
	@Test
	void testSingleIteration() {
		assertTrue(transaction.validTransaction("A", "S", testledger));
	}

	/*
	 * A -> D = True
	 */
	@Test
	void testSecondIteration() {
		assertTrue(transaction.validTransaction("A", "D", testledger));
	}
	
	/*
	 * A -> X = True
	 */
	@Test
	void testMultipleAccounts() {
		assertTrue(transaction.validTransaction("A", "X", testledger));
	}		
	
	/*
	 * A -> L = False
	 */
	@Test
	void testMultipleAccountsOutOfOrder() {
		assertFalse(transaction.validTransaction("A", "L", testledger));
	}
	
	/*
	 * B -> S = False
	 */
	@Test
	void testFalsePositiveAccountToAccountIteration() {
		assertFalse(transaction.validTransaction("B", "S", testledger));
	}
}
