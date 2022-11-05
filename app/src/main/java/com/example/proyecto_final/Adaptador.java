package com.example.proyecto_final;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proyecto_final.Models.prueba;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>
{
    prueba[] prueba;
    Context context;
    ArrayList<prueba> pruebas;
    ArrayList<prueba> lista_original;

    public Adaptador(ArrayList<prueba> pruebas, recycleview activity)
    {
        this.pruebas=pruebas;
        this.context=activity;
        lista_original=new ArrayList<>();
        lista_original.addAll(pruebas);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Filtrar(String busqueda)
    {
        int longitud=busqueda.length();
        if(longitud==0)
        {
            pruebas.clear();
            pruebas.addAll(lista_original);
        }
        else
        {
            List<prueba> collection = pruebas.stream().filter(i -> i.getTitle().toLowerCase().contains(busqueda.toLowerCase())).collect(Collectors.toList());
            pruebas.clear();
            pruebas.addAll(collection);
            for(prueba c: lista_original)
            {
                if(c.getTitle().toLowerCase().contains(busqueda.toLowerCase()))
                {
                    pruebas.add(c);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview,parent,false);
        ViewHolder viewHolder=new ViewHolder((view));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.ViewHolder holder, int position) {
        final prueba pruebaList = pruebas.get(position);
        holder.txt_Id.setText(String.valueOf(pruebaList.getId()));
        holder.txt_UserId.setText(String.valueOf(pruebaList.getUserId()));
        holder.txt_title.setText(pruebaList.getTitle());
        holder.txt_body.setText(pruebaList.getBody());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,String.valueOf(pruebaList.getId()),Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pruebas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_Id, txt_UserId, txt_title, txt_body;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txt_Id=itemView.findViewById(R.id.txt_Id);
            txt_UserId=itemView.findViewById(R.id.txt_UserId);
            txt_title=itemView.findViewById(R.id.txt_title);
            txt_body=itemView.findViewById(R.id.txt_body);

        }
    }
}



/*
public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    private List<prueba> datos;
    private LayoutInflater mInflater;
    private Context context;

    public  Adaptador(List<prueba> itemlist, Context context)
    {
        this.mInflater=LayoutInflater.from(context);
        this.context=context;
        this.datos=itemlist;
    }
    @Override
    public int getItemCount()
    {
        return datos.size();
    }
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = mInflater.inflate(R.layout.activity_busqueda,null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void OnBindViewHolder(final ListAdapter.ViewHolder holder, final int position)
    {
        holder.bindData(datos.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView texto_tarjeta;
        ViewHolder(View itemView)
        {
            super(itemView)
            {
                texto_tarjeta=itemView.find
            }
        }
    }

}
*/