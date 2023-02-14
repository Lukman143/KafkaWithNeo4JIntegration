
package com.sk.repository;

import org.springframework.stereotype.Repository;
import com.sk.model.CustomerNeo4JEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

@Repository
public interface CustomerRepository extends Neo4jRepository<CustomerNeo4JEntity, Long> {

}
