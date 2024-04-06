package aze.coders.service;

import aze.coders.connection.DbConnection;
import aze.coders.entity.Accounts;
import aze.coders.entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountTable {
    public static Map<Integer, Accounts> map = new HashMap<>();

    public static void create_account_table(Connection connection){

        try {
            String strquery = "CREATE TABLE IF NOT EXISTS accounts (account_id INT, accountname text NOT NULL, branch_id INT NOT NULL, currency_id INT NOT NULL, CONSTRAINT foreign_acc_id FOREIGN KEY (account_id) REFERENCES clients (client_id)) ON DELETE CASCADE";

            Statement statement = connection.createStatement();
            statement.executeUpdate(strquery);
            System.out.println("account table created successfully");
            statement.close();
        } catch (Exception e) {
            System.out.println("table could not be created");
        }
    }
    public static void insert_accounts(){

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbConnection.getConnection();
            String sql = "SELECT * FROM clients";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                map.put(rs.getInt("client_id"),new Accounts(rs.getInt("client_id"), rs.getString("client_name"),rs.getInt("client_id"),rs.getInt("client_id")));
            }

            String intoOp = "INSERT INTO accounts (account_id, accountname, branch_id, currency_id) values (?, ?, ?, ?)";

            PreparedStatement prepState = conn.prepareStatement(intoOp);

                for (Map.Entry<Integer, Accounts> set: map.entrySet()){

                    Accounts accounts = set.getValue();
                    String sqlaccount = "SELECT * FROM accounts";
                    pstmt = conn.prepareStatement(sqlaccount);
                    rs = pstmt.executeQuery();

                    prepState.setInt(1, set.getKey());
                    prepState.setString(2, accounts.getAccountname()+set.getKey());
                    prepState.setInt(3, accounts.getBranch_id());
                    prepState.setInt(4, accounts.getCurrency_id());

                    prepState.addBatch();
                }
                prepState.executeBatch();


        } catch (Exception e) {
            System.out.println("Error map prossess");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Error closing resources");
            }
        }
    }
}
