package org.mps.authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

@Tag("Fase2")
public class IntegracionFaseDosTest {
    /*Están disponibles las implementaciones de las clases UserRegistration y CredentialValidatorImpl*/

    CredentialStore store;
    CredentialValidator validator;
    UserRegistration userRegistration;
    Email email;
    PasswordString passwordString;


    @BeforeEach
    public void setUp() {
        store = Mockito.mock(CredentialStore.class);
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
        Mockito.when(store.credentialExists(email, passwordString)).thenReturn(false);

        userRegistration.register(email, passwordString);

        Mockito.verify(store).store(email, passwordString);
    }

    @Test
    @DisplayName("Invalid email returns an error")
    public void shouldAnInvalidEmailNotCallStoreFunction() {

        Mockito.when(email.validate()).thenReturn(false);
        Mockito.when(passwordString.validate()).thenReturn(true);
        Mockito.when(store.credentialExists(email, passwordString)).thenReturn(false);

        userRegistration.register(email, passwordString);

        verify(store, times(0)).store(email, passwordString);
    }

    @Test
    @DisplayName("Invalid password returns an error")
    public void shouldAnInvalidPasswordNotCallStoreFunction() {

        Mockito.when(email.validate()).thenReturn(true);
        Mockito.when(passwordString.validate()).thenReturn(false);
        Mockito.when(store.credentialExists(email, passwordString)).thenReturn(false);

        userRegistration.register(email, passwordString);

        verify(store, times(0)).store(email, passwordString);
    }

    @Test
    @DisplayName("Return an error if the credentials are already in use")
    public void shouldTheCredentialsAlreadyInUseNotCallStoreFunction() {

        Mockito.when(email.validate()).thenReturn(true);
        Mockito.when(passwordString.validate()).thenReturn(true);
        Mockito.when(store.credentialExists(email, passwordString)).thenReturn(true);

        userRegistration.register(email, passwordString);

        verify(store, times(0)).store(email, passwordString);
    }
}
