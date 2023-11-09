package peaksoft;

import org.junit.jupiter.api.Test;
import peaksoft.entity.User;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserServiceImplTest {
    @Test
    public void testSave() {
        peaksoft.service.implService.UserServiceImpl userServiceImpl = mock(peaksoft.service.implService.UserServiceImpl.class);
        User user = new User();
        userServiceImpl.save(user);
        verify(userServiceImpl).save(user);
    }


}
