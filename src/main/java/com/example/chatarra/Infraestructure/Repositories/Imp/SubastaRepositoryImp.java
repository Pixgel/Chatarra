package com.example.chatarra.Infraestructure.Repositories.Imp;

import com.example.chatarra.Domain.RepositoryContracts.SubastaRepository;
import com.example.chatarra.Domain.Entitys.Subasta;
import com.example.chatarra.Infraestructure.Repositories.SubastaRepositoryJPA;
import com.example.chatarra.Aplication.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubastaRepositoryImp implements SubastaRepository {
    @Autowired
    SubastaRepositoryJPA db;

    @Override
    public Subasta guardar(Subasta subasta) {
        return db.save(subasta);
    }

    @Override
    public void eliminarporId(Integer id) {
        db.deleteById(id);
    }

    @Override
    public Subasta buscarPorId(Integer id) {
        return db.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Existe esta subasta: "+id));
    }

    @Override
    public List<Subasta> FiltrarEstado(String estado) {
        return db.FiltrarEstado(estado);
    }

    @Override
    public List<Subasta> ListarPorUsuario(Integer id) {
        return db.ListarPorUsuario(id);
    }

    @Override
    public List<Subasta> FiltrarEstadoUsuario(String estado, Integer id) {
        return db.FiltrarEstadoUsuario(estado,id);
    }
}
