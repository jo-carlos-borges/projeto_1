package com.gestor.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.gestor.dto.ActivityResponseDTO;
import com.gestor.model.Activity;
import com.gestor.model.Person;
import com.gestor.repository.ActivityRepository;
import com.gestor.repository.PersonRepository;
import com.gestor.dto.ActivityRequestDTO;
import com.gestor.dto.PersonDTO;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final PersonRepository personRepository;

    public ActivityService(ActivityRepository activityRepository, PersonRepository personRepository) {
        this.activityRepository = activityRepository;
        this.personRepository = personRepository;
    }

    public ActivityResponseDTO createActivity(ActivityRequestDTO dto) {
        Activity activity = new Activity();
        activity.setTitle(dto.getTitle());
        activity.setStartDate(dto.getStartDate());
        
        Set<Person> people = new HashSet<>(personRepository.findAllById(dto.getPeopleIds()));
        activity.setPeople(people);
        
        Activity savedActivity = activityRepository.save(activity);
        return convertToDTO(savedActivity);
    }

    public List<ActivityResponseDTO> getAllActivities() {
        return activityRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ActivityResponseDTO updateActivity(Long id, ActivityRequestDTO dto) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));
        
        activity.setTitle(dto.getTitle());
        activity.setStartDate(dto.getStartDate());
        
        Set<Person> people = new HashSet<>(personRepository.findAllById(dto.getPeopleIds()));
        activity.setPeople(people);
        
        Activity updatedActivity = activityRepository.save(activity);
        return convertToDTO(updatedActivity);
    }

    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    private ActivityResponseDTO convertToDTO(Activity activity) {
        ActivityResponseDTO dto = new ActivityResponseDTO();
        dto.setId(activity.getId());
        dto.setTitle(activity.getTitle());
        dto.setStartDate(activity.getStartDate());
        
        dto.setPeople(activity.getPeople().stream()
                .map(p -> new PersonDTO(p.getId(), p.getName()))
                .collect(Collectors.toList()));
        
        return dto;
    }
}