/**
 * Created by RoSteik (Telegram: @RoSteik)
 * Project name: lab4-db
 * Package name: com.rostyslav.service
 * Interface: GeneralService
 */

package com.rostyslav.service;

import java.util.List;
import java.util.Optional;

public interface GeneralService<T, ID> {
    List<T> findAll();

    Optional<T> findById(ID id);

    int create(T entity);

    int update(ID id, T entity);

    int delete(ID id);
}
