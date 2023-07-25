package com.sm.shopmore.exception;

public class UserDisabledException extends Throwable {
    public UserDisabledException(String accountIsDisabled) {
        super(accountIsDisabled);
    }
}
