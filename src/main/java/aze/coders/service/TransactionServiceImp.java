package aze.coders.service;

import aze.coders.entity.Transactions;

import java.sql.*;

public class TransactionServiceImp implements TransactionsService {
   @Override
   public void createTransaction(Connection connection) {

       try {
           Statement statement = connection.createStatement();
           String query="create table transactions (transaction_id serial primary key  " +
                   ", date varchar(15)," +
                   "sender_account integer," +
                   "reciever_account integer, " +
                   "amount integer," +
                   " currency varchar(10) check(upper(currency) in ('AZN','USD','EURO')))";
           statement.execute(query) ;
           System.out.println("Create Transaction");

       } catch (SQLException e) {
           System.out.println("Xetaaaa Create"+e.getMessage());
       }


   }

   @Override
   public void insertIntoTransaction(Transactions transactions,Connection connection) {
       try {

           String query="insert into transactions(date,sender_account,reciever_account,amount,currency) values(?,?,?,?,?);";
           PreparedStatement preparedStatement= connection.prepareStatement(query);
           preparedStatement.setString(1,transactions.getDate());
           preparedStatement.setInt(2,transactions.getSender_account());
           preparedStatement.setInt(3,transactions.getReceiver_account());
           preparedStatement.setDouble(4,transactions.getAmount());
           preparedStatement.setString(5,transactions.getCurrency());
           preparedStatement.execute();
           System.out.println("Insert olundu");
           preparedStatement.close();
       } catch (SQLException e) {
           System.out.println("Xetaaaa Insert"+e.getMessage());
       }
   }

   @Override
   public void dropTransaction(Connection connection) {

       try {
           Statement statement = connection.createStatement();
           String query="drop table transactions";
           statement.execute(query) ;
           System.out.println("Drop Transaction");

       } catch (SQLException e) {
           System.out.println("Xetaaaa Drop"+e.getMessage());
       }
   }

   @Override
   public void getWithId(Integer id,Connection connection) {

       Transactions transactions=new Transactions();
       try {
           Statement statement = connection.createStatement();
           String query="select* from transactions where transaction_id ="+id+";";
           ResultSet resultSet = statement.executeQuery(query);
           while(resultSet.next()){
               transactions.setTransaction_id(resultSet.getInt("transaction_id"));
               transactions.setSender_account(resultSet.getInt("sender_account"));
               transactions.setReceiver_account(resultSet.getInt("reciever_account"));
               transactions.setDate(resultSet.getString("date"));
               transactions.setAmount(resultSet.getDouble("amount"));
               transactions.setCurrency(resultSet.getString("currency"));
               System.out.println(transactions);
           }
           System.out.println("Select Transaction");

       } catch (SQLException e) {
           System.out.println("Xetaaaa Select"+e.getMessage());
       }
   }

   @Override
   public void deleteWithId(Integer id,Connection connection) {

       try {
           Statement statement = connection.createStatement();
           String query="delete from transactions where transaction_id ="+id+";";
           statement.execute(query);
           System.out.println("Delete id Transaction");

       } catch (SQLException e) {
           System.out.println("Xetaaaa Delete"+e.getMessage());
       }
   }

   @Override
   public void updateWithId(Integer id,Transactions transactions,Connection connection) {

       try {
//            String query="update transactions set date="+ transactions.getDate()+",sender_account= "+transactions.getSender_account()
//                    +", reciever_account ="+transactions.getReceiver_account()+",amount="+transactions.getAmount()+" ,currency="+transactions.getCurrency()
//                    +" where transaction_id="+id+";";
           String query="update transactions set date=?,sender_account= ?, reciever_account =?,amount=? ,currency=? where transaction_id=?";
           PreparedStatement preparedStatement= connection.prepareStatement(query);
           preparedStatement.setString(1,transactions.getDate());
           preparedStatement.setInt(2,transactions.getSender_account());
           preparedStatement.setInt(3,transactions.getReceiver_account());
           preparedStatement.setDouble(4,transactions.getAmount());
           preparedStatement.setString(5,transactions.getCurrency());
           preparedStatement.setInt(6,id);
           //ResultSet resultSet = preparedStatement.executeQuery(query);
           int updatedColumns=preparedStatement.executeUpdate();
           if(updatedColumns>0)
           System.out.println("Update olundu");
           preparedStatement.close();
       } catch (SQLException e) {
           System.out.println("Xetaaaa Update"+e.getMessage());
       }
   }

    @Override
    public void selectAll(Connection connection) {
        Transactions transactions=new Transactions();
        try {
            Statement statement = connection.createStatement();
            String query="select* from transactions;";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                transactions.setTransaction_id(resultSet.getInt("transaction_id"));
                transactions.setSender_account(resultSet.getInt("sender_account"));
                transactions.setReceiver_account(resultSet.getInt("reciever_account"));
                transactions.setDate(resultSet.getString("date"));
                transactions.setAmount(resultSet.getDouble("amount"));
                transactions.setCurrency(resultSet.getString("currency"));
                System.out.println(transactions);
            }
            System.out.println("Select All Transaction");

        } catch (SQLException e) {
            System.out.println("Xetaaaa Select All"+e.getMessage());
        }
    }

    @Override
    public void alterAddTransaction(String columnName,String dataType,Connection connection) {
        try {
            String query="alter table transactions add column "+ columnName+"  "+dataType+" ";
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Alter Add Column Transaction");
        } catch (SQLException e) {
            System.out.println("Xetaaaa Add column"+e.getMessage());
        }
    }
    public void alterForeignKey(String table_name,String constraint_name,String column_name,String other_table,String other_column,Connection connection) {
        try {
            String query="alter table ? DROP CONSTRAINT ? ,ADD CONSTRAINT ? FOREIGN KEY (?) REFERENCES ?(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,table_name);
            preparedStatement.setString(2,constraint_name);
            preparedStatement.setString(3,constraint_name);
            preparedStatement.setString(4,column_name);
            preparedStatement.setString(5,other_table);
            preparedStatement.setString(6,other_column);
           preparedStatement.executeUpdate();
            System.out.println("ADD Constraint  id Transaction");
        } catch (SQLException e) {
            System.out.println("Xetaaaa Alter Constarint"+e.getMessage());
        }
    }

  //  @Override
//     public void firstFiveTranWithFunc() {
//         Connection connection= DBConnection.getConnection();
//         try {
// String query="CREATE OR REPLACE FUNCTION get_ilkfirst_5_transactions()\n" +
//         "RETURNS TABLE (\n" +
//         "    transaction_id INT,\n" +
//         "    date DATE,\n" +
//         "    sender_account INT,\n" +
//         "    receiver_account INT,\n" +
//         "    amount NUMERIC,\n" +
//         "    currency TEXT\n" +
//         ") AS $$\n" +
//         "BEGIN\n" +
//         "    RETURN QUERY SELECT transaction_id, date, sender_account, receiver_account, amount, currency \n" +
//         "                 FROM transactions \n" +
//         "                 LIMIT 5;\n" +
//         "END;\n" +
//         "$$ LANGUAGE plpgsql;";
//
//             System.out.println("gfdhj");
//             Statement statement = connection.createStatement();
//             statement.execute(query);
//
//             String callQuery="select*from get_ilkfirst_5_transactions()";
//             CallableStatement callStatement = connection.prepareCall(callQuery);
//             callStatement.registerOutParameter(1, Types.OTHER);
//              callStatement.execute(callQuery);
//           ResultSet resultSet= (ResultSet) callStatement.getObject(1);
//             System.out.println("678900");
//             Transactions transactions=new Transactions();    statement.close();
//             while(resultSet.next()){
//                // transactions.setTransaction_id(resultSet.getInt("transaction_id"));
//                 transactions.setSender_account(resultSet.getInt("sender_account"));
//                 transactions.setReceiver_account(resultSet.getInt("reciever_account"));
//                 transactions.setDate(resultSet.getString("date"));
//                 transactions.setAmount(resultSet.getDouble("amount"));
//                 transactions.setCurrency(resultSet.getString("currency"));
//                 System.out.println(transactions);
//             }
//             callStatement.close();
//             resultSet.close();
//             System.out.println("Func declare Transaction");
//         } catch  (SQLException e) {
//             System.out.println("Xetaaaa Func Declare"+e.getMessage());
//         }
//     }
}