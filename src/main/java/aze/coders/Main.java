package aze.coders;

import aze.coders.connection.DbConnection;
import aze.coders.entity.Accounts;
import aze.coders.entity.Client;
import aze.coders.entity.Transactions;
import aze.coders.service.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    private static final TransactionsService transactionsService= new TransactionServiceImp();
    public static void main(String[] args) {

        String[] clientNames = {"Nərmin", "Tural", "Aysel", "Fərid", "Leyla", "Elçin", "Vüsal", "Nigar", "Rauf", "Aynur",
                "Elmir", "Fərhad", "Aynura", "Ramil", "Nigar", "Murad", "Günel", "Elvin", "Günay", "Nərgiz"};

        Connection conn= DbConnection.getConnection();
 // createTable(conn);
  //insertData(conn, clientNames);
        ClientServiceImpl client = new ClientServiceImpl();
//       Client client1=new Client("Eyvaz",21);
//     client.deleteClient(21);
//       client.updateClientName(20, "Eyvaz");

        System.out.println("--------------------------------------------------------------------------------------");

     // AccountTable.create_account_table(conn); // account table-yarat
     //  AccountTable.insert_accounts();  // movcud client ucun account yarat

        //AccountServiceImpl accountService = new AccountServiceImpl(); //class obyekti yarat

        //accountService.selectGetaccountsby(); filter secimi

        //accountService.updateAccountName(1, "Nəzrin1"); adi yenileme
        System.out.println("***********************************************************");
        Transactions transactions=new Transactions("2012-23-01",5,17,666666,"euro");
      // transactionsService.createTransaction( conn);
     //transactionsService.insertIntoTransaction(transactions, conn);
       // transactionsService.dropTransaction(conn);
        // transactionsService.getWithId(1,conn);
       // transactionsService.deleteWithId(3,conn);
        // transactionsService.updateWithId(1,transactions,conn);
       // transactionsService.selectAll(conn);
     // transactionsService.alterAddTransaction("phone","text",conn);
      //transactionsService.alterForeignKey("transactions","sender_fk","sender_account","accounts","account_id",conn );
       // transactionsService.alterForeignKey("transactions","receiver_fk","reciever_account","accounts","account_id",conn );
          transactionsService.firstFiveTranWithFunc(conn);

    }
    public static void createTable(Connection conn) {
        try {
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS clients (" +
                    "client_id SERIAL PRIMARY KEY," +
                    "client_name VARCHAR(255) NOT NULL" +
                    ")";

            stmt.executeUpdate(sql);
            System.out.println("Table created successfully");
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error while creating the table");
        }
    }
    public static void insertData(Connection conn, String[] clientNames) {
        PreparedStatement pstmt = null;
        try {
            // SQL command to insert a row into the table
            String sql = "INSERT INTO clients (client_name) VALUES (?)";

            pstmt = conn.prepareStatement(sql);

            for (String name : clientNames) {
                pstmt.setString(1, name);
                pstmt.addBatch();
            }
            pstmt.executeBatch();

            System.out.println("Data inserted successfully");

            // Close the statement
            pstmt.close();
        } catch (Exception e) {
            System.out.println("Error inserting data");
        }

    }
}