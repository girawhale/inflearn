package java8.diexample;

import java8.di.Inject;

public class AccountService {
    @Inject
    AccountRepository accountRepository;

    public void join() {
        System.out.println("Service.join");
        accountRepository.save();
    }
}
