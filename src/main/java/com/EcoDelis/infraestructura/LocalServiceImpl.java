package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("LocalService")
@Transactional
public class LocalServiceImpl {

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private LocalRepositoryImpl localRepositoryImpl;

    @Transactional
    public boolean validarCredenciales(String email, String password) {
        Local local = localRepository.buscarLocal(email,password);
        return local != null;
    }

    public Local buscarPorEmail(String email) {
        return localRepository.buscarPorEmail(email);
    }
}
