package com.zion.school.service;

import com.zion.school.model.SiblingInformation;

import java.util.List;

public interface SiblingInformationService {

    List<SiblingInformation> getAll(Long studentId);

    boolean create(Long studentId,List<SiblingInformation> siblingsInformation);

    boolean delete(Long id);

    boolean update(Long id, SiblingInformation siblingInformation);

    SiblingInformation getById(Long id);


}
