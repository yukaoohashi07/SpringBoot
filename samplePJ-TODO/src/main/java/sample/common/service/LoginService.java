package sample.common.service;

import java.util.Optional;
import sample.common.dao.entity.Login;

public interface LoginService {
    void register(String username, String rawPassword);

    Optional<Login> authenticate(String username, String rawPassword);
}