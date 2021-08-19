package com.example.springdemo.dto

import com.example.springdemo.entity.Car
import com.example.springdemo.mapstruct.CarMapper
import com.example.springdemo.mapstruct.UserTodoMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

@ExtendWith
internal class UserTodoEntityDTOTest {

    private val log = LoggerFactory.getLogger(UserTodoEntityDTOTest::class.java)
    val userTodoMapper = Mappers.getMapper(UserTodoMapper::class.java)
    val carMapper = Mappers.getMapper(CarMapper::class.java)

    val todoBaseTest = TodoDto().apply {
        this.id = 1
        this.title = "title"
        this.description = "~~~blabla"
        this.schedule = LocalDateTime.now()
        this.createdAt = LocalDateTime.now()
        this.updatedAt = null
    }

    val userBaseTest = UserDto().apply {
        this.id = 1
        this.name = "wonyoel"
        this.todos = mutableListOf()
        this.createdAt = LocalDateTime.now()
        this.updatedAt = LocalDateTime.now()
    }
    val userTodoBaseTest = userTodoMapper.userTodoEntityDTO(todoBaseTest, userBaseTest)

    @Test
    @DisplayName("여러dto를 하나의 새로운 dto 매핑하는 mapstruct")
    fun `test mapstruct allInOneDto`() {
        /* given */
        val createdTodo = TodoDto().apply {
            this.id = 1
            this.title = "title"
            this.description = "~~~blabla"
            this.schedule = LocalDateTime.now()
            this.createdAt = LocalDateTime.now()
            this.updatedAt = null
        }

        val createUser = UserDto().apply {
            this.id = 1
            this.name = "wonyoel"
            this.todos = mutableListOf()
            this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now()
        }


        /**
         * mapstruct에서
         * fun userTodoEntityDTO(todo: TodoEntity, user: UserEntity): UserTodoEntityDTO
         * 파라미터를 여러개 사용하여 하나의 객체(DTO)로 합칠수있다.
         * 이때 주의할점은 source,target의 매칭이다.
         * 저는 여기서 entity를 바로 접근하였지만 dto로 해볼것!
         */
        /* when */
        val createdUserTodo = userTodoMapper.userTodoEntityDTO(createdTodo, createUser)

        /* then */
        log.info("createdUserTodo : {}", createdUserTodo)
        assertThat(createdUserTodo).isNotNull
        assertEquals(createdTodo.title, createdUserTodo.title)
    }

    @Test
    @DisplayName("기존의 객체에 추가적인 데이터 매핑")
    fun `test mapstruct `() {
        /* given */
        val createdTodo = TodoDto().apply {
            this.id = 1
            this.title = "new title "
            this.description = "장난 완전 하냐"
            this.schedule = LocalDateTime.now()
            this.createdAt = LocalDateTime.now()
            this.updatedAt = null
        }

        val createUser = UserDto().apply {
            this.id = 1
            this.name = "elyowon"
            this.todos = mutableListOf()
            this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now()
        }

        /* when */
        val userTodoEntityDTO = userTodoBaseTest
        userTodoMapper.writeUserTodoEntityDTO(createdTodo, createUser, userTodoEntityDTO)

        /* then */
        log.info("userTodoBaseTest : {}", userTodoBaseTest)

        log.info("userTodoEntityDTO : {}", userTodoEntityDTO)
        assertThat(userTodoEntityDTO).isNotNull
        assertEquals(createdTodo.title, userTodoEntityDTO.title)
    }


    /**
     * Int 형을 받아서 string으로 변환가능합니다.
     * numberFormat = "$#.00"
     * carDto의 price 값이  entity로 변화하면서  "$1000.00" string 타입으로 변환됨
     *
     * 그리고 필드의 null일때의 널처리를 할수있다. ignore 설정
     * 널정책은
     * @Mapping(
        source = "description",
        target = "description",
        ignore = true,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT
        )

     널 전략을 SET_TO_DEFAULT 지정하면 list일때는 list, 문자열을 빈문자열, 오브젝트는 기본셋팅 설정값으로 지정한다.
     */
    @Test
    @DisplayName("필드 타입변환 테스트 ")
    fun `test mapstruct 3`() {
        /* given */
        val carDto = CarDto().apply {
            this.name = "a-model"
            this.color = "red"
            this.price = 1000
        }
        /* when */
        val carEntity = carMapper.toEntity(carDto)
        log.info("carDto : {}", carEntity)

        assertThat(carEntity).isNotNull
        assertEquals("$1000.00", carEntity.modelPrice)
    }

    @Test
    @DisplayName("before, after mapping test ")
    fun `test mapstruct 4`() {
        /* given */

        var testCar = Car().apply {
            this.modelName = "bmw x4"
            this.modelColor = "nothing"
            this.modelPrice = "1"
            this.description = "nothing"
        }

        var carDto = CarDto().apply {
            this.name = "bmw x1"
            this.color = "red"
            this.price = 1000
        }
        carMapper.setColor(carDto,testCar)
        log.info("testCar : {}", testCar)

        /* when */
        val carEntity = carMapper.toEntity(carDto)
        log.info("carDto : {}", carEntity)

        carMapper.setDescription(testCar)
        log.info("testCar : {}", testCar)
//
//        assertThat(carEntity).isNotNull
//        assertEquals("$1000.00", carEntity.modelPrice)
    }



}