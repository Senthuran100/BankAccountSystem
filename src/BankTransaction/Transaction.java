package BankTransaction;

/** *********************************************************************
 * File:      Transaction.java	
 * Author:    P. Howells	
 * Contents:  7SENG007W Coursework 2019/20
 *	      Provides the basic data structure for a bank transaction. 
 *	      That is customer id & the amount to either deposit or withdraw 
 * Date:      16/2/20 
 ************************************************************************ */

class Transaction
{
    private final String CustomerID ;  
    private final int    amount ;

    public Transaction( String CID, int amount )
    {
        this.CustomerID  = CID ;
        this.amount 	 = amount ;
    }

    public String getCustomerID( ){ return CustomerID ; }

    public int    getAmount( )    { return amount ; }

    public String toString( )
    {
        return  new String( "[ " +
                               "Customer: " + CustomerID + ", " + 
                               "Amount: "   + amount     +
                            "]"  ) ;
    }

} // Transaction

