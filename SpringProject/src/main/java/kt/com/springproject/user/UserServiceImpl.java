package kt.com.springproject.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;

    @Override
    public void insertUser(UserVO vo) {

    }

    @Override
    public void updateUser(UserVO vo) {

    }

    @Override
    public void deleteUser(UserVO vo) {

    }

    @Override
    public UserVO getUser(UserVO vo) {
        return userDAO.getUser(vo);
    }

    @Override
    public List<UserVO> getUserList(UserVO vo) {
        return null;
    }
}
