package com.realmaverick.entities;

//my project classes
import com.realmaverick.db.DbUtil;

//bootstrap classes
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class QuotesDao {

//Pre-Action preparation
//-----------------------------------------------------------------------------------------------------------------
	// creating connection using the static method of DbUtil
	Connection con;

	// static fields for preparing single statements for each type of method action
	PreparedStatement displayAllQuotesStmt;
	PreparedStatement displayQuotesOfUsersStmt;
	PreparedStatement addQuotesStmt;
	PreparedStatement editQuotesStmt;
	PreparedStatement deleteQuotesStmt;
	PreparedStatement likeQuotesStmt;
	PreparedStatement unLikeQuotesStmt;
	PreparedStatement myFavQuotesStmt;

	// As connections should automatically generated....
	// main() created object of service ---> service created object of dao ---> in
	// dao we create connections.
	public QuotesDao() throws Exception {

		// creating connection using the static method of DbUtil and taking object int
		// Reference of Connection
		con = DbUtil.getConnection();

		// 3.myFavQuotesStmt
		String myFavQuotesSql = "select quote from quotes q join fav_quotes fq on (q.id = fq.quote_id) where fq.user_id = ?";
		myFavQuotesStmt = con.prepareStatement(myFavQuotesSql);

		// 4.a likeQuotesStmt
		String likeQuotesSql = "insert into fav_quotes values(?,?)";
		likeQuotesStmt = con.prepareStatement(likeQuotesSql);

		// 4.b UnlikeQuotesStmt :
		String unLikeQuotesSql = "delete from fav_quotes where user_id = ? and quote_id = ?";
		unLikeQuotesStmt = con.prepareStatement(unLikeQuotesSql);

		// 5.displayAllQuotes() Query :
		String displayAllQuotesSql = "SELECT id, quote, author, user_id, created_at FROM quotes";
		displayAllQuotesStmt = con.prepareStatement(displayAllQuotesSql);

		// 6.displayQuotesOfUsersStmt() Query :
		String displayQuotesOfUsersSql = "SELECT id, quote, author, user_id, created_at FROM quotes WHERE user_id = ?";
		displayQuotesOfUsersStmt = con.prepareStatement(displayQuotesOfUsersSql);

		// 7.addQuotesStmt() Query :
		String addQuotesSql = "INSERT INTO quotes(quote, author, user_id, created_at) VALUES(?, ?, ?, ?)";
		addQuotesStmt = con.prepareStatement(addQuotesSql);

		// 8.editQuotesStmt() Query :
		String editQuotesSql = "UPDATE quotes SET quote = ? WHERE id = ? and user_id = ?";
		editQuotesStmt = con.prepareStatement(editQuotesSql);

		// 8.editQuotesStmt() Query :
		String deleteQuotesSql = "DELETE FROM quotes WHERE id = ? and user_id = ?";
		deleteQuotesStmt = con.prepareStatement(deleteQuotesSql);

	}

//Action : Methods for dealing with DB

// 1.displayAllQuotes Stmt
//-----------------------------------------------------------------------------------------------------------------

	public ArrayList<Quotes> daoDisplayAllQuotes() throws Exception {

		// ArrayList to store resultset of Quotes
		ArrayList<Quotes> qList = new ArrayList<>();

		// nothing to set in displayAllQuotesStmt

		// executing displayAllQuotesStmt
		ResultSet rs = displayAllQuotesStmt.executeQuery();

		while (rs.next()) {

			// Getting values of DB
			int id = rs.getInt("id");
			String quote = rs.getString("quote");
			String author = rs.getString("author");
			int userId = rs.getInt("user_id");

			// We get TimeStamp ---> have to convert it into Date
			Timestamp ts = rs.getTimestamp("created_at");
			Date createdAt = new Date(ts.getTime());

			// putting values into object of Quote
			Quotes q = new Quotes(id, quote, author, userId, createdAt);

			// adding all quotes into list
			qList.add(q);

		}
		return qList;
	}

// 2.displayQuotesOfUsers stmt
//-----------------------------------------------------------------------------------------------------------------

	public ArrayList<Quotes> daodisplayQuotesOfUsers(int userId) throws Exception {

		// ArrayList to store resultset of Quotes
		ArrayList<Quotes> qList = new ArrayList<>();

		// set displayQuotesOfUsersStmt
		displayQuotesOfUsersStmt.setInt(1, userId);

		// executing displayAllQuotesStmt
		ResultSet rs = displayQuotesOfUsersStmt.executeQuery();

		while (rs.next()) {

			// Getting values of DB
			int id = rs.getInt("id");
			String quote = rs.getString("quote");
			String author = rs.getString("author");
			int uId = rs.getInt("user_id");

			// We get TimeStamp ---> have to convert it into Date
			Timestamp ts = rs.getTimestamp("created_at");
			Date createdAt = new Date(ts.getTime());

			// putting values into object of Quote
			Quotes q = new Quotes(id, quote, author, userId, createdAt);

			// adding all quotes into list
			qList.add(q);

		}
		return qList;
	}

// 3 myFavQuotesStmt
//-----------------------------------------------------------------------------------------------------------------

	public ArrayList<Quotes> daoMyFavQuotes(int uId) throws Exception {

		ArrayList<Quotes> qList = new ArrayList<>();

		// setting myFavQuotesStmt
		myFavQuotesStmt.setInt(1, uId);

		//
		ResultSet rs = myFavQuotesStmt.executeQuery();
		while (rs.next()) {

			// Getting values of DB

			String quote = rs.getString("quote");

			// putting values into object of Quote
			Quotes q = new Quotes(0, quote, null, 0, null);

			// adding all quotes into list
			qList.add(q);

		}

		return qList;
	}

// 4.a LikeStmt
//-----------------------------------------------------------------------------------------------------------------

	public int daoLikeQuotes(int qId, int uId) throws Exception {
		int cnt = 0;

		// setting likeQuotesStmt
		likeQuotesStmt.setInt(1, uId);
		likeQuotesStmt.setInt(2, qId);

		//
		cnt = likeQuotesStmt.executeUpdate();
		return cnt;
	}

// 4.b unlike Stmt:
//-----------------------------------------------------------------------------------------------------------------

	public int daoUnlikeQuote(int qId, int uId) throws Exception {
		int cnt = 0;

		// setting likeQuotesStmt
		unLikeQuotesStmt.setInt(1, uId);
		unLikeQuotesStmt.setInt(2, qId);

		//
		cnt = unLikeQuotesStmt.executeUpdate();

		return cnt;
	}

// 5.addQuote stmt
//-----------------------------------------------------------------------------------------------------------------

	public int daoAddQuote(Quotes q) throws Exception {

		int cnt = 0;

		// setting the stmt :
		addQuotesStmt.setString(1, q.getQuote());
		addQuotesStmt.setString(2, q.getAuthor());
		addQuotesStmt.setInt(3, q.getUserId());

		// creating timestamp :
		long time = new Date().getTime();
		java.sql.Timestamp ts = new Timestamp(time);
		addQuotesStmt.setTimestamp(4, ts);

		// executing stmt..
		cnt = addQuotesStmt.executeUpdate();

		return cnt;
	}

// 6.editQuote stmt
//-----------------------------------------------------------------------------------------------------------------

	public int daoEditQuote(String newQuote, int qId, int userId) throws Exception {
		int cnt = 0;

		editQuotesStmt.setString(1, newQuote);
		editQuotesStmt.setInt(2, qId);
		editQuotesStmt.setInt(3, userId);

		cnt = editQuotesStmt.executeUpdate();

		return cnt;
	}

// 7.deleteQuoteById stmt
//-----------------------------------------------------------------------------------------------------------------
	public int daoDeleteQuoteById(int qId, int userId) throws Exception {
		int cnt = 0;

		deleteQuotesStmt.setInt(1, qId);
		deleteQuotesStmt.setInt(2, userId);

		cnt = deleteQuotesStmt.executeUpdate();

		return cnt;

	}

}
