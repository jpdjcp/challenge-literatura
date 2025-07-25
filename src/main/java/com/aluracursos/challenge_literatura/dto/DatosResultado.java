package com.aluracursos.challenge_literatura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosResultado(
        List<DatosLibro> results
) {
}
