package com.financeme.account;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class AccountReferenceCompatibilityTest {
    @Test
    public void referenceAccountContractIsSupported() {
        Account account = new Account(1010101010L, "Shubham", "Saving Account", 20000.0);
        assertEquals(account.getAccountNo(), Long.valueOf(1010101010L));
        assertEquals(account.getCustomerName(), "Shubham");
        assertEquals(account.getPolicy(), "Saving Account");
        assertEquals(account.getBalance(), 20000.0);
    }
}
