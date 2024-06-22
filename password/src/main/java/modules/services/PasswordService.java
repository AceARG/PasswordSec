package modules.services;

import lombok.extern.slf4j.Slf4j;
import modules.models.PasswordModel;
import modules.entities.PasswordEntity;
import modules.repositories.PasswordRepository;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;

@Service
@Slf4j
public class PasswordService {

private final PasswordRepository passwordRepository;
private final EncryptionService encryptionService;
private final RandomStingGeneratorService randomStingGeneratorService;

    public PasswordService(PasswordRepository passwordRepository, EncryptionService encryptionService, RandomStingGeneratorService randomStingGeneratorService) {
        this.passwordRepository = passwordRepository;
        this.encryptionService = encryptionService;
        this.randomStingGeneratorService = randomStingGeneratorService;
    }


    public SecretKey getSecretKey() throws Exception {
        String secretKeyString = "MzQ5NDY0NTEwNTcxNjk1NjIzNDA3Mjg3OTk5Mzc3ODM1NTQ3Njc0NzE2MTYxOTU5NzkxNDk1ODMxMzAyNzc1OTI3NjYwMDkwMTMwNjI1MzQ0NjE4MzYwODc4NjMxNzY2MDQzMjU0OTI1OTM0NDM1NDU3OTY3MTM0MzA5NjUyMjcwNzAwMTA1MjI4Mjk2MDk5OTM0MTcwMzYzODE5MDE1NDM2NjU0MTQ4MDA4MTM4MzIwODkwODg3MTA4OTc5Mjg4MTYzNTYzMTg3ODU1NzY0ODkyNzgzODMyMDYwODQ1NjQ0NTc4Nzk2MTQzNTkyMDQ3MjEy";
        return encryptionService.getKeyFromString(secretKeyString);
    }

    public String getString() throws Exception {
        return encryptionService.keyToString(getSecretKey());
    }

    //create password
    public PasswordModel createPassword() throws Exception {
        PasswordEntity passwordEntity = mapToEntity();
        passwordRepository.save(passwordEntity);
        return mapToModel();
    }

    //get password by id
    public PasswordModel getPasswordById(Long id) throws Exception {
        passwordRepository.findById(id).orElseThrow();
        return mapToModel();
    }

    //update password
    public PasswordModel updatePassword(Long id) throws Exception {
        PasswordEntity passwordEntity = passwordRepository.findById(id).orElseThrow();
        passwordEntity.setPassword(encryptionService.encrypt(getString(), getSecretKey()));
        passwordRepository.save(passwordEntity);
        return mapToModel();

    }

    //delete password
    public void deletePassword(Long id) {
        passwordRepository.deleteById(id);
    }



    PasswordModel mapToModel() throws Exception {
        PasswordModel passwordModel = new PasswordModel();
        log.info(getSecretKey().toString());
        passwordModel.setPassword(encryptionService.decrypt(getString(), getSecretKey()));
        return passwordModel;
    }

    public PasswordEntity mapToEntity() throws Exception {
        PasswordEntity passwordEntity = new PasswordEntity();
        log.info(encryptionService.generateKey(256).toString());
        passwordEntity.setPassword(encryptionService.encrypt(getString(), getSecretKey()));
        passwordEntity = passwordRepository.save(passwordEntity);
        return passwordEntity;
    }

}
