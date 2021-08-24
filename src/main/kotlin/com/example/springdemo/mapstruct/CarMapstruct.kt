package com.example.springdemo.mapstruct

import com.example.springdemo.dto.CarDTO
import com.example.springdemo.entity.CarEntity
import org.mapstruct.*
import org.springframework.stereotype.Component


@Component@Mapper(
    componentModel = "spring"
)
interface CarMapstruct {

    @BeforeMapping
    fun setColor(carDto: CarDTO, @MappingTarget carEntity: CarEntity) : Unit{
        if (carDto.name.equals("bmw x4")) {
            carEntity.modelColor = "red"
        } else {
            carEntity.modelColor = "black"
        }
    }

    @Mappings(
        Mapping(source = "name", target = "modelName"),
        Mapping (source = "color", target = "modelColor"),
        Mapping(source = "price", target = "modelPrice", numberFormat = "$#.00"),
        Mapping( target = "description", ignore = true)
    )
    fun toEntity(carDto: CarDTO): CarEntity


    companion object {
        @AfterMapping
        fun setDescription(@MappingTarget carEntity: CarEntity) {
            carEntity.description = "${carEntity.modelName} ${carEntity.modelColor} !!! $${carEntity.modelPrice}"
        }
    }


}
