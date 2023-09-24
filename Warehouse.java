package raw;

import java.util.ArrayList;
import java.util.List;

// generic class
class Warehouse < A extends Account > {
    private List <A> accounts;

    // class constructor
    public Warehouse() {
        accounts = new ArrayList<>();
    }
    //  method
    public void addAccount(A account) {
        accounts.add(account);
    }
    public void removeAccount(int index) {
        accounts.remove(index);
    }

    //return method
    public int getNumAccounts() {
        return accounts.size();
    }

    public A getAccount(int index) {
        return accounts.get(index);
    }
    //to check the given account is instance of subclasses
    public <T> T getAccount(Class<T> obj, int index) {
        Account account = accounts.get(index);
        if (obj.isInstance(account)) {
            return obj.cast(account);
        } else {
            return null;
        }
    }
}