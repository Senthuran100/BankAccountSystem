package BankTransaction;

/** *********************************************************************
 * File:      StatementEntry.java	
 * Author:    P. Howells	
 * Contents:  7SENG007W Coursework 2019/20
 *	      This provides the basic data structure for a single
 *            bank account statement entry.
 * Date:      16/2/20 
 ************************************************************************ */


public class StatementEntry 
{
    private final char TAB = '\t' ;

    private final String CustomerID ;
    private final int    amount ;
    private final int    currentBal ;

    public StatementEntry( String CID, int amount, int currentBal )
    {
        this.CustomerID = CID ;
        this.amount     = amount ;
        this.currentBal = currentBal ;
    }

    public String getCustomer()      { return CustomerID ; }

    public int    getAmount()        { return amount ;     }

    public int    getCurrentBalance(){ return currentBal ; }


} // StatementEntry

