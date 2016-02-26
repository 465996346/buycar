package com.suminghui.bycar.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.suminghui.bycar.bean.Car;
import com.suminghui.bycar.bean.Pagination;
import com.suminghui.bycar.dao.CarDao;
import com.suminghui.bycar.service.CarService;

public class CarServiceImpl implements CarService {
    private CarDao carDao;
    
    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public List<Car> selectCarList(Pagination pagination) {
        try {
            return carDao.selectCarList(pagination);
        } catch (SQLException exception) {
            // log
        }
        return null;
    }

    @Override
    public Car selectCarById(Integer carId) {
        try {
            return carDao.selectCarById(carId);
        } catch (SQLException exception) {
            // log
        }
        return null;
    }

    @Override
    public void insertCar(Car car) {
        try {
            carDao.insertCar(car);
        } catch (SQLException exception) {
            // log
        }
    }

    @Override
    public void updateCar(Car car) {
        try {
            carDao.updateCar(car);
        } catch (SQLException exception) {
            // log
        }
    }

    @Override
    public void deleteCar(Integer carId) {
        try {
            carDao.deleteCar(carId);
        } catch (SQLException exception) {
            // log
        }
    }

}
