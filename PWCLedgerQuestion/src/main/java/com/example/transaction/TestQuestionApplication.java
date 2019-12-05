package com.example.transaction;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PwcLedgerQuestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(PwcLedgerQuestionApplication.class, args);
		
		testLedger();
	}

	private static void testLedger() {
		ArrayList<Transaction> ledger;
		Transaction transactionClass;
		
		Boolean result = null;
		
		transactionClass = new Transaction("", "", java.sql.Timestamp.valueOf("2020-01-01 07:00:00.0"));
		ledger = new ArrayList<Transaction>();
		
		ledger.add(new Transaction("A", "S", java.sql.Timestamp.valueOf("2020-01-01 07:01:00.0")));
		ledger.add(new Transaction("B", "F", java.sql.Timestamp.valueOf("2020-01-01 07:02:00.0")));
		ledger.add(new Transaction("D", "L", java.sql.Timestamp.valueOf("2020-01-01 07:03:00.0")));
		ledger.add(new Transaction("A", "D", java.sql.Timestamp.valueOf("2020-01-01 07:04:00.0")));
		ledger.add(new Transaction("C", "L", java.sql.Timestamp.valueOf("2020-01-01 07:05:00.0")));
		ledger.add(new Transaction("X", "Z", java.sql.Timestamp.valueOf("2020-01-01 07:06:00.0")));
		ledger.add(new Transaction("D", "X", java.sql.Timestamp.valueOf("2020-01-01 07:07:00.0")));
		ledger.add(new Transaction("B", "Q", java.sql.Timestamp.valueOf("2020-01-01 07:08:00.0")));
		ledger.add(new Transaction("Q", "Q", java.sql.Timestamp.valueOf("2020-01-01 07:09:00.0")));
		ledger.add(new Transaction("Q", "R", java.sql.Timestamp.valueOf("2020-01-01 07:10:00.0")));
		
//		result =  transactionClass.validTransaction("A", "A", ledger);  // true good
//		System.out.println("T A->A result: " + result.toString());
//		System.out.println();
		
//		result =  transactionClass.validTransaction("A", "D", ledger);  // true good
//		System.out.println("T A->D result: " + result.toString());
//		System.out.println();
		
//		result =  transactionClass.validTransaction("A", "Z", ledger);  // false good 
//		System.out.println("F A->Z result: " + result.toString());
//		System.out.println();
		
//		result =  transactionClass.validTransaction("A", "X", ledger);  // true  good
//		System.out.println("T A->X result: " + result.toString());
//		System.out.println();
		
//		result =  transactionClass.validTransaction("A", "L", ledger);  // true  good
//		System.out.println("F A->L result: " + result.toString());
//		System.out.println(); 
		
		result =  transactionClass.validTransaction("B", "S", ledger);  // true  wip
		System.out.println("F B->S result: " + result.toString());
		System.out.println();
	}
}
