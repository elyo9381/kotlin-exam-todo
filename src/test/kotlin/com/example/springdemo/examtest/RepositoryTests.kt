//package com.example.springdemo.examtest
//
//import com.example.springdemo.blog.Article
//import com.example.springdemo.blog.ArticleRepository
//import com.example.springdemo.blog.UserRepository
//import com.example.springdemo.blog.User
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
//
//
//@DataJpaTest
//class RepositoryTests(
//    @Autowired val entityManager: TestEntityManager,
//    @Autowired val userRepository: UserRepository,
//    @Autowired val articleRepository: ArticleRepository
//) {
//
//
//    @Test
//    internal fun `When findById then return User`() {
//        val juergen = User("springjuergen", "Juergen", "Hoeller")
//        entityManager.persist(juergen)
//        entityManager.flush()
//        val found = userRepository.findById(juergen.login)
//        assertThat(found.get()).isEqualTo(juergen)
//    }
//
//    @Test
//    internal fun `When findById then return Article`() {
//        val user = User("testUser", "won", "Hoeller")
//        entityManager.persist(user)
//        val article = Article( 1,"Dear Spring community ...", "Lorem ipsum", "testcontent",user)
//        articleRepository.save(article)
//        val found = articleRepository.findById(article.id!!)
//        println(found.get().id)
//        assertThat(found.get()).isEqualTo(article)
//    }
//
//}