package org.mps.authentication;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

@Tag("Fase4")
public class IntegracionFaseCuatroTest {
    /*Están disponibles las implementaciones de todas las clases*/

    CredentialStore store;
    CredentialValidator validator;
    UserRegistration userRegistration;
    Email email;
    PasswordString passwordString;

    @BeforeEach
    public void setUp() {
        store = new CredentialStoreSet();
        validator = new CredentialValidatorImpl(store);
        userRegistration = new UserRegistration(validator, store);
    }

    @AfterEach
    public void tearDown() {
        store = null;
        validator = null;
        userRegistration = null;
        email = null;
        passwordString = null;
    }

    @Test
    @DisplayName("Correct credentials create a new user")
    public void shouldTheCorrectCredentialsCallStoreFunctionOnce() {
        email = new Email("antonio-ortega@uma.es");
        passwordString = new PasswordString("Password.123");


        userRegistration.register(email, passwordString);

        boolean expectedValidationStatus = true;
        boolean actualValidationStatus = store.credentialExists(email, passwordString);
        assertEquals(expectedValidationStatus, actualValidationStatus);
        int expectedStoreSize = 1;
        int actualStoreSize = store.size();
        assertEquals(expectedStoreSize, actualStoreSize);
        String expectedPassword = "Password.123";
        String actualPassword = passwordString.getPassword();
        assertEquals(expectedPassword, actualPassword);
    }

    @Nested
    @DisplayName("Invalid email")
    class InvalidEmail {

        @Test
        @DisplayName("Invalid email (more than one @) returns an error")
        public void shouldAnInvalidEmailNotCallStoreFunction() {
            email = new Email("notAnEmail@notAnEmail@notAnEmail");
            passwordString = new PasswordString("Password.123");


            userRegistration.register(email, passwordString);

            boolean expectedValidationStatus = false;
            boolean actualValidationStatus = store.credentialExists(email, passwordString);
            assertEquals(expectedValidationStatus, actualValidationStatus);
            int expectedStoreSize = 0;
            int actualStoreSize = store.size();
            assertEquals(expectedStoreSize, actualStoreSize);
        }

        @Test
        @DisplayName("Invalid email (no @) returns an error")
        public void shouldAnInvalidEmailNotCallStoreFunction2() {
            email = new Email("notAnEmail");
            passwordString = new PasswordString("Password.123");


            userRegistration.register(email, passwordString);

            boolean expectedValidationStatus = false;
            boolean actualValidationStatus = store.credentialExists(email, passwordString);
            assertEquals(expectedValidationStatus, actualValidationStatus);
            int expectedStoreSize = 0;
            int actualStoreSize = store.size();
            assertEquals(expectedStoreSize, actualStoreSize);
        }

        @Test
        @DisplayName("Invalid email (less than 2 subdomains) returns an error")
        public void shouldAnInvalidEmailNotCallStoreFunction3() {
            email = new Email("notAnEmail@notAnEmail");
            passwordString = new PasswordString("Password.123");

            userRegistration.register(email, passwordString);

            boolean expectedValidationStatus = false;
            boolean actualValidationStatus = store.credentialExists(email, passwordString);
            assertEquals(expectedValidationStatus, actualValidationStatus);
            int expectedStoreSize = 0;
            int actualStoreSize = store.size();
            assertEquals(expectedStoreSize, actualStoreSize);
        }

        @Test
        @DisplayName("Invalid email (no mailBox) returns an error")
        public void shouldAnInvalidEmailNotCallStoreFunction4() {
            email = new Email("@notAnEmail.es");
            passwordString = new PasswordString("Password.123");

            userRegistration.register(email, passwordString);

            boolean expectedValidationStatus = false;
            boolean actualValidationStatus = store.credentialExists(email, passwordString);
            assertEquals(expectedValidationStatus, actualValidationStatus);
            int expectedStoreSize = 0;
            int actualStoreSize = store.size();
            assertEquals(expectedStoreSize, actualStoreSize);
        }

        @Test
        @DisplayName("Invalid email (invalid simbol in mailBoxName) returns an error")
        public void shouldAnInvalidEmailNotCallStoreFunction5() {
            email = new Email("antonio)ortega@notAnEmail.es");
            passwordString = new PasswordString("Password.123");

            userRegistration.register(email, passwordString);

            boolean expectedValidationStatus = false;
            boolean actualValidationStatus = store.credentialExists(email, passwordString);
            assertEquals(expectedValidationStatus, actualValidationStatus);
            int expectedStoreSize = 0;
            int actualStoreSize = store.size();
            assertEquals(expectedStoreSize, actualStoreSize);
        }
    }

    @Nested
    @DisplayName("Invalid password")
    class InvalidPassword {

        @Test
        @DisplayName("Invalid password returns an error")
        public void shouldAnInvalidPasswordNotCallStoreFunction() {
            email = new Email("antonioortega@uma.es");
            passwordString = new PasswordString("NotAPassword");


            userRegistration.register(email, passwordString);

            boolean expectedValidationStatus = false;
            boolean actualValidationStatus = store.credentialExists(email, passwordString);
            assertEquals(expectedValidationStatus, actualValidationStatus);
            int expectedStoreSize = 0;
            int actualStoreSize = store.size();
            assertEquals(expectedStoreSize, actualStoreSize);
        }

        @Test
        @DisplayName("Invalid password (less than 8 characters) returns an error")
        public void shouldAnInvalidPasswordNotCallStoreFunction2(){
            email = new Email("antonioortega@uma.es");
            passwordString = new PasswordString("Invalid");


            userRegistration.register(email, passwordString);

            boolean expectedValidationStatus = false;
            boolean actualValidationStatus = store.credentialExists(email, passwordString);
            assertEquals(expectedValidationStatus, actualValidationStatus);
            int expectedStoreSize = 0;
            int actualStoreSize = store.size();
            assertEquals(expectedStoreSize, actualStoreSize);
        }

        @Test
        @DisplayName("Invalid password (no digits) returns an error")
        public void shouldAnInvalidPasswordNotCallStoreFunction3(){
            email = new Email("antonioortega@uma.es");
            passwordString = new PasswordString(".NotAPassword");


            userRegistration.register(email, passwordString);

            boolean expectedValidationStatus = false;
            boolean actualValidationStatus = store.credentialExists(email, passwordString);
            assertEquals(expectedValidationStatus, actualValidationStatus);
            int expectedStoreSize = 0;
            int actualStoreSize = store.size();
            assertEquals(expectedStoreSize, actualStoreSize);
        }
    }

    @Test
    @DisplayName("Return an error if the credentials are already in use")
    public void shouldTheCredentialsAlreadyInUseNotCallStoreFunction() {
        email = new Email("antonioortega@uma.es");
        passwordString = new PasswordString("Password.123");


        userRegistration.register(email, passwordString);

        boolean expectedValidationStatus = true;
        boolean actualValidationStatus = store.credentialExists(email, passwordString);
        assertEquals(expectedValidationStatus, actualValidationStatus);
        int expectedStoreSize = 1;
        int actualStoreSize = store.size();
        assertEquals(expectedStoreSize, actualStoreSize);
        // The second registration attempt should fail
        assertThrows(CredentialExistsException.class, () -> store.store(email, passwordString));
        actualStoreSize = store.size();
        assertEquals(expectedStoreSize, actualStoreSize);
    }
}
