package com.example.springdemo.mapstruct

import com.example.springdemo.dto.CarDto
import com.example.springdemo.dto.TodoDto
import com.example.springdemo.entity.Car
import com.example.springdemo.entity.TodoEntity
import org.mapstruct.*
import org.springframework.stereotype.Component


@Component@Mapper(
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
    componentModel = "spring"
)
abstract class CarMapper {

    @BeforeMapping
    fun setColor(carDto: CarDto, @MappingTarget car: Car) : Unit{
        if (carDto.name.equals("bmw x4")) {
            car.modelColor = "red"
        } else {
            car.modelColor = "black"
        }
    }

    @Mappings(
        Mapping(source = "name", target = "modelName"),
        Mapping (source = "color", target = "modelColor"),
        Mapping(source = "price", target = "modelPrice", numberFormat = "$#.00"),
        Mapping( target = "description", ignore = true)
    )
    abstract fun toEntity(carDto: CarDto): Car


    @AfterMapping
    fun setDescription(@MappingTarget car: Car) : Unit{
        car.description = "${car.modelName} ${car.modelColor} !!! $${car.modelPrice}";
    }


}
