package org.mysj;
import org.mysj.domain.PatientEntity;
import org.mysj.domain.PatientRepo;
import org.mysj.dto.PatientDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {

    private final PatientRepo patientRepo;

    public PatientController(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @PostMapping("/patient")
    public Long createPatient(@RequestBody PatientDto patient) {
        var entity = new PatientEntity();
        entity.setPatientId(patient.getPatientId());
        entity.setAge(patient.getAge());
        entity.setName(patient.getName());
        return patientRepo.save(entity).getId();
    }

    @GetMapping("/patient/{id}")
    public PatientDto getPatient(@PathVariable("id") Long id) {
        var entity = patientRepo.findById(id).orElseThrow();
        var patient = new PatientDto();
        patient.setPatientId(entity.getPatientId());
        patient.setName(entity.getName());
        patient.setAge(entity.getAge());
        return patient;
    }
}
