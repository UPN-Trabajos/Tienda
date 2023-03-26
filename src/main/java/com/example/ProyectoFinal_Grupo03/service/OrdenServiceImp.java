package com.example.ProyectoFinal_Grupo03.service;

import com.example.ProyectoFinal_Grupo03.model.Orden;
import com.example.ProyectoFinal_Grupo03.model.Usuario;
import com.example.ProyectoFinal_Grupo03.repository.IOrdenRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenServiceImp implements IOrdenService {

    @Autowired
    private IOrdenRepository ordenRepository;

    @Override
    public void Guardar(Orden orden) {
        ordenRepository.save(orden);
    }

    @Override
    public List<Orden> FindAll() {
        return (List<Orden>) ordenRepository.findAll();
    }

    public String generarNumeroOrden() {
        int numero = 0;
        String numeroConcatenado = "";

        List<Orden> ordenes = FindAll();
        List<Integer> numeros = new ArrayList<Integer>();

        ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));

        if (ordenes.isEmpty()) {
            numero = 1;
        } else {
            numero = numeros.stream().max(Integer::compare).get();
            numero++;
        }

        if(numero<10){
            numeroConcatenado="000000000"+String.valueOf(numero);
        }else if(numero<100){
            numeroConcatenado="00000000"+String.valueOf(numero);
        }else if(numero<1000){
            numeroConcatenado="0000000"+String.valueOf(numero);
        }else if(numero<10000){
            numeroConcatenado="000000"+String.valueOf(numero);
        }
        
        return numeroConcatenado;
    }

    @Override
    public List<Orden> findByUsuario(Usuario usuario) {
        return ordenRepository.findByUsuario(usuario);
    }

    @Override
    public Optional<Orden> findById(Integer id) {
        return ordenRepository.findById(id);
    }

    @Override
    public float montoTotal() {
        return ordenRepository.montoTotal();
    }

    @Override
    public int cantidadOrdenes() {
        return ordenRepository.cantidadOrdenes();
    }

    @Override
    public List<Orden> findByDate(String dato) {
        return ordenRepository.findByDate(dato);
    }

    @Override
    public List<Orden> OrderAsc() {
        return ordenRepository.OrderAsc();
    }

    @Override
    public List<Orden> OrderDesc() {
        return ordenRepository.OrderDesc();
    }

    @Override
    public int VentasPorProducto(Integer id) {
        return ordenRepository.VentasPorProducto(id);
    }

    @Override
    public int VentasPorCliente(Integer id) {
        return ordenRepository.VentasPorCliente(id);
    }
}
