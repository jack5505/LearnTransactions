package com.ofb.services;

import com.ofb.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@Service
// @Transactional(propagation = Propagation.REQUIRED) // Transactional can put over class then it means it relating to all methods of class
public class ProductService {

    @Autowired
    private ProductRepository repository;


    // Transactional rollback for only for runtimeExceptions in runtimeExceptions
    // This fundamental rule for transactional
    // @Transactional // rolls back for runtime exception but does not rollback for checked exceptions
   // @Transactional(rollbackFor = Exception.class) // this will rollback for checked exception
    @Transactional
    public void addOneProduct() throws Exception {
            // when you put try catch into this exception it will never works because in aspect never can see
           // because of try catch use aspect never see inside of exception that's why it will save
            repository.addProduct("jack");

    }

    /*
     * REQUIRED (default)
     * REQUIRES_NEW
     * MANDATORY
     * NEVER
     * SUPPORTS
     * NOT_SUPPORTED
     * NESTED
     *
     * Required means
     * if methods dosn't recieve transaction it created transaction
     * If method has transaction than it uses existed transaction
     *
     *
     *
     * REQUIRES_NEW
     * even if the transaction you always create new transaction
     * no matter you have or havn't transaction you always create new transaction
     *
     *
     * MANDATORY
     * Mandatory dosn't create transaction it tells you should have transaction to use MANDATORY propogation
     * If you have not already transaction before calling it then it fails
     *
     * NEVER
     * it means that you can't call it with Transactional you should call this method without Transactional.
     * with propogation NEVER you can't use at all use TRANSACTIONAL with that propogation
     *
     *
     * SUPPORTS
     * Support means that this Transactional propogate SUPPORTS means if you call this with transactional this will execute
     * that transactional that you call it
     * If you call it without Transactional this will execute without any Transactional
     *
     *
     * NOT_SUPPORTED
     * this avoid using Transactional
     * you might use this with TRANSACTIONAL but it won't use that existed transactional it will execute without transactional
     *
     *
     */

    // @Transactional(propagation = Propagation.REQUIRED) by default
    // @Transactional(propagation = Propagation.MANDATORY) // this @Transactional that define over method means that I override @Transactaional that put over class
    @Transactional
    public void addProducts(){ // created transaction
        IntStream.range(1,10).forEach(i ->{
            repository.addProduct(i,"jack" + i);
           // if(i == 5) throw new RuntimeException(":)");
        });
        // commite here
    }

    /*
    * DEFAULT -> read commited isoluation by default it will be
    *
    * when it will execute concurrent that time isoluation plays good role to isolate it from each other.
    * READ_UNCOMMITED
    * READ_COMMITED
    * REPEATABLE_READ
    * SERIALIZABLE
    *
    * PROBLEMS
    *  - dirty_reads
    *  - repeatable reads
    *  - phantom reads
    *
    *
    * This example in READ_UNCOMMITED
    * For instance we have two transactions below. The first transaction read from table value 10
    * and the second transaction change it value to 20 and first transaction read that value from that table
    * and get changed value 20 and somehow the second transaction is roll back and first transaction is still have
    * 20 value that changed the second transaction and this is not good!. This is calling is dirty read.
    *T1 -----10----------20--------------->
    *
    *
    *T2 -----------20---------------R--->  10 ???
    *
    *
    */

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void addProductsIsolation(){
        IntStream.range(1,10).forEach(i ->{
            repository.addProduct(i,"jack" + i);
            // if(i == 5) throw new RuntimeException(":)");
        });
    }
}
