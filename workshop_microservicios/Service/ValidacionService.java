package com.workshop_microservicios.workshop_microservicios.Service;

import com.workshop_microservicios.workshop_microservicios.Model.Registro;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ValidacionService {
    // Expresión regular para validar correos
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    // Formato de fecha
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final LocalDate MIN_DATE = LocalDate.of(1980, 1, 1);
    // Títulos válidos
    private static final List<String> TITULOS_VALIDOS = List.of("Haematologist", "Phytotherapist", "Building Surveyor", "Insurance Account Manager", "Educational Psychologist");

    public boolean validarRegistro(Registro registro) {
        return validarEmail(registro.getEmail()) &&
                validarFechaNacimiento(registro.getFechaNacimiento()) &&
                validarTituloProfesional(registro.getTituloProfesional());
    }

    public boolean validarEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean validarFechaNacimiento(String fecha) {
        LocalDate fechaNacimiento = LocalDate.parse(fecha, DATE_FORMATTER);
        return fechaNacimiento.isAfter(MIN_DATE);
    }

    public boolean validarTituloProfesional(String titulo) {
        return TITULOS_VALIDOS.contains(titulo);
    }

    // Validar código postal y teléfono como opcionales
    public boolean validarCodigoPostal(String codigoPostal) {
        return codigoPostal != null && codigoPostal.matches("\\d{5}");
    }

    public boolean validarTelefono(String telefono) {
        return telefono != null && telefono.matches("\\+\\d{10,15}");
    }
}
