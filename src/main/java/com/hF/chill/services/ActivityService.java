package com.hF.chill.services;

import com.hF.chill.models.Activity;
import com.hF.chill.repositories.ActivityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    public Activity create(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity update(Activity activityToBeUpdated, Activity updatedActivity) {
        BeanUtils.copyProperties(updatedActivity, activityToBeUpdated, "id");
        return activityRepository.save(activityToBeUpdated);
    }

    public void delete(Long id) {
        activityRepository.delete(activityRepository.getById(id));
    }

    public Activity randomize() {
        List<Activity> activities = getAll();
        int rand = (int) (Math.random() * activities.size());
        return activities.get(rand);
    }

    public void save(Activity activity) {
        activityRepository.save(activity);
    }
}
