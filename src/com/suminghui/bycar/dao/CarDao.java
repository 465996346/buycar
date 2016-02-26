package com.suminghui.bycar.dao;

import java.sql.SQLException;
import java.util.List;

import com.suminghui.bycar.bean.Car;
import com.suminghui.bycar.bean.Pagination;

public interface CarDao {

    public List<Car> selectCarList(Pagination pagination) throws SQLException;

    public Car selectCarById(Integer carId) throws SQLException;

    public void insertCar(Car car) throws SQLException;

    public void updateCar(Car car) throws SQLException;

    public void deleteCar(Integer carId) throws SQLException;
    
    public int getTotalCount() throws SQLException;
}
