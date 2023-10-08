package org.mps.authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@Tag("Fase1")
public class IntegracionFaseUnoTest {
    /*Solo está disponible la implementación de la clase UserRegistration.*/

    @Test
    @DisplayName("Correct credentials create a new user")
    public void shouldTheCorrectCredentialsCallStoreFunctionOnce() {
        CredentialValidator validator = Mockito.mock(CredentialValidator.class);
        CredentialStore store = Mockito.mock(CredentialStore.class);

        Email email = Mockito.mock(Email.class);
        PasswordString passwordString = Mockito.mock(PasswordString.class);

        Mockito.when(validator.validate(email, passwordString)).thenReturn(CredentialValidator.ValidationStatus.VALIDATION_OK);
        UserRegistration userRegistration = new UserRegistration(validator, store);
        userRegistration.register(email, passwordString);

        Mockito.verify(store, times(1)).store(email, passwordString);
    }

    @Test
    @DisplayName("Invalid email returns an error")
    public void shouldAnInvalidEmailNotCallStoreFunction() {
        CredentialValidator validator = Mockito.mock(CredentialValidator.class);
        CredentialStore store = Mockito.mock(CredentialStore.class);

        Email email = Mockito.mock(Email.class);
        PasswordString passwordString = Mockito.mock(PasswordString.class);

        Mockito.when(validator.validate(email, passwordString)).thenReturn(CredentialValidator.ValidationStatus.EMAIL_INVALID);
        UserRegistration userRegistration = new UserRegistration(validator, store);
        userRegistration.register(email, passwordString);

        Mockito.verify(store, times(0)).store(email, passwordString);
    }

    @Test
    @DisplayName("Invalid password returns an error")
    public void shouldAnInvalidPasswordNotCallStoreFunction() {
        CredentialValidator validator = Mockito.mock(CredentialValidator.class);
        CredentialStore store = Mockito.mock(CredentialStore.class);

        Email email = Mockito.mock(Email.class);
        PasswordString passwordString = Mockito.mock(PasswordString.class);

        Mockito.when(validator.validate(email, passwordString)).thenReturn(CredentialValidator.ValidationStatus.PASSWORD_INVALID);
        UserRegistration userRegistration = new UserRegistration(validator, store);
        userRegistration.register(email, passwordString);

        Mockito.verify(store, times(0)).store(email, passwordString);
    }

    @Test
    @DisplayName("Return an error if the credentials are already in use")
    public void shouldTheCredentialsAlreadyInUseNotCallStoreFunction() {
        CredentialValidator validator = Mockito.mock(CredentialValidator.class);
        CredentialStore store = Mockito.mock(CredentialStore.class);

        Email email = Mockito.mock(Email.class);
        PasswordString passwordString = Mockito.mock(PasswordString.class);

        Mockito.when(validator.validate(email, passwordString)).thenReturn(CredentialValidator.ValidationStatus.EXISTING_CREDENTIAL);
        UserRegistration userRegistration = new UserRegistration(validator, store);
        userRegistration.register(email, passwordString);

        Mockito.verify(store, times(0)).store(email, passwordString);
    }
}
