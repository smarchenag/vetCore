package com.demovet.service;

import com.demovet.entity.Mascota;
import com.demovet.entity.Usuario;
import com.demovet.repository.MascotaRepository;
import org.springframework.stereotype.Service;

@Service
public class IMascotaServiceImpl implements IMascotaService{

    private final MascotaRepository repository;

    public IMascotaServiceImpl(MascotaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mascota guardarMascota(Long id,Mascota mascota) {
        return repository.save(mascota);
    }
}
