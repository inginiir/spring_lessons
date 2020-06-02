package kalita.projects.spring.service;

import kalita.projects.spring.domain.Role;
import kalita.projects.spring.domain.User;
import kalita.projects.spring.repos.UserRepo;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private MailSender mailSender;

    @Test
    public void addUser() {
        User user = new User();
        user.setEmail("some@mail.ru");
        boolean isUserAdded = userService.addUser(user);
        assertTrue(isUserAdded);
        assertNotNull(user.getActivationCode());
        assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        verify(userRepo, times(1)).save(user);
        verify(mailSender, times(1)).send(
                ArgumentMatchers.eq(user.getEmail()),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()
        );

    }

    @Test
    public void addUserDuplicate() {
        User user = new User();

        user.setUsername("pavel");

        doReturn(new User())
                .when(userRepo)
                .findByUsername("pavel");
        boolean isUserAdded = userService.addUser(user);
        assertFalse(isUserAdded);

        verify(userRepo, times(0)).save(any(User.class));
        verify(mailSender, times(0)).send(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()
        );
    }

    @Test
    public void activateUser() {
        User user = new User();
        user.setActivationCode("bingo!");
        doReturn(user)
                .when(userRepo)
                .findByActivationCode("activate");
        boolean isUserActivated = userService.activateUser("activate");
        assertTrue(isUserActivated);
        assertNull(user.getActivationCode());

        verify(userRepo, times(1)).save(user);
    }

    @Test
    public void activateUserFail() {
        boolean isUserActivated = userService.activateUser("activate me");
        assertFalse(isUserActivated);

        verify(userRepo, times(0)).save(any(User.class));

    }

}