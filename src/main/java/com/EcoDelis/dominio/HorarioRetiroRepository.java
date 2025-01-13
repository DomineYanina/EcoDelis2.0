package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRetiroRepository {
    void agregar(HorarioRetiro horarioRetiro);
}
