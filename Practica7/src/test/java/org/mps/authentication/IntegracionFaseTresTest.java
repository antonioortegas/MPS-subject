package org.mps.authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

@Tag("Fase3")
public class IntegracionFaseTresTest {
    /*Están disponibles las implementaciones de las clases UserRegistration, CredentialValidatorImpl, y CredentialStoreSet*/

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

        email = Mockito.mock(Email.class);
        passwordString = Mockito.mock(PasswordString.class);
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

        Mockito.when(email.validate()).thenReturn(true);
        Mockito.when(passwordString.validate()).thenReturn(true);

        userRegistration.register(email, passwordString);

        boolean expectedValidationStatus = true;
        boolean actualValidationStatus = store.credentialExists(email, passwordString);
        assertEquals(expectedValidationStatus, actualValidationStatus);
        int expectedStoreSize = 1;
        int actualStoreSize = store.size();
        assertEquals(expectedStoreSize, actualStoreSize);
    }

    @Test
    @DisplayName("Invalid email returns an error")
    public void shouldAnInvalidEmailNotCallStoreFunction() {

        Mockito.when(email.validate()).thenReturn(false);
        Mockito.when(passwordString.validate()).thenReturn(true);

        userRegistration.register(email, passwordString);

        boolean expectedValidationStatus = false;
        boolean actualValidationStatus = store.credentialExists(email, passwordString);
        assertEquals(expectedValidationStatus, actualValidationStatus);
        int expectedStoreSize = 0;
        int actualStoreSize = store.size();
        assertEquals(expectedStoreSize, actualStoreSize);
    }

    @Test
    @DisplayName("Invalid password returns an error")
    public void shouldAnInvalidPasswordNotCallStoreFunction() {

        Mockito.when(email.validate()).thenReturn(true);
        Mockito.when(passwordString.validate()).thenReturn(false);

        userRegistration.register(email, passwordString);

        boolean expectedValidationStatus = false;
        boolean actualValidationStatus = store.credentialExists(email, passwordString);
        assertEquals(expectedValidationStatus, actualValidationStatus);
        int expectedStoreSize = 0;
        int actualStoreSize = store.size();
        assertEquals(expectedStoreSize, actualStoreSize);
    }

    @Test
    @DisplayName("Return an error if the credentials are already in use")
    public void shouldTheCredentialsAlreadyInUseNotCallStoreFunction() {

        Mockito.when(email.validate()).thenReturn(true);
        Mockito.when(passwordString.validate()).thenReturn(true);

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
