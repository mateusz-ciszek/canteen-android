package com.canteen.app;

import com.canteen.app.activity.common.RegisterActivity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testRetypePassword() {
        String password="halo";
        String retypePassword="halo";
        RegisterActivity registerActivity =new RegisterActivity();

                assertEquals(true, registerActivity.checkRetypePassword(password,retypePassword));
        }

    @Test
    public void testPassword() {
        String password="Haslo123";
        RegisterActivity registerActivity =new RegisterActivity();
        assertEquals(true, registerActivity.checkPassword(password));
    }

    @Test
    public void testMail() {
        String mail="dawid@wp.pl";
        RegisterActivity registerActivity =new RegisterActivity();
        assertEquals(true, registerActivity.checkEmail(mail));
    }

    @Test
    public void testName() {
        String name="Dawid";
        RegisterActivity registerActivity =new RegisterActivity();
        assertEquals(true, registerActivity.checkName(name));
    }

    }

