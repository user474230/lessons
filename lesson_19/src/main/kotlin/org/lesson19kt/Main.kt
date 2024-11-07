package org.example.org.lesson19kt

import jakarta.persistence.LockModeType
import org.hibernate.cfg.Configuration
import org.lesson19kt.Lot
import org.lesson19kt.User
import org.lesson19kt.createTables
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit.SECONDS
import kotlin.random.Random
import kotlin.time.measureTime

//OPTIMISTIC - 3.562392600s
//PESSIMISTIC_WRITE - 2.018035200s

fun main() {
    createTables()
    val timeOptimistic = startTest(LockModeType.OPTIMISTIC)
    val timePessimistic = startTest(LockModeType.PESSIMISTIC_WRITE)

    println("OPTIMISTIC - $timeOptimistic")
    println("PESSIMISTIC_WRITE - $timePessimistic")
}

fun startTest(lockMode: LockModeType) = measureTime {
    val executors = Executors.newFixedThreadPool(8)
    for (i in 0L until 8L) {
        executors.execute {
            Configuration().configure("hibernate2.cfg.xml").buildSessionFactory().use { factory ->
                factory.currentSession.use { session ->
                    session.transaction.begin()
                    val user = session.find(User::class.java, i)
                    session.transaction.commit()

                    for (j in 0 until 1000) {
                        val lotInd = Random.nextInt(0, 4)
                        var commited = false
                        while (!commited) {
                            session.transaction.begin()
                            val lot = session.createQuery("SELECT l FROM Lot l WHERE l.id = :id", Lot::class.java)
                                .setParameter("id", lotInd)
                                .setLockMode(lockMode)
                                .singleResult

                            lot.price = 100 + (lot.price ?: 0)
                            lot.user = user
                            runCatching {
                                session.transaction.commit()
                            }.onFailure {
                                session.transaction.rollback()
                            }.onSuccess {
                                commited = true
                            }
                        }
                    }
                }
            }
        }
    }
    executors.shutdown()
    executors.awaitTermination(100, SECONDS)
}