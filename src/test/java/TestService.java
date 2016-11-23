import biz.bagira.shds.eshop.service.PasswordService;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Dmitriy on 23.11.2016.
 */
public class TestService {
    PasswordService passwordService = new PasswordService();

    @Test
    public void testValidString() {
        String first = null;
        boolean validString = PasswordService.isValidString(first);
        Assert.assertFalse(validString);

        String second = "";
        validString = PasswordService.isValidString(second);
        Assert.assertFalse(validString);

        String third = "aaa";
        validString = PasswordService.isValidString(third);
        Assert.assertTrue(validString);

    }

    @Test
    public void testNumber() {
        String first = null;
        boolean validString = PasswordService.isNumber(first);
        Assert.assertFalse(validString);

        String second = "";
        validString = PasswordService.isNumber(second);
        Assert.assertFalse(validString);

        String third = "a2a2a.33";
        validString = PasswordService.isNumber(third);
        Assert.assertFalse(validString);

        String forth = "22.222";
        validString = PasswordService.isNumber(forth);
        Assert.assertTrue(validString);

        String fifth = "222";
        validString = PasswordService.isNumber(fifth);
        Assert.assertTrue(validString);
    }

    @Test
    public void testValidatePassword() {
        String firstPassword = "12drtfhg1544ty4";
        String secondPassword = "12drtfhg1544ty4";

        boolean validatePassword = passwordService.validatePassword(firstPassword, secondPassword);
        Assert.assertTrue(validatePassword);

        firstPassword = "12drtfhg1544ty4";
        secondPassword = "5512drtfhg1544ty4";
        validatePassword = passwordService.validatePassword(firstPassword, secondPassword);
        Assert.assertFalse(validatePassword);
    }

    @Test
    public void testSalt() {
        for (int i = 0; i < 100000; i++) {
            byte[] array1 = passwordService.getSalt();
            byte[] array2 = passwordService.getSalt();
            Assert.assertThat(array1, IsNot.not(IsEqual.equalTo(array2)));
        }

    }

    @Test
    public void testPassword(){
        String password = "qwerty";
        byte[] salt = passwordService.getSalt();
        String securePassword = passwordService.getSecurePassword(password, salt);

        String secondPasswort = "asdfgh";
        String secureSecondPassword = passwordService.getSecurePassword(secondPasswort,salt);

        Assert.assertFalse(passwordService.validatePassword(securePassword,secureSecondPassword));

        String thirdPasswort = "qwerty";
        String secureThirdPassword = passwordService.getSecurePassword(thirdPasswort,salt);

        Assert.assertTrue(passwordService.validatePassword(password,thirdPasswort));


    }


}
