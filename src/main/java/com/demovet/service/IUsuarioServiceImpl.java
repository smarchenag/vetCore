package com.demovet.service;

import com.demovet.entity.Mascota;
import com.demovet.entity.Usuario;
import com.demovet.repository.MascotaRepository;
import com.demovet.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class IUsuarioServiceImpl implements IUsuarioService{

    private final UsuarioRepository repository;

    private final MascotaRepository mascotaRepository;

    public IUsuarioServiceImpl(UsuarioRepository repository, MascotaRepository mascotaRepository) {
        this.repository = repository;
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public void guardarUsuario(Usuario u) {
        if (u.getMascotas() != null){
            for (Mascota m: u.getMascotas()){
                System.out.println(m.toString());
                u.addMascota(m);
            }
        }
        repository.save(u);
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void eliminarUsuario(Usuario u) {
        repository.delete(u);
    }

    @Override
    public Usuario crearMascota(Long idUsuario,Mascota mascota) {
        Usuario usuarioFromDB = buscarUsuarioPorId(idUsuario);
        usuarioFromDB.addMascota(mascota);
        mascotaRepository.save(mascota);
        return usuarioFromDB;
    }


}
