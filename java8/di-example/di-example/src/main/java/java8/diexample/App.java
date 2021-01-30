package java8.diexample;

import java8.di.ContainerService;

public class App {
    public static void main(String[] args) {
        AccountService accountService = ContainerService.getObject(AccountService.class);
        accountService.join();
    }
}
