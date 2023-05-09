package com.courseproject.circuitelectricalcalculation.repository

import com.courseproject.circuitelectricalcalculation.model.Calculation
import org.bson.types.ObjectId

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query


interface CalculationRepository : MongoRepository<Calculation?, ObjectId?> {
  /*  @Query("{name:'?0'}")
    fun findItemByName(name: String?): Calculation?

    @Query(value = "{category:'?0'}", fields = "{'name' : 1, 'quantity' : 1}")
    fun findAll(category: String?): List<Calculation?>?
    override fun count(): Long*/
}