package aze.coders.service;

import aze.coders.entity.Accounts;
import aze.coders.entity.Client;

import java.util.List;
import java.util.Map;

public interface AccountService {

    void selectGetaccountsby();
    Map<Integer, Accounts> getAllAccounts();
    void addAccount();
    void deleteAccount(int acc_id);
    void updateAccountName(int id, String new_acc_Name);
}
