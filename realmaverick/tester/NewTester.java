package com.realmaverick.tester;

//my project classes.
import com.realmaverick.entities.QuotesService;
import com.realmaverick.entities.User;
import com.realmaverick.entities.UserService;

//bootstrap classes.
import java.util.Scanner;

public class NewTester {

	public static void main(String[] args) {

		// calling methods of service using the switch statement..
		UserService userservice = new UserService();
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {

			boolean isTrue = false;

			System.out.println();
			System.out.println("_________________****__WELCOME_TO_LOGIN_SECTION__****__________________________");
			System.out.println();

			System.out.println("0. Exit");
			System.out.println("1.SignIn()");
			System.out.println("2.SignUp()");

			System.out.println();
			System.out.println("ENTER CHOICE :");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				// 1.signIn()
				User u = userservice.signIn();

				if (u != null)
					mainMenu(u);
				else
					System.out.println("signIn is failed...");
				break;

			case 2:
				// 2.signUp()
				isTrue = userservice.signUp();

				if (isTrue == true)
					System.out.println("User is registered....");
				else
					System.out.println("registration is failed...");

				break;

			}
		} while (choice != 0);

		System.out.println();
		System.out.println("_________________****__VISIT_AGAIN_TO_AWESOME_QUOTES__****__________________________");
		System.out.println();
	}

	public static void mainMenu(User u) {

		Scanner sc = new Scanner(System.in);

		// Creating an object of Service classes ...and use it to call methods in
		// service.
		UserService userservice = new UserService();
		QuotesService quotesservice = new QuotesService();
		int choice = 0;
		do {

			int cnt = 0;
			System.out.println();
			System.out.println("_________________****__WELCOME_TO_MAINMENU_SECTION__****__________________________");
			System.out.println();

			System.out.println("0. Sign Out");
			System.out.println("1. All Quotes");
			System.out.println("2. My Quotes");
			System.out.println("3. My Favorite Quotes");
			System.out.println("4. Like/Unlike Quote");
			System.out.println("5. New Quote");
			System.out.println("6. Edit Quote");
			System.out.println("7. Delete Quote");
			System.out.println("8. Change Password");
			System.out.println("9. Edit Proﬁle");

			System.out.println();
			System.out.println("ENTER CHOICE :");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				// 1. All Quotes
				quotesservice.displayAllQuotes();
				break;
			case 2:
				// 2. My Quotes
				quotesservice.displayQuotesOfUsers(u.getUserId());
				break;
			case 3:
				// 3. My Favorite Quotes
				quotesservice.myFavQuotes(u.getUserId());
				break;
			case 4:
				// 4. Like/Unlike Quote
				int count = 0;
				do {
					System.out.println("0. EXIT");
					System.out.println("1. LIKE SECTION");
					System.out.println("2. UNLIKE SECTION");

					System.out.println();

					System.out.println("ENTER CHOICE :");
					count = sc.nextInt();

					switch (count) {
					case 1:
						cnt = quotesservice.likeQuotes(u.getUserId());
						if (cnt != 0)
							System.out.println("Quote liked successfully...");
						else
							System.out.println("Quote like failed....");
						break;
					case 2:
						cnt = quotesservice.UnlikeQuote(u.getUserId());
						if (cnt != 0)
							System.out.println("Quote Unliked successfully...");
						else
							System.out.println("Quote unlike failed....");
						
						break;
					}
				} while (count != 0);
				
				break;
			case 5:
				// 5. New Quote
				quotesservice.addQuote(u.getUserId());
				break;
			case 6:
				// 6. Edit Quote
				quotesservice.editQuote(u.getUserId());
				break;
			case 7:
				// 7. Delete Quote
				cnt = quotesservice.deleteQuoteById(u.getUserId());
				if (cnt != 0)

					System.out.println("Quote deletion successfully...");
				else
					System.out.println("Quote deletion failed....");
				break;
			case 8:
				// 8. Change Password"
				userservice.changePasswd();
				break;
			case 9:
				// 9. Edit Proﬁle
				userservice.editProfile(u.getUserId());
				break;
			}
		} while (choice != 0);

	}
}
