package com.suminghui.bycar.service;

import java.util.List;

import com.suminghui.bycar.bean.Car;
import com.suminghui.bycar.bean.Pagination;
import com.suminghui.bycar.exception.ParameterException;

public interface CarService {

    public List<Car> selectCarList(Pagination pagination) throws ParameterException;

    public Car selectCarById(Integer carId);

    public void insertCar(Car car) throws ParameterException;

    public void updateCar(Car car) throws ParameterException;

    public void deleteCar(Integer carId);
}
