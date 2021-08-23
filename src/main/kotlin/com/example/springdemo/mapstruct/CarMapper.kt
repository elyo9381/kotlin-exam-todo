package com.example.springdemo.mapstruct

import com.example.springdemo.dto.CarDto
import com.example.springdemo.entity.Car
import org.mapstruct.*
import org.springframework.stereotype.Component


@Component@Mapper(
    componentModel = "spring"
)
interface CarMapper {

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
    fun toEntity(carDto: CarDto): Car


    companion object {
        @AfterMapping
        fun setDescription(@MappingTarget car: Car) {
            car.description = "${car.modelName} ${car.modelColor} !!! $${car.modelPrice}"
        }
    }


}
