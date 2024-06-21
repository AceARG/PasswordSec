package modules.services;

import modules.models.UsernameModel;
import modules.entities.UsernameEntity;
import modules.repositories.UsernameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsernameService {
    private final PasswordService passwordService;
    private final UsernameRepository usernameRepository;

    public UsernameService(PasswordService passwordService, UsernameRepository usernameRepository) {
        this.passwordService = passwordService;
        this.usernameRepository = usernameRepository;
    }
    //create username
    public UsernameModel createUsername(UsernameModel usernameModel) throws Exception {
        UsernameEntity usernameEntity = mapToEntity(usernameModel);
        usernameRepository.save(usernameEntity);
        return mapToModel(usernameEntity);
    }

    //get by id
    public UsernameModel getUsernameById(Long id) throws Exception {
        UsernameEntity usernameEntity = usernameRepository.findById(id).orElse(null);
        return mapToModel(usernameEntity);
    }
    //get all
    public List<UsernameModel> getAllUsernames(){
        List<UsernameEntity> usernameEntities = usernameRepository.findAll();
        return usernameEntities.stream().map(usernameEntity -> {
            try {
                return mapToModel(usernameEntity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }


    public UsernameModel mapToModel(UsernameEntity usernameEntity) throws Exception {
        UsernameModel usernameModel = new UsernameModel();
        usernameModel.setUsername(usernameEntity.getUsername());
        usernameModel.setPasswordModel(passwordService.mapToModel(usernameEntity.getPassword()));
        return usernameModel;
    }

    public UsernameEntity mapToEntity(UsernameModel usernameModel) throws Exception {
        UsernameEntity usernameEntity = new UsernameEntity();
        usernameEntity.setUsername(usernameModel.getUsername());
        usernameEntity.setPassword(passwordService.mapToEntity(usernameModel.getPasswordModel()));
        return usernameEntity;
    }
}
