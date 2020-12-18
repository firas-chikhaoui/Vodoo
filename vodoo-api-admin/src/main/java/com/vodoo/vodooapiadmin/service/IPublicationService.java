package com.vodoo.vodooapiadmin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapipersistance.model.Publication;

public interface IPublicationService {

    Page<Publication> findAll(Pageable pageable) throws GetException;

    Publication findOne(String id) throws GetException;

    Publication add(Publication publication) throws CreateException;

    Publication update(Publication publication) throws UpdateException;

    boolean delete(String id) throws DeleteException;


}