package com.theironyard.services;

import com.theironyard.entities.Fav;
import com.theironyard.entities.TomALike;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zach on 7/7/16.
 */
public interface FavRepository extends CrudRepository<Fav, Integer> {
}
