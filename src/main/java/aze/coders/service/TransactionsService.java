package aze.coders.service;

import aze.coders.entity.Transactions;

import java.sql.Connection;

public interface TransactionsService {
 void createTransaction(Connection connection);
 void insertIntoTransaction(Transactions transactions,Connection connection);
 void dropTransaction(Connection connection);
 void getWithId(Integer id,Connection connection);
 void deleteWithId(Integer id,Connection connection);
 void updateWithId(Integer id,Transactions transactions,Connection connection);
 void selectAll(Connection connection);
 void alterAddTransaction(String columnName,String dataType,Connection connection);
 void alterForeignKey(String table_name,String constraint_name,String column_name,String other_table,String other_column,Connection connection);
 //void firstFiveTranWithFunc(Connection connection);
}
