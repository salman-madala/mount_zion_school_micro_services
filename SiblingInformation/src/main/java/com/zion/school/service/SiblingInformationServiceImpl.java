package com.zion.school.service;

import com.zion.school.model.SiblingInformation;
import com.zion.school.repo.SiblingInformationRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiblingInformationServiceImpl implements SiblingInformationService {


    private final SiblingInformationRepo siblingInformationRepo;

    public SiblingInformationServiceImpl(SiblingInformationRepo siblingInformationRepo) {
        this.siblingInformationRepo = siblingInformationRepo;
    }



    public List<SiblingInformation> getAll(Long studentId) {
        return siblingInformationRepo.getAllByStudentId(studentId);
    }

    public boolean create(Long studentId,List<SiblingInformation> siblingsInformation) {
        try {
            siblingsInformation.forEach(siblingInformation -> {
                siblingInformation.setStudentId(studentId);
                siblingInformationRepo.save(siblingInformation);
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(Long id) {

        if(siblingInformationRepo.existsById(id)) {
            siblingInformationRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean update(Long id, SiblingInformation siblingInformation) {

        Optional<SiblingInformation> optionalCompanie = siblingInformationRepo.findById(id);
        if (optionalCompanie.isPresent()) {
            SiblingInformation siblingInformation1 = new SiblingInformation();
            siblingInformation1.setName(siblingInformation.getName());
            siblingInformation1.setAge(siblingInformation.getAge());
            siblingInformationRepo.save(siblingInformation1);
            return true;
        }
        return false;

    }

    @Override
    public SiblingInformation getById(Long id) {
        return siblingInformationRepo.findById(id).orElse(null);
    }

}
