//package com.example.springdemo.examtest
//
//import com.example.springdemo.examRest.repository.CustomerRepository
//import org.assertj.core.api.Assertions.*
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//
//@DataJpaTest
//internal class CustomerTest(
//    @Autowired val customerRepository: CustomerRepository,
//){
//
//    @Test
//    internal fun `testCustomer`() {
//        val testUser = Customer(1,"won","1234","a123123-z")
//        customerRepository.save(testUser)
//        val found = customerRepository.findById(testUser.id!!)
//        assertThat(found.get().id).isEqualTo(testUser.id)
//    }
//
//
//}