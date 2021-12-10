package com.example.cse226_end_term;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class LoginActivityTest {

    private static final String STRING = "Login was successful";



    @Test
    public void validateUser() {



        // ...when the string is returned from the object under test...
        String result = LoginActivity.validate("rishab","rishab");

        // ...then the result should be the expected one.
        assertThat(result, is(STRING));
    }

}