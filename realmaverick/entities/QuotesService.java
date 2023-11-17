package com.realmaverick.entities;

//bootstrap classes
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class QuotesService {

//Assocition : to call the DB specific methods of QuotesDao....
//------------------------------------------------------------------------------------------------------------
	// Creating reference of QuotesDao as field for association..
	QuotesDao quotesdao;

	// initialising quotesdao in own ctor...so that whenever the object of service
	// is
	// created....quotesdao also created...or else NPE
	public QuotesService() {
		try {
			quotesdao = new QuotesDao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//Service Methods....
	
// 1.displayAllQuotes()
//------------------------------------------------------------------------------------------------------------
	
	
	public void displayAllQuotes() {

		System.out.println();
		System.out.println("_________________****__YOU_ARE_IN_ALL_QUOTES_SECTION__****__________________________");
		System.out.println();

		ArrayList<Quotes> qList = null;
		try {
			// taking o/p from Quotesdao
			qList = quotesdao.daoDisplayAllQuotes();

			// if returned
			if (qList != null)
				qList.forEach(s -> System.out.println(s));
			else
				System.out.println("LIST NOT RETURNED....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// 2.displayQuotesOfUsers()
//------------------------------------------------------------------------------------------------------------

	
	public void displayQuotesOfUsers(int userId) {

		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("_________________****__YOU_ARE_IN_MY_QUOTES_SECTION__****__________________________");
		System.out.println();

		ArrayList<Quotes> qList = null;

		try {
			// taking o/p from Quotesdao
			qList = quotesdao.daodisplayQuotesOfUsers(userId);

			// if returned
			if (qList != null)
				qList.forEach(s -> System.out.println(s));
			else
				System.out.println("LIST NOT RETURNED....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// 3. fav Quotes..
//------------------------------------------------------------------------------------------------------------

	
	public void myFavQuotes(int uId) {

		System.out.println();
		System.out.println("_________________****__YOU_ARE_IN_MY_FAV_QUOTES_SECTION__****__________________________");
		System.out.println();

		ArrayList<Quotes> qList = null;
		try {
			// taking o/p from Quotesdao
			qList = quotesdao.daoMyFavQuotes(uId);

			// if returned
			if (qList != null)
				qList.forEach(s -> System.out.println(s.getQuote()));
			else
				System.out.println("LIST NOT RETURNED....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// 4.a LikeQuotes....
//------------------------------------------------------------------------------------------------------------

	
	public int likeQuotes(int uId) {

		System.out.println();
		System.out.println("_________________****__YOU_ARE_IN_LIKE_QUOTES_SECTION__****__________________________");
		System.out.println();

		Scanner sc = new Scanner(System.in);

		System.out.println("ENTER THE QUOTE_ID TO LIKE IT:");

		int qId = sc.nextInt();

		int cnt = 0;

		try {
			cnt = quotesdao.daoLikeQuotes(qId, uId);
			return cnt;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

// 4.b Unlike Quote
//------------------------------------------------------------------------------------------------------------
	
	
	public int UnlikeQuote(int uId) {

		System.out.println();
		System.out.println("_________________****__YOU_ARE_IN_UNLIKE_QUOTES_SECTION__****__________________________");
		System.out.println();

		Scanner sc = new Scanner(System.in);

		System.out.println("ENTER THE QUOTE_ID TO UNLIKE IT:");
		int qId = sc.nextInt();

		int cnt = 0;

		try {
			cnt = quotesdao.daoUnlikeQuote(qId, uId);
			return cnt;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

// 5. add Quotes
//------------------------------------------------------------------------------------------------------------

	
	public void addQuote(int userId) {

		// daoAddQuote
		Scanner sc = new Scanner(System.in);
		int cnt = 0;

		System.out.println();
		System.out.println("_________________****__YOU_ARE_IN_ADD_QUOTES_SECTION__****__________________________");
		System.out.println();

		// taking i/p from user......
		System.out.print("ENTER QUOTE : ");
		String quote = sc.nextLine();

		System.out.print("AUTHOR : ");
		String author = sc.nextLine();

		Date createdAt = null;

		// putting into object....
		Quotes q = new Quotes(0, quote, author, userId, createdAt);

		try {
			cnt = quotesdao.daoAddQuote(q);

			if (cnt != 0)
				System.out.println("Quote added successfully...");
			else
				System.out.println("Quote adding failed....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
// 6.Edit Quote
//------------------------------------------------------------------------------------------------------------

	
	public void editQuote(int userId) {

		// daoAddQuote
		Scanner sc = new Scanner(System.in);
		int cnt = 0;

		System.out.println();
		System.out.println("_________________****__YOU_ARE_IN_EDIT_QUOTES_SECTION__****__________________________");
		System.out.println();

		// taking i/p from user......
		System.out.print("QUOTE ID : ");
		int qId = sc.nextInt();

		// nextLine() is not working......
		System.out.print("ENTER NEW QUOTE : ");
		String quote = sc.next();

		try {
			cnt = quotesdao.daoEditQuote(quote, qId, userId);

			if (cnt != 0)
				System.out.println("Quote edit successfully...");
			else
				System.out.println("Quote edit failed....");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

// 7.delete Quotes
//------------------------------------------------------------------------------------------------------------

	
	public int deleteQuoteById(int userId) {

		// daoAddQuote
		Scanner sc = new Scanner(System.in);
		int cnt = 0;

		System.out.println();
		System.out.println("_________________****__YOU_ARE_IN_DELETE_QUOTES_SECTION__****__________________________");
		System.out.println();

		// taking i/p from user......
		System.out.print("QUOTE ID : ");
		int qId = sc.nextInt();

		try {
			cnt = quotesdao.daoDeleteQuoteById(qId, userId);

			return cnt;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cnt;
	}

//------------------------------------------------------------------------------------------------------------

}
