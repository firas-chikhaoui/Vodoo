package com.vodoo.vodooapiadmin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.repository.ITraceAccesRepository;
import com.vodoo.vodooapipersistance.model.TraceAcces;

@Service
@Transactional
@Primary
public class TraceAccesServiceImpl implements ITraceAccesService {

    @Autowired
    private ITraceAccesRepository traceAccesRepository;
    private static final String ENTITY_NAME = "TraceAcces";

    @Transactional(readOnly = true)
    public Page<TraceAcces> findAll(Pageable pageable) {

        return traceAccesRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public TraceAcces findOne(String id) throws GetException {

        Optional<TraceAcces> traceAcces = traceAccesRepository.findById(id);

        if(!traceAcces.isPresent()){

                GetException getException = new GetException();
                getException.setNomEntitie(ENTITY_NAME);
                getException.setRaisonExceptionNotFound();
                throw  getException;

        }

        return traceAcces.get();
    }

    @Override
    public TraceAcces add(TraceAcces traceAcces) throws CreateException {


        return traceAccesRepository.saveAndFlush(traceAcces);
    }

    @Override
    public TraceAcces update(TraceAcces traceAcces) throws UpdateException {

        if( traceAcces.getIdTrace() == null){

            UpdateException updateException = new UpdateException();
            updateException.setNomEntitie(ENTITY_NAME);
            updateException.setRaisonExceptionIdNull();

            throw updateException;

        }

        return traceAccesRepository.saveAndFlush(traceAcces);
    }

    @Override
    public boolean delete(String id) throws DeleteException {

        if(!traceAccesRepository.existsById(id)){
            DeleteException deleteException = new DeleteException();
            deleteException.setNomEntitie(ENTITY_NAME);
            deleteException.setRaisonDosentExist();

            throw deleteException;
        }

        traceAccesRepository.deleteById(id);
        return !traceAccesRepository.existsById(id);
    }

}