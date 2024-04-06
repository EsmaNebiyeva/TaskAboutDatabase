package aze.coders.service;

import aze.coders.connection.DbConnection;
import aze.coders.entity.Accounts;
import aze.coders.entity.Client;

import java.sql.*;
import java.util.*;

public class AccountServiceImpl implements AccountService{
    @Override
    public void selectGetaccountsby() {

        System.out.println("choose a number: ");

        Scanner scanner = new Scanner(System.in);

        System.out.println(   "1) First ? offset ?");
        System.out.println(   "2) accountname start ?--letter");
        System.out.println(   "3) column contain ?--letter");

        Connection conn = null;
        Statement statement = null;

        int i = scanner.nextInt();

            try {
                if (i==2){
                    System.out.println("write start letter:");
                    String l = scanner.next();
                    conn = DbConnection.getConnection();
                    String sql = "select * from accounts WHERE accountname like '"+l+"%'";
                    statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()){
                        Accounts accounts = new Accounts(resultSet.getInt("account_id"),
                                resultSet.getString("accountname"),
                                resultSet.getInt("branch_id"),
                                resultSet.getInt("currency_id"));
                        System.out.println(accounts); // (add to list)
                    }
                } else if (i==1) {
                    System.out.println("write limit number: ");
                    int j = scanner.nextInt();
                    System.out.println("write skip number: ");
                    int s = scanner.nextInt();
                    conn = DbConnection.getConnection();
                    String sql = "select * from accounts limit "+j+" OFFSET "+s;
                    statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()){
                        Accounts accounts = new Accounts(resultSet.getInt("account_id"),
                                resultSet.getString("accountname"),
                                resultSet.getInt("branch_id"),
                                resultSet.getInt("currency_id"));
                        System.out.println(accounts); // (add to list)
                    }
                }else if (i==3){
                    System.out.println("write column name:");
                    String n = scanner.next();
                    System.out.println("write letter name:");
                    String ln = scanner.next();
                    conn = DbConnection.getConnection();
                    String sql = "select * from accounts WHERE "+n+" like '%"+ln+"%'";
                    statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()){
                        Accounts accounts = new Accounts(resultSet.getInt("account_id"),
                                resultSet.getString("accountname"),
                                resultSet.getInt("branch_id"),
                                resultSet.getInt("currency_id"));
                        System.out.println(accounts); // (add to list)
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error");
            } finally {
                try {
                    if (statement != null) statement.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    System.out.println("Error closing resources");
                }
            }

     }

    @Override
    public Map<Integer, Accounts> getAllAccounts(){

        Map<Integer, Accounts> mapall = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet ac = null;

        try {
            conn = DbConnection.getConnection();
            String sql = "SELECT * FROM accounts";
            pstmt = conn.prepareStatement(sql);
            ac = pstmt.executeQuery();

            while (ac.next()) {
                Accounts account = new Accounts();
                account.setAccount_id(ac.getInt("account_id"));
                account.setAccountname(ac.getString("accountname"));
                account.setBranch_id(ac.getInt("branch_id"));
                account.setCurrency_id(ac.getInt("currency_id"));

                mapall.put(ac.getInt("account_id"), account);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving all accounts");
        } finally {
            try {
                if (ac != null) ac.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Error closing resources");
            }
        }

        return mapall;
    }

    @Override
    public void addAccount() {
        AccountTable.insert_accounts();
    }

    @Override
    public void deleteAccount(int acc_id) {
        // with foreign key automotic delete account
    }

    @Override
    public void updateAccountName(int id, String new_acc_Name) {

        Connection conn = null;
        PreparedStatement prst = null;

        try {
            conn = DbConnection.getConnection();
            String sql = "UPDATE accounts SET accountname = ? WHERE account_id = ?";
            prst = conn.prepareStatement(sql);
            prst.setString(1, new_acc_Name);
            prst.setInt(2, id);
            prst.executeUpdate();
            System.out.println("account name updated successfully");
        } catch (SQLException e) {
            System.out.println("Error updating account name");
        } finally {
            try {
                if (prst != null) prst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources");
            }
        }
    }
    
}
