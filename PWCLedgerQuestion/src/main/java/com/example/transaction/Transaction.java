package com.example.transaction;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Transaction {
	String Sender;
	String Receiver;
	Timestamp Time;

	public Transaction(String src, String dest, Timestamp time) {
		super();
		Sender = src;
		Receiver = dest;
		Time = time;
	}

	public boolean validTransaction(String pSource, String pDestination, ArrayList<Transaction> pTransactions) {
		
		// Account to Account transfers return true
		if (pSource.equalsIgnoreCase(pDestination))
		{
			return true;
		}
		
		ArrayList<Transaction> parentAccounts = new ArrayList<Transaction>();
		
		parentAccounts = (ArrayList<Transaction>) pTransactions.parallelStream().filter(P -> P.Sender.equalsIgnoreCase(pSource)).collect(Collectors.toList());
		
//		System.out.println("Loop for " + pSource + " -> " + pDestination + " Sender Matches: " + parentAccounts.size() );
		
		if (parentAccounts.size() > 0)
		{
			for (Transaction tran : parentAccounts)
			{
//				System.out.println("  Sender: " + tran.Sender + " Receiver: " + tran.Receiver + " Time: " + tran.Time);
				
				if (tran.Receiver == pDestination)
				{
//					System.out.println("Found Destination: " + tran.Receiver + " from Source " + tran.Sender);
					return true;  // we found a valid path to the Destination
				}	
				else
				{
					if ( validTransaction(tran.Receiver, pDestination, (ArrayList<Transaction>) pTransactions.stream().filter(T -> T.Time.compareTo(tran.Time) > 0).collect(Collectors.toList())) == true)
					{
						return true;  // return true only if a child is 
					}
					// continue to iterate through the children
				}
			}			
		}
		else
		{			
			return false;  // Source not found
		}
				
		return false;
	}
}
