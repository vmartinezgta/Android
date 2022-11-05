package com.example.proyecto_final.Interfaz;
import com.example.proyecto_final.Models.Productos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Post_Productos {
    @GET("productos")
    Call<List<Productos>> getDatos();
}
