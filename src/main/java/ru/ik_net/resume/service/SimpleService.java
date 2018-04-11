package ru.ik_net.resume.service;

import org.springframework.stereotype.Service;

/**
 * Author: Igor Kovalkov.
 * 13.04.2018
 */
@Service
public class SimpleService implements IService {
    @Override
    public String getNameService() {
        return getClass().getSimpleName();
    }
}
