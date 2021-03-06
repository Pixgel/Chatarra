package com.example.chatarra.Domain.Services;

import com.example.chatarra.Domain.Entitys.Comprador;
import com.example.chatarra.Domain.Entitys.Vendedor;
import com.example.chatarra.Infraestructure.Repositories.Imp.VendedorRepositoryImp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorService {

    private final VendedorRepositoryImp vendedorRepositoryImp;

    public VendedorService(VendedorRepositoryImp vendedorRepositoryImp) {
        this.vendedorRepositoryImp = vendedorRepositoryImp;
    }

    public Vendedor login(String user, String password){
        return vendedorRepositoryImp.login(user,password);
    }
    public Vendedor buscarPorId(Integer id){
        return vendedorRepositoryImp.buscarPorId(id);
    }
}
