package com.demovet.service;

import com.demovet.entity.Mascota;
import com.demovet.entity.Usuario;

public interface IUsuarioService {

    void guardarUsuario(Usuario u);

    Usuario buscarUsuarioPorId(Long id);

    void eliminarUsuario(Usuario u);

    Usuario crearMascota(Long idUsuario,Mascota mascota);
}
