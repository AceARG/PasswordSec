package modules.services;

import modules.entities.UserEntity;
import modules.entities.UsernameEntity;
import modules.models.UserModel;
import modules.models.UsernameModel;
import modules.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UsernameService usernameService;
    private final UserRepository userRepository;

    public UserService(UsernameService usernameService, UserRepository userRepository) {
        this.usernameService = usernameService;
        this.userRepository = userRepository;
    }
    //create user
    public UserModel createUser(UserModel userModel){
        UserEntity userEntity;
        try {
            userEntity = mapToEntity(userModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userEntity = userRepository.save(userEntity);
        try {
            return mapToModel(userRepository.save(userEntity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //get user by id
    public UserModel getUserById(Long userId){
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        try {
            return mapToModel(userEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //get all users
    public List<UserModel> getUsers(){
        List<UserEntity> userEntity = userRepository.findAll();
        return  userEntity.stream().map(userEntity1 -> {
            try {
                return mapToModel(userEntity1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public UserModel mapToModel(UserEntity userEntity) throws Exception {
        UserModel userModel = new UserModel();
        userModel.setEmail(userEntity.getEmail());
        userModel.setMasterPassword(userEntity.getMasterPassword());
        userModel.setUsernameModel((List<UsernameModel>) usernameService.mapToModel((UsernameEntity) userEntity.getUsername()));
        return userModel;
    }

    public UserEntity mapToEntity(UserModel userModel) throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userModel.getEmail());
        userEntity.setMasterPassword(userModel.getMasterPassword());
        userEntity.setUsername((List<UsernameEntity>) usernameService.mapToEntity((UsernameModel) userModel.getUsernameModel()));
        return userEntity;
    }
}
