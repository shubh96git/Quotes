package com.realmaverick.entities;

import java.util.Scanner;

public class UserService {

//Assocition : to call the DB specific methods of UserDao....
//------------------------------------------------------------------------------------------------------------
	// Creating reference of UserDao as field for association..
	UserDao userdao;

	// initialising userdao in own ctor...so that whenever the object of service is
	// created....userdao also created...or else NPE
	public UserService() {
		try {
			userdao = new UserDao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//Service Methods....
//------------------------------------------------------------------------------------------------------------

	// 1.signIn()
	public User signIn() {

		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("_________________****__YOU_ARE_IN_SIGN-IN_SECTION__****__________________________");
		System.out.println();

		System.out.print("Email: ");
		String email = sc.next();
		System.out.print("Password: ");
		String passwd = sc.next();

		try {
			User u = userdao.daoSignIn(email, passwd);

			// if returned object is not null then...email and passwd is correct...and
			// welcome it.
			if (u != null)
				return u;
			else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 2.signUp()
	public boolean signUp() {

		boolean flag = false;
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("_________________****__YOU_ARE_IN_SIGN-UP_SECTION__****__________________________");
		System.out.println();

		// taking i/p from user......
		System.out.print("First Name: ");
		String firstName = sc.next();
		System.out.print("Last Name: ");
		String lastName = sc.next();
		System.out.print("Email: ");
		String email = sc.next();
		System.out.print("Password: ");
		String passwd = sc.next();
		System.out.print("Mobile: ");
		String mobile = sc.next();

		// putting into object....
		User u = new User(0, firstName, lastName, email, passwd, mobile);

		// passing the object as argument for purpose of query inserting into db
		int cnt = 0;
		try {
			cnt = userdao.daoRegister(u);

			if (cnt != 0)
				flag = true;
			else
				return flag;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	//changePasswd
	public void changePasswd() {

		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("_________________****__YOU_ARE_IN_CHANGE_PASSWD_SECTION__****__________________________");
		System.out.println();

		System.out.print("Email: ");
		String email = sc.next();
		System.out.print("Password: ");
		String passwd = sc.next();
		System.out.print("New Password: ");
		String newPasswd = sc.next();

		int cnt = 0;

		try {
			cnt = userdao.daochangePasswd(newPasswd, email, passwd);

			if (cnt != 0) { // if rows inserted...then cnt = 1
				System.out.println("Passwd has been changed..........");
			} else
				System.out.println("Passwd changing failed..........");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//editProfile
	public void editProfile(int userId) {
		Scanner sc = new Scanner(System.in);
		int choice=0;
		do {
			System.out.println("0. EXIT");
			System.out.println("1. EDIT FIRST NAME");
			System.out.println("2. EDIT LAST NAME");
			System.out.println("3. EDIT EMAIL");
			System.out.println("4. MOBILE NO");
			
			System.out.println();
			System.out.print("ENTER YOU CHOICE :");
			choice = sc.nextInt();
			
			//we have 3 ? in query...fieldName, fieldValue, userId
			String fieldName =null;
			String fieldValue =null;
			
			switch(choice) {
			case 1:
				System.out.println("ENTER NEW FIRST NAME :");
				fieldValue =sc.next();
				fieldName = "first_name";
				break;
			case 2:
				System.out.println("ENTER NEW LAST NAME :");
				fieldValue =sc.next();
				fieldName = "last_name";
				break;
			case 3:
				System.out.println("ENTER NEW EMAIL NAME :");
				fieldValue =sc.next();
				fieldName = "email";
				break;
			case 4:
				System.out.println("ENTER NEW MOBILE NAME :");
				fieldValue =sc.next();
				fieldName = "mobile";
				break;	
			}
			
			try {
				int cnt = userdao.daoEditProfile(fieldName, fieldValue, userId);
				if(cnt != 0)
					System.out.println("Edit successful....");
				else
					System.out.println("Edit failed.....");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}while(choice != 0);

	}

}
