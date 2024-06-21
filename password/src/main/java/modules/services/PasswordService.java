package modules.services;

import modules.models.PasswordModel;
import modules.entities.PasswordEntity;
import modules.repositories.PasswordRepository;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;

@Service
public class PasswordService {

private final PasswordRepository passwordRepository;
private EncryptionService encryptionService;
private RandomStingGeneratorService randomStingGeneratorService;

    public PasswordService(PasswordRepository passwordRepository, EncryptionService encryptionService, RandomStingGeneratorService randomStingGeneratorService) {
        this.passwordRepository = passwordRepository;
        this.encryptionService = encryptionService;
        this.randomStingGeneratorService = randomStingGeneratorService;
    }

    public SecretKey getSecretKey() {
        String secretKeyString = randomStingGeneratorService.generateRandomString(20);
        SecretKey secretKey = encryptionService.getKeyFromString(secretKeyString);
        return secretKey;
    }



    //create password
    public PasswordModel createPassword(PasswordModel passwordModel) throws Exception {
        PasswordEntity passwordEntity = mapToEntity(passwordModel);
        passwordEntity = passwordRepository.save(passwordEntity);
        return mapToModel(passwordEntity);
    }

    //get password by id
    public PasswordModel getPasswordById(Long id) throws Exception {
        PasswordEntity passwordEntity = passwordRepository.findById(id).orElseThrow();
        return mapToModel(passwordEntity);
    }
    //update password
    public PasswordModel updatePassword(Long id, PasswordModel passwordModel) throws Exception {
        PasswordEntity passwordEntity = passwordRepository.findById(id).orElseThrow();

        passwordEntity.setPassword(encryptionService.encrypt(passwordModel.getPassword(), getSecretKey()));
        passwordEntity = passwordRepository.save(passwordEntity);
        return mapToModel(passwordEntity);

    }
    //delete password
    public void deletePassword(Long id) {
        passwordRepository.deleteById(id);
    }
    PasswordModel mapToModel(PasswordEntity passwordEntity) throws Exception {
        PasswordModel passwordModel = new PasswordModel();
        passwordModel.setPassword(encryptionService.decrypt(passwordEntity.getPassword(), getSecretKey()));
        return passwordModel;
    }

    public PasswordEntity mapToEntity(PasswordModel passwordModel) throws Exception {
        PasswordEntity passwordEntity = new PasswordEntity();
        passwordEntity.setPassword(encryptionService.encrypt(passwordModel.getPassword(), getSecretKey()));
        return passwordEntity;
    }

}
