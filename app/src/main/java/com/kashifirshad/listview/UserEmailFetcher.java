package com.kashifirshad.listview;

/**
 * Created by biome on 2/27/2018.
 */
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

public class UserEmailFetcher {

     String getEmail(Context context) {
        AccountManager accountManager = AccountManager.get(context);
        Account account = getAccount(accountManager);

        if (account == null) {
            return null;
        } else {
//            Log.e("Google Account", account.toString());
            return account.name;
        }
    }

    private Account getAccount(AccountManager accountManager) {
        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account account;
        if (accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }
        return account;
    }
}